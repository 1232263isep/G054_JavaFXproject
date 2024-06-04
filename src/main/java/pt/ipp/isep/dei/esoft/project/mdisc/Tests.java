package pt.ipp.isep.dei.esoft.project.mdisc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Tests {

    public static void main(String[] args) throws FileNotFoundException {
        String parentDir = new File(Main.class.getClassLoader().getResource("csv/TARGET.txt").getPath()).getParent();
        File[] files = new File(parentDir).listFiles();

        List<File> us14Files = new ArrayList<>();
        for (File file : files) {
            if (file.isFile() && file.getName().startsWith("us14_")) {
                us14Files.add(file);
            }
        }

        if (us14Files.isEmpty()) {
            System.out.println("No US14 files found.");
            return;
        }

        System.out.println("US14 Files:");
        for (File us14File : us14Files) {
            System.out.printf("%s\n", us14File.getName());
        }

        List<Long> times = new ArrayList<>();
        List<Integer> sizes = new ArrayList<>();
        System.out.println("Performing tests...");
        for (File us14File : us14Files) {
            GraphImporter gi = new GraphImporter(us14File.getAbsolutePath());
            try {
                Graph g = gi.importGraph();
                MST mst = new MST();
                System.out.println("Graph: "+g.getVertexList().size());
                long startTime =System.nanoTime();
                Graph mstGraph = mst.mstKruskal(g);
                long endTime = System.nanoTime();
                times.add(endTime-startTime);
                sizes.add(g.getVertexList().size());
            } catch (Exception e) {
                System.out.println("Error importing graph from " + us14File.getName());
            }
        }


        System.out.println("Results:");
        for (int i = 0; i < us14Files.size(); i++) {
            System.out.printf("Graph with %d vertices: %d ms\n", sizes.get(i), times.get(i)/1000000);
        }

        File out = new File("./out");
        out.mkdirs();
        File outFile = new File(out, "results.csv");
        PrintWriter pw = new PrintWriter(outFile);
        pw.println("Vertices,Time(ms)");
        for (int i = 0; i < us14Files.size(); i++) {
            pw.printf("%d,%d\n", sizes.get(i), times.get(i)/1000000);
        }
        pw.flush();
        pw.close();
    }



}
