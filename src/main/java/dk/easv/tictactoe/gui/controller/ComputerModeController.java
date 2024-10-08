
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

    @FXML private GridPane gridPane;
    @FXML private String player1Icon;
    @FXML private String player2Icon;

    private IGameBoard game;
    private boolean hasEnded = false;

    private static final String TXT_PLAYER = "Player: ";
    private final int PLAYER = 0;
    private final int COMPUTER = 1;
    private final BestMoveCalculator calculator = new BestMoveCalculator();
    private final List<Button> buttons = new ArrayList<>();

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            if (game.play(r, c, PLAYER))
            {
                if (!hasEnded)
                {
                    Button btn = (Button) event.getSource();
                    playerMove(btn);
                    if (game.isGameOver())
                    {
                        gameOver();
                    }
                    else
                    {
                        enemyMove();
                        if (game.isGameOver())
                        {
                            gameOver();
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

    @FXML
    private void handleNewGame(ActionEvent event)
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
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                buttons.add((Button) node);
            }
        }
        setPlayer();
    }

    private void setPlayer()
    {
        lblPlayer.setText(TXT_PLAYER + game.getNextPlayer(PLAYER));
    }


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
    private void playerMove(Button btn){
        btn.setText(player1Icon);
        btn.setDisable(false);
        setPlayer();
        playClickSound();
    }

    private void enemyMove(){
        int[] bestMove = calculator.findBestMove(game.getBoard());
        int bestRow = bestMove[0];
        int bestCol = bestMove[1];
        game.play(bestRow, bestCol, COMPUTER);
        int buttonIndex = bestRow*3 + bestCol;
        playClickSound();
        buttons.get(buttonIndex).setText(player2Icon);
        buttons.get(buttonIndex).setDisable(false);
    }
    private void gameOver()
    {
        int winner = game.getWinner();
        displayWinner(winner);
        hasEnded = true;
    }
}