
package dk.easv.tictactoe.gui;

// Java imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import dk.easv.tictactoe.bll.ChatGPTAPI;


public class TicTacToe extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/intro_scene.fxml"));
        Parent scene = loader.load();
        stage.setScene(new Scene(scene));
        stage.setResizable(false);
        stage.setTitle("Tic Tac Toe");
        stage.centerOnScreen();

        stage.show();

    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
