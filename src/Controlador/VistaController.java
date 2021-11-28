package Controlador;

import com.sun.javafx.logging.PlatformLogger;
import com.sun.javafx.logging.PlatformLogger.Level;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.lang.System.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class VistaController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Btn_con_empleado;

    @FXML
    private Button Btn_ganancias;

    @FXML
    private Button Btn_menu;

    @FXML
    private Button Btn_rutas;

    @FXML
    private Button Btn_vehiculos;

    @FXML
    private StackPane Content_area;

    @FXML
    private Label Usuario_nombbre;

    @FXML
    private Button btn_administrativa;

    @FXML
    private Button btn_conductores;

    @FXML
    private Button cont_conductor;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        assert Btn_con_empleado != null : "fx:id=\"Btn_con_empleado\" was not injected: check your FXML file 'Vista.fxml'.";
        assert Btn_ganancias != null : "fx:id=\"Btn_ganancias\" was not injected: check your FXML file 'Vista.fxml'.";
        assert Btn_menu != null : "fx:id=\"Btn_menu\" was not injected: check your FXML file 'Vista.fxml'.";
        assert Btn_rutas != null : "fx:id=\"Btn_rutas\" was not injected: check your FXML file 'Vista.fxml'.";
        assert Btn_vehiculos != null : "fx:id=\"Btn_vehiculos\" was not injected: check your FXML file 'Vista.fxml'.";
        assert Content_area != null : "fx:id=\"Content_area\" was not injected: check your FXML file 'Vista.fxml'.";
        assert Usuario_nombbre != null : "fx:id=\"Usuario_nombbre\" was not injected: check your FXML file 'Vista.fxml'.";
        assert btn_administrativa != null : "fx:id=\"btn_administrativa\" was not injected: check your FXML file 'Vista.fxml'.";
        assert btn_conductores != null : "fx:id=\"btn_conductores\" was not injected: check your FXML file 'Vista.fxml'.";
        assert cont_conductor != null : "fx:id=\"cont_conductor\" was not injected: check your FXML file 'Vista.fxml'.";

    }

    public void empleado(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../Vista/Contrato_empleado.fxml"));
        Content_area.getChildren().removeAll();
        Content_area.getChildren().setAll(fxml);
    }

    public void Home(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../Vista/Home.fxml"));
        Content_area.getChildren().removeAll();
        Content_area.getChildren().setAll(fxml);
    }

    public void contra_conductores(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../Vista/Contrato_conductores.fxml"));
        Content_area.getChildren().removeAll();
        Content_area.getChildren().setAll(fxml);
    }

    public void administrativa(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../Vista/Administrativa.fxml"));
        Content_area.getChildren().removeAll();
        Content_area.getChildren().setAll(fxml);
    }

    public void conductores(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../Vista/Conductores.fxml"));
        Content_area.getChildren().removeAll();
        Content_area.getChildren().setAll(fxml);
    }

    public void ganancias(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../Vista/Ganancias.fxml"));
        Content_area.getChildren().removeAll();
        Content_area.getChildren().setAll(fxml);
    }

    public void rutas(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../Vista/Rutas.fxml"));
        Content_area.getChildren().removeAll();
        Content_area.getChildren().setAll(fxml);
    }

    public void vehiculos(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../Vista/Vehiculos.fxml"));
        Content_area.getChildren().removeAll();
        Content_area.getChildren().setAll(fxml);
    }
    
 
}
