/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author IDEAPAD S330
 */
public class Ganancias {
    private int ID;
    private String Ruta;
    private String Placa;
    private int Paradas;
    private int Total;
    
    public Ganancias(){
        
    }

    public Ganancias(int ID, String Ruta, String Placa, int Paradas, int Total) {
        this.ID = ID;
        this.Ruta = Ruta;
        this.Placa = Placa;
        this.Paradas = Paradas;
        this.Total = Total;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRuta() {
        return Ruta;
    }

    public void setRuta(String Ruta) {
        this.Ruta = Ruta;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public int getParadas() {
        return Paradas;
    }

    public void setParadas(int Paradas) {
        this.Paradas = Paradas;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }
    
    java.sql.Connection conn = Conexion.getConnection();

    Statement st;
    ResultSet rs;
    
    public ObservableList<Ganancias> getGanaciasdatos(){
        ObservableList<Ganancias> obs = FXCollections.observableArrayList();
        
        try {
            String MSQL="Select ID,Ruta,Placa,N_paradas,Total from Ganancias";
            st = conn.createStatement();
            rs = st.executeQuery(MSQL);
            
            while (rs.next()) {                
                int id=rs.getInt("ID");
                String ruta = rs.getString("Ruta");
                String placa = rs.getString("Placa");
                int paradas = rs.getInt("N_paradas");
                int total = rs.getInt("Total");
                
                Ganancias G = new Ganancias(id, ruta, placa, paradas, total);
                obs.add(G);
            }

        } catch (Exception e) {
            
        }
        
        return obs;
    }
}
