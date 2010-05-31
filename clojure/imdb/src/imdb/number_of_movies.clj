(ns imdb.number-of-movies
  (:use [clojure.contrib.duck-streams :only [spit]]
        [clojure.contrib.str-utils :only [str-join]]
        imdb.util)
  (:gen-class))

(defn number-of-movies [input-file output-file]
  (spit output-file "not yet implemented"))

