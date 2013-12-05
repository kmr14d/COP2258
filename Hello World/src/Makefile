CXXFLAGS =	-O2 -g -Wall -fmessage-length=0

OBJS =		Hello.o

LIBS =

TARGET =	all

$(TARGET):	$(OBJS)
	$(CXX) -o $(TARGET) $(OBJS) $(LIBS)

all:	$(TARGET)

clean:
	rm -f $(OBJS) $(TARGET)
