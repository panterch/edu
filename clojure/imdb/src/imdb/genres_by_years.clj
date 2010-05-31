(ns imdb.genres-by-years
  (:use [clojure.contrib.duck-streams :only [spit]]
        [clojure.contrib.str-utils :only [str-join]]
        imdb.util)
  (:gen-class))

(defn genres-by-years [input-file output-file]
  (spit output-file "not yet implemented"))

