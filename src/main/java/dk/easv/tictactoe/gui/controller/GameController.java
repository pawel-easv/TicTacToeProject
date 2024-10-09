package dk.easv.tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class GameController {
    @FXML
    private Button btnNewGame;
    private int roundNumber = 1;
    @FXML
    private GridPane gridPane;
    protected void displayScoreboard(int winner, ActionEvent event, int playerOneScore, int playerTwoScore)
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
    protected void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
        }
    }
    protected void playClickSound() {
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
}
