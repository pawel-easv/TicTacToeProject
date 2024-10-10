
package dk.easv.tictactoe.bll;

/**
 *
 * @author EASV
 */
public class GameBoard implements IGameBoard
{
    private int[][] board = {{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}};
    private int currentPlayer;
    private int currentTurn = 0;
    private boolean hasDrawn = false;

    private int currentPlayerScore = 0;
    private int otherPlayerScore = 0;

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer(int player)
    {
        player = player == 0 ? 1 : 0;
        currentPlayer = player;
        return player;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public int getCurrentPlayer()
    {
        return currentPlayer;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int row, int col, int player)
    {
        if (board[row][col] == -1) //check if the cell is empty
        {
            board[row][col] = player;
            currentTurn++;
            return true;
        }
        return false;
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    public boolean isGameOver()
    {
        for (int i = 0; i<3; i++)
        {
            if(board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != -1) // check if someone won horizontally
            {
                return true;
            }
            else if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] != -1)// check if someone won vertically
            {
                return true;
            }
        }
        // check the diagonals
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != -1)
        {
            return true;
        }
        if (board[0][2] == board [1][1] && board[0][2] == board[2][0] && board[0][2] != -1)
        {
            return true;
        }
        // if the whole board is filled and none of the previous statements are true, then return draw
        if(currentTurn >= 9)
        {
            hasDrawn = true;
            return true;
        }
        return false;
    }
    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        if (hasDrawn) return -1;
        return getNextPlayer(currentPlayer);
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        board = new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
        currentTurn = 0;
        hasDrawn = false;
    }
    public int[][] getBoard(){
        return board;
    }

    public int getCurrentPlayerScore(){
        return currentPlayerScore;
    }

    public int getOtherPlayerScore(){
        return otherPlayerScore;
    }

    public void setCurrentPlayerScore(int score){
            this.currentPlayerScore = score;
    }

    public void setOtherPlayerScore(int score){
            this.otherPlayerScore = score;
    }

    public void setScore(int winner){
        if(winner != -1){
            if(winner == 0){
                this.currentPlayerScore += 1;
            } else if (winner == 1){
                this.otherPlayerScore += 1;
            }
        }
    }
}
