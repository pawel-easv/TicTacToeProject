
package dk.easv.tictactoe.gui.controller;
import java.io.File;
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
import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ComputerModeController  extends GameController implements Initializable
{

    @FXML private Label lblPlayer;
    @FXML private Button btnNewGame;
    @FXML private GridPane gridPane;
    @FXML private static String player1Icon;
    @FXML private static String player2Icon;

    private IGameBoard game;
    private int roundNumber = 1;
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
                        gameOver(event);
                    }
                    else
                    {
                        enemyMove();
                        if (game.isGameOver())
                        {
                            gameOver(event);
                        }
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
        for (Node node : gridPane.getChildren()) { // Iterates through every node in gridpane to find
            if (node instanceof Button) {          // buttons and adds them to list
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
        String message = "Player " + winner + " wins!!!";
        if (winner == -1)
        {
            message = "It's a draw :-(";
        }
        lblPlayer.setText(message);
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
        //int[] bestMove = calculator.findBestMove(game.getBoard(), COMPUTER);
        int[] bestMove = calculator.findBestMove(game.getBoard(), COMPUTER); // calculates the best move
        int bestRow = bestMove[0];
        int bestCol = bestMove[1];
        game.play(bestRow, bestCol, COMPUTER);
        int buttonIndex = bestRow*3 + bestCol; //calculates the button index based on the row and column
        playClickSound();
        buttons.get(buttonIndex).setText(player2Icon);
        buttons.get(buttonIndex).setDisable(false);
    }
    private void gameOver(ActionEvent event)
    {
        int winner = game.getWinner();
        game.setScore(winner);
        int playerOneScore = game.getCurrentPlayerScore();
        int playerTwoScore = game.getOtherPlayerScore();
        super.displayScoreboard(winner, event, playerOneScore, playerTwoScore, true);
        hasEnded = true;
    }
    public void setCurrentPlayer(Integer player){
        //this.player = player;
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