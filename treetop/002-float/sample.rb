require 'rubygems'
require 'treetop'
require 'grammar'

parser = NumberParser.new
input = "1.1"
tree = parser.parse(input)

if tree
  puts "Treetop parsed '#{input}' to #{tree}"
  puts tree.inspect
else
  puts "Treetop could not parse '#{input}' #{parser.failure_reason}"
end