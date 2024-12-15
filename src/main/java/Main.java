import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Format;
import java.util.*;
import java.util.function.*;
import java.util.jar.JarEntry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("unchecked")
public class Main {
    private final PrintWriter pw;
    private final FastScanner fs;

    public Main(PrintWriter pw, FastScanner fs) {
        this.pw = pw;
        this.fs = fs;
    }

    public static void main(String[] args) {
        solve(System.in, System.out);
    }

    private void solve() {
        var N = fs.ni();
        var S = fs.nl();
        var Ai = fs.nla(N);

        var sum = Arrays.stream(Ai).sum();
        var acc = new long[N + 1];
        var set = new HashSet<Long>();
        set.add(0l);
        for (int i = 1; i < acc.length; i++) {
            acc[i] = acc[i - 1] + Ai[i - 1];
            set.add(acc[i]);
            set.add(acc[i] + sum);
        }
        //debugArray(acc);

        //debug(S % sum);
        for (int i = 0; i < acc.length; i++) {
            //debug(S % sum + acc[i]);
            if (set.contains((Long) ((S % sum) + acc[i]))) {
                pw.println("Yes");
                return;
            }
        }
        pw.println("No");
    }

    public void temp() {

    }


    //--------------
    record TPair<S, T>(S a, T b) {
    }

    record TTri<S, T, U>(S a, T b, U c) {
    }

    record Pair(long a, long b) {
    }

    record IntPair(int a, int b) {
    }

    record Triple(long a, long b, long c) {
    }


    private void Yes() {
        pw.println("Yes");
    }

    private void No() {
        pw.println("No");
    }


    public static int[] concat(int[] a, int[] b) {
        var ret = new int[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            ret[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            ret[i + a.length] = b[i];
        }
        return ret;
    }

    public static long[] concat(long[] a, long[] b) {
        var ret = new long[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            ret[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            ret[i + a.length] = b[i];
        }
        return ret;
    }


    public static void solve(InputStream in, PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        FastScanner fs = new FastScanner(in);
        try {
            new Main(pw, fs).solve();
        } finally {
            pw.flush();
        }
    }


    //-------------------------------------------------------------------
    private static void debug(Object x) {
        System.err.println(x);
    }

    private static void debugArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            debugArray(arr[i]);
        }
    }

    private static void debugArray(long[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            debugArray(arr[i]);
        }
    }


    private static void debugArray(int[] arr) {
        debug(Arrays.toString(arr));
    }

    private static void debugArray(long[] arr) {
        debug(Arrays.toString(arr));
    }

    private static void debugArray(boolean[] arr) {
        debug(Arrays.toString(arr));
    }

    private static void debugArray(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            debugArray(arr[i]);
        }
    }


    /**
     * 各インデックスが配列の長さ以内に収まっているか境界チェックを行う
     * <p>
     * 多次元配列のチェックをいちいち書くのがしんどい時に。
     * arrayBound(new int[]{1,2,3} , new int[]{3,4,3})
     *
     * @param target 配列に設定したいインデックス
     * @param len    　配列の長さ
     * @return 配列の長さ内に収まってる時 true
     */
    private static boolean inarr(int[] target, int[] len) {
        var b = true;
        if (target.length != len.length) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < target.length; i++) {
            b &= (0 <= target[i] && target[i] < len[i]);
        }
        return b;
    }

    private static int[] arr(int... a) {
        return Arrays.copyOf(a, a.length);
    }

    private static long[] arr(long... a) {
        return Arrays.copyOf(a, a.length);
    }

    //半時計90度回転
    private static int[][] rot(int[][] grid) {
        var h = grid.length;
        var w = grid[0].length;

        var result = new int[w][h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                result[w - 1 - j][i] = grid[i][j];
            }
        }
        return result;
    }


