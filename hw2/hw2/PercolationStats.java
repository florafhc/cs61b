 package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private double[] percolationPerc;
    private int time;
    private int size;

    /** Perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        size = N;
        time = T;
        if (N <= 0 | T <= 0) {
            throw new IllegalArgumentException("Please enter valid input value!");
        }
        percolationPerc = new double[T];
        for (int i = 0; i < T; i += 1) {
            Percolation uf = pf.make(N);
            while (!uf.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                uf.open(row, col);
            }
            percolationPerc[i] = (double) uf.numberOfOpenSites() / (size * size);
        }
    }
    /** Sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(percolationPerc);
    }
    /** Sample standard deviation of percolation threshold */
    public double stddev() {
        if (time == 1) {
            return Double.NaN;
        } else {
            return StdStats.stddev(percolationPerc);
        }
    }
    /** Low endpoint of 95% confidence interval */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(time);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(time);
    }

    /**
    public static  void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(10, 200, pf);
        System.out.println(ps.mean());
        System.out.println(ps.confidenceLow());
        System.out.println(ps.confidenceHigh());
    }
     */
}
