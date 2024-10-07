
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

    public int getNextPlayer(int player)
    {
        player = player == 0 ? 1 : 0; // switch players
        currentPlayer = player;
        return player;
    }
    public boolean play(int col, int row, int player)
    {
        if (board[col][row] == -1) //check if the cell is empty
        {
            board[col][row] = player;
            currentTurn++;
            return true;
        }
        return false;
    }
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
    public int getWinner()
    {
        if (hasDrawn) return -1;
        return currentPlayer; // currentPlayer is the last player to play, so if !hasDrawn then he won
    }

    public void newGame() //sets up a blank board
    {
        board = new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
        currentTurn = 0;
        hasDrawn = false;
    }
}
