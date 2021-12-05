import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Main {

    // https://atcoder.jp/contests/abc207/tasks/abc207_c
    public static void main(String[] args) {
        solve(System.in, System.out);
    }

    public static void solve(InputStream in, PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        FastScanner fs = new FastScanner(in);
        //==================
        boolean[] isEndClose = new boolean[]{true, false, true, false};
        boolean[] isStartClose = new boolean[]{true, true, false, false};

        int n = fs.nextInt();

        int[][] range = new int[n][3];
        for (int i = 0; i < n; i++) {
            range[i][0] = fs.nextInt();
            range[i][1] = fs.nextInt();
            range[i][2] = fs.nextInt();
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // i << j
                if (isEndClose[range[i][0] - 1] && isStartClose[range[j][0] - 1]) {
                    if (range[i][2] < range[j][1]) {
                        continue;
                    }
                }else{
                    if (range[i][2] <= range[j][1]) {
                        continue;
                    }
                }

                //j << i
                if (isEndClose[range[j][0] - 1] && isStartClose[range[i][0] - 1]) {
                    if (range[j][2] < range[i][1]) {
                        continue;
                    }
                }else{
                    if (range[j][2] <= range[i][1]) {
                        continue;
                    }
                }
                count++;
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
