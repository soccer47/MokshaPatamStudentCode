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

        int[][] visitedSq = new int[boardsize][boardsize];



        return 0;
    }

    public static int[][] rollResult(int oldSquare, int roll, int[][] ladders, int[][] snakes, int[][] visitedSq) {
        // If the current square is the end square or has already been visited, return the visited squares
        if (oldSquare == visitedSq.length * visitedSq.length || oldSquare == visitedSq[oldSquare / 10][oldSquare % 10]) {
            return visitedSq;
        }

        // Change the value of the current square in visitedSq from 0 to 1
        // This shows that the square has now been visited
        visitedSq[oldSquare / 10][oldSquare % 10] = 1;

        int resultSquare = oldSquare + roll;

        for (int i = 0; i < ladders.length; i++) {
            if (resultSquare == ladders[i][1]) {
                return rollResult(ladders[i][2], 1, ladders, snakes, visitedSq);
            }
        }
        for (int i = 0; i < snakes.length; i++) {
            if (resultSquare == snakes[i][1]) {
                return rollResult(snakes[i][2], 1, ladders, snakes, visitedSq);
            }
        }

        return rollResult(resultSquare, 1, ladders, snakes, visitedSq);
    }
}
