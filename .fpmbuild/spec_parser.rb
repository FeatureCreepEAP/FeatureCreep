require 'fileutils'

class SpecParser

  $Spec
  $Location
  $remap
  $mappings
  $remap_depndencies

  def initialize(spec, location)
    $Spec = spec
    $Location = location
  end

  def get_spec_location
    return $Spec
  end

  def get_fpmbuild_location
    return $Location
  end

  def get_sources_location
    return $Location+"/SOURCES"
  end

  def get_fpm_dir
    return $Location+"/FPMS"
  end

  def get_sfpm_dir
    return $Location+"/SFPMS"
  end

  def find_description_part_to_int(array)
    line_num = 0
    description_line =0
    array.each do |line|
      line_num = line_num + 1
      if line.strip == "%description"
        description_line = line_num
      end
    end
    return description_line
  end


  def find_prep_part_to_int(array)
    line_num = 0
    prep_line =0
    array.each do |line|
      line_num = line_num + 1
      if line.strip == "%prep"
        prep_line = line_num
      end
    end
    return prep_line
  end



  def pre_validate (string)
    if string.strip != nil


      if  string.strip.include?('#')
        string_array = string.strip.split('#')
        return string_array[0]
      else
        return string.strip
      end




    else
      return nil

    end
    end


  def get_name
    spec_array = IO.readlines($Spec)
    spec_array.each do |line|
      if line .include?("Name:")
        return pre_validate(line.gsub("Name:", ""))
      end
    end

  end

  def get_version
    spec_array = IO.readlines($Spec)
    spec_array.each do |line|
      if line .include?("Version:")
        return pre_validate(line.gsub("Version:", ""))
      end
    end

  end


  def get_dist
    return "fc4"
  end

  def get_release
    spec_array = IO.readlines($Spec)
    spec_array.each do |line|
      if line .include?("Release:")
        return pre_validate(line.gsub("Release:", "").gsub("%{?dist}", get_dist))
      end
    end

  end


  def get_summary
    spec_array = IO.readlines($Spec)
    spec_array.each do |line|
      if line .include?("Summary:")
        return pre_validate(line.gsub("Summary:", ""))
      end
    end

  end


  def get_licence
    spec_array = IO.readlines($Spec)
    spec_array.each do |line|
      if line .include?("License:")
        return pre_validate(line.gsub("License:", ""))
      end
    end

  end


  def get_url
    spec_array = IO.readlines($Spec)
    spec_array.each do |line|
      if line .include?("URL:")
        return pre_validate(line.gsub("URL:", ""))
      end
    end

  end

  def get_source0
    spec_array = IO.readlines($Spec)
    spec_array.each do |line|
      if line .include?("Source0:")
        return pre_validate(line.gsub("Source0:", ""))
      end
    end

  end

  def get_build_requires
    spec_array = IO.readlines($Spec)
    spec_array.each do |line|
      if line .include?("BuildRequires:")
        return pre_validate(line.gsub("BuildRequires:", ""))
      end
    end

  end

  def get_requires
    spec_array = IO.readlines($Spec)
    spec_array.each do |line|
      if line .include?("Requires:")
        return pre_validate(line.gsub("Requires:", ""))
      end
    end

  end

  def get_main_class
    spec_array = IO.readlines($Spec)
    spec_array.each do |line|
      if line .include?("Main-Class:")
        return pre_validate(line.gsub("Main-Class:", ""))
      end
    end

  end


  def get_description


    line_first = find_build_part_to_int(IO.readlines($Spec))
    line_last = find_install_part_to_int(IO.readlines($Spec)) -1
    current_line = line_first
    description = ""
    while (current_line < line_last)
      description = description + pre_validate(IO.readlines($Spec)[current_line])
      current_line + 1
    end

  end

  #https://alvinalexander.com/blog/post/ruby/ruby-method-read-in-entire-file-as-string/ Copying and pasting code is faster than writing it
  def get_file_as_string(filename)
    data = ''
    f = File.open(filename, "r")
    f.each_line do |line|
      data += line
    end
    return data
  end



  def get_build_root
    return $Location+"/BUILD_ROOT"
  end

  def get_build_location
    return $Location+"/BUILD"
  end

  def validate (string)
    str =    pre_validate(string)
    if str.include?("%{?dist}")
      str = str.gsub("%{?dist}", get_dist)
    end
    if str.include?("%{?name}")
      str =  str.gsub("%{?name}", get_name)
    end
    if str.include?("%{?version}")
      str =  str.gsub("%{?version}", get_version)
    end
    if str.include?("%{?release}")
      str =   str.gsub("%{?release}", get_release)
        end
    if str.include?("%{?summary}")
      str =  str.gsub("%{?summary}", get_summary)
    end
    if str.include?("%{?license}")
      str =  str.gsub("%{?license}", get_licence)
    end
    if str.include?("%{?url}")
      str =  str.gsub("%{?url}", get_url)
    end
    if str.include?("%{?source0}")
      str = str.gsub("%{?source0}", get_source0)
    end
    if str.include?("%{?build_requires}")
      str =     str.gsub("%{?build_requires}", get_build_requires)
      end
      if str.include?("%{?requires}")
        str = str.gsub("%{?requires}", get_requires)
        end
    if str.include?("%{?description}")
      str = str.gsub("%{?description}", get_description)
    end
    if str.include?("%{?fpmbuild_location}")
      str =   str.gsub("%{?fpmbuild_location}", get_fpmbuild_location)
    end
    if str.include?("%{?main-class}")
      str =   str.gsub("%{?main-class}", get_main_class)
    end
    if str.include?("%{?sources_location}")
      str =   str.gsub("%{?sources_location}", get_sources_location)
    end
    if str.include?("$FPM_BUILD_ROOT")
      str =   str.gsub("$FPM_BUILD_ROOT", get_build_root)
    end
    if str.include?("%{?build_root}")
      str =   str.gsub("%{?build_root}", get_build_root)
    end
    if str.include?("%{?fpm_dir}")
      str =   str.gsub("%{?fpm_dir}", get_fpm_dir)
    end
    if str.include?("%{?sfpm_dir}")
      str =   str.gsub("%{?sfpm_dir}", get_sfpm_dir)
    end



    return  str



  end


  def find_build_part_to_int(array)
    line_num = 0
    build_line =0
    array.each do |line|
      line_num = line_num + 1
      if line.strip == "%build"
        build_line = line_num
      end
    end
    return build_line
  end

  def find_install_part_to_int(array)
    line_num = 0
    install_line =0
    array.each do |line|
      line_num = line_num + 1
      if line.strip == "%install"
        install_line = line_num
      end
    end
    return install_line
  end




