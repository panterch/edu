(ns imdb.analyses
  (:use [imdb.number-of-movies]
        [imdb.movies-by-years]
        [imdb.genres-by-years])
  (:gen-class))

(def *usage* "SYNOPSIS: name-of-analysis imdb-input-file csv-output-file")

(defn -main [& args]
  (if  (> 3 (count args)) (println *usage*)
       (let [[analysis input output] args]
         (time (condp = (keyword analysis)
                 :number-of-movies (number-of-movies input output)
                 :movies-by-years  (movies-by-years input output)
                 :genres-by-years  (genres-by-years input output))))))
