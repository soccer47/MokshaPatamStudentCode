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

        // Initialize array of booleans of same length as number of total squares representing whether squares have been visited
        boolean[] visitedSq = new boolean[boardsize * boardsize];
        // Lowest number of turns taken initialized at highest representable int
        int lowestTurns = Integer.MAX_VALUE;

        for (int i = 1; i < 7; i++) {
            int turns = rollResult(1, i, ladders, snakes, visitedSq, boardsize);
            if (turns == -1 || turns > lowestTurns) {
                continue;
            }
            lowestTurns = turns;
        }

        // If lowestTurns hasn't been changed (if end can't be reached) return -1
        if (lowestTurns == Integer.MAX_VALUE) {
            return -1;
        }
        // Return the lowest number of possible turns
        return lowestTurns;
    }

    public static int rollResult(int oldSquare, int roll, int[][] ladders, int[][] snakes, boolean[] visitedSq, int boardsize) {
        // If the current square is the end square or has already been visited, return the visited squares
        if (oldSquare == boardsize * boardsize || visitedSq[oldSquare - 1] == true) {
            return countTurns(visitedSq);
        }

        // Change the value of the current square in visitedSq from false to true
        // This shows that the square has now been visited
        visitedSq[oldSquare - 1] = true;

        int resultSquare = oldSquare + roll;
        // Lowest number of turns initialized at highest representable int
        int lowestTurns = Integer.MAX_VALUE;

        for (int i = 0; i < ladders.length; i++) {
            if (resultSquare == ladders[i][0]) {
                resultSquare = ladders[i][1];
                break;
            }
        }
        for (int i = 0; i < snakes.length; i++) {
            if (resultSquare == snakes[i][0]) {
                resultSquare = snakes[i][1];
                break;
            }
        }

        for (int i = 1; i < 7; i++) {
            int turns = rollResult(resultSquare, i, ladders, snakes, visitedSq, boardsize);
            // If the given board has a greater number of turns than lowestTurns or doesn't reach the end square, continue
            if (turns == -1 || turns > lowestTurns) {
                continue;
            }
            // Otherwise if the given number of turns is lower, lowestTurns is set equal to turns
            lowestTurns = turns;
        }

        // Return the lowest number of turns (subtract 1 to exclude the starting square)
        return lowestTurns - 1;
    }

    // Method counts the number of turns taken by counting how many squares have been visited in boolean array
    public static int countTurns(boolean[] visitedSquares) {
        // If the end square hasn't been reached, return -1
        if (visitedSquares[visitedSquares.length - 1] == false) {
            return -1;
        }

        // Number of turns taken initialized to 0
        int turns = 0;
        // Traverses through every square in boolean array visitedSquares
        for (int i = 0; i < visitedSquares.length; i++) {
            if (visitedSquares[i] == true) {
                turns++;
            }
        }
        // Returns the number of turns taken
        return turns;
    }

}
