/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Conexion;
import Modelo.Empleados;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IDEAPAD S330
 */
public class Editar_trabajadorController implements Initializable {

    Connection conn = Conexion.getConnection();

    Statement st;
    ResultSet rs;

    @FXML
    private ResourceBundle resources;
    
    @FXML
    private TextField txt_edit_direccion;

    @FXML
    private URL location;

    @FXML
    private Button btn_actualizar_tra;

    @FXML
    private Button btn_cancelar;

    @FXML
    private ComboBox<String> cb_editar_cont_tra;

    @FXML
    private ComboBox<String> cb_editar_tipo_tra;

    @FXML
    private TextField txt_ediat_arl_tra;

    @FXML
    private TextField txt_editar_cc_tra;

    @FXML
    private TextField txt_editar_edad_tra;

    @FXML
    private TextField txt_editar_eps_tra;

    @FXML
    private DatePicker txt_editar_fin_tra;

    @FXML
    private DatePicker txt_editar_inicio_tra;

    @FXML
    private TextField txt_editar_nombre_tra;

    @FXML
    private TextField txt_editar_tel_tra;
    @FXML
    private TextField txt_edit_caja_trabaj;

    @FXML
    void Actualizar(ActionEvent event) {
        try {
            String nombre = txt_editar_nombre_tra.getText();
            int edad = Integer.parseInt(txt_editar_edad_tra.getText());
            int cc = Integer.parseInt(txt_editar_cc_tra.getText());
            int telefono = Integer.parseInt(txt_editar_tel_tra.getText());
            String tipo_empleado = cb_editar_tipo_tra.getSelectionModel().getSelectedItem();
            String contrato = cb_editar_cont_tra.getSelectionModel().getSelectedItem();
            String eps = txt_editar_eps_tra.getText();
            String arl = txt_ediat_arl_tra.getText();
            LocalDate inicio = txt_editar_inicio_tra.getValue();
            LocalDate fin = txt_editar_fin_tra.getValue();
            String caja = txt_edit_caja_trabaj.getText();
            String direccion = txt_edit_direccion.getText();
            
            String MSQL = "UPDATE Empleados SET Edad='"+edad+"',Direccion='"+direccion+"',Telefono='"+telefono+"',Tipo_de_trabajo='"+tipo_empleado+"',Tipo_de_contrato='"+contrato+"',Inicio_contrato='"+inicio+"',Fin_contrato='"+fin+"',EPS='"+eps+"',ARL='"+arl+"',Caja_compensacion='"+caja+"' WHERE CC='"+cc+"'   ";
            st = conn.createStatement();
            st.execute(MSQL);
            
            JOptionPane.showMessageDialog(null, "Empleado Modificado");
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            
            
        } catch (Exception e) {
            System.out.println("No se hizo el update");
        }

    }

    @FXML
    void Cancelar(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb_editar_cont_tra.getItems().setAll("Termino fijo", "Indefinido", "De obra o labor");
        cb_editar_tipo_tra.getItems().setAll("Aseador", "Vigilante", "Asistente de ruta", "Cajero", "Mecanico");
        assert btn_actualizar_tra != null : "fx:id=\"btn_actualizar_tra\" was not injected: check your FXML file 'Editar_trabajador.fxml'.";
        assert btn_cancelar != null : "fx:id=\"btn_cancelar\" was not injected: check your FXML file 'Editar_trabajador.fxml'.";
        assert cb_editar_cont_tra != null : "fx:id=\"cb_editar_cont_tra\" was not injected: check your FXML file 'Editar_trabajador.fxml'.";
        assert cb_editar_tipo_tra != null : "fx:id=\"cb_editar_tipo_tra\" was not injected: check your FXML file 'Editar_trabajador.fxml'.";
        assert txt_ediat_arl_tra != null : "fx:id=\"txt_ediat_arl_tra\" was not injected: check your FXML file 'Editar_trabajador.fxml'.";
        assert txt_editar_cc_tra != null : "fx:id=\"txt_editar_cc_tra\" was not injected: check your FXML file 'Editar_trabajador.fxml'.";
        assert txt_editar_edad_tra != null : "fx:id=\"txt_editar_edad_tra\" was not injected: check your FXML file 'Editar_trabajador.fxml'.";
        assert txt_editar_eps_tra != null : "fx:id=\"txt_editar_eps_tra\" was not injected: check your FXML file 'Editar_trabajador.fxml'.";
        assert txt_editar_fin_tra != null : "fx:id=\"txt_editar_fin_tra\" was not injected: check your FXML file 'Editar_trabajador.fxml'.";
        assert txt_editar_inicio_tra != null : "fx:id=\"txt_editar_inicio_tra\" was not injected: check your FXML file 'Editar_trabajador.fxml'.";
        assert txt_editar_nombre_tra != null : "fx:id=\"txt_editar_nombre_tra\" was not injected: check your FXML file 'Editar_trabajador.fxml'.";
        assert txt_editar_tel_tra != null : "fx:id=\"txt_editar_tel_tra\" was not injected: check your FXML file 'Editar_trabajador.fxml'.";
        assert txt_edit_direccion != null : "fx:id=\"txt_edit_direccion\" was not injected: check your FXML file 'Editar_trabajador.fxml'.";
        assert txt_edit_caja_trabaj != null : "fx:id=\"txt_edit_caja_trabaj\" was not injected: check your FXML file 'Editar_trabajador.fxml'.";

    }

    public void initComponents(Empleados EditEmpleado) {
        txt_editar_nombre_tra.setText(EditEmpleado.getNombre());
        txt_editar_edad_tra.setText(String.valueOf(EditEmpleado.getEdad()));
        txt_editar_cc_tra.setText(String.valueOf(EditEmpleado.getCC()));
        txt_editar_tel_tra.setText(String.valueOf(EditEmpleado.getTelefono()));
        txt_editar_eps_tra.setText(EditEmpleado.getEPS());
        txt_ediat_arl_tra.setText(EditEmpleado.getARL());
        txt_editar_inicio_tra.setValue(LocalDate.parse(EditEmpleado.getInicio_contrato()));
        txt_editar_fin_tra.setValue(LocalDate.parse(EditEmpleado.getFin_contrato()));
        txt_edit_caja_trabaj.setText(EditEmpleado.getCaja_compensacion());
        txt_edit_direccion.setText(EditEmpleado.getDireccion());
        cb_editar_cont_tra.setValue(EditEmpleado.getTipo_de_contrato());
        cb_editar_tipo_tra.setValue(EditEmpleado.getTipo_de_trabajo());

    }

}
