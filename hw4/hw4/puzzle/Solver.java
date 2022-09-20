package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.Arrays;

public class Solver {
    int stepCount;
    MinPQ<SearchNode> moveSequence;
    SearchNode finalNode;
    boolean solved;

    private class SearchNode implements Comparable<SearchNode> {
        WorldState worldstate;
        int count;
        SearchNode prevNode;
        int estimatedCount;
        int estimatedTotalCount;

        SearchNode(WorldState newWord, SearchNode prev) {
            worldstate = newWord;
            prevNode = prev;
            if (prevNode == null) {
                count = 0;
            } else {
                count = prevNode.count + 1;
            }
            estimatedCount = worldstate.estimatedDistanceToGoal();
            estimatedTotalCount = count + estimatedCount;
        }
        @Override
        public int compareTo(SearchNode node) {
            return this.estimatedTotalCount - node.estimatedTotalCount;
        }
    }

    /** Constructor which solves the puzzle, computing
     everything necessary for moves() and solution() to
     not have to solve the problem again. Solves the
     puzzle using the A* algorithm. Assumes a solution exists.*/
    public Solver(WorldState initial) {
        SearchNode init = new SearchNode(initial, null);
        moveSequence = new MinPQ<SearchNode>();
        moveSequence.insert(init);
        solved = false;

        while (!solved) {
            SearchNode min = moveSequence.delMin();
            if (min.estimatedCount == 0) {
                stepCount = min.count;
                finalNode = min;
                solved = true;
            } else {
                for (WorldState neighbour : min.worldstate.neighbors()) {
                    SearchNode neighbourNode = new SearchNode(neighbour, min);
                    if (min.prevNode == null) {
                        moveSequence.insert(neighbourNode);
                    } else if (!neighbourNode.worldstate.equals(min.prevNode.worldstate)) {
                        moveSequence.insert(neighbourNode);
                    }
                }
            }
        }

    }

    /** Returns the minimum number of moves to solve the puzzle starting
     at the initial WorldState.*/
    public int moves() {
        return stepCount;
    }

    /** Returns a sequence of WorldStates from the initial WorldState
     to the solution.*/
    public Iterable<WorldState> solution() {
        WorldState[] stateList = new WorldState[moves() + 1];
        SearchNode currentNode = finalNode;
        for (int i = moves(); i >= 0; i -= 1) {
            stateList[i] = currentNode.worldstate;
            currentNode = currentNode.prevNode;
        }
        return Arrays.asList(stateList);
    }
}