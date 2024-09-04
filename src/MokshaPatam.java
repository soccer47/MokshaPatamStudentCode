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
        // Creates new Queue to which the cells to be explored are added
        Queue<Integer> toVisit = new LinkedList<>();
        int[] prevSq = new int[boardsize + 1];
        // Adds the start cell to the solution
        toVisit.add(current);

        while (prevSq[boardsize] == 0) {
            if (toVisit.peek() == null) {
                return -1;
            }
            current = toVisit.remove();

            // Add the next possible squares to the toVisit queue (if they aren't already visited)
            for (int i = 1; i < 7; i++) {
                int resultSq = current + i;
                if (resultSq == boardsize) {
                    prevSq[boardsize] = current;
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
                        toVisit.add(resultSq);
                        prevSq[resultSq] = current;
                    }
                }
            }

        }

        // Return the lowest number of possible turns
        return getSolution(prevSq);
    }


    // Return the number of turns taken
    public static int getSolution(int[] prevSq) {
        // Set the current square equal to the end square to start
        int current = prevSq[prevSq.length - 1];
        // Set turns equal to 1 to start
        int turns = 1;

        // While current square isn't the first square, continue looping
        while (current != 1) {
            // Set current to the previous square of the current square
            current = prevSq[current];
            // Add ones to turns
            turns++;
        }

        // Return the number of turns taken
        return turns;
    }

    public static int rollResult(int oldSquare, int roll, int[][] ladders, int[][] snakes, boolean[] visitedSq, int boardsize) {
        // If the old square is the end square, return the number of visited squares
        if (oldSquare == boardsize) {
            // Set the end square equal to true to show that it has been reached
            visitedSq[boardsize - 1] = true;
            return countTurns(visitedSq);
        }
        // Return -1 if oldSquare exceeds board values, or if square has already been visited
        else if (oldSquare > boardsize || visitedSq[oldSquare - 1] == true) {
            return -1;
        }

        // Change the value of the current square in visitedSq from false to true
        // This shows that the square has now been visited
        visitedSq[oldSquare - 1] = true;

        // Current Square is the previous square plus the roll
        int resultSquare = oldSquare + roll;

        // Lowest number of turns initialized at highest representable int
        int lowestTurns = Integer.MAX_VALUE;

        // If the result roll lands on a ladder, go to the corresponding square on the board
        for (int i = 0; i < ladders.length; i++) {
            if (resultSquare == ladders[i][0]) {
                resultSquare = ladders[i][1];
                break;
            }
        }
        // If the result roll lands on a snake, go to the corresponding square on the board
        for (int i = 0; i < snakes.length; i++) {
            if (resultSquare == snakes[i][0]) {
                resultSquare = snakes[i][1];
                break;
            }
        }

        // If the current square is the end square, return the number of visited squares
        if (resultSquare == boardsize) {
            // Set the end square equal to true to show that it has been reached
            visitedSq[boardsize - 1] = true;
            return countTurns(visitedSq);
        }

        // Recursive step
        // Iterates through the possible roles (1-6), calling rollResult recursively on each possible roll
        // Finds the roll with the lowest number of turns out of the 6
        for (int i = 1; i < 7; i++) {
            // Note- I got the .copyOf() fnc by looking up how to transfer the values from one array to another
            // I thought the .copyOf fnc would be 'cleaner' than using a for loop to add the values one at a time
            // boolean[] visitedSq2 = Arrays.copyOf(visitedSq, boardsize);
            int turns = rollResult(resultSquare, i, ladders, snakes, visitedSq, boardsize);
            // If winning is possible with the given roll and turns is lower than the current lowest number of turns, lowestTurns equals turns
            if (turns != -1 && turns < lowestTurns) {
                lowestTurns = turns;
            }
        }

        // If lowestTurns hasn't been changed (if end can't be reached) return -1
        if (lowestTurns == Integer.MAX_VALUE) {
            return -1;
        }
        // Return the lowest number of turns
        return lowestTurns;
    }

    // Method counts the number of turns taken by counting how many squares have been visited in boolean array
    // Assumes end square has been reached
    public static int countTurns(boolean[] visitedSquares) {
        // Number of turns taken initialized to 0
        int turns = 0;
        // Traverses through every square in boolean array visitedSquares
        for (int i = 0; i < visitedSquares.length; i++) {
            // If the square has been visited, increment turns by 1
            if (visitedSquares[i] == true) {
                turns++;
            }
        }
        // Returns the number of turns taken (subtract 1 to exclude the starting square)
        return turns - 1;
    }

}
