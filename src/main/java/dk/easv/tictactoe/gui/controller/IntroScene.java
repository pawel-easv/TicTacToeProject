package dk.easv.tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//12345
public class IntroScene {
    @FXML private Button btnMultiPlayer;
    @FXML private Button btnSinglePlayer;
    private final int MULTIPLAYER = 1;
    private final int SINGLEPLAYER = 0;
    @FXML
    protected void handleMultiPlayerButtonClicked(ActionEvent event) {
        setNewStage(MULTIPLAYER, event);
    }

    @FXML
    protected void handleSinglePlayerButtonClick(ActionEvent event) {
        setNewStage(SINGLEPLAYER, event);

    }
    private void setNewStage(int gameMode, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CharacterSelectionView.fxml"));
            Parent root = loader.load();
            CharacterSelectionController controller = loader.getController();
            controller.setGameMode(gameMode);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    }

