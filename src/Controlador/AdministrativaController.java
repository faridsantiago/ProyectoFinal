/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Conexion;
import Modelo.Empleados;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

public class AdministrativaController implements Initializable {

    Connection conn = Conexion.getConnection();

    Statement st;
    ResultSet rs;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_aggempleado;

    @FXML
    private Button btn_descargare4;

    @FXML
    private Button btn_editempleado;

    @FXML
    private Button btn_elimempleado;
    @FXML
    private TableColumn<Empleados, Integer> columCC;

    @FXML
    private TableColumn<Empleados, String> columCaja;

    @FXML
    private TableColumn<Empleados, String> columContrato;

    @FXML
    private TableColumn<Empleados, String> columDireccion;

    @FXML
    private TableColumn<Empleados, String> columFin;

    @FXML
    private TableColumn<Empleados, Integer> columID;

    @FXML
    private TableColumn<Empleados, String> columInicio;

    @FXML
    private TableColumn<Empleados, String> columNombre;

    @FXML
    private TableColumn<Empleados, Integer> columTelefono;

    @FXML
    private TableColumn<Empleados, String> columTrabajo;
    @FXML
    private TableColumn<Empleados, Integer> ColumEdad;
    @FXML
    private TableColumn<Empleados, String> columEps;
    @FXML
    private TableColumn<Empleados, String> columArl;

    @FXML
    private TableView<Empleados> TablaEmpleados;

    @FXML
    void Agregar_empleados(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vista/Agregar_trabajador.fxml"));
        Parent ventana2 = loader.load();

        Stage ventana = new Stage();
        Scene scene = new Scene(ventana2);
        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.initStyle(StageStyle.UNDECORATED);
        ventana.setScene(scene);
        ventana.showAndWait();

    }

    @FXML
    void Editar_empleado(ActionEvent event) throws IOException {
        Empleados datosemp = this.TablaEmpleados.getSelectionModel().getSelectedItem();

        if (datosemp == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Debe seleccionar un dato");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vista/Editar_trabajador.fxml"));
            Parent ventana2 = loader.load();

            Editar_trabajadorController datos_trabajador = loader.getController();
            Empleados empleado = new Empleados();

            empleado.setNombre(datosemp.getNombre());
            empleado.setCC(datosemp.getCC());
            empleado.setEdad(datosemp.getEdad());
            empleado.setDireccion(datosemp.getDireccion());
            empleado.setTelefono(datosemp.getTelefono());
            empleado.setTipo_de_trabajo(datosemp.getTipo_de_trabajo());
            empleado.setTipo_de_contrato(datosemp.getTipo_de_contrato());
            empleado.setEPS(datosemp.getEPS());
            empleado.setARL(datosemp.getARL());
            empleado.setInicio_contrato(datosemp.getInicio_contrato());
            empleado.setFin_contrato(datosemp.getFin_contrato());
            empleado.setCaja_compensacion(datosemp.getCaja_compensacion());

            datos_trabajador.initComponents(empleado);

            Stage ventana = new Stage();
            Scene scene = new Scene(ventana2);
            ventana.initModality(Modality.APPLICATION_MODAL);
            ventana.initStyle(StageStyle.UNDECORATED);
            ventana.setScene(scene);
            ventana.showAndWait();

        }

    }

    @FXML
    void EliminarEmpleado(ActionEvent event) throws SQLException {
        Empleados datosemp = this.TablaEmpleados.getSelectionModel().getSelectedItem();

        if (datosemp == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Debe seleccionar un dato");
            alert.showAndWait();
        } else {
            try {
                Integer ce = this.columCC.getCellData(datosemp);
                String MSQL = "Delete from Empleados where CC='" + ce + "' ";
                st = conn.createStatement();
                rs = st.executeQuery(MSQL);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Succesfull");
                alert.setContentText("El conductor fue eliminado de la bdd");
                alert.showAndWait();
                this.TablaEmpleados.refresh();
            } catch (SQLException e) {
            }
        }

    }

    @FXML
    void DescargarPDF(ActionEvent event) {
        Document documento = new Document(PageSize.A3.rotate());
        
        try {
            String rutapdf = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(rutapdf+"/Desktop/Reporte_empleados.pdf"));
            documento.open();
            
            PdfPTable tabla = new PdfPTable(13);
            tabla.addCell("ID");
            tabla.addCell("Nombre");
            tabla.addCell("CC");
            tabla.addCell("Edad");
            tabla.addCell("Direccion");
            tabla.addCell("Telefono");
            tabla.addCell("Tipo de trabajo");
            tabla.addCell("Tipo de contrato");
            tabla.addCell("Inicio contrato");
            tabla.addCell("Fin contrato");
            tabla.addCell("EPS");
            tabla.addCell("ARL");   
            tabla.addCell("Caja de compensacion");
            
            try {
                String MSQL="Select * from Empleados";
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
                        tabla.addCell(rs.getString(13));
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
        assert ColumEdad != null : "fx:id=\"ColumEdad\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert TablaEmpleados != null : "fx:id=\"TablaEmpleados\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert btn_aggempleado != null : "fx:id=\"btn_aggempleado\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert btn_descargare4 != null : "fx:id=\"btn_descargare4\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert btn_editempleado != null : "fx:id=\"btn_editempleado\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert btn_elimempleado != null : "fx:id=\"btn_elimempleado\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert columArl != null : "fx:id=\"columArl\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert columCC != null : "fx:id=\"columCC\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert columCaja != null : "fx:id=\"columCaja\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert columContrato != null : "fx:id=\"columContrato\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert columDireccion != null : "fx:id=\"columDireccion\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert columEps != null : "fx:id=\"columEps\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert columFin != null : "fx:id=\"columFin\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert columID != null : "fx:id=\"columID\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert columInicio != null : "fx:id=\"columInicio\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert columNombre != null : "fx:id=\"columNombre\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert columTelefono != null : "fx:id=\"columTelefono\" was not injected: check your FXML file 'Administrativa.fxml'.";
        assert columTrabajo != null : "fx:id=\"columTrabajo\" was not injected: check your FXML file 'Administrativa.fxml'.";

        this.columID.setCellValueFactory(new PropertyValueFactory("ID"));
        this.columNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.columCC.setCellValueFactory(new PropertyValueFactory("CC"));
        this.ColumEdad.setCellValueFactory(new PropertyValueFactory("Edad"));
        this.columDireccion.setCellValueFactory(new PropertyValueFactory("Direccion"));
        this.columTelefono.setCellValueFactory(new PropertyValueFactory("Telefono"));
        this.columTrabajo.setCellValueFactory(new PropertyValueFactory("Tipo_de_trabajo"));
        this.columContrato.setCellValueFactory(new PropertyValueFactory("Tipo_de_contrato"));
        this.columInicio.setCellValueFactory(new PropertyValueFactory("Inicio_contrato"));
        this.columFin.setCellValueFactory(new PropertyValueFactory("Fin_contrato"));
        this.columEps.setCellValueFactory(new PropertyValueFactory("EPS"));
        this.columArl.setCellValueFactory(new PropertyValueFactory("ARL"));
        this.columCaja.setCellValueFactory(new PropertyValueFactory("caja_compensacion"));

        Empleados C = new Empleados();

        ObservableList<Empleados> items = C.getEmpleadosdatos();
        this.TablaEmpleados.setItems(items);
        this.TablaEmpleados.refresh();
        //System.out.println(items);

    }

}
