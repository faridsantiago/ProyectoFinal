/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Conductores;
import Modelo.Empleados;
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
public class Contrato_empleadoController implements Initializable {
     @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Empleados, ?> columFinal;

    @FXML
    private TableColumn<Empleados, ?> columID;

    @FXML
    private TableColumn<Empleados, ?> columInicio;

    @FXML
    private TableColumn<Empleados, ?> columNombre;

    @FXML
    private TableColumn<Empleados, ?> columTipo;

    @FXML
    private TableView<Empleados> tb_con_emp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert columFinal != null : "fx:id=\"columFinal\" was not injected: check your FXML file 'Contrato_empleado.fxml'.";
        assert columID != null : "fx:id=\"columID\" was not injected: check your FXML file 'Contrato_empleado.fxml'.";
        assert columInicio != null : "fx:id=\"columInicio\" was not injected: check your FXML file 'Contrato_empleado.fxml'.";
        assert columNombre != null : "fx:id=\"columNombre\" was not injected: check your FXML file 'Contrato_empleado.fxml'.";
        assert columTipo != null : "fx:id=\"columTipo\" was not injected: check your FXML file 'Contrato_empleado.fxml'.";
        assert tb_con_emp != null : "fx:id=\"tb_con_emp\" was not injected: check your FXML file 'Contrato_empleado.fxml'.";
        
       this.columID.setCellValueFactory(new PropertyValueFactory("ID"));
       this.columNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
       this.columTipo.setCellValueFactory(new PropertyValueFactory("Tipo_de_contrato"));
       this.columInicio.setCellValueFactory(new PropertyValueFactory("Inicio_contrato"));
       this.columFinal.setCellValueFactory(new PropertyValueFactory("Fin_contrato"));
       
        Empleados c = new Empleados();
        ObservableList<Empleados> items = c.getEmpleadosdatos();
        this.tb_con_emp.setItems(items);

       
    }

}
