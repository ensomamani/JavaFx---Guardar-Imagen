/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guardarimagen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author w7
 */

public class Main extends Application{
    Stage primaryStage = null;
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SaveImage.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        primaryStage = stage;       
    }
    public static void main(String[] args) {
        launch(args);
    }

}
