/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Conductores;
import Modelo.Conexion;
import Modelo.Rutas;
import com.sun.jdi.Value;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.collections.FXCollections.observableList;
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
public class Agregar_busController implements Initializable {
    
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
    private ComboBox<Conductores> comboConductor;

    @FXML
    private ComboBox<Rutas> comboRuta;

    @FXML
    private TextField txt_placa;

    @FXML
    private TextField txt_propietario;
    
    @FXML
    void Agg_bus(ActionEvent event) throws SQLException{
        try {
            Rutas rutasbus = this.comboRuta.getSelectionModel().getSelectedItem();
            Conductores conductor = this.comboConductor.getSelectionModel().getSelectedItem();
            String placa = this.txt_placa.getText();
            String propietario = this.txt_propietario.getText();
            
            String Mysql="Insert into Vehiculo (Placa,ruta,Conductor,Propietario) values ('"+placa+"','"+rutasbus+"','"+conductor+"','"+propietario+"')";
            
            st = conn.createStatement();
            st.execute(Mysql);
            
            JOptionPane.showMessageDialog(null, "Vehiculo agregado");
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            
        } catch (Exception e) {
            System.out.println("No se agregó pa");
            
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
        assert btn_aggconductor != null : "fx:id=\"btn_aggconductor\" was not injected: check your FXML file 'Agregar_bus.fxml'.";
        assert btn_elimconductor != null : "fx:id=\"btn_elimconductor\" was not injected: check your FXML file 'Agregar_bus.fxml'.";
        assert comboConductor != null : "fx:id=\"comboConductor\" was not injected: check your FXML file 'Agregar_bus.fxml'.";
        assert comboRuta != null : "fx:id=\"comboRuta\" was not injected: check your FXML file 'Agregar_bus.fxml'.";
        assert txt_placa != null : "fx:id=\"txt_placa\" was not injected: check your FXML file 'Agregar_bus.fxml'.";
        assert txt_propietario != null : "fx:id=\"txt_propietario\" was not injected: check your FXML file 'Agregar_bus.fxml'.";

        initCombos();
            
    }
        private void initCombos() {
        
        Rutas r = new Rutas();
        ObservableList<Rutas> lista = r.getRutasdatos();
        this.comboRuta.setItems(lista);
        
        Conductores c = new Conductores();
        ObservableList<Conductores> lista2 = c.getConductoresDatos();
        this.comboConductor.setItems(lista2);
        
        
    }
   
        

}
