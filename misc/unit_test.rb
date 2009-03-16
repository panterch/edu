require 'test/unit'

class UnitTest < Test::Unit::TestCase

  def test_will_fail
    assert_equal 1, 1
  end

  def test_demo
    assert true
  end

  def test_demo_math
    assert_equal 2, 1+1
    assert_nil nil
  end
end

require 'test/unit/ui/console/testrunner'
Test::Unit::UI::Console::TestRunner.run(UnitTest)

