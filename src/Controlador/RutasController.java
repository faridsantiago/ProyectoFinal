/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Rutas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author IDEAPAD S330
 */
public class RutasController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TableColumn<Rutas, Integer> columID;

    @FXML
    private TableColumn<Rutas, String> columRecorrido;

    @FXML
    private TableView<Rutas> tablaRutas;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert columID != null : "fx:id=\"columID\" was not injected: check your FXML file 'Rutas.fxml'.";
        assert columRecorrido != null : "fx:id=\"columRecorrido\" was not injected: check your FXML file 'Rutas.fxml'.";
        assert tablaRutas != null : "fx:id=\"tablaRutas\" was not injected: check your FXML file 'Rutas.fxml'.";
        
        this.columID.setCellValueFactory(new PropertyValueFactory("ID"));
        this.columRecorrido.setCellValueFactory(new PropertyValueFactory("Recorrido"));
        Rutas r = new Rutas();

        ObservableList<Rutas> items = r.getRutasdatos();
        this.tablaRutas.setItems(items);
        //System.out.println(items);
        
    }    
    
}
