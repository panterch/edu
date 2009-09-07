# demo makefile, as described on
# http://edu.panter.ch/cgi-bin/moin.fcg/FormelleBedingungenUebungenBetriebssysteme
#

GCC=/usr/sfw/bin/gcc

test: foo.o
	$(GCC) -o test foo.o bar.o

foo.o: bar.o
	$(GCC) -c foo.c

bar.o:
	$(GCC) -c bar.c

clean:
	rm -f test foo.o bar.o core

	
