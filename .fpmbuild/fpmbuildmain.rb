args = ARGV
fpmbuild_location = args[0]
require fpmbuild_location +'/.fpmbuild/spec_parser'

if args[1] == "-ba"
  parser = SpecParser.new(args[2], fpmbuild_location)
  puts parser.get_spec_location.to_s
  parser.build_fpm

end
