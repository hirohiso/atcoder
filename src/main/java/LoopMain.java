import java.io.ByteArrayInputStream;
import java.util.Random;

public class LoopMain {
    public static void main(String[] args) {
        loop(args);
    }
    private static void loop(String[] args) {
        while (true) {
            System.out.println("------START-----");
            Main.main(args);
            System.out.println("------END-------");
        }
    }
}
/**
 * - ループ内部で大量のメモリ確保をしてないか
 * loop{
 * int[N][N]
 * }
 * Hash系もメモリ確保が走るよ
 * <p>
 * <p>
 * - 二分探索(lowerBound upperBound
 * <p>
 * {1,1,1,2,[3],3,3,5,5,5,} X.lowerBound(3)
 * {1,1,1,2,3,3,3,[5],5,5,} X.lowerBound(4)
 * {1,1,1,2,3,3,3,5,5,5,[]} X.lowerBound(8)
 * <p>
 * {1,1,1,2,3,3,3,[5],5,5,} X.upperBound(3)
 * {1,1,1,2,3,3,3,[5],5,5,} X.upperBound(4)
 * {1,1,1,2,3,3,3,5,5,5,[]} X.upperBound(8)
 * <p>
 * i未満のうち最大の要素
 * 　X[X.lowerBound(i) - 1] ただしX.lowerBound(i) - 1 == -1のとき存在しない
 * i以下のうち最大の要素
 * 　X[X.upperBound(i) - 1] ただしX.upperBound(i) - 1 == -1のとき存在しない
 * iより大きいうちの最小の要素
 * X[X.upperBound(i)] ただしX.upperBound(i)  == len(X)のとき存在しない
 * i以上のうちの最小の要素
 * X[X.lowerBound(i)] ただしX.lowerBound(i)  == len(X)のとき存在しない
 */