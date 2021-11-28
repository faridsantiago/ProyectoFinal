/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author IDEAPAD S330
 */
public class Rutas {

    private int ID;
    private String Recorrido;

    public Rutas(int ID, String Recorrido) {
        this.ID = ID;
        this.Recorrido = Recorrido;
    }

    public Rutas() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRecorrido() {
        return Recorrido;
    }

    public void setRecorrido(String Recorrido) {
        this.Recorrido = Recorrido;
    }
    java.sql.Connection conn = Conexion.getConnection();

    Statement st;
    ResultSet rs;

    public ObservableList<Rutas> getRutasdatos() {
        ObservableList<Rutas> obs = FXCollections.observableArrayList();

        try {
            String MSQL = "Select ID,Recorrido from Rutas";
            st = conn.createStatement();
            rs = st.executeQuery(MSQL);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nombre = rs.getString("Recorrido");

                Rutas r = new Rutas(id, nombre);
                obs.add(r);
                
            }
        } catch (Exception e) {
        }

        return obs;
    }

   
    
    @Override
    public String toString() {
        return Recorrido;
    }
    
}
