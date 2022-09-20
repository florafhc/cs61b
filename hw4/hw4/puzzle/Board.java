package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;
public class Board implements WorldState {
    private int[][] board;
    private int[][] goal;
    private int N;
    private static final int BLANK = 0;
    /** Constructs a board from an N-by-N array of tiles where
     tiles[i][j] = tile at row i, column j */
    public Board(int[][] tiles) {
        N = tiles[0].length;
        board = new int[N][N];
        for (int m = 0; m < N; m++) {
            for (int n = 0; n < N; n++) {
                board[m][n] = tiles[m][n];
            }
        }
        goal = new int[N][N];
        int k = 1;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                goal[i][j] = k;
                if (k == N * N) {
                    goal[i][j] = BLANK;
                }
                k += 1;
            }
        }

    }

    /** Returns value of tile at row i, column j (or 0 if 0) */
    public int tileAt(int i, int j) {
        if (i < 0 | i > size() - 1 | j < 0 | j > size() - 1) {
            throw new IndexOutOfBoundsException();
        } else {
            return board[i][j];
        }
    }

    /** Returns the board size N */
    public int size() {
        return N;
    }

    /** Returns the neighbors of the current board */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
        /**
        Queue<WorldState> neighbors = new Queue<>();
        int N = size();
        int blankRow = -1;
        int blankCol = -1;

        // locate the blank state in the board
        for (int m = 0; m < N; m++) {
            for (int n = 0; n < N; n++) {
                if (tileAt(m, n) == BLANK) {
                    blankRow = m;
                    blankCol = n;
                }
            }
        }

        // create a copy of current board subject to neighbour change
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.abs(i - blankRow) + Math.abs(j - blankCol) - 1 == 0) {
                    int[][] newBoard = new int[N][N];
                    for (int m = 0; m < N; m++) {
                        for (int n = 0; n < N; n++) {
                            newBoard[m][n] = tileAt(m, n);
                        }
                    }
                    newBoard[blankRow][blankCol] = tileAt(i, j);
                    newBoard[i][j] = BLANK;
                    Board neighbor = new Board(newBoard);
                    neighbors.enqueue(neighbor);
                }
            }
        }
        return neighbors;
         */
    }

    /** Calculate the number of tiles in the wrong position */
    public int hamming() {
        int diff = 0;
        for (int i = 0; i < size(); i += 1) {
            for (int j = 0; j < size(); j += 1) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                } else if (tileAt(i, j) != goal[i][j]) {
                    diff += 1;
                }
            }
        }
        return diff;
    }
    /** Calculate the sum of the Manhattan distances (sum of the
     * vertical and horizontal distance) from the tiles
     * to their goal positions.*/
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < size(); i += 1) {
            for (int j = 0; j < size(); j += 1) {
                sum += helpManhattan(tileAt(i, j), goal[i][j]);
            }
        }

        return sum;
    }
    private int col(int num) {
        if (num == 0) {
            return size() - 1;
        } else {
            return (num - 1) % size();
        }
    }

    private int row(int num) {
        if (num == 0) {
            return size() - 1;
        } else {
            return (num - 1) / size();
        }
    }
    /** Calculate the Manhattan distances between integer a and b */
    private int helpManhattan(int a, int b) {
        if (a == BLANK) {
            return 0;
        }
        int rowA, colA, rowB, colB, diff;
        rowA = row(a);
        rowB = row(b);
        colA = col(a);
        colB = col(b);
        diff = Math.abs(rowA - rowB) + Math.abs(colA - colB);
        return diff;

    }
    /** Estimated distance to goal. This method should
     simply return the results of manhattan() when submitted to
     Gradescope. */
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    /** Returns true if this board's tile values are the same
     position as y's */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || getClass() != y.getClass()) {
            return false;
        }
        Board board1 = (Board) y;
        if (size() != board1.size()) {
            return false;
        }
        for (int i = 0; i < size(); i += 1) {
            for (int j = 0; j < size(); j += 1) {
                if (tileAt(i, j) != board1.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }



}
