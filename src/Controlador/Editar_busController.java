/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Conductores;
import Modelo.Conexion;
import Modelo.Rutas;
import Modelo.Vehiculo;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IDEAPAD S330
 */
public class Editar_busController implements Initializable {

    Connection conn = Conexion.getConnection();

    Statement st;
    ResultSet rs;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_aggconductor;

    @FXML
    private Button btn_elimconductor;

    @FXML
    private ComboBox<Conductores> comboEditarConductor;

    @FXML
    private ComboBox<Rutas> comboEditarbus;

    @FXML
    private TextField txt_editarPropietario;

    @FXML
    private TextField txt_editarplaca;

    @FXML
    void Cancelar(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Modificar(ActionEvent event) throws SQLException {
        try {
            Rutas ruta = comboEditarbus.getSelectionModel().getSelectedItem();
            Conductores conductores = comboEditarConductor.getSelectionModel().getSelectedItem();
            String placa = txt_editarplaca.getText();
            String propietario = txt_editarPropietario.getText();

            String MSQL = "UPDATE Vehiculo SET ruta='"+ruta+"',Conductor='"+conductores+"' where Placa='"+placa+"'   ";
            st = conn.createStatement();
            st.execute(MSQL);
            JOptionPane.showMessageDialog(null, "Vehiculo Modificado");
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            
        } catch (Exception e) {
            System.out.println("No se hizo el update");
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert btn_aggconductor != null : "fx:id=\"btn_aggconductor\" was not injected: check your FXML file 'Editar_bus.fxml'.";
        assert btn_elimconductor != null : "fx:id=\"btn_elimconductor\" was not injected: check your FXML file 'Editar_bus.fxml'.";
        assert comboEditarConductor != null : "fx:id=\"comboEditarConductor\" was not injected: check your FXML file 'Editar_bus.fxml'.";
        assert comboEditarbus != null : "fx:id=\"comboEditarbus\" was not injected: check your FXML file 'Editar_bus.fxml'.";
        assert txt_editarPropietario != null : "fx:id=\"txt_editarPropietario\" was not injected: check your FXML file 'Editar_bus.fxml'.";
        assert txt_editarplaca != null : "fx:id=\"txt_editarplaca\" was not injected: check your FXML file 'Editar_bus.fxml'.";
        initCombos();
    }

    public void initComponents(Vehiculo editbus) {

        txt_editarplaca.setText(editbus.getPlaca());
        txt_editarPropietario.setText(editbus.getPropietario());

    }

    private void initCombos() {

        Rutas r = new Rutas();
        ObservableList<Rutas> lista = r.getRutasdatos();
        this.comboEditarbus.setItems(lista);

        Conductores c = new Conductores();
        ObservableList<Conductores> lista2 = c.getConductoresDatos();
        this.comboEditarConductor.setItems(lista2);

    }

}
