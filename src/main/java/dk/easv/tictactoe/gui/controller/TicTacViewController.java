
package dk.easv.tictactoe.gui.controller;

// Java imports
import java.io.File;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
// Project imports
import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author EASV
 */
public class TicTacViewController extends GameController implements Initializable
{
    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;
    private static final String TXT_PLAYER = "Player: ";
    private IGameBoard game;
    private int player = 0;
    private boolean hasEnded = false;
    private int roundNumber = 1;
    @FXML
    private static String player1Icon;
    @FXML
    private static String player2Icon;
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            player = game.getNextPlayer(player);
            if (game.play(c, r, player)) {
                if (!hasEnded)
                {
                    Button btn = (Button) event.getSource();
                    String xOrO = player == 0 ? player1Icon : player2Icon;
                    btn.setText(xOrO);
                    btn.setDisable(false);
                    setPlayer();
                    super.playClickSound();
                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    game.setScore(winner);
                    int playerOneScore = game.getCurrentPlayerScore();
                    int playerTwoScore = game.getOtherPlayerScore();
                    super.displayScoreboard(winner, event, playerOneScore, playerTwoScore, false);
                    hasEnded = true;
                }
            }
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleNewGame(ActionEvent event)
    {
        hasEnded = false;
        game.newGame();
        setPlayer();
        clearBoard();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        game = new GameBoard();
        setPlayer();
    }

    /**
     * Set the next player
     */
    private void setPlayer()
    {
        lblPlayer.setText(TXT_PLAYER + game.getCurrentPlayer());
    }

    public void setCurrentPlayer(Integer player){
        this.player = player;
    }

    public void setRoundNumber(int roundNumber){
        this.roundNumber = roundNumber;
    }

    public void setPlayerOneScore(int score){
        game.setCurrentPlayerScore(score);
    }

    public void setPlayerTwoScore(int score){
        game.setOtherPlayerScore(score);
    }

    public void setPlayerIcons(String player1Icon, String player2Icon) {
        this.player1Icon = player1Icon;
        this.player2Icon = player2Icon;
    }

}


