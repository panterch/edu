(ns imdb.util
  (:import (java.io File InputStreamReader BufferedReader FileInputStream)
           (java.util.zip GZIPInputStream))
  (:use [clojure.contrib.seq-utils :only [partition-all]])
  (:gen-class))

(defn zip-reader
  "create a streamed reader for a zipped file"
  [file-name]
  (->> (File. file-name)
       FileInputStream.
       GZIPInputStream.
       InputStreamReader.
       BufferedReader.))

(def bundles-of-50
     (partial partition-all 50))