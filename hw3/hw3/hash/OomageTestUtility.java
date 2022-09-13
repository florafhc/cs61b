package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] count = new int[M];
        int N = oomages.size();
        double down = N / 50;
        double up = N / 2.5;
        for (Oomage i : oomages) {
            int hashcode = (i.hashCode() & 0x7FFFFFFF) % M;
            count[hashcode] += 1;
        }
        for (int j = 0; j < M; j += 1) {
            if (count[j] < down || count[j] > up) {
                return false;
            }
        }
        return true;
    }
}
