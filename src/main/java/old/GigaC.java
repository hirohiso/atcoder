package old;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class GigaC {

    //https://atcoder.jp/contests/gigacode-2019/tasks/gigacode_2019_d
    public static void main(String[] args) {
        int H, W, K;
        long V;

        long[][] map;
        PrintWriter out = new PrintWriter(System.out);
        FastScanner fs = new FastScanner(System.in);
        H = fs.nextInt();
        W = fs.nextInt();
        K = fs.nextInt();
        V = fs.nextLong();
        map = new long[H + 1][W + 1];

        for (int i = 1; i < H + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                map[i][j] = fs.nextInt() + K;
            }
        }

        //二次元累積和
        for (int i = 1; i < H + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                map[i][j] += map[i][j - 1];
            }
        }
        for (int j = 1; j < W + 1; j++) {
            for (int i = 1; i < H + 1; i++) {
                map[i][j] += map[i - 1][j];
            }
        }

        /*
        System.out.println(check(map, H, W, V));
        System.out.println(check(map, 1, 1, V));
        System.out.println(check(map, 3, 3, V));
        System.out.println(check(map, 3, 4, V));
        System.out.println(check(map, 4, 3, V));
        */

        long result = 0;
        for (int i = W; i > 0; i--) {
            int hstart = 0;
            int hend = H;
            while (hend - hstart > 0) {
                int hmid = ((hend + hstart) / 2);
                if (check(map, (hmid + 1), i, V)) {
                    hstart = hmid + 1;
                    result = Math.max(result, i * (hmid + 1));
                } else {
                    hend = hmid;
                }
            }
        }
        System.out.println(result);
    }


    private static boolean check(long[][] area, int h, int w, long v) {
        //累積和area内で、v以下であるh * w の長方形が存在するかの判定
        for (int i = h; i < area.length; i++) {
            for (int j = w; j < area[0].length; j++) {
                long sum = area[i][j] - area[i - h][j] - area[i][j - w] + area[i - h][j - w];
                if (sum <= v) {
                    return true;
                }
            }
        }
        return false;
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
