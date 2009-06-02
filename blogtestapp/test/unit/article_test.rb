require 'test_helper'

class ArticleTest < ActiveSupport::TestCase
  # Replace this with your real tests.
  test "should have comments" do
    a = Article.create!(:title => "hello world")
    c = Comment.create!(:body => "hello article")
    a.comments << c
    assert_equal a.comments.size, 1
  end
end
