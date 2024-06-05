package model.mdisc;

public class Edge implements Comparable<Edge> {
    private String origin;
    private String destination;
    private int weight;

    public Edge(String origin, String destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return weight-o.weight;
    }
}
