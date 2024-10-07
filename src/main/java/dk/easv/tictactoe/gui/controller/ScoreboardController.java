package dk.easv.tictactoe.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScoreboardController {
    @FXML
    Label lblRoundNumber = new Label();

    @FXML
    Label lblWinnerPlayer = new Label();

    @FXML
    Label lblPlayerOneName = new Label();

    @FXML
    Label lblPlayerOneScore = new Label();

    @FXML
    Label lblPlayerTwoName = new Label();

    @FXML
    Label lblPlayerTwoScore = new Label();

    public static void ScoreBoard() {
    }

    public void setLblRoundNumber(Integer roundNumber) {
        lblRoundNumber.setText(roundNumber.toString());
    }

    public void setLblWinnerPlayer(Integer winnerPlayer) {
        lblWinnerPlayer.setText(winnerPlayer.toString());
    }

    public void setLblPlayerOneName(String playerOneName) {
        lblPlayerOneName.setText(playerOneName);
    }

    public void setLblPlayerOneScore(Integer playerOneScore) {
        lblPlayerOneScore.setText(playerOneScore.toString());
    }

    public void setLblPlayerTwoName(String playerTwoName) {
        lblPlayerTwoName.setText(playerTwoName);
    }

    public void setLblPlayerTwoScore(Integer playerTwoScore) {
        lblPlayerTwoScore.setText(playerTwoScore.toString());
    }
}
