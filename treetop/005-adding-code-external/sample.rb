require 'rubygems'
require 'treetop'
# need to require 'nodes.rb' which contains the custom node classes
require 'nodes'
require 'grammar'


parser = ArithmeticParser.new
input = "123 * 233"
tree = parser.parse(input)

if tree
  puts "Treetop parsed '#{input}' to #{tree}"
  puts tree.inspect
  puts "The result of #{input} is #{tree.evaluate}"
else
  puts "Treetop could not parse '#{input}'"
end


