CC = g++
CFLAGS = -Wall -Wextra -std=c++17 -Werror=vla
LDFLAGS = -lm

TARGETS=crypto stocks valley ridge trigigel

build: $(TARGETS)

crypto: crypto.cpp
	$(CC) $(CFLAGS) crypto.cpp -o crypto

stocks: stocks.cpp
	$(CC) $(CFLAGS) stocks.cpp -o stocks

valley: valley.cpp
	$(CC) $(CFLAGS) valley.cpp -o valley

ridge: ridge.cpp
	$(CC) $(CFLAGS) ridge.cpp -o ridge

trigigel: trigigel.cpp
	$(CC) $(CFLAGS) trigigel.cpp -o trigigel

run-p1: 
	./crypto

run-p2:
	./stocks

run-p3:
	./valley

run-p4:
	./ridge

run-p5:
	./trigigel

clean:
	rm -f $(TARGETS)