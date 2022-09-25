package lab11.graphs;

import java.util.Observable;

/**
 * @author Josh Hug
 */
public abstract class MazeExplorer extends Observable {
    protected int[] distTo; // Distance to draw over the maze
    protected int[] edgeTo; // Edges to draw on Maze
    protected boolean[] marked; // locations to mark in blue
    protected Maze maze;


    /**
     * Notify all Observers of a change.
     */
    protected void announce() {
        setChanged();
        notifyObservers();
    }

    /** Initialize the MazeExplorer */
    public MazeExplorer(Maze m) {
        maze = m;

        distTo = new int[maze.V()];
        edgeTo = new int[maze.V()];
        marked = new boolean[maze.V()];
        for (int i = 0; i < maze.V(); i += 1) {
            distTo[i] = Integer.MAX_VALUE;
            edgeTo[i] = Integer.MAX_VALUE;
        }
        addObserver(maze);
    }

    /**
     * Solves the maze, modifying distTo and edgeTo as it goes.
     */
    public abstract void solve();

    /* Getters for AG. */
    public int[] getDistTo() {
        return distTo;
    }

    public int[] getEdgeTo() {
        return edgeTo;
    }
}
