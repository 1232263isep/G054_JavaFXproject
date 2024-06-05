package model.mdisc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphImporter {

    private final String path;

    public GraphImporter(String path) {
        this.path = path;
    }

    public Graph importGraph() throws FileNotFoundException {
        Graph g = new Graph();
        File f = new File(path);

        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(";");
            if (parts.length == 3) {
                String origin = parts[0];
                String destination = parts[1];
                int weight = Integer.parseInt(parts[2]);
                g.addEdge(new Edge(origin, destination, weight));
            }
        }
        return g;
    }



}