//http://fantom1x.blog130.fc2.com/blog-entry-194.html

    /**
     * <h1>指定した値以上の先頭のインデクスを返す</h1>
     * <p>配列要素が０のときは、０が返る。</p>
     *
     * @param arr   ： 探索対象配列(単調増加であること)
     * @param value ： 探索する値
     * @return<b>int</b> ： 探索した値以上で、先頭になるインデクス
     */
    public static final int lowerBound(final long[] arr, final long value) {
        int low = 0;
        int high = arr.length;
        int mid;
        while (low < high) {
            mid = ((high - low) >>> 1) + low;    //(low + high) / 2 (オーバーフロー対策)
            if (arr[mid] < value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    /**
     * <h1>指定した値より大きい先頭のインデクスを返す</h1>
     * <p>配列要素が０のときは、０が返る。</p>
     *
     * @param arr   ： 探索対象配列(単調増加であること)
     * @param value ： 探索する値
     * @return<b>int</b> ： 探索した値より上で、先頭になるインデクス
     */
    public static final int upperBound(final long[] arr, final long value) {
        int low = 0;
        int high = arr.length;
        int mid;
        while (low < high) {
            mid = ((high - low) >>> 1) + low;    //(low + high) / 2 (オーバーフロー対策)
            if (arr[mid] <= value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

//----------------

    private boolean bet(long l, long v, long r) {
        return l <= v && v < r;
    }

    private int ni() {
        return fs.ni();
    }

    private long nl() {
        return fs.nl();
    }

    private int[] nia(int N) {
        return fs.nia(N);
    }

    private long[] nla(int N) {
        return fs.nla(N);
    }

    private int[][] nia(int N, int M) {
        return fs.niaa(N, M);
    }

    private long[][] nla(int N, int M) {
        return fs.nlaa(N, M);
    }

    private String n() {
        return fs.n();
    }

    private String[] na(int n) {
        return fs.na(n);
    }

    private char nc() {
        return fs.n().toCharArray()[0];
    }

    private char[] nca() {
        return fs.n().toCharArray();
    }

    private char[][] ncaa(int n, int m) {
        return fs.ncaa(n, m);
    }

//-------------------------------------------------------------------
}

class FastScanner {
    InputStream in;
    byte[] buffer = new byte[1 << 10];
    int length = 0;
    int ptr = 0;
    private final Predicate<Byte> isPrintable;


    public FastScanner(InputStream in) {
        this.in = in;
        this.isPrintable = b -> (33 <= b && b <= 126);
    }

    public FastScanner(InputStream in, Predicate<Byte> predicate) {
        this.in = in;
        this.isPrintable = predicate;
    }

    private boolean hasNextByte() {
        if (ptr < length) {
            return true;
        }
        try {
            length = in.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ptr = 0;
        return length != 0;
    }


    private byte read() {
        if (hasNextByte()) {
            return buffer[ptr++];
        }
        return 0;
    }

    private void skip() {
        while (hasNextByte() && !isPrintable(buffer[ptr])) {
            ptr++;
        }
    }

    private boolean hasNext() {
        skip();
        return hasNextByte();
    }

    private boolean isPrintable(byte b) {
        return 33 <= b && b <= 126;
    }


    private String innerNext(Predicate<Byte> isReadable) {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        StringBuilder sb = new StringBuilder();
        byte b = read();
        while (isReadable.test(b)) {
            sb.appendCodePoint(b);
            b = read();
        }
        return sb.toString();
    }

    public String n() {
        return innerNext(b -> (33 <= b && b <= 126));
    }

    public int ni() {
        return (int) nl();
    }

    public char[][] ncaa(int n, int m) {
        var grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            grid[i] = n().toCharArray();
        }
        return grid;
    }

    public int[] nia(int n) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = ni();
        }
        return result;
    }

    public int[][] niaa(int h, int w) {
        int[][] result = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                result[i][j] = ni();
            }
        }
        return result;
    }

    public long[][] nlaa(int h, int w) {
        long[][] result = new long[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                result[i][j] = nl();
            }
        }
        return result;
    }

    public String[] na(int n) {
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = n();
        }
        return result;
    }

    public long[] nla(int n) {
        long[] result = new long[n];
        for (int i = 0; i < n; i++) {
            result[i] = nl();
        }
        return result;
    }

    public long nl() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        long result = 0;
        boolean minus = false;
        byte b;

        b = read();
        if (b == '-') {
            minus = true;
            b = read();
        }

        while (isPrintable(b)) {
            if (b < '0' || b > '9') {
                throw new NumberFormatException();
            }
            result *= 10;
            result += (b - '0');
            b = read();
        }

        return minus ? -result : result;
    }
}

