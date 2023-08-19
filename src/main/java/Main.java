import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        solve(System.in, System.out);
    }

    public static void solve(InputStream in, PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        FastScanner fs = new FastScanner(in);

        //==================
        int N = fs.nextInt();

        String result = switch (N){
            case 0 -> "zero";
            case 1 -> "one";
            case 2 -> new Test(2,3).x + "";
            default -> "others";
        };

        pw.println(result);
        //=================
        pw.flush();
    }

    public static record Test(int x,int y){};
    //-------------------------------------------------------------------

    public static class FastScanner {
        InputStream in;
        byte[] buffer = new byte[1 << 10];
        int length = 0;
        int ptr = 0;
        private final Predicate<Byte> isPrintable;


        public FastScanner(InputStream in) {
            this.in = in;
            this.isPrintable = b -> (33 < b && b < 126);
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
            return 33 < b && b < 126;
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

        public String next() {
            return innerNext(b -> (33 < b && b < 126));
        }

        public int nextInt() {
            return (int) nextLong();
        }

        public int[] nextIntArray(int n) {
            int[] result = new int[n];
            for (int i = 0; i < n; i++) {
                result[i] = nextInt();
            }
            return result;
        }

        public String[] nextArray(int n) {
            String[] result = new String[n];
            for (int i = 0; i < n; i++) {
                result[i] = next();
            }
            return result;
        }

        public long[] nextLongArray(int n) {
            long[] result = new long[n];
            for (int i = 0; i < n; i++) {
                result[i] = nextLong();
            }
            return result;
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
