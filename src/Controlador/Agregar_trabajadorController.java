/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Modelo.Empleados;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IDEAPAD S330
 */
public class Agregar_trabajadorController implements Initializable {
   
    Connection conn = Conexion.getConnection();

    Statement st;
    ResultSet rs;
    
    @FXML
    private ComboBox<String> Combo_contrato;

    @FXML
    private ComboBox<String> Combo_tipoempleado;

    @FXML
    private TextField Txt_CC;

    @FXML
    private TextField Txt_arl;

    @FXML
    private TextField Txt_cajaCompensacion;

    @FXML
    private TextField Txt_edad;

    @FXML
    private TextField Txt_eps;

    @FXML
    private DatePicker Txt_finContrato;

    @FXML
    private DatePicker Txt_inicioContrato;

    @FXML
    private TextField Txt_nombre;

    @FXML
    private TextField Txt_telefono;

    @FXML
    private Button btn_aggconductor;

    @FXML
    private Button btn_cancelar;

    @FXML
    private TextField txt_direccion;

    @FXML
    void Agg_trabajador(ActionEvent event) throws SQLException {
        try {
            String nombre = this.Txt_nombre.getText();
            int CC = Integer.parseInt(this.Txt_CC.getText());
            int Edad = Integer.parseInt(this.Txt_edad.getText());
            String Direccion = this.txt_direccion.getText();
            int Telefono = Integer.parseInt(this.Txt_telefono.getText());
            String Tipo_de_trabajo = this.Combo_tipoempleado.getSelectionModel().getSelectedItem();
            String Tipo_contrato = this.Combo_contrato.getSelectionModel().getSelectedItem();
            String eps = this.Txt_eps.getText();
            String arl = this.Txt_arl.getText();
            LocalDate inicio = this.Txt_inicioContrato.getValue();
            LocalDate Fin = this.Txt_finContrato.getValue();
            String Compensacion = this.Txt_cajaCompensacion.getText();
            
            String Mysql="INSERT INTO Empleados(Nombre,CC,Edad,Direccion,Telefono,Tipo_de_trabajo,Tipo_de_contrato,Inicio_contrato,Fin_contrato,EPS,ARL,Caja_compensacion) values('"+nombre+"','"+CC+"','"+Edad+"','"+Direccion+"','"+Telefono+"','"+Tipo_de_trabajo+"','"+Tipo_contrato+"','"+inicio+"','"+Fin+"','"+eps+"','"+arl+"','"+Compensacion+"')";
            
            st = conn.createStatement();
            st.execute(Mysql);
           
            JOptionPane.showMessageDialog(null, "Empleado agregado");
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            
            
            
        } catch (NumberFormatException e) {
            System.out.println("dsssssssssssdsdssdsdds");
            JOptionPane.showMessageDialog(null, "no pudo ser agregado");
        }
        
        
    }

    @FXML
    void Cancelar(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Combo_contrato.getItems().setAll("Termino fijo", "Indefinido", "De obra o labor");
        Combo_tipoempleado.getItems().setAll("Aseador","Vigilante","Asistente de ruta","Cajero","Mecanico");
        assert Txt_CC != null : "fx:id=\"Txt_CC\" was not injected: check your FXML file 'Agregar_trabajador.fxml'.";
        assert Txt_arl != null : "fx:id=\"Txt_arl\" was not injected: check your FXML file 'Agregar_trabajador.fxml'.";
        assert Txt_cajaCompensacion != null : "fx:id=\"Txt_cajaCompensacion\" was not injected: check your FXML file 'Agregar_trabajador.fxml'.";
        assert Txt_edad != null : "fx:id=\"Txt_edad\" was not injected: check your FXML file 'Agregar_trabajador.fxml'.";
        assert Txt_eps != null : "fx:id=\"Txt_eps\" was not injected: check your FXML file 'Agregar_trabajador.fxml'.";
        assert Txt_finContrato != null : "fx:id=\"Txt_finContrato\" was not injected: check your FXML file 'Agregar_trabajador.fxml'.";
        assert Txt_inicioContrato != null : "fx:id=\"Txt_inicioContrato\" was not injected: check your FXML file 'Agregar_trabajador.fxml'.";
        assert Txt_nombre != null : "fx:id=\"Txt_nombre\" was not injected: check your FXML file 'Agregar_trabajador.fxml'.";
        assert Txt_telefono != null : "fx:id=\"Txt_telefono\" was not injected: check your FXML file 'Agregar_trabajador.fxml'.";
        assert btn_aggconductor != null : "fx:id=\"btn_aggconductor\" was not injected: check your FXML file 'Agregar_trabajador.fxml'.";
        assert btn_cancelar != null : "fx:id=\"btn_cancelar\" was not injected: check your FXML file 'Agregar_trabajador.fxml'.";
        assert txt_direccion != null : "fx:id=\"txt_direccion\" was not injected: check your FXML file 'Agregar_trabajador.fxml'.";

        
    }

}
