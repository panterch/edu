class AddPositionToPicture < ActiveRecord::Migration
  def self.up
    add_column :pictures, :position, :integer
    pos = 1
    Picture.find(:all).each do |picture|
      picture.position = pos
      picture.save
      pos = pos + 1
    end
  end

  def self.down
    remove_column :pictures, :position
  end
end
