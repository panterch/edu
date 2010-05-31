(ns imdb.movies-by-years
  (:use [clojure.contrib.duck-streams :only [spit]]
        [clojure.contrib.str-utils :only [str-join]]
        [clojure.contrib.seq-utils :only [partition-all]]
        imdb.util)
  (:gen-class))

(def movie-re #"([^\t]+)\t+([^\t]+)")

(defn parse-movie [line]
  (rest (re-find movie-re line)))

(defn only-movies-with-proper-year [parsed-movie]
  (let [year (second parsed-movie)]
    (and (not (nil? year))
         (re-matches #"\d+" year))))

(defn count-years [lines]
  (->> (map parse-movie lines)
       (filter only-movies-with-proper-year)
       (map #(hash-map (second %) 1))
       (apply merge-with +)))

(defn movies-by-years-analysis [input-file]
  (->> (zip-reader input-file) ;; this is defined in util
       (line-seq)
       (bundles-of-50) ;; this is also defined in util
       (pmap count-years)
       (apply merge-with +)))

(defn movies-by-years [input-file output-file]
  (->> (sort (movies-by-years-analysis input-file))
       (map #(str-join \, %))
       (str-join "\n")
       (spit output-file)))

