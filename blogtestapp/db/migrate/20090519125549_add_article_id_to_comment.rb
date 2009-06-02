class AddArticleIdToComment < ActiveRecord::Migration
  def self.up
    add_column :comments, :article_id, :integer
  end

  def self.down
    remove_column :comments, :article_id
  end
end
