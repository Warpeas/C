#exercise: main.cpp fun.cpp
#	g++ -o exercise main.cpp fun.cpp


CC = g++

TARGET = exercise
INCLUDE = fun.h
#SRC = main.cpp fun.cpp
OBJ = main.o fun.o

TARGET1 = exercise2
INCLUDE1 = CandyBar.h
OBJ1 = candybar.o cmain.o

CFLAGS = -c -Wall

#$(TARGET):$(SRC)
#$(CC) -o $(TARGET) $(SRC)

#main.o: main.cpp $(INCLUDE)
#$(CC) -c main.cpp
#fun.o: fun.cpp $(INCLUDE)
#$(CC) -c factorial.cpp

$(TARGET): $(OBJ)
	$(CC) $^ -o $@

%.o: %.cpp $(INCLUDE)
	$(CC) $(CFLAGS) $< -o $@

.PHONY: clean
clean:
	rm -f *.o $(TARGET)
