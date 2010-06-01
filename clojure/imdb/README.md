# imdb

Conduct empirical analyses on IMDB's data.

## Usage

### Get the data

    http://www.imdb.com/interfaces
    ftp://ftp.fu-berlin.de/pub/misc/movies/database/

    curl ftp://ftp.fu-berlin.de/pub/misc/movies/database/movies.list.gz > /tmp/movies.list.gz
    curl ftp://ftp.fu-berlin.de/pub/misc/movies/database/genres.list.gz > /tmp/genres.list.gz

### Run analyses

    java -jar imdb-standalone.jar 
    java -jar imdb-standalone.jar number-of-movies /tmp/movies.list.gz number-of-movies.csv
    java -jar imdb-standalone.jar movies-by-years /tmp/movies.list.gz movies-by-years.csv
    java -jar imdb-standalone.jar genres-by-years /tmp/genres.list.gz genres-by-years.csv

or

    lein repl
    user=> (load-file "src/imdb/analyses.clj")
    user=> (imdb.analyses/-main)
    user=> (imdb.analyses/-main :movies-by-years "/tmp/movies.list.gz" "/tmp/out.csv")

## Installation

    git clone ...
    cd imdb/src
    lein repl

    lein compile
    lein uberjar

## License

No license yet.
