require 'rubygems'
require 'treetop'
require 'grammar'

parser = TrainParser.new
input = 'The train to Chur leaves from platform 4'
tree = parser.parse(input)

if tree
  puts "Treetop parsed '#{input}' to #{tree}"
  puts tree.inspect
else
  puts "Treetop could not parse '#{input}': #{parser.failure_reason}"
end


puts
puts
# Treetop does only match first rule found in grammar (root node of tree)
parser.parse('4')
puts "trying to parse something which should match rule 'number' => #{ parser.failure_reason}"
