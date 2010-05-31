(ns imdb.movies-by-years
  (:use [clojure.contrib.duck-streams :only [spit]]
        [clojure.contrib.str-utils :only [str-join]]
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
       (count-years)))

(defn parallel-movies-by-years-analysis [input-file]
  (->> (zip-reader input-file) ;; this is defined in util
       (line-seq)
       (bundles-of-50) ;; this is also defined in util
       (pmap count-years)
       (apply merge-with +)))

(defn movies-by-years [input-file output-file]
  (->> (sort (parallel-movies-by-years-analysis input-file))
       (map #(str-join \, %))
       (str-join "\n")
       (spit output-file)))

(comment
  (time (movies-by-years "../res/movies.list.gz" "../res/movies-by-years.csv"))
  (movies-by-years-analysis "../res/movies.list.gz")

  (def ten-lines (take 10 (drop 500 (line-seq (zip-reader "../res/movies.list.gz"))))) ten-lines
  (def ten-parsed-lines (map parse-movie ten-lines)) ten-parsed-lines
  (def ten-hashmaps (map #(hash-map (second %) 1) ten-parsed-lines)) ten-hashmaps
  (def dataset (apply merge-with + ten-hashmaps)) dataset
)
