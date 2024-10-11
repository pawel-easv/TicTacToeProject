package dk.easv.tictactoe.gui.controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CharacterSelectionController {

    @FXML
    private ComboBox<String> player1ComboBox, player2ComboBox;
    @FXML
    private Label lblPlayerOneText;
    @FXML
    private Label lblPlayerTwoText;
    private String player1Icon = "❌";
    private String player2Icon = "⭕";
    private final int MULTIPLAYER = 1;
    private final int SINGLEPLAYER = 0;
    private int gameMode;

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }
    @FXML
    public void initialize() {
        // List of emoji options
        String[] emojiOptions = {"❌", "⭕", "😊", "😂", "😎", "🥳", "🤖", "👻", "🐱", "🌟"};

        // Set options in ComboBoxes
        player1ComboBox.setItems(FXCollections.observableArrayList(emojiOptions));
        player2ComboBox.setItems(FXCollections.observableArrayList(emojiOptions));

        // Set default selections
        player1ComboBox.setValue(player1Icon);
        player2ComboBox.setValue(player2Icon);
        // Set listener to update player icons when a selection is made
        player1ComboBox.setOnAction(event -> player1Icon = player1ComboBox.getValue());
        player2ComboBox.setOnAction(event -> player2Icon = player2ComboBox.getValue());
    }

    public String getPlayer1Icon() {
        return player1Icon;
    }

    public String getPlayer2Icon() {
        return player2Icon;
    }
    public void setPlayersLabels() {
        lblPlayerOneText.setText("Player");
        lblPlayerTwoText.setText("Computer");
    }
    public void continueSelection(ActionEvent actionEvent) throws IOException {
        try {
            // Update player icons based on ComboBox selections
            this.player1Icon = player1ComboBox.getValue();
            this.player2Icon = player2ComboBox.getValue();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Set the new scene and show the stage
            if(gameMode == MULTIPLAYER) {
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/views/multiplayer_mode.fxml"));
                Parent singlePlayerRoot= loader2.load();
                TicTacViewController controller2 = loader2.getController();
                controller2.setPlayerIcons(player1Icon, player2Icon);
                stage.setScene(new Scene(singlePlayerRoot));
            }
            else{
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/views/singleplayer_mode.fxml"));
                Parent multiPlayerRoot = loader1.load();;
                ComputerModeController controller1 = loader1.getController();
                controller1.setPlayerIcons(player1Icon, player2Icon);
                stage.setScene(new Scene(multiPlayerRoot));
            }
            stage.show();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }


}