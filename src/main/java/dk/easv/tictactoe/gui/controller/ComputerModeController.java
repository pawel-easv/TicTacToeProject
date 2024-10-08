
package dk.easv.tictactoe.gui.controller;

// Java imports
import java.io.File;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dk.easv.tictactoe.bll.BestMoveCalculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
// Project imports
import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author EASV
 */
public class ComputerModeController implements Initializable
{
    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;
    @FXML private Button btn4;
    @FXML private Button btn5;
    @FXML private Button btn6;
    @FXML private Button btn7;
    @FXML private Button btn8;
    @FXML private Button btn9;
    private List<Button> buttons = new ArrayList<>();
    @FXML
    private GridPane gridPane;
    private static final String TXT_PLAYER = "Player: ";
    private IGameBoard game;
    private BestMoveCalculator calculator = new BestMoveCalculator();
    private int player = 0;
    private int ai = 1;
    private boolean hasEnded = false;
    @FXML
    private String player1Icon;
    @FXML
    private String player2Icon;
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            if (game.play(r, c, player)) {
                if (!hasEnded)
                {
                    Button btn = (Button) event.getSource();
                    btn.setText(player1Icon);
                    btn.setDisable(false);
                    setPlayer();
                    playClickSound();
                    if (game.isGameOver()) {
                        int winner = game.getWinner();
                        displayWinner(winner);
                        hasEnded = true;
                    }
                    else{
                        int[] bestMove = calculator.findBestMove(game.getBoard());
                        int bestRow = bestMove[0];
                        int bestCol = bestMove[1];
                        game.play(bestRow, bestCol, ai);
                        int buttonIndex = bestRow*3 + bestCol;
                        playClickSound();
                        buttons.get(buttonIndex).setText(player2Icon);
                        buttons.get(buttonIndex).setDisable(false);
                        if (game.isGameOver()) {
                            int winner = game.getWinner();
                            displayWinner(winner);
                            hasEnded = true;
                        }
                    }
                }
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void playClickSound() {
        try {
            // Load the sound file
            String soundPath = "src/main/resources/sounds/btnClick.mp3";
            Media sound = new Media(new File(soundPath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);

            // Play the sound
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }

    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event)
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
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                buttons.add((Button) node);
            }
        }
        setPlayer();
    }

    /**
     * Set the next player
     */
    private void setPlayer()
    {
        lblPlayer.setText(TXT_PLAYER + game.getNextPlayer(player));
    }


    /**
     * Finds a winner or a draw and displays a message based
     * @param winner
     */
    private void displayWinner(int winner)
    {
        String message = "";
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";
                break;

            default:
                message = "Player " + winner + " wins!!!";
                break;
        }
        lblPlayer.setText(message);
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

    public void setPlayerIcons(String player1Icon, String player2Icon) {
        this.player1Icon = player1Icon;
        this.player2Icon = player2Icon;
    }
}