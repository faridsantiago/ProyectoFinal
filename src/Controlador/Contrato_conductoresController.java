/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Conductores;
import Modelo.Vehiculo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author IDEAPAD S330
 */
public class Contrato_conductoresController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Conductores, ?> columFinal;

    @FXML
    private TableColumn<Conductores, ?> columID;

    @FXML
    private TableColumn<Conductores, ?> columInicio;

    @FXML
    private TableColumn<Conductores, ?> columLicencia;

    @FXML
    private TableColumn<Conductores, ?> columNombre;

    @FXML
    private TableColumn<Conductores, ?> columTipo;

    @FXML
    private TableView<Conductores> tb_contra_conduc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert columFinal != null : "fx:id=\"columFinal\" was not injected: check your FXML file 'Contrato_conductores.fxml'.";
        assert columID != null : "fx:id=\"columID\" was not injected: check your FXML file 'Contrato_conductores.fxml'.";
        assert columInicio != null : "fx:id=\"columInicio\" was not injected: check your FXML file 'Contrato_conductores.fxml'.";
        assert columLicencia != null : "fx:id=\"columLicencia\" was not injected: check your FXML file 'Contrato_conductores.fxml'.";
        assert columNombre != null : "fx:id=\"columNombre\" was not injected: check your FXML file 'Contrato_conductores.fxml'.";
        assert columTipo != null : "fx:id=\"columTipo\" was not injected: check your FXML file 'Contrato_conductores.fxml'.";
        assert tb_contra_conduc != null : "fx:id=\"tb_contra_conduc\" was not injected: check your FXML file 'Contrato_conductores.fxml'.";
        
        this.columID.setCellValueFactory(new PropertyValueFactory("ID"));
        this.columNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.columLicencia.setCellValueFactory(new PropertyValueFactory("Licencia"));
        this.columTipo.setCellValueFactory(new PropertyValueFactory("Tipo_de_contrato"));
        this.columInicio.setCellValueFactory(new PropertyValueFactory("Ininio_contrato"));
        this.columFinal.setCellValueFactory(new PropertyValueFactory("Fin_contrato"));
        
        Conductores c = new Conductores();
        ObservableList<Conductores> items = c.getConductoresDatos();
        this.tb_contra_conduc.setItems(items);

        
    }

}
