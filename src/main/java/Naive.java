import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.IntStream;


public class Naive {
    private final PrintWriter pw;
    private final FastScanner fs;

    private static boolean debug;

    public static void main(String[] args) {
        solve(System.in, System.out);
    }

    private Naive(PrintWriter pw, FastScanner fs) {
        this.pw = pw;
        this.fs = fs;
    }

    void naive() {
        var T = fs.ni();
    }



    public static void solve(InputStream in, PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        FastScanner fs = new FastScanner(in);
        try {
            new Naive(pw, fs).naive();
        } finally {
            pw.flush();
        }
    }
}
