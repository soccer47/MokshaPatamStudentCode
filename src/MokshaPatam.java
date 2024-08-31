/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: [YOUR NAME HERE]
 *
 */

public class MokshaPatam {

    /**
     * TODO: Complete this function, fewestMoves(), to return the minimum number of moves
     *  to reach the final square on a board with the given size, ladders, and snakes.
     */
    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {

        // Lowest number of turns taken initialized at highest representable int
        int lowestTurns = Integer.MAX_VALUE;

        for (int i = 1; i < 7; i++) {
            // Initialize array of booleans of same length as number of total squares representing whether squares have been visited
            boolean[] visitedSq = new boolean[boardsize];
            // Set turns equal to the lowest roll path from rollResult with given roll
            int turns = rollResult(1, i, ladders, snakes, visitedSq, boardsize);
            // If winning is possible with the given roll and turns is lower than the current lowest number of turns, lowestTurns equals turns
            if (turns != -1 && turns < lowestTurns) {
                lowestTurns = turns;
            }
        }

        // If lowestTurns hasn't been changed (if end can't be reached) return -1
        if (lowestTurns == Integer.MAX_VALUE) {
            return -1;
        }
        // Return the lowest number of possible turns
        return lowestTurns;
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
            boolean[] visitedSq2 = new boolean[boardsize];
            for (int j = 0; j < boardsize; j++) {
                visitedSq2[i] = visitedSq[i];
            }
            int turns = rollResult(resultSquare, i, ladders, snakes, visitedSq2, boardsize);
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
