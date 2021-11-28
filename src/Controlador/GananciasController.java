package Controlador;

import Modelo.Conexion;
import Modelo.Ganancias;
import Modelo.Rutas;
import Modelo.Vehiculo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IDEAPAD S330
 */
public class GananciasController implements Initializable {

    Connection conn = Conexion.getConnection();

    Statement st;
    ResultSet rs;

    @FXML
    private Button btn_calcular;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button btnImprimir;



    @FXML
    private TableColumn<Ganancias, ?> columNparadas;

    @FXML
    private TableColumn<Ganancias, ?> columPlaca;

    @FXML
    private TableColumn<Ganancias, ?> columRuta;

    @FXML
    private TableColumn<Ganancias, ?> columTotal;
    @FXML
    private TableColumn<Ganancias, ?> columID;

    @FXML
    private ComboBox<Vehiculo> comboPlaca;

    @FXML
    private ComboBox<Rutas> comboRuta;

    @FXML
    private TableView<Ganancias> tb_ganancia;

    @FXML
    private TextField txt_paradas;

    @FXML
    private TextField txt_precioPasaje;

    @FXML
    private TextField txt_total;

    @FXML
    void Calcular(ActionEvent event) throws SQLException {
        
        try {
            int precio = Integer.parseInt(txt_precioPasaje.getText());
            int paradas = Integer.parseInt(txt_paradas.getText());
            txt_total.setText(String.valueOf(precio * paradas));
            String total = txt_total.getText();
            Rutas ruta = comboRuta.getSelectionModel().getSelectedItem();
            Vehiculo vehiculo = comboPlaca.getSelectionModel().getSelectedItem();

            String Mysql = "INSERT INTO Ganancias(Ruta,Placa,N_paradas,Total)values('" + ruta + "','" + vehiculo + "','"+paradas+"','" + total + "')";
            st = conn.createStatement();
            st.execute(Mysql);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Sucefull");
            alert.setContentText("Ganancias del dia agregada");
            alert.showAndWait();
            txt_paradas.setText(" ");
            txt_precioPasaje.setText(" ");
            
            tb_ganancia.refresh();
            
           
            
        } catch (Exception e) {
            
        }
      

    }
     @FXML
    void ImprimirPDF(ActionEvent event) {
        Document documento = new Document(PageSize.LETTER.rotate());
        
        try {
            String rutapdf = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(rutapdf+"/Desktop/Reporte_Ganancias.pdf"));
            documento.open();
            
            PdfPTable tabla = new PdfPTable(4);
            tabla.addCell("ID");
            tabla.addCell("Ruta");
            tabla.addCell("Placa");
            tabla.addCell("Total");
           
            
            try {
                String MSQL="Select ID,Ruta,Placa,Total from Ganancias";
                st = conn.createStatement();
                rs=st.executeQuery(MSQL);
                
                if (rs.next()) {
                    do {                        
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
        
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
        assert btn_calcular != null : "fx:id=\"btn_calcular\" was not injected: check your FXML file 'Ganancias.fxml'.";
        assert columNparadas != null : "fx:id=\"columNparadas\" was not injected: check your FXML file 'Ganancias.fxml'.";
        assert columPlaca != null : "fx:id=\"columPlaca\" was not injected: check your FXML file 'Ganancias.fxml'.";
        assert columRuta != null : "fx:id=\"columRuta\" was not injected: check your FXML file 'Ganancias.fxml'.";
        assert columTotal != null : "fx:id=\"columTotal\" was not injected: check your FXML file 'Ganancias.fxml'.";
        assert comboPlaca != null : "fx:id=\"comboPlaca\" was not injected: check your FXML file 'Ganancias.fxml'.";
        assert comboRuta != null : "fx:id=\"comboRuta\" was not injected: check your FXML file 'Ganancias.fxml'.";
        assert tb_ganancia != null : "fx:id=\"tb_ganancia\" was not injected: check your FXML file 'Ganancias.fxml'.";
        assert txt_paradas != null : "fx:id=\"txt_paradas\" was not injected: check your FXML file 'Ganancias.fxml'.";
        assert txt_precioPasaje != null : "fx:id=\"txt_precioPasaje\" was not injected: check your FXML file 'Ganancias.fxml'.";
        assert txt_total != null : "fx:id=\"txt_total\" was not injected: check your FXML file 'Ganancias.fxml'.";
        assert btnImprimir != null : "fx:id=\"btnImprimir\" was not injected: check your FXML file 'Ganancias.fxml'.";

        this.columID.setCellValueFactory(new PropertyValueFactory("ID"));
        this.columRuta.setCellValueFactory(new PropertyValueFactory("Ruta"));
        this.columPlaca.setCellValueFactory(new PropertyValueFactory("Placa"));
        //this.columNparadas.setCellValueFactory(new PropertyValueFactory("N_paradas"));
        this.columTotal.setCellValueFactory(new PropertyValueFactory("Total"));
        Ganancias G = new Ganancias();

        ObservableList<Ganancias> items = G.getGanaciasdatos();
        this.tb_ganancia.setItems(items);
        this.tb_ganancia.refresh();
        
        initCombos();

    }

    private void initCombos() {
        Rutas comboruta = new Rutas();
        ObservableList<Rutas> lista = comboruta.getRutasdatos();
        this.comboRuta.setItems(lista);

        Vehiculo v = new Vehiculo();
        ObservableList<Vehiculo> lista2 = v.getVehiculos();
        this.comboPlaca.setItems(lista2);

    }

}
