class OperatorNode < Treetop::Runtime::SyntaxNode
  def evaluate(a, b)
    if self.text_value == '+'
      a.evaluate + b.evaluate
    elsif self.text_value == '-'
      a.evaluate - b.evaluate
    end
    # or with some ruby send magic
    a.evaluate.send(self.text_value, b.evaluate)
  end
end

class NumberNode < Treetop::Runtime::SyntaxNode
  def evaluate()
    self.text_value.to_i
  end
end


class CalculationNode < Treetop::Runtime::SyntaxNode
  def evaluate()
    operator.evaluate(first, second)
  end
end
