CC = g++
CFLAGS = -Wall -Wextra -std=c++17 -Werror=vla
LDFLAGS = -lm

TARGETS=poduri adrese lego

build: $(TARGETS)

poduri: poduri.cpp
	$(CC) $(CFLAGS) poduri.cpp -o poduri

adrese: adrese.cpp
	$(CC) $(CFLAGS) adrese.cpp -o adrese

lego: lego.cpp
	$(CC) $(CFLAGS) lego.cpp -o lego

run-p1: 
	./poduri

run-p2:
	./adrese

run-p3:
	./lego

run-p4:

clean:
	rm -f $(TARGETS)