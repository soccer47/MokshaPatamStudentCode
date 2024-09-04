import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Stevie Halprin
 *
 */

public class MokshaPatam {

    /**
     * TODO: Complete this function, fewestMoves(), to return the minimum number of moves
     *  to reach the final square on a board with the given size, ladders, and snakes.
     */
    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {

        // Sets the current cell to the starting cell of the maze
        int current = 1;
        // Creates new Queue to which the squares to be explored are added
        Queue<Integer> toVisit = new LinkedList<>();
        // Array of integers that holds the parent square of each square at prevSq[square #]
        int[] prevSq = new int[boardsize + 1];
        // Adds the start cell to the solution
        toVisit.add(current);

        // While the end square hasn't been visited, continue looping
        while (prevSq[boardsize] == 0) {
            // If there aren't any more squares to visit in the queue, return -1 (no solution)
            if (toVisit.peek() == null) {
                return -1;
            }

            // Current square is the next value in the toVisit queue
            current = toVisit.remove();

            // Add the next possible squares to the toVisit queue (if they aren't already visited)
            for (int i = 1; i < 7; i++) {
                int resultSq = current + i;
                // If the result square is the end square, set the parent square equal to current square and break
                if (resultSq == boardsize) {
                    prevSq[boardsize] = current;
                    break;
                }

                // Makes sure that the result square isn't greater than the end square, and hasn't been given a parent square yet
                if (resultSq <= boardsize && prevSq[resultSq] == 0) {
                    // If the result roll lands on a ladder, go to the corresponding square on the board
                    for (int j = 0; j < ladders.length; j++) {
                        if (resultSq == ladders[j][0]) {
                            resultSq = ladders[j][1];
                            break;
                        }
                    }
                    // If the result roll lands on a snake, go to the corresponding square on the board
                    for (int j = 0; j < snakes.length; j++) {
                        if (resultSq == snakes[j][0]) {
                            resultSq = snakes[j][1];
                            break;
                        }
                    }

                    // Makes sure the parent of the result square hasn't yet been determined
                    if (prevSq[resultSq] == 0) {
                        // Add the result square to the toVisit queue
                        toVisit.add(resultSq);
                        // Set the parent of the result square equal to the current square
                        prevSq[resultSq] = current;
                    }
                }
            }

        }

        // Return the lowest number of possible turns
        return getSolution(prevSq);
    }


    // Return the number of turns taken by looping backward from the end square
    public static int getSolution(int[] prevSq) {
        // Set the current square equal to the end square to start
        int current = prevSq[prevSq.length - 1];
        // Set turns equal to 1 to start
        int turns = 1;

        // While current square isn't the start square, continue looping
        while (current != 1) {
            // Set current to the parent square of the current square
            current = prevSq[current];
            // Add ones to turns
            turns++;
        }

        // Return the number of turns taken
        return turns;
    }

}
