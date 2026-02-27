args = ARGV
fpmbuild_location = args[0]

require File.join(fpmbuild_location, '.fpmbuild', 'spec_parser')

def localised_warning
  # Get system language from environment variables
  lang = ENV['LANG'] || ENV['LC_CTYPE']

  # Language detection logic
  case lang&.downcase
  when /zh/ then # Chinese
    "[fpmbuild] 警告：缺少参数 '-ba'。将不会执行构建。预期用法：fpmbuildmain.rb <fpmbuild_location> -ba <spec_file>"
  when /en/ then # English
    "[fpmbuild] Warning: missing argument '-ba'. Build will not be executed. Expected usage: fpmbuildmain.rb <fpmbuild_location> -ba <spec_file>"
  else # Default to Spanish (original)
    "[fpmbuild] Advertencia: falta el argumento '-ba'. No se ejecutará la build. Uso esperado: fpmbuildmain.rb <fpmbuild_location> -ba <spec_file>"
  end
end

if args[1] == '-ba'
  parser = SpecParser.new(args[2], fpmbuild_location)
  puts parser.get_spec_location.to_s
  parser.build_fpm
else
  warn localised_warning
end
