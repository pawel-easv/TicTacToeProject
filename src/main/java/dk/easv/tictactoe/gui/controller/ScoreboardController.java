package dk.easv.tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoreboardController {
    @FXML
    Label lblRoundNumber = new Label();

    @FXML
    Label lblResult = new Label();

    @FXML
    Label lblPlayerOneName = new Label();

    @FXML
    Label lblPlayerOneScore = new Label();

    @FXML
    Label lblPlayerTwoName = new Label();

    @FXML
    Label lblPlayerTwoScore = new Label();

    @FXML
    Button newGameButton = new Button();

    private int winner;

    private Stage stage;

    private Integer roundNumber;
    private Integer playerOneScore;
    private Integer playerTwoScore;
    private int gameMode;
    private final int SINGLEPLAYER = 0;
    private final int MULTIPLAYER = 1;
    public void initialize(){
        lblPlayerOneName.setText("Player 0");
        lblPlayerTwoName.setText("Player 1");

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLblRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
        lblRoundNumber.setText(roundNumber.toString());
    }

    public void setLblResult(int winner) {
        this.winner = winner;
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

        lblResult.setText(message);
    }

    public void setLblPlayerOneName(int playerOneName) {


    }

    public void setLblPlayerOneScore(Integer playerOneScore) {
        this.playerOneScore = playerOneScore;
        lblPlayerOneScore.setText(playerOneScore.toString());
    }

    public void setLblPlayerTwoName(int playerTwoName) {

    }

    public void setLblPlayerTwoScore(Integer playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
        lblPlayerTwoScore.setText(playerTwoScore.toString());
    }

    public void btnNewGameClicked(ActionEvent actionEvent) throws IOException {

        try {
            if(gameMode == MULTIPLAYER){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TicTacView.fxml"));
                Parent root = loader.load();
                TicTacViewController controller = loader.getController();
                controller.handleNewGame(actionEvent);
                controller.setCurrentPlayer(winner);
                controller.setRoundNumber(this.roundNumber + 1);
                controller.setPlayerOneScore(this.playerOneScore);
                controller.setPlayerTwoScore(this.playerTwoScore);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
            }
            else if(gameMode == SINGLEPLAYER){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ComputerModeView.fxml"));
                Parent root = loader.load();
                ComputerModeController controller = loader.getController();
                controller.handleNewGame(actionEvent);
                controller.setCurrentPlayer(winner);
                controller.setRoundNumber(this.roundNumber + 1);
                controller.setPlayerOneScore(this.playerOneScore);
                controller.setPlayerTwoScore(this.playerTwoScore);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
            }

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }
}
