package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] childTo;
    public boolean[] marked;
    */
    private Maze maze;
    private int s = 0;
    private int[] childTo;
    boolean cycleFound = false;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        childTo = new int[m.V()];
        distTo[s] = 0;
        childTo[s] = s;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        /**
        Stack<Integer> stack = new Stack<Integer>();
         distTo[s] = 0;
         childTo[s] = s;
         marked[s] = true;
         stack.push(s);
         announce();

         while(!stack.isEmpty()) {
             int v = stack.pop();
             for (int w : maze.adj(v)) {
                 if (marked[w] & (w != childTo[v])) {
                     edgeTo[w] =
                     childTo[w] = v;
                     distTo[w] = distTo[v] + 1;
                     announce();
                     return;
                 }
                 if (!marked[w]) {
                     stack.push(w);
                     childTo[w] = v;
                     distTo[w] = distTo[v] + 1;
                     marked[w] = true;
                     announce();
                 }

             }
         }
         */
        solveHelper(s);


    }
    private void solveHelper(int v) {
        marked[s] = true;
        announce();

        for (int w : maze.adj(v)) {
            if (cycleFound) {
                return;
            }
            if (marked[w] & (w != childTo[v])) {
                drawCycle(v, w);
                cycleFound = true;
                announce();
                return;
            }
            if (!marked[w]) {
                marked[w] = true;
                childTo[w] = v;
                distTo[w] = distTo[v] + 1;
                announce();
                solveHelper(w);
            }

        }



    }

    private void drawCycle(int v, int w) {
        int parent = v;
        while (parent != w) {
            parent = childTo[v];
            edgeTo[v] = parent;
            v = parent;
        }
        announce();
        // edgeTo[origin] = w;
        // announce();
    }
    // Helper methods go here
}

