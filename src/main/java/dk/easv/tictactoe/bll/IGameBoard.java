
package dk.easv.tictactoe.bll;

/**
 *
 * @author EASV
 */
public interface IGameBoard
{
    int getNextPlayer(int player);

    /**
     * @return int Id of the curent player.
     */
    int getCurrentPlayer();

    /**
     * Attempts to let the current player play at the given coordinates. If the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    boolean play(int row, int col, int player);
    boolean isGameOver();
    int getWinner();
    void newGame();


    void setScore(int winner);

    int getOtherPlayerScore();

    int getCurrentPlayerScore();

    void setCurrentPlayerScore(int score);

    void setOtherPlayerScore(int score);

    int[][] getBoard();
}

