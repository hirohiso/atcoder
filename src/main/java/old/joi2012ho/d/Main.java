package old.joi2012ho.d;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class Main {

    // https://atcoder.jp/contests/joi2012ho/tasks/joi2012ho4
    public static void main(String[] args) {
        solve(System.in, System.out);
    }

    public static void solve(InputStream in, PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        FastScanner fs = new FastScanner(in);
        //==================

        int n = fs.nextInt();
        int m = fs.nextInt();

        int[][] range = new int[m][3];
        for (int i = 0; i < m; i++) {
            range[i][0] = fs.nextInt();
            range[i][1] = fs.nextInt();
            range[i][2] = fs.nextInt();
        }

        int count = 0;
        int[][] tri = new int[n + 1][n + 1];

        for (int i = 0; i < range.length; i++) {
            int a = range[i][0];
            int b = range[i][1];
            int x = range[i][2];
            tri[a + x][b] = Math.max(tri[a + x][b],x + 1);
        }
        for (int col = 1; col <= n; col++) {
            for (int row = n - 1; row >= col; row--) {
                int next = tri[row + 1][col];
                tri[row][col] = Math.max(tri[row][col], next - 1);
            }
        }
        for (int row = 1; row <= n; row++) {
            for (int col = 2; col <= row; col++) {
                int next = tri[row][col - 1];
                tri[row][col] = Math.max(tri[row][col], next - 1);
            }
        }
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= row; col++) {
                if(tri[row][col] != 0){
                    count++;
                }
            }
        }

        //==================

        pw.println(count);
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
