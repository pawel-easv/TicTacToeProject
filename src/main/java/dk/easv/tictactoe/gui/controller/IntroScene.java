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
    @FXML
    private Button startButton;

    @FXML
    protected void onStartButtonClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CharacterSelectionView.fxml"));
            Parent root = loader.load();
            CharacterSelectionController controller = loader.getController();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
    //@FXML
    //private ImageView grid;

    //@FXML
    //private ImageView introImage;

   // @FXML
   // public void initialize() {
//        File file = new File("src/main/resources/images/grid.png");

       /*Image image = new ImageIcon("src/main/resources/images/grid.png").getImage();
introImage*/
    }


    // Constructor to set up the GUI components
//    public TicTacToeIntro() {
//        // Create the main frame
//        JFrame frame = new JFrame("Tic-Tac-Toe Game");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(420, 540);  // Set the size of the window
//        frame.setLocationRelativeTo(null);  // Center the window
//
//        // Create a panel to hold components
//        JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//
//        // Create a label for the introduction
//        JLabel titleLabel = new JLabel("Welcome to Tic-Tac-Toe!", JLabel.CENTER);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        titleLabel.setForeground(Color.BLUE);
//
//        // Create a button to start the game
//        JButton startButton = new JButton("Start Game");
//        startButton.setFont(new Font("Arial", Font.PLAIN, 18));
//
//        // Action listener for the Start Game button
//        startButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                try {
////                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TicTacView.fxml"));
////                    Parent root = loader.load();
////                    TicTacViewController controller = loader.getController();
////
////                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
////                    stage.setScene(new Scene(root));
////                    stage.show();
////                } catch (IOException ex) {
////                    throw new RuntimeException(ex);
////                }
//
//            }
//        });
//
//        // Add components to the panel
//        panel.add(titleLabel, BorderLayout.CENTER);
//        panel.add(startButton, BorderLayout.SOUTH);
//
//        // Add panel to frame
//        frame.add(panel);
//
//        // Make the frame visible
//        frame.setVisible(true);
//    }



//}
