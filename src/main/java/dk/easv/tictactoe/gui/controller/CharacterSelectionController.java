package dk.easv.tictactoe.gui.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class CharacterSelectionController {

    @FXML
    private ComboBox<String> player1ComboBox, player2ComboBox;

    private String player1Icon = "❌";
    private String player2Icon = "⭕";

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
        System.out.println(player1ComboBox.getValue());
        System.out.println(player2ComboBox.getValue());

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

    public void continueSelection(ActionEvent actionEvent) {
        this.player1Icon = player1ComboBox.getValue();
        this.player2Icon = player2ComboBox.getValue();
        System.out.println(player1Icon);
        System.out.println(player2Icon);
    }
}
