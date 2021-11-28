/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Conductores;
import Modelo.Conexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IDEAPAD S330
 */
public class Registrar_conductorController implements Initializable {

    Connection conn = Conexion.getConnection();

    Statement st;
    ResultSet rs;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker Campo_fin_contrato;

    @FXML
    private DatePicker Campo_inicio_contrato;

    @FXML
    private ComboBox<String> Combo_contrato;

    @FXML
    private TextField Txt_arl;

    @FXML
    private TextField Txt_cc_trabajador;

    @FXML
    private TextField Txt_compensacion;

    @FXML
    private TextField Txt_conductor;

    @FXML
    private TextField Txt_eps;

    @FXML
    private TextField Txt_telefono;

    @FXML
    private Button btn_aggconductor;

    @FXML
    private Button btn_elimconductor;

    @FXML
    private TextField Txt_licencia;

    @FXML
    private TextField Txt_edad;

    @FXML
    private Button btn_cancelar;

    @FXML
    void Accion_combo(ActionEvent event) throws SQLException {

    }

    @FXML
    void Agg_conductor(ActionEvent event) throws SQLException {
        String nombre = this.Txt_conductor.getText();
        int CC = Integer.parseInt(this.Txt_cc_trabajador.getText());
        int Licencia = Integer.parseInt(this.Txt_licencia.getText());
        int Telefono = Integer.parseInt(this.Txt_telefono.getText());
        String Tipo_contrato = this.Combo_contrato.getSelectionModel().getSelectedItem();
        String EPS = this.Txt_eps.getText();
        String ARL = this.Txt_arl.getText();
        LocalDate Inicio = this.Campo_inicio_contrato.getValue();
        LocalDate Fin = this.Campo_fin_contrato.getValue();
        String Caja = this.Txt_compensacion.getText();
        int Edad = Integer.parseInt(this.Txt_edad.getText());
        
        try {
            String MSQL = "Insert into Conductores(Nombre,CC,Edad,Licencia,Telefono,Tipo_de_contrato,Inicio_contrato,Fin_contrato,EPS,ARL,Caja_compensacion) values('" + nombre + "','" + CC + "','" + Edad + "','" + Licencia + "','" + Telefono + "','" + Tipo_contrato + "','" + Inicio + "','" + Fin + "','" + EPS + "','" + ARL + "','" + Caja + "')";

            st = conn.createStatement();
            st.execute(MSQL);
            JOptionPane.showMessageDialog(null, "Conductor agregado");
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            System.out.println("dsssssssssssdsdssdsdds");
            JOptionPane.showMessageDialog(null, "no pudo ser agregado");

        }

        /*if (Txt_conductor.getText().isEmpty() || Txt_cc_trabajador.getText().isEmpty() || Txt_licencia.getText().isEmpty() || Txt_telefono.getText().isEmpty()
                || Combo_contrato.getSelectionModel().getSelectedItem()==null || Txt_eps.getText().isEmpty() || Txt_arl.getText().isEmpty() || Campo_inicio_contrato.getValue() == null || Campo_fin_contrato.getValue() == null
                || Txt_compensacion.getText().isEmpty()||Txt_edad.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Deben estar todos los datos llenos");
            alert.showAndWait();

        } else {
           

        }*/
    }

    @FXML
    void Cancelar(ActionEvent event) {
        //JOptionPane.showMessageDialog(null, "Proceso cancelado");
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
        assert Campo_fin_contrato != null : "fx:id=\"Campo_fin_contrato\" was not injected: check your FXML file 'Registrar_conductor.fxml'.";
        assert Campo_inicio_contrato != null : "fx:id=\"Campo_inicio_contrato\" was not injected: check your FXML file 'Registrar_conductor.fxml'.";
        assert Combo_contrato != null : "fx:id=\"Combo_contrato\" was not injected: check your FXML file 'Registrar_conductor.fxml'.";
        assert Txt_arl != null : "fx:id=\"Txt_arl\" was not injected: check your FXML file 'Registrar_conductor.fxml'.";
        assert Txt_cc_trabajador != null : "fx:id=\"Txt_cc_trabajador\" was not injected: check your FXML file 'Registrar_conductor.fxml'.";
        assert Txt_compensacion != null : "fx:id=\"Txt_compensacion\" was not injected: check your FXML file 'Registrar_conductor.fxml'.";
        assert Txt_conductor != null : "fx:id=\"Txt_conductor\" was not injected: check your FXML file 'Registrar_conductor.fxml'.";
        assert Txt_edad != null : "fx:id=\"Txt_edad\" was not injected: check your FXML file 'Registrar_conductor.fxml'.";
        assert Txt_eps != null : "fx:id=\"Txt_eps\" was not injected: check your FXML file 'Registrar_conductor.fxml'.";
        assert Txt_licencia != null : "fx:id=\"Txt_licencia\" was not injected: check your FXML file 'Registrar_conductor.fxml'.";
        assert Txt_telefono != null : "fx:id=\"Txt_telefono\" was not injected: check your FXML file 'Registrar_conductor.fxml'.";
        assert btn_aggconductor != null : "fx:id=\"btn_aggconductor\" was not injected: check your FXML file 'Registrar_conductor.fxml'.";
        assert btn_cancelar != null : "fx:id=\"btn_cancelar\" was not injected: check your FXML file 'Registrar_conductor.fxml'.";
    }

}
