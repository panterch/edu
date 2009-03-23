# build map of strategies
strategies = {
  :noop =>   lambda {},
  :stdout => lambda { |msg| puts msg },
  :sterr =>  lambda { |msg| $stderr.puts msg },
  :time =>   lambda { |msg| puts "#{Time.now} #{msg}" }
  }

# demo call
strategies[:time].call "test"

