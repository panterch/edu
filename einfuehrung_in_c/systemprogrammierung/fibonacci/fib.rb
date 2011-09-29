def fib(n)
  ( n < 2 ) ? n : fib(n-1) + fib(n-2)
end

puts fib(10)
