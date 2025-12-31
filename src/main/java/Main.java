import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class Main {
    private final PrintWriter pw;
    private final FastScanner fs;

    private static boolean debug;

    public Main(PrintWriter pw, FastScanner fs) {
        this.pw = pw;
        this.fs = fs;
    }

    public static void main(String[] args) {
        solve(System.in, System.out);
    }

    static int mod = 998244353;

    private void solve() {
        var N = ni();
        var K = ni();
        nFact = 1;
        for (int i = 1; i <= N; i++) {
            nFact *= i;
            nFact %= mod;
        }

        var ret = new ArrayList<List<Integer>>();
        var v = new ArrayList<Integer>();
        partition(N, N, v, ret);


        var ans = 0L;
        for (var list : ret) {
            //debug(list);
            var t = cal(list, K);
            //debug(t);
            ans += t;
            ans %= mod;
        }
        pw.println(ans);
    }

    long nFact;

    private long cal(List<Integer> list, int K) {


        //A*B*C*....
        var deno = 1L;
        for (var v : list) {
            deno *= v;
            deno %= mod;
        }

        //A,B,C,,,,の入れ替え(組を区別しない list.size()!
        var map = list.stream().collect(Collectors.groupingBy(
                v -> v
        ));
        var k = 1L;
        for (var e : map.entrySet()) {
            for (int i = 1; i <= e.getValue().size(); i++) {
                k *= i;
                k %= mod;
            }
        }
        var invK = modInv(k, mod);

        //最小公倍数を求める
        var lcm = 1L;
        for (var v : list) {
            lcm = lcm(lcm, v);
        }
        var invDeno = modInv(deno, mod) * invK % mod;
        var temp = (nFact * invDeno % mod);
        //debug("lcm %d temp %d", lcm, temp);
        var plcm = powmod(lcm, K, mod);
        return ((temp * plcm) % mod);
    }

    private long lcm(long a, long b) {
        long temp;
        long c = a;
        c *= b;
        while ((temp = a % b) != 0) {
            a = b;
            b = temp;
        }
        return (long) (c / b);
    }

    private static void partition(int n, int max, ArrayList<Integer> v, ArrayList<List<Integer>> ret) {
        if (n == 0) {
            ret.add(v.stream().toList());
        }
        for (int i = Math.min(n, max); i > 0; i--) {
            v.add(i);
            partition(n - i, i, v, ret);
            v.remove(v.size() - 1);
        }
    }

    record TPair<S, T>(S a, T b) {
    }

    record TTri<S, T, U>(S a, T b, U c) {
    }


    record IntPair(int a, int b) {
    }

    record LongPair(long a, long b) {
    }

    record IntTriple(int a, int b, int c) {
    }

    record LongTriple(long a, long b, long c) {
    }


    private void Yes() {
        pw.println("Yes");
    }

    private void No() {
        pw.println("No");
    }


    public static void solve(InputStream in, PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        FastScanner fs = new FastScanner(in);
        try {
            var atcoder = System.getenv("ATCODER");
            debug = !("1".equals(atcoder));
            new Main(pw, fs).solve();
        } finally {
            pw.flush();
        }
    }


    //-------------------------------------------------------------------
    private static void debug(Object x) {
        if (!debug) {
            return;
        }
        System.err.println(x);
    }

    private static void debug(String format, Object... x) {
        if (!debug) {
            return;
        }
        System.err.println(String.format(format, x));
    }

    private static void debugArray(int[][] arr) {
        if (!debug) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            debugArray(arr[i]);
        }
    }

    private static void debugArray(double[][] arr) {
        if (!debug) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            debugArray(arr[i]);
        }
    }

    private static void debugArray(char[][] arr) {
        if (!debug) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            debug(new String(arr[i]));
        }
    }

    private static void debugArray(long[][] arr) {
        if (!debug) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            debugArray(arr[i]);
        }
    }


    private static void debugArray(int[] arr) {
        if (!debug) {
            return;
        }
        debug(Arrays.toString(arr));
    }

    private static void debugArray(double[] arr) {
        if (!debug) {
            return;
        }
        debug(Arrays.toString(arr));
    }

    private static <T> void debugArray(T[] arr) {
        if (!debug) {
            return;
        }
        debug(Arrays.toString(arr));
    }

    private static void debugArray(long[] arr) {
        if (!debug) {
            return;
        }
        debug(Arrays.toString(arr));
    }

    private static void debugArray(boolean[] arr) {
        if (!debug) {
            return;
        }
        debug(Arrays.toString(arr));
    }

    private static void debugArray(boolean[][] arr) {
        if (!debug) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            debugArray(arr[i]);
        }
    }


    static long modInv(long a, long m) {
        var result = 1L;
        var n = m - 2;
        var x = a % m;
        while (n > 0) {
            if ((n & 0b1) == 0b1) {
                result = (result * x) % m;
            }
            x = (x * x) % m;
            n >>= 1;
        }
        return result;
    }


    private static int[] arr(int... a) {
        return Arrays.copyOf(a, a.length);
    }

    private static long[] arr(long... a) {
        return Arrays.copyOf(a, a.length);
    }

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

    //時計周り90回転
    private static int[][] rrot(int[][] grid) {
        var h = grid.length;
        var w = grid[0].length;
        var result = new int[w][h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                result[j][h - 1 - i] = grid[i][j];
            }
        }
        return result;
    }

    private static char[][] rot(char[][] grid) {
        var h = grid.length;
        var w = grid[0].length;

        var result = new char[w][h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                result[w - 1 - j][i] = grid[i][j];
            }
        }
        return result;
    }

    //時計周り90回転
    private static char[][] rrot(char[][] grid) {
        var h = grid.length;
        var w = grid[0].length;
        var result = new char[w][h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                result[j][h - 1 - i] = grid[i][j];
            }
        }
        return result;
    }

    //dの桁数
    private int countDigits(long d) {
        var ret = 0;
        while (d > 0) {
            ret++;
            d /= 10;
        }
        return ret;
    }

    private long pow(long a, long b) {
        var ans = 1L;
        while (b != 0) {
            ans *= a;
            b--;
        }
        return ans;
    }

    /*
     * 繰り返し二乗法
     */
    static long powmod(long a, long n, long m) {
        var result = 1l;
        var x = a % m;
        while (n > 0l) {
            if ((n & 1l) == 1l) {
                result = (result * x) % m;
            }
            x = (x * x) % m;
            n >>= 1l;
        }
        return result;
    }


    private int[] foldl(int[] arr, IntBinaryOperator o, IntSupplier e, boolean containZero) {
        var init = e.getAsInt();
        int[] result;
        if (containZero) {
            result = new int[arr.length + 1];
            result[0] = init;
        } else {
            result = new int[arr.length];
            result[0] = arr[0];
        }
        for (int i = 1; i < result.length; i++) {
            result[i] = o.applyAsInt(result[i - 1], arr[containZero ? i - 1 : i]);
        }
        return result;
    }

    private int[] foldr(int[] arr, IntBinaryOperator o, IntSupplier e, boolean containZero) {
        var init = e.getAsInt();
        int[] result;
        if (containZero) {
            result = new int[arr.length + 1];
            result[arr.length] = init;
        } else {
            result = new int[arr.length];
            result[arr.length - 1] = arr[arr.length - 1];
        }
        for (int i = result.length - 2; i >= 0; i--) {
            result[i] = o.applyAsInt(arr[containZero ? i : i + 1], result[i + 1]);
        }
        return result;
    }

    private long[] foldl(long[] arr, LongBinaryOperator o, LongSupplier e, boolean containZero) {
        var init = e.getAsLong();
        long[] result;
        if (containZero) {
            result = new long[arr.length + 1];
            result[0] = init;
        } else {
            result = new long[arr.length];
            result[0] = arr[0];
        }
        for (int i = 1; i < result.length; i++) {
            result[i] = o.applyAsLong(result[i - 1], arr[containZero ? i - 1 : i]);
        }
        return result;
    }

    private long[] foldr(long[] arr, LongBinaryOperator o, LongSupplier e, boolean containZero) {
        var init = e.getAsLong();
        long[] result;
        if (containZero) {
            result = new long[arr.length + 1];
            result[arr.length] = init;
        } else {
            result = new long[arr.length];
            result[arr.length - 1] = arr[arr.length - 1];
        }
        for (int i = result.length - 2; i >= 0; i--) {
            result[i] = o.applyAsLong(arr[containZero ? i : i + 1], result[i + 1]);
        }
        return result;
    }

    private int[] reverseArray(int[] arr) {
        var reversed = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversed[i] = arr[arr.length - 1 - i];
        }
        return reversed;
    }


    private long[] reverseArray(long[] arr) {
        var reversed = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversed[i] = arr[arr.length - 1 - i];
        }
        return reversed;
    }

    private int[] sort(int[] arr) {
        var result = Arrays.copyOf(arr, arr.length);
        Arrays.sort(result);
        return result;
    }

    private long[] sort(long[] arr) {
        var result = Arrays.copyOf(arr, arr.length);
        Arrays.sort(result);
        return result;
    }


    private static long isqrt(long n) {
        var x = n;
        var y = (x + 1) / 2;
        while (y < x) {
            x = y;
            y = (n / y + y) / 2;
        }
        return x;
    }
    //State

    //HashMapで取り扱えるようにarrをラップしたstate
    //ハッシュ値の計算やオブジェクト同士の比較にO(|arr|)かかる
    record ArrayState(int[] arr) {
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            ArrayState arrayState = (ArrayState) o;
            return Objects.deepEquals(arr, arrayState.arr);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(arr);
        }

        public static ArrayState e = new ArrayState(new int[]{});

        public int lastNum() {
            if (this.arr.length == 0) {
                return -1;
            }
            return this.arr[this.arr.length - 1];
        }

        public int len() {
            return this.arr.length;
        }

        public ArrayState append(int v) {
            var narr = Arrays.copyOf(this.arr, this.arr.length + 1);
            narr[narr.length - 1] = v;
            return new ArrayState(narr);
        }

        public ArrayState replace(int v, int index) {
            var narr = Arrays.copyOf(this.arr, this.arr.length);
            narr[index] = v;
            return new ArrayState(narr);
        }

        @Override
        public String toString() {
            return Arrays.toString(arr);
        }
    }

    //----------------------


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
            mid = ((high - low) >>> 1) + low;
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
            mid = ((high - low) >>> 1) + low;
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

    private int[][] niaa(int N, int M) {
        return fs.niaa(N, M);
    }

    private long[][] nlaa(int N, int M) {
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