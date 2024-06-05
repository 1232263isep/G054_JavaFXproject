package model.mdisc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String parentDir = new File(Main.class.getClassLoader().getResource("csv/TARGET.txt").getPath()).getParent();

        File[] files = new File(parentDir).listFiles();

        List<File> us13Files = new ArrayList<>();
        for (File file : files) {
            if (file.isFile() && file.getName().startsWith("US13_")) {
                us13Files.add(file);
            }
        }

        if (us13Files.isEmpty()) {
            System.out.println("No US13 files found.");
            return;
        }
        int key = 1;
        System.out.println("US13 Files:");
        for (File us13File : us13Files) {
            System.out.printf("%d - %s\n", key, us13File.getName());
            key++;
        }
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Choose a file: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
        } while (choice < 1 || choice > us13Files.size());

        File f = us13Files.get(choice - 1);

        GraphImporter gi = new GraphImporter(f.getAbsolutePath());
        Graph g = gi.importGraph();

        MST mst = new MST();
        Graph mstGraph = mst.mstKruskal(g);


        File out = new File("./out");
        out.mkdirs();
        File outFile = new File(out, "mst.csv");
        PrintWriter pw = new PrintWriter(outFile);
        for (Edge e : mstGraph.getEdgeList()) {
            System.out.printf("%s;%s;%d\n", e.getOrigin(), e.getDestination(), e.getWeight());
            pw.printf("%s;%s;%d\n", e.getOrigin(), e.getDestination(), e.getWeight());
        }
        pw.flush();
        pw.close();


    }
}
