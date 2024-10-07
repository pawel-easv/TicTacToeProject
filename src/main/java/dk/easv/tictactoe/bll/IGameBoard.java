
package dk.easv.tictactoe.bll;

/**
 *
 * @author EASV
 */
public interface IGameBoard
{
    int getNextPlayer(int player);
    boolean play(int col, int row, int player);
    boolean isGameOver();
    int getWinner();
    void newGame();
}

