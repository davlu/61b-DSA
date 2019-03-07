package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // create N-by-N grid, with all sites initially blocked
    private boolean[] openGrid;
    private boolean[] copyGrid;
    private int N;
    private int openSites;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF copyUnion;
    private int topWaterVal;
    private int bottomVal;

    public Percolation(int N) {
        if (0 >= N) {
            throw new java.lang.IllegalArgumentException();
        }
        this.N = N;
        topWaterVal = N * N;
        bottomVal = N * N + 1;
        this.uf = new WeightedQuickUnionUF(N * N + 2);
        this.copyUnion = new WeightedQuickUnionUF(N * N + 1);
        this.openGrid = new boolean[N * N];
        this.copyGrid = new boolean[N * N];
        for (int i = 0; i < N; i++) {
            uf.union(topWaterVal, helperIndex(0, i));
            copyUnion.union(topWaterVal, helperIndex(0, i));
        }
        for (int i = 0; i < N; i++) {
            uf.union(bottomVal, helperIndex(N - 1, i));
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= N || col >= N || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        if (!isOpen(row, col)) {
            this.openGrid[helperIndex(row, col)] = true;
            this.copyGrid[helperIndex(row, col)] = true;
            openSites++;
        }
        if (row + 1 < N && isOpen(row + 1, col)) {
            this.uf.union(helperIndex(row, col), helperIndex(row + 1, col));
            this.copyUnion.union(helperIndex(row, col), helperIndex(row + 1, col));
        }
        if (col + 1 < N && isOpen(row, col + 1)) {
            this.uf.union(helperIndex(row, col), helperIndex(row, col + 1));
            this.copyUnion.union(helperIndex(row, col), helperIndex(row, col + 1));
        }
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            this.uf.union(helperIndex(row, col), helperIndex(row - 1, col));
            this.copyUnion.union(helperIndex(row, col), helperIndex(row - 1, col));
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            this.uf.union(helperIndex(row, col), helperIndex(row, col - 1));
            this.copyUnion.union(helperIndex(row, col), helperIndex(row, col - 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= N || col >= N || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return this.openGrid[helperIndex(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= N || col >= N || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (isOpen(row, col) && row == N - 1) {
            if (col - 1 < 0) {
                return isFull(row - 1, col) || (isOpen(row, col + 1) && isFull(row, col + 1));
            } else if (col + 1 == N) {
                return isFull(row - 1, col) || (isOpen(row, col - 1) && isFull(row, col - 1));
            } else {
                return isFull(row - 1, col) || (isOpen(row, col - 1) && isFull(row, col - 1)) || (isOpen(row, col + 1) && isFull(row, col + 1));
            }
        }
        if (isOpen(row, col)) {
            return copyUnion.connected(helperIndex(row, col), topWaterVal);
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(topWaterVal, bottomVal);
    }

    public int helperIndex(int row, int col) {
        if (row < 0 || row >= N || col >= N || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return N * row + col;
    }


    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        for (int i = 0; i < 10; i++) {
            p.open(i, 2);
        }
        p.open(9, 5);
    }
}
