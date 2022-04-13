import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class Main {

    // https://atcoder.jp/contests/typical90/tasks/typical90_b
    public static void main(String[] args) {
        solve(System.in, System.out);
    }

    public static void solve(InputStream in, PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        FastScanner fs = new FastScanner(in);
        //==================

        // 0 : (
        // 1: )
        int n = fs.nextInt();
        long max = 1 << n;
        for (long i = 0; i < max; i++) {
            if (judge(i, n)) {
                pw.println(text(i, n));
            }
        }


        long result = 1;
        //==================


        pw.flush();
    }

    static boolean judge(long x, int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if ((x & 1) == 1) {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                return false;
            }
            x = x >> 1;
        }
        return count == 0 ? true : false;
    }

    static String text(long x, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if ((x & 1) == 1) {
                sb.append(')');
            } else {
                sb.append('(');
            }
            x = x >> 1;
        }
        return sb.reverse().toString();
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
