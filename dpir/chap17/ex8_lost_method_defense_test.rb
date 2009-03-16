#!/usr/bin/env ruby
require 'test/unit'
require 'pp'

require 'ex4_base'
require 'ex3_members'
require 'ex8_lost_method_defense'

class RedefTestFixture < CompositeBase
  def parent_population
  end
end

class MethodRedefnitionTest < Test::Unit::TestCase

  def test_member_of works
    jungle1 = Jungle.new('southern jungle')
    jungle2 = Jungle.new('northern jungle')
    t = Tiger.new('tony')

    jungle1.add_sub_population(t)
    assert_equal jungle1, t.parent_population
    assert jungle1.sub_populations.include?(t)

    jungle1.delete_sub_population(t)
    assert ! jungle1.sub_populations.include?(t)
    assert_nil t.parent_population

    jungle2.add_sub_population(t)
    assert_equal jungle2, t.parent_population
  end

  def test_method_redef_check
     assert_raise(RuntimeError){ RedefTestFixture.member_of(:population) }
  end

end
