class Picture < ActiveRecord::Base
  has_attached_file :photo, :styles => {:small => "150x150>", :medium => "300x300>"}
  acts_as_list
end
