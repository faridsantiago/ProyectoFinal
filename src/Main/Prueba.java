/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

/**
 *
 * @author IDEAPAD S330
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Prueba extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }
    @Override
    public void start(Stage ventana) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vista/Login.fxml"));
        Pane microventana = (Pane) loader.load();
        Scene escena =  new Scene(microventana);
        ventana.setScene(escena);
        ventana.show();
        

    }
}