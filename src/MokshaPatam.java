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

        // Initialize
        boolean[][] visitedSq = new boolean[boardsize][boardsize];
        // Lowest number of turns taken starts at
        int lowestTurns = Integer.MAX_VALUE;

        for (int i = 1; i < 7; i++) {
            int turns = rollResult(1, i, ladders, snakes, visitedSq);
            if (turns == -1 || turns > lowestTurns) {
                continue;
            }
            lowestTurns = turns;
        }
        return lowestTurns;
    }

    public static int rollResult(int oldSquare, int roll, int[][] ladders, int[][] snakes, boolean[][] visitedSq) {
        // If the current square is the end square or has already been visited, return the visited squares
        if (oldSquare == visitedSq.length * visitedSq.length || visitedSq[oldSquare / 10][oldSquare % 10] == true) {
            return countTurns(visitedSq);
        }

        // Change the value of the current square in visitedSq from 0 to 1
        // This shows that the square has now been visited
        visitedSq[oldSquare / 10][oldSquare % 10] = true;

        int resultSquare = oldSquare + roll;
        int lowestTurns = Integer.MAX_VALUE;
        boolean[][] lowestPath = new boolean[visitedSq.length][visitedSq.length];

        for (int i = 0; i < ladders.length; i++) {
            if (resultSquare == ladders[i][1]) {
                resultSquare = ladders[i][2];
            }
        }
        for (int i = 0; i < snakes.length; i++) {
            if (resultSquare == snakes[i][1]) {
                resultSquare = snakes[i][2];
            }
        }

        for (int i = 1; i < 7; i++) {
            int turns = rollResult(resultSquare, i, ladders, snakes, visitedSq);
            if (turns == -1 || turns > lowestTurns) {
                continue;
            }
            lowestTurns = turns;
        }
        return lowestTurns;
    }

    // Method counts the number of turns taken by counting how many squares have been visited in boolean array
    public static int countTurns(boolean[][] visitedSquares) {

        // If the end square hasn't been reached, return -1
        if (visitedSquares[visitedSquares.length][visitedSquares.length] == false) {
            return -1;
        }

        // Number of turns taken initialized to 0
        int turns = 0;
        // Traverses through every square in boolean array visitedSquares
        for (int j = 0; j < visitedSquares.length; j++) {
            for (int k = 0; k < visitedSquares.length; k++) {
                // If square has been visited, increase turns by 1
                if (visitedSquares[j][k] == true) {
                    turns++;
                }
            }
        }

        // Returns the number of turns taken
        return turns;
    }

}
