require 'fileutils'

class SpecParser

  $Spec
  $Location
  $remap
  $mappings
  $remap_depndencies
  $actual_location #para cd

  def initialize(spec, location)
    $Spec = spec
    $Location = location
    $actual_location = location
  end

  def get_spec_location
    $Spec
  end

  def get_fpmbuild_location
    $Location
  end

  def get_sources_location
    File.join($Location, "SOURCES")
  end

  def get_fpm_dir
    File.join($Location, "FPMS")
  end

  def get_sfpm_dir
    File.join($Location, "SFPMS")
  end

  def find_description_part_to_int(array)
    line_num = 0
    description_line = 0
    array.each do |line|
      line_num += 1
      description_line = line_num if line.strip == "%description"
    end
    description_line
  end

  def find_prep_part_to_int(array)
    line_num = 0
    prep_line = 0
    array.each do |line|
      line_num += 1
      prep_line = line_num if line.strip == "%prep"
    end
    prep_line
  end

  def pre_validate(string)
    if string && string.strip != nil
      s = string.strip
      if s.include?('#')
        s.split('#')[0]
      else
        s
      end
    else
      nil
    end
  end

  def get_name
    IO.readlines($Spec).each do |line|
      return pre_validate(line.sub("Name:", "")) if line.include?("Name:")
    end
  end

  def get_version
    IO.readlines($Spec).each do |line|
      return pre_validate(line.sub("Version:", "")) if line.include?("Version:")
    end
  end

  def get_dist
    "fc5"
  end

  def get_release
    IO.readlines($Spec).each do |line|
      if line.include?("Release:")
        return pre_validate(line.sub("Release:", "").gsub("%{?dist}", get_dist))
      end
    end
  end

  def get_summary
    IO.readlines($Spec).each do |line|
      return pre_validate(line.sub("Summary:", "")) if line.include?("Summary:")
    end
  end

  def get_licence
    IO.readlines($Spec).each do |line|
      return pre_validate(line.sub("License:", "")) if line.include?("License:")
    end
  end

  def get_url
    IO.readlines($Spec).each do |line|
      return pre_validate(line.sub("URL:", "")) if line.include?("URL:")
    end
  end

  def get_source0
    IO.readlines($Spec).each do |line|
      return pre_validate(line.sub("Source0:", "")) if line.include?("Source0:")
    end
  end

  def get_build_requires
    IO.readlines($Spec).each do |line|
      return pre_validate(line.sub("BuildRequires:", "")) if line.include?("BuildRequires:")
    end
  end

  def get_requires
    IO.readlines($Spec).each do |line|
      return pre_validate(line.sub("Requires:", "")) if line.include?("Requires:")
    end
  end

  def get_main_class
    IO.readlines($Spec).each do |line|
      return pre_validate(line.sub("Main-Class:", "")) if line.include?("Main-Class:")
    end
  end

  def get_description
    line_first = find_build_part_to_int(IO.readlines($Spec))
    line_last  = find_install_part_to_int(IO.readlines($Spec)) - 1
    current    = line_first
    desc       = ""
    while current < line_last
      desc << pre_validate(IO.readlines($Spec)[current]).to_s
      current += 1
    end
    desc
  end

  # https://alvinalexander.com/blog/post/ruby/ruby-method-read-in-entire-file-as-string/
  def get_file_as_string(filename)
    data = ''
    File.open(filename, "r") { |f| f.each_line { |line| data << line } }
    data
  end

  def get_build_root
    File.join($Location, "BUILD_ROOT")
  end

  def get_build_location
    File.join($Location, "BUILD")
  end

  def validate(string)
    str = pre_validate(string).to_s
    str = str.gsub("%{?dist}",        get_dist)        if str.include?("%{?dist}")
    str = str.gsub("%{?name}",        get_name)        if str.include?("%{?name}")
    str = str.gsub("%{?version}",     get_version)     if str.include?("%{?version}")
    str = str.gsub("%{?release}",     get_release)     if str.include?("%{?release}")
    str = str.gsub("%{?summary}",     get_summary)     if str.include?("%{?summary}")
    str = str.gsub("%{?license}",     get_licence)     if str.include?("%{?license}")
    str = str.gsub("%{?url}",         get_url)         if str.include?("%{?url}")
    str = str.gsub("%{?source0}",     get_source0)     if str.include?("%{?source0}")
    str = str.gsub("%{?build_requires}", get_build_requires) if str.include?("%{?build_requires}")
    str = str.gsub("%{?requires}",    get_requires)    if str.include?("%{?requires}")
    str = str.gsub("%{?description}", get_description) if str.include?("%{?description}")
    str = str.gsub("%{?fpmbuild_location}", get_fpmbuild_location) if str.include?("%{?fpmbuild_location}")
    str = str.gsub("%{?main-class}",  get_main_class)  if str.include?("%{?main-class}")
    str = str.gsub("%{?sources_location}", get_sources_location) if str.include?("%{?sources_location}")
    str = str.gsub("$FPM_BUILD_ROOT", get_build_root)  if str.include?("$FPM_BUILD_ROOT")
    str = str.gsub("%{?build_root}",  get_build_root)  if str.include?("%{?build_root}")
    str = str.gsub("%{?fpm_dir}",     get_fpm_dir)     if str.include?("%{?fpm_dir}")
    str = str.gsub("%{?sfpm_dir}",    get_sfpm_dir)    if str.include?("%{?sfpm_dir}")
    str
  end

  def find_build_part_to_int(array)
    line_num = 0
    build_line = 0
    array.each do |line|
      line_num += 1
      build_line = line_num if line.strip == "%build"
    end
    build_line
  end

  def find_install_part_to_int(array)
    line_num = 0
    install_line = 0
    array.each do |line|
      line_num += 1
      install_line = line_num if line.strip == "%install"
    end
    install_line
  end

  def execute(line)
    if line.include?("buildfpm_maven")
      args = line.split(" ")
      proyecto = args[1]
      puts proyecto

      classes_path = File.join(proyecto, "target", "classes")
      puts classes_path
      manifest     = File.join(classes_path, "META-INF", "MANIFEST.MF")

      if File.exist?(manifest)
        contenido = get_file_as_string(manifest)
        unless contenido.include?("Main-Class :") || contenido.include?("Main-Class:")
          manifest_text = File.read(manifest)
          manifest_text = manifest_text.each_line.reject { |x| x.strip == "" }.join
          File.open(manifest, 'w') { |file| file.write(manifest_text) }
          File.open(manifest, 'a') { |file| file.puts "Main-Class: #{get_main_class.strip}" unless get_main_class.nil? }
        end
      else
        # si no existe, créalo mínimo
        FileUtils.mkdir_p(File.join(classes_path, "META-INF"))
        File.open(manifest, 'w') do |f|
          f.puts "Manifest-Version: 1.0"
          f.puts "Main-Class: #{get_main_class.strip}" unless get_main_class.nil?
        end
      end

      files = Dir[File.join(classes_path, '*')].select

      FileUtils.mkdir_p(File.join(get_build_root, "mods"))
      FileUtils.mkdir_p(File.join(get_build_root, "packing"))

      FileUtils.cp(get_spec_location, classes_path)

      files.each { |file| FileUtils.cp_r(file, File.join(get_build_root, "packing")) rescue nil }

      libs_path = Dir[File.join(proyecto, "libs", '*')].select
      libs_path.each { |lib| FileUtils.cp_r(lib.to_s, File.join(get_build_root, "mods")) rescue nil }

      temp_file = File.join(get_build_root, "mods", "#{get_name}-#{get_version}-#{get_release}.noarch.zip")
      command   = %(jar -cfvM "#{temp_file}" -C "#{classes_path}" .)
      puts command
      system(command)

      FileUtils.cp(temp_file, get_build_location)

      fpm  = File.join(get_fpm_dir,  "#{get_name}-#{get_version}-#{get_release}.noarch.fpm")
      sfpm = File.join(get_sfpm_dir, "#{get_name}-#{get_version}-#{get_release}.noarch.sfpm")

      system(%(jar -cfvM "#{fpm}" -C "#{File.join(get_build_root, "packing")}" .))

      Dir[File.join(get_build_root, '*')].each { |brf| FileUtils.rm_rf(brf) rescue nil }

      # copy spec to buildroot y proyecto completo
      FileUtils.cp(get_spec_location, get_build_root)
      FileUtils.cp_r(proyecto, get_build_root)

      system(%(jar -cfvM "#{sfpm}" -C "#{get_build_root}" .))

      Dir[File.join(get_build_root, '*')].each { |brf| FileUtils.rm_rf(brf) rescue nil }

    elsif line.include?("buildfpm_gradle")
      # Uso: buildfpm_gradle <path_del_proyecto_gradle>
      args = line.split(" ")
      proyecto = args[1]
      puts proyecto

      classes_path   = File.join(proyecto, "build", "classes", "java", "main")
      resources_path = File.join(proyecto, "build", "resources", "main")
      libs_dir       = File.join(proyecto, "build", "libs")
      puts classes_path

      meta_inf = File.join(classes_path, "META-INF")
      manifest = File.join(meta_inf, "MANIFEST.MF")
      FileUtils.mkdir_p(meta_inf)

      if File.exist?(manifest)
        contenido = get_file_as_string(manifest)
        unless contenido.include?("Main-Class:")
          manifest_text = File.read(manifest)
          manifest_text = manifest_text.each_line.reject { |x| x.strip == "" }.join
          File.open(manifest, 'w') { |f| f.write(manifest_text) }
          File.open(manifest, 'a') { |f| f.puts "Main-Class: #{get_main_class.strip}" unless get_main_class.nil? }
        end
      else
        File.open(manifest, 'w') do |f|
          f.puts "Manifest-Version: 1.0"
          f.puts "Main-Class: #{get_main_class.strip}" unless get_main_class.nil?
        end
      end

      if Dir.exist?(resources_path)
        Dir[File.join(resources_path, '**', '*')].each do |rf|
          next unless File.file?(rf)
          rel = rf.sub(%r{\A#{Regexp.escape(resources_path)}/}, '')
          dst = File.join(classes_path, rel)
          FileUtils.mkdir_p(File.dirname(dst))
          FileUtils.cp(rf, dst)
        end
      end

      files = Dir[File.join(classes_path, '*')].select

      FileUtils.mkdir_p(File.join(get_build_root, "mods"))
      FileUtils.mkdir_p(File.join(get_build_root, "packing"))

      FileUtils.cp(get_spec_location, classes_path) rescue nil

      files.each { |file| FileUtils.cp_r(file, File.join(get_build_root, "packing")) rescue nil }

      if Dir.exist?(libs_dir)
        Dir[File.join(libs_dir, '*')].each { |lib| FileUtils.cp_r(lib.to_s, File.join(get_build_root, "mods")) rescue nil }
      end

      temp_file = File.join(get_build_root, "mods", "#{get_name}-#{get_version}-#{get_release}.noarch.zip")
      command   = %(jar -cfvM "#{temp_file}" -C "#{classes_path}" .)
      puts command
      system(command)

      FileUtils.cp(temp_file, get_build_location) rescue nil

      fpm  = File.join(get_fpm_dir,  "#{get_name}-#{get_version}-#{get_release}.noarch.fpm")
      sfpm = File.join(get_sfpm_dir, "#{get_name}-#{get_version}-#{get_release}.noarch.sfpm")

      system(%(jar -cfvM "#{fpm}" -C "#{File.join(get_build_root, "packing")}" .))

      Dir[File.join(get_build_root, '*')].each { |brf| FileUtils.rm_rf(brf) rescue nil }

      FileUtils.cp(get_spec_location, get_build_root) rescue nil
      FileUtils.cp_r(proyecto, get_build_root) rescue nil

      system(%(jar -cfvM "#{sfpm}" -C "#{get_build_root}" .))

      Dir[File.join(get_build_root, '*')].each { |brf| FileUtils.rm_rf(brf) rescue nil }

    elsif line.include?("remap")
      valid = validate(line)
      args  = valid.split("(")[1].split(")")[0].split(",") # get the args

      $remap = true
      $mappings = args[0]
      $remap_depndencies = args[1]
      puts "remapping"
      cmd = %(java -jar ./.fpmbuild/fpmbuild-java.jar -remap "#{File.join(get_fpm_dir, "#{get_name}-#{get_version}-#{get_release}.noarch.fpm")}" #{$mappings} #{$remap_depndencies})
      puts cmd
      system(cmd)

    elsif line.start_with?("cd")
      valid = validate(line)
      if valid == "cd /dev/null"
        $actual_location = $Location
      else
        $actual_location = valid[3..-1]
      end

    else
      valid = validate(line)
      unless valid.empty?
        system(%(cd "#{$actual_location}" && #{valid})) # execute the command
      end
    end
  end

  def build_fpm
    $actual_location = $Location
    spec_array = IO.readlines($Spec)

    Dir[File.join(get_build_root, '*')].each { |brf| FileUtils.rm_rf(brf) rescue nil }

    line_first = find_build_part_to_int(spec_array)
    line_last  = find_install_part_to_int(spec_array) - 1
    current    = line_first
    while current < line_last
      execute(validate(spec_array[current]))
      current += 1
    end
  end

end

