package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {

    private double[] values;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.values = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation newPercolation = pf.make(N);
            boolean percolated = false;
            while (!percolated) {
                newPercolation.open(StdRandom.uniform(0, N), StdRandom.uniform(0, N));
                percolated = newPercolation.percolates();
            }
            int openSites = newPercolation.numberOfOpenSites();
            values[i] = (double) openSites / Math.pow(N, 2.0);
        }
    } // perform T independent experiments on an N-by-N grid

    public double mean() {
        double sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        return sum / values.length;
    } // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(this.values);
    } // sample standard deviation of percolation threshold

    public double confidenceLow() {
        return this.mean() - ((1.96 * this.stddev())) / (Math.pow(this.values.length, 0.5));
    } // low endpoint of 95% confidence interval

    public double confidenceHigh() {
        return this.mean() + ((1.96 * this.stddev()) / (Math.pow(this.values.length, 0.5)));
    } // high endpoint of 95% confidence interval
}
