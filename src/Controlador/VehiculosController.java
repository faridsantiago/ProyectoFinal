/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Conexion;
import Modelo.Vehiculo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IDEAPAD S330
 */
public class VehiculosController implements Initializable {

    Connection conn = Conexion.getConnection();

    Statement st;
    ResultSet rs;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_aggvehiculo;

    @FXML
    private Button btn_descargare1;

    @FXML
    private Button btn_editvehiculo;

    @FXML
    private Button btn_elimvehiculo;

    @FXML
    private TableColumn<Vehiculo, String> columConductor;

    @FXML
    private TableColumn<Vehiculo, Integer> columID;

    @FXML
    private TableColumn<Vehiculo, String> columPlaca;

    @FXML
    private TableColumn<Vehiculo, String> columPropietario;

    @FXML
    private TableColumn<Vehiculo, String> columRuta;

    @FXML
    private TableView<Vehiculo> tablaVehiculos;

    @FXML
    void Agregar_vehiculo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vista/Agregar_bus.fxml"));
        Parent ventana2 = loader.load();

        Stage ventana = new Stage();
        Scene scene = new Scene(ventana2);
        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.initStyle(StageStyle.UNDECORATED);
        ventana.setScene(scene);
        ventana.showAndWait();
    }

    @FXML
    void Editar_vehiculo(ActionEvent event) throws IOException {

        Vehiculo vehiculo = this.tablaVehiculos.getSelectionModel().getSelectedItem();

        if (vehiculo == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Debe seleccionar un dato");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vista/Editar_bus.fxml"));
            Parent ventana2 = loader.load();

            Editar_busController datos_bus = loader.getController();
            Vehiculo bus = new Vehiculo();

            bus.setPlaca(vehiculo.getPlaca());
            bus.setConductor(vehiculo.getConductor());
            bus.setPropietario(vehiculo.getPropietario());
            bus.setRuta(vehiculo.getRuta());

            datos_bus.initComponents(bus);

            Stage ventana = new Stage();
            Scene scene = new Scene(ventana2);
            ventana.initModality(Modality.APPLICATION_MODAL);
            ventana.initStyle(StageStyle.UNDECORATED);
            ventana.setScene(scene);
            ventana.showAndWait();
        }
    }

    @FXML
    void EliminarVehiculo(ActionEvent event) throws SQLException {
        Vehiculo tbVehiculo = this.tablaVehiculos.getSelectionModel().getSelectedItem();

        if (tbVehiculo == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Debe seleccionar un dato");
            alert.showAndWait();

        } else {
            Integer id = this.columID.getCellData(tbVehiculo);
            String MSQL = "Delete from Vehiculo where ID='" + id + "' ";
            st = conn.createStatement();
            rs = st.executeQuery(MSQL);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Succesfull");
            alert.setContentText("El vehiculo fue eliminado de la bdd");
            alert.showAndWait();

            this.tablaVehiculos.refresh();
        }
    }

    @FXML
    void DescargarPDF(ActionEvent event) {
        Document documento = new Document(PageSize.LETTER.rotate());
        
        try {
            String rutapdf = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(rutapdf+"/Desktop/Reporte_Vehiculos.pdf"));
            documento.open();
            
            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("ID");
            tabla.addCell("Placa");
            tabla.addCell("Ruta");
            tabla.addCell("Conductor");
            tabla.addCell("Propietario");
           
            
            try {
                String MSQL="Select * from Vehiculo";
                st = conn.createStatement();
                rs=st.executeQuery(MSQL);
                
                if (rs.next()) {
                    do {                        
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                  
                       
                    }while(rs.next());
                    documento.add(tabla);
                  
                }
            } catch (DocumentException |SQLException e) {
                
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte generado");
        } catch (DocumentException|HeadlessException| FileNotFoundException e) {
            
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert btn_aggvehiculo != null : "fx:id=\"btn_aggvehiculo\" was not injected: check your FXML file 'Vehiculos.fxml'.";
        assert btn_descargare1 != null : "fx:id=\"btn_descargare1\" was not injected: check your FXML file 'Vehiculos.fxml'.";
        assert btn_editvehiculo != null : "fx:id=\"btn_editvehiculo\" was not injected: check your FXML file 'Vehiculos.fxml'.";
        assert btn_elimvehiculo != null : "fx:id=\"btn_elimvehiculo\" was not injected: check your FXML file 'Vehiculos.fxml'.";
        assert columConductor != null : "fx:id=\"columConductor\" was not injected: check your FXML file 'Vehiculos.fxml'.";
        assert columID != null : "fx:id=\"columID\" was not injected: check your FXML file 'Vehiculos.fxml'.";
        assert columPlaca != null : "fx:id=\"columPlaca\" was not injected: check your FXML file 'Vehiculos.fxml'.";
        assert columPropietario != null : "fx:id=\"columPropietario\" was not injected: check your FXML file 'Vehiculos.fxml'.";
        assert columRuta != null : "fx:id=\"columRuta\" was not injected: check your FXML file 'Vehiculos.fxml'.";
        assert tablaVehiculos != null : "fx:id=\"tablaVehiculos\" was not injected: check your FXML file 'Vehiculos.fxml'.";

        this.columID.setCellValueFactory(new PropertyValueFactory("ID"));
        this.columPlaca.setCellValueFactory(new PropertyValueFactory("Placa"));
        this.columRuta.setCellValueFactory(new PropertyValueFactory("Ruta"));
        this.columConductor.setCellValueFactory(new PropertyValueFactory("Conductor"));
        this.columPropietario.setCellValueFactory(new PropertyValueFactory("Propietario"));

        Vehiculo V = new Vehiculo();

        ObservableList<Vehiculo> items = V.getVehiculos();
        this.tablaVehiculos.setItems(items);

    }

}
