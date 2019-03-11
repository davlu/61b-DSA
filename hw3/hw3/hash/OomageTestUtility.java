package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        double lowerBound = oomages.size() / 50;
        double upperBound = oomages.size() / 2.5;
        int[] bucket = new int[M];
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            bucket[bucketNum] += 1;
        }
        for (int i = 0; i < M; i++) {
            if (bucket[i] > upperBound || bucket[i] < lowerBound) {
                return false;
            }
        }
        return true;
    }
}
