#!/usr/bin/env ruby

require '../example'

example %q{

divisor=0

# One way to say it

if (divisor == 0) 	
  puts 'Division by zero'
end

# And another

puts 'Division by zero' if (divisor == 0)

# And a third

(divisor == 0) && puts('Division by zero')

}

example %q{

puts('A fine way to call puts')
puts 'Another fine way to call puts'
}

example %q{

def method_with_3_args a, b, c
  puts "Method 1 called with #{a} #{b} #{c}"
  if a == 0
    puts 'a is zero'
  end
end


method_with_3_args 0, 1, 2

}

example %q{
file = File.open('empty_file')

if (file.eof?())
  puts('Reached end of file')
end

if file.eof?
  puts 'Reached end of file' 
end

puts 'Reached end of file' if file.eof?


file.eof? && puts('Reached end of file')
}
