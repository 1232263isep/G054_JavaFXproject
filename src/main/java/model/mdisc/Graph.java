package model.mdisc;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<Vertex> vertexList;
    private final List<Edge> edgeList;


    public Graph() {
        this.vertexList = new ArrayList<>();
        this.edgeList = new ArrayList<>();
    }

    public void addVertex(Vertex vertex) {
        this.vertexList.add(vertex);
    }

    public void addEdge(Edge edge) {
        Vertex vertex1=null;
        Vertex vertex2=null;


        for (Edge e : this.edgeList) {
            if (e.getOrigin() == edge.getOrigin() && e.getDestination() == edge.getDestination()) {
                return;
            }
        }

        for (Vertex v : this.vertexList) {
            if (v.getId() == edge.getOrigin()) {
                vertex1 = v;
            }
            if (v.getId() == edge.getDestination()) {
                vertex2 = v;
            }
        }

        if (vertex1==null) this.vertexList.add(new Vertex(edge.getOrigin()));
        if (vertex2==null) this.vertexList.add(new Vertex(edge.getDestination()));
        this.edgeList.add(edge);
    }

    public List<Vertex> getVertexList() {
        return this.vertexList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

}
