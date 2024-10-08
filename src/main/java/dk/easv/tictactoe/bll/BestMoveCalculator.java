package dk.easv.tictactoe.bll;

public class BestMoveCalculator {

    private static class Move {
        private int row, col;

        public int getRow() {
            return row;
        }
        public int getCol() {
            return col;
        }
    }

    private int player = 1, opponent = 0;

    // Method to check if there are moves left
    public boolean isMovesLeft(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == -1) {
                    return true;
                }
            }
        }
        return false;
    }

    // Evaluate the board for win conditions
    public int evaluate(int[][] board) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == player) {
                    return +10;
                } else if (board[row][0] == opponent) {
                    return -10;
                }
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == player) {
                    return +10;
                } else if (board[0][col] == opponent) {
                    return -10;
                }
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == player) {
                return +10;
            } else if (board[0][0] == opponent) {
                return -10;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == player) {
                return +10;
            } else if (board[0][2] == opponent) {
                return -10;
            }
        }

        // No winner, return 0
        return 0;
    }

    // Minimax function
    public int minimax(int[][] board, int depth, boolean isMax) {
        int score = evaluate(board);

        // If the game has been won, return the score
        if (score == 10 || score == -10) {
            return score;
        }

        // If there are no moves left, it's a tie
        if (!isMovesLeft(board)) {
            return 0;
        }

        // Maximizing player's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == -1) {
                        // Make the move
                        board[i][j] = player;

                        best = Math.max(best, minimax(board, depth + 1, false));

                        // Undo the move
                        board[i][j] = -1;
                    }
                }
            }
            return best;
        } else { // Minimizing player's move
            int best = 1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == -1) {
                        board[i][j] = opponent;

                        best = Math.min(best, minimax(board, depth + 1, true));

                        // Undo the move
                        board[i][j] = -1;
                    }
                }
            }
            return best;
        }
    }

    // Public method to find the best move for the AI
    public int[] findBestMove(int[][] board) {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = player;

                    int moveVal = minimax(board, 0, false);

                    board[i][j] = -1;

                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return new int[]{bestMove.row, bestMove.col};
    }
    /*public static void main(String[] args) {
        int[][] board = {{-1,-1,1},{-1,0,1},{-1,-1,-1}};
        int[] bestMove = findBestMove(board);
    }*/
}
