package old.tenka1_2018.d;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class Main {

    // https://atcoder.jp/contests/tenka1-2018-beginner/tasks/tenka1_2018_d
    public static void main(String[] args) {
        solve(System.in, System.out);
    }

    public static void solve(InputStream in, PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        FastScanner fs = new FastScanner(in);
        //==================

        int n = fs.nextInt();
        if (n == 1) {
            pw.println("Yes");
            pw.println("2");
            pw.println("1 1");
            pw.println("1 1");
            pw.flush();
            return;
        }
        int k;
        if ((k = judge(n)) == -1) {
            pw.println("No");
            pw.flush();
            return;
        }

        System.out.println("Yes");
        System.out.println(k);
        for (int i = 1; i <= k; i++) {
            System.out.print(k -1);
            System.out.print(" ");
            for (int j = 1, b = i -1; j <= i - 1; b =b + (k -(j+1)),j++) {
                System.out.print(b);
                System.out.print(" ");
            }
            for (int j = 1, a = ((2* k - i) * (i -1) /2 ) + 1; j <= k - i; a++,j++) {
                System.out.print(a);
                System.out.print(" ");
            }

            System.out.println("");
        }

        long result = 1;
        //==================

        pw.println("");
        pw.flush();
    }

    static int judge(int n) {
        for (int i = 1; i <= n; i++) {
            if (i * (i - 1) == 2 * n) {
                return i;
            } else if (i * (i - 1) > 2 * n) {
                return -1;
            } else {
                continue;
            }
        }
        return -1;
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
