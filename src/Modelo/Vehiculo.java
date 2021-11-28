/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Vehiculo {
    int ID;
    String Placa;
    String Ruta;
    String Conductor;
    String Propietario;
    
    public Vehiculo(){
        
    }

    public Vehiculo(int ID, String Placa, String Ruta, String Conductor, String Propietario) {
        this.ID = ID;
        this.Placa = Placa;
        this.Ruta = Ruta;
        this.Conductor = Conductor;
        this.Propietario = Propietario;
    }

  

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getRuta() {
        return Ruta;
    }

    public void setRuta(String Ruta) {
        this.Ruta = Ruta;
    }

    public String getConductor() {
        return Conductor;
    }

    public void setConductor(String Conductor) {
        this.Conductor = Conductor;
    }

    public String getPropietario() {
        return Propietario;
    }

    public void setPropietario(String Propietario) {
        this.Propietario = Propietario;
    }
    
    java.sql.Connection conn = Conexion.getConnection();

    Statement st;
    ResultSet rs;
    
    public ObservableList<Vehiculo> getVehiculos()  {
        ObservableList<Vehiculo> obs = FXCollections.observableArrayList();

      
        try {
            String MSQL = "Select ID,Placa,ruta,Conductor,Propietario from Vehiculo";
            st = conn.createStatement();
            rs = st.executeQuery(MSQL);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String placa = rs.getString("Placa");
                String conductor =rs.getString("Conductor");
                String rutabus = rs.getString("ruta");
                String propietario = rs.getString("Propietario");
                
                Vehiculo V = new Vehiculo(id, placa, rutabus, conductor, propietario);
                obs.add(V);
                
            }

        } catch (Exception e) {
            
        }
        return obs;
    }
    
    @Override
    public String toString() {
        return Placa;
    }
    
    
    
}
