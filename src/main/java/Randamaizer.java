import java.io.ByteArrayInputStream;
import java.util.*;

public class Randamaizer {
    static Random rand = new Random();

    public static void main(String[] args) {
        large(args);

    }

    private static void large(String[] args) {
        InputBuilder gen = new InputBuilder();
        String input = gen
                .addInt("N", 3, 5)
                .addInt("M", 3, 6)
                //.addArray("A", "N", 1, 100)
                .addGraph("G", "N", "M", true)
                .build();
        System.out.println(input);
        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        Main.solve(testInput, System.out);
    }

}

class InputBuilder {
    private final StringBuilder sb = new StringBuilder();
    private final Map<String, Integer> vars = new HashMap<>();
    private final Random rand = new Random();

    public InputBuilder addInt(String name, int min, int max) {
        int val = rand.nextInt(max - min + 1) + min;
        vars.put(name, val);
        sb.append(val).append("\n");
        return this;
    }

    public InputBuilder addArray(String name, String sizeVar, int min, int max) {
        int size = vars.get(sizeVar);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(max - min + 1) + min;
            sb.append(arr[i]);
            if (i < size - 1) sb.append(" ");
        }
        sb.append("\n");
        return this;
    }

    public InputBuilder addGraph(String name, String nVar, String mVar, boolean undirected) {
        int n = vars.get(nVar);
        int m = vars.get(mVar);
        Set<String> edges = new HashSet<>();
        for (int i = 0; i < m; ) {
            int u = rand.nextInt(n) + 1;
            int v = rand.nextInt(n) + 1;
            if (u == v) continue;
            String edge = u < v ? u + " " + v : v + " " + u;
            if (edges.contains(edge)) continue;
            edges.add(edge);
            sb.append(u).append(" ").append(v).append("\n");
            i++;
        }
        return this;
    }

    public String build() {
        return sb.toString();
    }
}