def execute (line)
  if line.include?("buildfpm_maven")
    args = line.split(" ")
    puts args[1]
    path = args[1]+"/target/classes".to_s
    puts path

    manifest = path + "/META-INF/MANIFEST.MF"

    if get_file_as_string(manifest).include?("Main-Class :")


    elsif get_file_as_string(manifest).include?("Main-Class:")



    else

      manifest_text = File.read(manifest)
      # manifest_text = manifest_text.gsub /^$\n/, ''   https://stackoverflow.com/questions/7339292/ruby-remove-empty-lines-from-string
      manifest_text = manifest_text.each_line.reject{|x| x.strip == ""}.join
      File.open(manifest, 'w') do |file|
        file.write(manifest_text)
      end


           File.open(manifest, 'a') do |file|
             unless get_main_class.nil?
             file.puts "Main-Class: "+get_main_class.strip
             end
           end



end









    files = Dir[path + '/*'].select

    FileUtils.mkdir(get_build_root+"/mods/")
    FileUtils.mkdir(get_build_root+"/packing/")

    FileUtils.cp(get_spec_location, path)


    files.each do |file|
      begin
      FileUtils.cp_r(file,  get_build_root+"/packing/")
      end

    end


    libs_path = Dir[args[1]+"/libs" + '/*'].select
    libs_path.each do |lib|
      begin
           FileUtils.cp_r(lib.to_s,  get_build_root+"/mods/")
      end
      end


    #   FileUtils.copy(file, dst)
    temp_file = get_build_root + "/mods/" + get_name + "-" + get_version + "-"+ get_release+".noarch.zip"
    command = "jar -cfvM " + temp_file + " -C " + path + " ."
    puts command
    system(command)

    FileUtils.cp(temp_file, get_build_location)




    fpm = get_fpm_dir + "/" + get_name + "-" + get_version + "-"+ get_release+".noarch.fpm"
    sfpm = get_sfpm_dir + "/" + get_name + "-" + get_version + "-"+ get_release+".noarch.sfpm"

    system("jar -cfvM " + fpm + " -C " + get_build_root+"/packing/" + " .")








    build_root_files = Dir[get_build_root + '/*'].select
       build_root_files.each do |brf|
      begin
        FileUtils.rm_rf(brf)
      end
    end

    #copy spec to buildroot
    FileUtils.cp(get_spec_location, get_build_root)
    FileUtils.cp_r(args[1], get_build_root)



    system("jar -cfvM " + sfpm + " -C " + get_build_root + " .")

    build_root_files = Dir[get_build_root + '/*'].select
     build_root_files.each do |brf|
      begin
        FileUtils.rm_rf(brf)
      end
    end

  elsif(line.include?("remap"))
    #get arg0 and arg 1 from the parenthasis
    valid=validate(line)
    args = valid.split("(")[1].split(")")[0].split(",") #get the args

    $remap=true
    $mappings =args[0]
    $remap_depndencies = args[1]


  end


  end










  def build_fpm
    # code here
    spec_array = IO.readlines($Spec)

    line_first = find_build_part_to_int(spec_array)
    line_last = find_install_part_to_int(spec_array) -1
    current_line = line_first
    while (current_line < line_last)
      execute(validate(spec_array[current_line]))
      current_line = current_line + 1
    end

    if $remap
      puts "remapping"
      puts "java -jar ./.fpmbuild/fpmbuild-java.jar -remap " + get_fpm_dir.to_s + "/" + get_name.to_s + "-" + get_version.to_s + "-"+ get_release.to_s+".noarch.fpm " +$mappings.to_s + " " + $remap_depndencies.to_s
      system("java -jar ./.fpmbuild/fpmbuild-java.jar -remap " + get_fpm_dir.to_s + "/" + get_name.to_s + "-" + get_version.to_s + "-"+ get_release.to_s+".noarch.fpm " +$mappings.to_s + " " + $remap_depndencies.to_s)
    end


  end

  end



































