/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Conductores;
import Modelo.Conexion;
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
import javafx.scene.Node;
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
public class ConductoresController implements Initializable {

    Connection conn = Conexion.getConnection();

    Statement st;
    ResultSet rs;

    @FXML
    private TableColumn<Conductores, Integer> ColumCC;

    @FXML
    private TableColumn<Conductores, Integer> columEdad;

    @FXML
    private TableColumn<Conductores, String> columArl;

    @FXML
    private TableColumn<Conductores, String> columCompensacion;

    @FXML
    private TableColumn<Conductores, String> columContrato;

    @FXML
    private TableColumn<Conductores, String> columEps;

    @FXML
    private TableColumn<Conductores, String> columFin;

    @FXML
    private TableColumn<Conductores, Integer> columID;

    @FXML
    private TableColumn<Conductores, String> columInicio;

    @FXML
    private TableColumn<Conductores, Integer> columLicencia;

    @FXML
    private TableColumn<Conductores, String> columNombre;

    @FXML
    private TableColumn<Conductores, Integer> columTelefono;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_aggconductor;

    @FXML
    private Button btn_descargare2;

    @FXML
    private Button btn_editconductor;

    @FXML
    private Button btn_elimconductor;
    @FXML
    private TableView<Conductores> tablaConductores;

    @FXML
    void Agregar_conductor(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vista/Registrar_conductor.fxml"));
        Parent ventana2 = loader.load();

        Stage ventana = new Stage();
        Scene scene = new Scene(ventana2);
        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.initStyle(StageStyle.UNDECORATED);
        ventana.setScene(scene);
        ventana.showAndWait();

    }

    @FXML
    void Editar_conductor(ActionEvent event) throws IOException {

        Conductores datoscon = this.tablaConductores.getSelectionModel().getSelectedItem();
        if (datoscon == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Debe seleccionar un dato");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vista/Editar_conductor.fxml"));
            Parent ventana2 = loader.load();

            Editar_conductorController datos_conductor = loader.getController();
            Conductores conductores = new Conductores();

            conductores.setNombre(datoscon.getNombre());
            conductores.setCC(datoscon.getCC());
            conductores.setEdad(datoscon.getEdad());
            conductores.setLicencia(datoscon.getLicencia());
            conductores.setTelefono(datoscon.getTelefono());
            conductores.setTipo_de_contrato(datoscon.getTipo_de_contrato());
            conductores.setIninio_contrato(datoscon.getIninio_contrato());
            conductores.setFin_contrato(datoscon.getFin_contrato());
            conductores.setEPS(datoscon.getEPS());
            conductores.setARL(datoscon.getARL());
            conductores.setCaja_compensacion(datoscon.getCaja_compensacion());

            datos_conductor.initComponents(conductores);

            Stage ventana = new Stage();
            Scene scene = new Scene(ventana2);
            ventana.initModality(Modality.APPLICATION_MODAL);
            ventana.initStyle(StageStyle.UNDECORATED);
            ventana.setScene(scene);
            ventana.showAndWait();

        }
    }

    @FXML
    void EliminarConductor(ActionEvent event) throws SQLException {
        Conductores tablacon = this.tablaConductores.getSelectionModel().getSelectedItem();
        if (tablacon == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Debe seleccionar un dato");
            alert.showAndWait();
        } else {
            Integer cc = this.ColumCC.getCellData(tablacon);
            String MSQL = "Delete from Conductores where CC='" + cc + "' ";
            st = conn.createStatement();
            rs = st.executeQuery(MSQL);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Succesfull");
            alert.setContentText("El conductor fue eliminado de la bdd");
            alert.showAndWait();

            this.tablaConductores.refresh();
        }
    }

    @FXML
    void DescargarPDF(ActionEvent event) {
        Document documento = new Document(PageSize.A3.rotate());
        
        try {
            String rutapdf = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(rutapdf+"/Desktop/Reporte_conductores.pdf"));
            documento.open();
            
            PdfPTable tabla = new PdfPTable(12);
            tabla.addCell("ID");
            tabla.addCell("Nombre");
            tabla.addCell("CC");
            tabla.addCell("Edad");
            tabla.addCell("Licencia");
            tabla.addCell("Telefono");
            tabla.addCell("Tipo de contrato");
            tabla.addCell("Inicio contrato");
            tabla.addCell("Fin contrato");
            tabla.addCell("EPS");
            tabla.addCell("ARL");   
            tabla.addCell("Caja de compensacion");
            
            try {
                String MSQL="Select * from Conductores";
                st = conn.createStatement();
                rs=st.executeQuery(MSQL);
                
                if (rs.next()) {
                    do {                        
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                        tabla.addCell(rs.getString(6));
                        tabla.addCell(rs.getString(7));
                        tabla.addCell(rs.getString(8));
                        tabla.addCell(rs.getString(9));
                        tabla.addCell(rs.getString(10));
                        tabla.addCell(rs.getString(11));
                        tabla.addCell(rs.getString(12));
                       
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

        assert ColumCC != null : "fx:id=\"ColumCC\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert btn_aggconductor != null : "fx:id=\"btn_aggconductor\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert btn_descargare2 != null : "fx:id=\"btn_descargare2\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert btn_editconductor != null : "fx:id=\"btn_editconductor\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert btn_elimconductor != null : "fx:id=\"btn_elimconductor\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert columArl != null : "fx:id=\"columArl\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert columCompensacion != null : "fx:id=\"columCompensacion\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert columContrato != null : "fx:id=\"columContrato\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert columEps != null : "fx:id=\"columEps\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert columFin != null : "fx:id=\"columFin\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert columID != null : "fx:id=\"columID\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert columInicio != null : "fx:id=\"columInicio\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert columLicencia != null : "fx:id=\"columLicencia\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert columNombre != null : "fx:id=\"columNombre\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert columTelefono != null : "fx:id=\"columTelefono\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert tablaConductores != null : "fx:id=\"tablaConductores\" was not injected: check your FXML file 'Conductores.fxml'.";
        assert columEdad != null : "fx:id=\"columEdad\" was not injected: check your FXML file 'Conductores.fxml'.";

        this.columID.setCellValueFactory(new PropertyValueFactory("ID"));
        this.columNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.ColumCC.setCellValueFactory(new PropertyValueFactory("CC"));
        this.columEdad.setCellValueFactory(new PropertyValueFactory("Edad"));
        this.columLicencia.setCellValueFactory(new PropertyValueFactory("Licencia"));
        this.columTelefono.setCellValueFactory(new PropertyValueFactory("Telefono"));
        this.columContrato.setCellValueFactory(new PropertyValueFactory("Tipo_de_contrato"));
        this.columInicio.setCellValueFactory(new PropertyValueFactory("Ininio_contrato"));
        this.columFin.setCellValueFactory(new PropertyValueFactory("Fin_contrato"));
        this.columEps.setCellValueFactory(new PropertyValueFactory("EPS"));
        this.columArl.setCellValueFactory(new PropertyValueFactory("ARL"));
        this.columCompensacion.setCellValueFactory(new PropertyValueFactory("Caja_compensacion"));

        Conductores C = new Conductores();

        ObservableList<Conductores> items = C.getConductoresDatos();
        this.tablaConductores.setItems(items);
        //System.out.println(items);

    }

}
