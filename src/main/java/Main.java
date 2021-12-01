import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class Main {

    //https://atcoder.jp/contests/jsc2019-qual/tasks/jsc2019_qual_b
    public static void main(String[] args) {
        solve(System.in, System.out);
    }

    public static void solve(InputStream in, PrintStream out) {
        long mod = 1_000_000_000 + 7;

        PrintWriter pw = new PrintWriter(out);
        FastScanner fs = new FastScanner(in);
        //==================

        int n = fs.nextInt();
        long k = fs.nextLong();
        int[] an = new int[n];
        long[] count = new long[2001];

        for (int i = 0; i < n; i++) {
            an[i] = fs.nextInt();
            count[an[i]]++;
        }
        int total = 0;
        int temp = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                total += temp;
                temp += count[i];
                total %= mod;
                temp %= mod;
            }
        }
        long p = 0;
        for (int i = 0; i < an.length; i++) {
            for (int j = i + 1; j < an.length; j++) {
                if (an[i] > an[j]) {
                    p++;
                    p %= mod;
                }
            }
        }
        long unit = (p * k) % mod;
        //==================
        long t = ((k * (k - 1))% mod / 2) ;
        long group = (total * t) % mod;
        pw.println((group + unit) % mod);
        pw.flush();
    }

    //-------------------------------------------------------------------
    public static class FastScanner {
        InputStream in;
        byte[] buffer = new byte[1 << 10];
        int length = 0;
        int ptr = 0;

        public FastScanner(InputStream in) {
            this.in = in;
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
            if (length == 0) {
                return false;
            }
            return true;
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
            return 33 < b && b < 126;
        }

        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            StringBuilder sb = new StringBuilder();
            byte b = read();
            while (isPrintable(b)) {
                sb.appendCodePoint(b);
                b = read();
            }
            return sb.toString();
        }


        public int nextInt() {
            return (int) nextLong();
        }

        public long nextLong() {
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

//-------------------------------------------------------------------
}
