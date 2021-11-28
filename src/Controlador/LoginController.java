package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import Modelo.Conexion;
import javafx.scene.input.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {

    Connection conn = Conexion.getConnection();

    Statement st;
    ResultSet rs;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BtnLogin;

    @FXML
    private PasswordField TxtPassword;

    @FXML
    private TextField TxtUser;

    @FXML
    void evenAction(KeyEvent event) {

    }

    @FXML
    void eventKey(KeyEvent event) {

    }

    @FXML
    private void Comprobar(MouseEvent event) throws SQLException {

        int user = Integer.parseInt(TxtUser.getText());
        int pass = Integer.parseInt(TxtPassword.getText());

        String MSQL = "Select * from login where usuario='" + user + "' and contrasena='" + pass + "' ";
        st = conn.createStatement();
        rs = st.executeQuery(MSQL);

        int usuario = 0;
        int contrasena = 0;
        while (rs.next()) {
            usuario = rs.getInt("usuario");
            contrasena = rs.getInt("contrasena");

        }

        if ((user == usuario) && (pass == contrasena)) {
            System.out.println("aaaaaaaaaaaaaaaaaaaa ");
           
            try {
                //eso es para cambiar de ventana 
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../Vista/Vista.fxml")));//ruta del fxml
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(null,"hay datos errados");
            System.out.println("No existe en el sistema");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert BtnLogin != null : "fx:id=\"BtnLogin\" was not injected: check your FXML file 'Login.fxml'.";
        assert TxtPassword != null : "fx:id=\"TxtPassword\" was not injected: check your FXML file 'Login.fxml'.";
        assert TxtUser != null : "fx:id=\"TxtUser\" was not injected: check your FXML file 'Login.fxml'.";

    }

}
