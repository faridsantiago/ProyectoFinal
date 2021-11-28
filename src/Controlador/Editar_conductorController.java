package Controlador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import Modelo.Conductores;
import Modelo.Conexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
public class Editar_conductorController implements Initializable {

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
    private ComboBox<String> cb_editar_contratoCon;

    @FXML
    private TextField txt_editarCC_con;

    @FXML
    private DatePicker txt_editar_InicioCon;

    @FXML
    private TextField txt_editar_arlCon;

    @FXML
    private TextField txt_editar_epsCon;

    @FXML
    private DatePicker txt_editar_finCon;

    @FXML
    private TextField txt_editar_licCon;

    @FXML
    private TextField txt_editar_nombreCon;

    @FXML
    private TextField txt_editar_telCon;

    @FXML
    private TextField txt_editarcaja_con;
    @FXML
    private TextField txt_editar_edadcon;

    @FXML
    void Cancelar(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Modificar(ActionEvent event) {
        try {
            String nombre = txt_editar_nombreCon.getText();
            int edad = Integer.parseInt(txt_editar_edadcon.getText());
            int cc = Integer.parseInt(txt_editarCC_con.getText());
            int telefono = Integer.parseInt(txt_editar_telCon.getText());
            int licencia = Integer.parseInt(txt_editar_licCon.getText());
            String tipo_contrato = cb_editar_contratoCon.getSelectionModel().getSelectedItem();
            String eps = txt_editar_epsCon.getText();
            String arl= txt_editar_arlCon.getText();
            LocalDate inicio = txt_editar_InicioCon.getValue();
            LocalDate fin = txt_editar_finCon.getValue();
            String caja = txt_editarcaja_con.getText();
            
            String MSQL = "UPDATE Conductores SET Edad='"+edad+"',Licencia='"+licencia+"',Telefono='"+telefono+"',Tipo_de_contrato='"+tipo_contrato+"',Inicio_contrato='"+inicio+"',Fin_contrato='"+fin+"',EPS='"+eps+"',ARL='"+arl+"',Caja_compensacion='"+caja+"' WHERE CC='"+cc+"' ";
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

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb_editar_contratoCon.getItems().setAll("Termino fijo", "Indefinido", "De obra o labor");
        assert btn_aggconductor != null : "fx:id=\"btn_aggconductor\" was not injected: check your FXML file 'Editar_conductor.fxml'.";
        assert btn_elimconductor != null : "fx:id=\"btn_elimconductor\" was not injected: check your FXML file 'Editar_conductor.fxml'.";
        assert txt_editarcaja_con != null : "fx:id=\"cb_editar_cajaCon\" was not injected: check your FXML file 'Editar_conductor.fxml'.";
        assert cb_editar_contratoCon != null : "fx:id=\"cb_editar_contratoCon\" was not injected: check your FXML file 'Editar_conductor.fxml'.";
        assert txt_editarCC_con != null : "fx:id=\"txt_editarCC_con\" was not injected: check your FXML file 'Editar_conductor.fxml'.";
        assert txt_editar_InicioCon != null : "fx:id=\"txt_editar_InicioCon\" was not injected: check your FXML file 'Editar_conductor.fxml'.";
        assert txt_editar_arlCon != null : "fx:id=\"txt_editar_arlCon\" was not injected: check your FXML file 'Editar_conductor.fxml'.";
        assert txt_editar_epsCon != null : "fx:id=\"txt_editar_epsCon\" was not injected: check your FXML file 'Editar_conductor.fxml'.";
        assert txt_editar_finCon != null : "fx:id=\"txt_editar_finCon\" was not injected: check your FXML file 'Editar_conductor.fxml'.";
        assert txt_editar_licCon != null : "fx:id=\"txt_editar_licCon\" was not injected: check your FXML file 'Editar_conductor.fxml'.";
        assert txt_editar_nombreCon != null : "fx:id=\"txt_editar_nombreCon\" was not injected: check your FXML file 'Editar_conductor.fxml'.";
        assert txt_editar_telCon != null : "fx:id=\"txt_editar_telCon\" was not injected: check your FXML file 'Editar_conductor.fxml'.";
        assert txt_editar_edadcon != null : "fx:id=\"txt_editar_edadcon\" was not injected: check your FXML file 'Editar_conductor.fxml'.";

    }
    public void initComponents(Conductores EditConductores){
        txt_editar_nombreCon.setText(EditConductores.getNombre());
        txt_editarCC_con.setText(String.valueOf(EditConductores.getCC()));
        txt_editar_telCon.setText(String.valueOf(EditConductores.getTelefono()));
        txt_editar_licCon.setText(String.valueOf(EditConductores.getLicencia()));
        cb_editar_contratoCon.setValue(EditConductores.getTipo_de_contrato());
        txt_editar_epsCon.setText(EditConductores.getEPS());
        txt_editar_arlCon.setText(EditConductores.getARL());
        txt_editar_InicioCon.setValue(LocalDate.parse(EditConductores.getIninio_contrato()));
        txt_editar_finCon.setValue(LocalDate.parse(EditConductores.getFin_contrato()));
        txt_editarcaja_con.setText(EditConductores.getCaja_compensacion());
        txt_editar_edadcon.setText(String.valueOf(EditConductores.getEdad()));
    } 
    

}
