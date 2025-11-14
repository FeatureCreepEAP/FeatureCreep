args = ARGV
fpmbuild_location = args[0]

# Evita el warning del '+' usando File.join
require File.join(fpmbuild_location, '.fpmbuild', 'spec_parser')

if args[1] == '-ba'
  parser = SpecParser.new(args[2], fpmbuild_location)
  puts parser.get_spec_location.to_s
  parser.build_fpm
else
  warn "[fpmbuild] Advertencia: falta el argumento '-ba'. No se ejecutará la build. " \
       "Uso esperado: fpmbuildmain.rb <fpmbuild_location> -ba <spec_file>"
end

