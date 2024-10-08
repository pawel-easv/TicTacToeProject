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

    public int minimax(int[][] board, int depth, boolean isMax) {
        int score = evaluate(board);

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

                        best = Math.max(best, minimax(board, depth + 1, false));
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

                        best = Math.min(best, minimax(board, depth + 1, true));

                        board[i][j] = -1;
                    }
                }
            }
            return best;
        }
    }
    public int[] findBestMove(int[][] board, int computer) {
        int bestVal = -1000;
        this.computer = computer;
        this.player = computer == 0 ? 1 : 0;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = computer;

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
