
package dk.easv.tictactoe.gui.controller;

// Java imports
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

/**
 *
 * @author EASV
 */
public class TicTacViewController implements Initializable
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
                    String xOrO = player == 0 ? "X" : "O";
                    btn.setText(xOrO);
                    setPlayer();
                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    game.setScore(winner);
                    int playerOneScore = game.getCurrentPlayerScore();
                    int playerTwoScore = game.getOtherPlayerScore();
                    displayScoreboard(winner, event, playerOneScore, playerTwoScore);
                    hasEnded = true;
                }
            }
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    public void handleNewGame(ActionEvent event)
    {
        hasEnded = false;
        game.newGame();
        setPlayer();
        clearBoard();
    }

    /**
     * Initializes a new controller
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param rb
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
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


    /**
     * Finds a winner or a draw and displays a message based
     * @param winner int
     */
    private void displayScoreboard(int winner, ActionEvent event, int playerOneScore, int playerTwoScore)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/scoreboard.fxml"));
            Parent root = loader.load();
            ScoreboardController controller = loader.getController();

            Stage currentStage = (Stage) btnNewGame.getScene().getWindow();

            controller.setStage((Stage) currentStage);

            controller.setLblRoundNumber(this.roundNumber);

//            controller.setLblPlayerOneName(game.getCurrentPlayer());
//            controller.setLblPlayerTwoName(game.getNextPlayer(player));

            controller.setLblPlayerOneScore(playerOneScore);
            controller.setLblPlayerTwoScore(playerTwoScore);

            controller.setLblResult(winner);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the game board in the GUI
     */
    private void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
        }
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
}


