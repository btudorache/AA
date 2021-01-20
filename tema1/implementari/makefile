JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
    AcyclicShortestPath.java \
    BellmanFord.java \
    DFS.java \
    Dijkstra.java \
    DirectedEdge.java \
    EdgeWeightedDigraph.java \
    TestRunner.java \
    IndexMinPQ.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run:
	java TestRunner