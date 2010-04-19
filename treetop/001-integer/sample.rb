require 'rubygems'
require 'treetop'
# treetop loads "polyglot" gem which allows to load *.treetop files with require
# without polyglot gem you have convert the treetop file to a rb file "manualy"
# on command line: tt grammar.treetop
# Using the command line generator is preferred for finding bugs

require 'grammar'

# Number Parser Class was automatically created when the treetop grammar was required
# The name of the Parser is <Grammar Name><Parser>: Number Grammer => NumberParser
parser = NumberParser.new
input = "123"
tree = parser.parse(input)

if tree
  puts "Treetop parsed '#{input}' to #{tree}"
  puts tree.inspect
else
  puts "Treetop could not parse '#{input}'"
end




# Notes:
# When you parse 123
# then you can find the structure of the grammar/rule reflected in the tree built by treetop
# 1. SyntaxNode+Integer0 offset=0, "123":
# 2. SyntaxNode offset=0, "1"
# 3. SyntaxNode offset=1, "23":
# 4.   SyntaxNode offset=1, "2"
# 5.   SyntaxNode offset=2, "3"
#
# 1. => Top Level Node
# 2. => Node for [1-9] part of rule
# 3. => Parent Node for [0-9]* part of rule

