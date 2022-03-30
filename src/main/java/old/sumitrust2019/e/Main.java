package old.sumitrust2019.e;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class Main {

    // https://atcoder.jp/contests/sumitrust2019/tasks/sumitb2019_e
    public static void main(String[] args) {
        solve(System.in, System.out);
    }

    public static void solve(InputStream in, PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        FastScanner fs = new FastScanner(in);
        //==================

        int n = fs.nextInt();

        int[] rgb = new int[3];

        int an;
        long result = 1;
        long e = 1000000007;
        for (int i = 0; i < n; i++) {
            an = fs.nextInt();
            int count = count(an,rgb);
            result = (result * count) % e;
            add(an,rgb);
            //System.out.println(Arrays.toString(rgb));
        }
        //==================

        pw.println(result);
        pw.flush();
    }

    static int count(int target,int[] array) {
        int result = 0;
        for(int n : array){
            if (n == target){
                result++;
            }
        }
        return result;
    }

    static void add(int target,int[] array){
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target){
                array[i]++;
                return;
            }
        }
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
