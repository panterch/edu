#!/usr/bin/env ruby
require '../example'

example %q{
/old/
/Russ|Russell/
/.*/ 
}

example_expected 5,   %q{/old/ =~ 'this old house'  # 5 - the index of 'old'}
example_expected nil, %q{/Russ|Russell/ =~ 'Fred'   # nil – Fred is not Russ nor Russell}
example_expected 0,   %q{/.*/ =~ 'any old string'   # 0 - the RE will match anything}

