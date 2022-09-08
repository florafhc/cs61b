package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int openNum;
    private int size; // denote te size of the Percolation grid
    private int source; // denote the 1D num of the virtual water source
    private int bottom; // denote the 1D num of the virtual bottom
    private boolean[] isOpen; // denote the open status of each grid
    private boolean[] isFull; // denote the full status of each grid
    private WeightedQuickUnionUF uf;

    /** Transform 2D point set to unique integer */
    private int twoToOne(int row, int col) {
        return row * size + col;
    }

    /** Create N-by-N grid, with all sites initially blocked */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException(" N must be a positive integer!");
        }
        size = N;
        openNum = 0;
        bottom = N * N * 2;
        uf = new WeightedQuickUnionUF(N * N * 2 + 1);

        // initialize a virtual top site which secured to have the largest weight
        for (int i = N * N; i < N * N * 2; i += 1) {
            uf.union(N * N, i);
        }
        source = uf.find(N * N);

        // initialize all sites initially blocked
        isOpen = new boolean[N * N];
        isFull = new boolean[N * N];
        for (int i = 0; i < N * N; i += 1) {
            isOpen[i] = false;
            isFull[i] = false;
        }
    }

    /** Open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        int index = twoToOne(row, col);

        // Deal with corner case
        if (row < 0 | row > size - 1) {
            throw new IllegalArgumentException("indices of row is out of bound!");
        }
        if (col < 0 | col > size - 1) {
            throw new IllegalArgumentException("indices of column is out of bound!");
        }

        if (!isOpen(row, col)) {
            openNum += 1;
            isOpen[index] = true;
            if (row == 0) {
                uf.union(index, source);
            } else if (row == size - 1) {
                uf.union(index, bottom);
            }

            openConnect(row, col);


        }
    }
    private void openConnect(int row, int col) {
        int index = twoToOne(row, col);
        if (row - 1 >= 0) {
            if (isOpen(row - 1, col)) {
                uf.union(index, twoToOne(row - 1, col));
            }
        }
        if (row + 1 <= size - 1) {
            if (isOpen(row + 1, col)) {
                uf.union(index, twoToOne(row + 1, col));
            }
        }
        if (col - 1 >= 0) {
            if (isOpen(row, col - 1)) {
                uf.union(index, twoToOne(row, col - 1));
            }
        }
        if (col + 1 <= size - 1) {
            if (isOpen(row, col + 1)) {
                uf.union(index, twoToOne(row, col + 1));
            }
        }
    }

    /** Is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        int index = twoToOne(row, col);
        // Deal with corner case
        if (row < 0 | row > size - 1) {
            throw new IllegalArgumentException("indices of row is out of bound!");
        }
        if (col < 0 | col > size - 1) {
            throw new IllegalArgumentException("indices of column is out of bound!");
        }

        return isOpen[index];
    }
    /** Is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        int index = twoToOne(row, col);
        // Deal with corner case
        if (row < 0 | row > size - 1) {
            throw new IllegalArgumentException("indices of row is out of bound!");
        }
        if (col < 0 | col > size - 1) {
            throw new IllegalArgumentException("indices of column is out of bound!");
        }


        if (!percolates()) {
             if (uf.find(index) == source) {
                isFull[index] = true;
             }
             return isFull[index];
        } else {
            if (!isOpen(row, col)) {
                return false;
            }
            if (row == 0) {
                isFull[index] = true;
                return true;
            }
            if (row - 1 >= 0) {
                if (isFull[twoToOne(row - 1, col)]) {
                    isFull[index] = true;
                    return true;
                }
            }
            if (col - 1 >= 0) {
                if (isFull[twoToOne(row, col - 1)]) {
                    isFull[index] = true;
                    return true;
                }
            }
            if (col + 1 <= size - 1) {
                if (isFull[twoToOne(row, col + 1)]) {
                    isFull[index] = true;
                    return true;
                }
            }
            return false;
        }

    }

    /** Number of open sites */
    public int numberOfOpenSites() {
        return openNum;
    }

    /** Does the system percolate? */
    public boolean percolates() {
        return uf.find(bottom) == source;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        int num;
        num = p.twoToOne(1,0);
        System.out.println(num);
    }

}
