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

    private int computer = 1;
    private int player = 0;

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
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == computer) {
                    return +10;
                } else if (board[row][0] == player) {
                    return -10;
                }
            }
        }


        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == computer) {
                    return +10;
                } else if (board[0][col] == player) {
                    return -10;
                }
            }
        }
        //check if someone won in diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == computer) {
                return +10;
            } else if (board[0][0] == player) {
                return -10;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == computer) {
                return +10;
            } else if (board[0][2] == player) {
                return -10;
            }
        }

        return 0;
    }
    // This method evaluates move, it simulates potential continuation till the game is ended by
    // one of the sides, and returns the best possible outcome
    public int minimax(int[][] board, boolean isMax) {
        int score = evaluate(board); // check if game is over, if not simulates next move

        if (score == 10 || score == -10) {
            return score;
        }
        if (!isMovesLeft(board)) {
            return 0;
        }
        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == -1) {
                        board[i][j] = computer;
                        best = Math.max(best, minimax(board, false)); // simulates the move and then deletes it
                        board[i][j] = -1;
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == -1) {
                        board[i][j] = player;

                        best = Math.min(best, minimax(board,true));

                        board[i][j] = -1;
                    }
                }
            }
            return best;
        }
    }
    public int[] findBestMove(int[][] board, int computer) {
        int bestVal = Integer.MIN_VALUE;
        this.computer = computer;
        this.player = computer == 0 ? 1 : 0;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = computer;

                    int moveVal = minimax(board, false); // evaluates the value of each move

                    board[i][j] = -1; // resets the board

                    if (moveVal > bestVal) { // checks if the value of current move is higher
                        bestMove.row = i;   // than current highest value, if yes,
                        bestMove.col = j;  // sets the highest value to the current move's value
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
