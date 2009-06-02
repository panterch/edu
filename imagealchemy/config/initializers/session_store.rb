# Be sure to restart your server when you modify this file.

# Your secret key for verifying cookie session data integrity.
# If you change this key, all old sessions will become invalid!
# Make sure the secret is at least 30 characters and all random, 
# no regular words or you'll be exposed to dictionary attacks.
ActionController::Base.session = {
  :key         => '_imagealchemy_session',
  :secret      => '83aa62ac6679160036ad503df1ba418e7ec3f681601334296f44c2913134b9ee915ba923110aeb4318dff14621850c252618f642ee09135989a5bc3083a77ebe'
}

# Use the database for sessions instead of the cookie-based default,
# which shouldn't be used to store highly confidential information
# (create the session table with "rake db:sessions:create")
# ActionController::Base.session_store = :active_record_store
