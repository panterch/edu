#!/bin/sh

CP=target/classes
CP=$CP:target/test-classes
#CP=$CP:~/.m2/repository/com/c2/fit/fit/1.1/fit-1.1.jar
CP=$CP:~/.m2/repository/org/fitnesse/fitnesse/20060719/fitnesse-20060719.jar
CP=$CP:~/.m2/repository/org/fitnesse/fitlibrary/20060719/fitlibrary-20060719.jar

FIT_RUNNER=fitlibrary.runner.FolderRunner

TEST_FOLDER=src/test/resources/storytests
OUTPUT_FOLDER=target/storytests

java -classpath $CP $FIT_RUNNER $@ $TEST_FOLDER $OUTPUT_FOLDER
