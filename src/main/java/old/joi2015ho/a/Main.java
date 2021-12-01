package old.joi2015ho.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class Main {

    //https://atcoder.jp/contests/joi2015ho/tasks/joi2015ho_a
    public static void main(String[] args) {
        solve(System.in, System.out);
    }

    public static void solve(InputStream in, PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        FastScanner fs = new FastScanner(in);
        //==================

        int n = fs.nextInt();
        int m = fs.nextInt();
        int[] Pm = new int[m];
        int[] An = new int[n];
        int[] Bn = new int[n];
        int[] Cn = new int[n];

        for (int i = 0; i < Pm.length; i++) {
            Pm[i] = fs.nextInt();
        }
        for (int i = 1; i < An.length; i++) {
            An[i] = fs.nextInt();
            Bn[i] = fs.nextInt();
            Cn[i] = fs.nextInt();
        }

        long[] sum = new long[n + 1];
        for (int i = 1; i < Pm.length; i++) {
            int a = Pm[i - 1];
            int b = Pm[i];
            if (a < b) {
                sum[a]++;
                sum[b]--;
            } else {
                sum[a]--;
                sum[b]++;
            }
        }
        //System.out.println(Arrays.toString(sum));
        long total = 0;
        long count = 0;
        for (int i = 1; i < sum.length - 1; i++) {
            count += sum[i];
            long kami = An[i] * count;
            long ic = (Bn[i] * count) + Cn[i];
            total += Math.min(kami, ic);
        }
        //System.out.println(total);

        //==================
        pw.println(total);
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
