/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import com.sun.jdi.connect.spi.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Modelo.Conexion;
import java.time.LocalDate;
import Modelo.Conexion;

public class Conductores {

    private int ID;
    private String Nombre;
    private int CC;
    private int Edad;
    private int Licencia;
    private int Telefono;
    private String Tipo_de_contrato;
    private String Ininio_contrato;
    private String Fin_contrato;
    private String EPS;
    private String ARL;
    private String Caja_compensacion;

    public Conductores() {

    }

    public Conductores(int ID, String Nombre, int CC, int Edad, int Licencia, int Telefono, String Tipo_de_contrato, String Ininio_contrato, String Fin_contrato, String EPS, String ARL, String Caja_compensacion) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.CC = CC;
        this.Edad = Edad;
        this.Licencia = Licencia;
        this.Telefono = Telefono;
        this.Tipo_de_contrato = Tipo_de_contrato;
        this.Ininio_contrato = Ininio_contrato;
        this.Fin_contrato = Fin_contrato;
        this.EPS = EPS;
        this.ARL = ARL;
        this.Caja_compensacion = Caja_compensacion;
    }

    Conductores(int id, String nombre, int idconductor, int edad, int direccion, int telefono, String trabajo, String contrato, String inicio, String fin, String eps, String arl, String caja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getCC() {
        return CC;
    }

    public void setCC(int CC) {
        this.CC = CC;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public int getLicencia() {
        return Licencia;
    }

    public void setLicencia(int Licencia) {
        this.Licencia = Licencia;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public String getTipo_de_contrato() {
        return Tipo_de_contrato;
    }

    public void setTipo_de_contrato(String Tipo_de_contrato) {
        this.Tipo_de_contrato = Tipo_de_contrato;
    }

    public String getIninio_contrato() {
        return Ininio_contrato;
    }

    public void setIninio_contrato(String Ininio_contrato) {
        this.Ininio_contrato = Ininio_contrato;
    }

    public String getFin_contrato() {
        return Fin_contrato;
    }

    public void setFin_contrato(String Fin_contrato) {
        this.Fin_contrato = Fin_contrato;
    }

    public String getEPS() {
        return EPS;
    }

    public void setEPS(String EPS) {
        this.EPS = EPS;
    }

    public String getARL() {
        return ARL;
    }

    public void setARL(String ARL) {
        this.ARL = ARL;
    }

    public String getCaja_compensacion() {
        return Caja_compensacion;
    }

    public void setCaja_compensacion(String Caja_compensacion) {
        this.Caja_compensacion = Caja_compensacion;
    }

    java.sql.Connection conn = Conexion.getConnection();

    Statement st;
    ResultSet rs;

    public ObservableList<Conductores> getConductoresDatos() {
        ObservableList<Conductores> obs = FXCollections.observableArrayList();

        //abrimos la coneccion
        try {
            String MSQL = "Select ID,Nombre,CC,Edad,Licencia,Telefono,Tipo_de_contrato,Inicio_contrato,Fin_contrato,EPS,ARL,Caja_compensacion from Conductores";
            st = conn.createStatement();
            rs = st.executeQuery(MSQL);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nombre = rs.getString("Nombre");
                int idconductor = rs.getInt("CC");
                int edad = rs.getInt("Edad");
                int licencia = rs.getInt("Licencia");
                int telefono = rs.getInt("Telefono");
                String contrato = rs.getString("Tipo_de_contrato");
                String inicio = rs.getString("Inicio_contrato");
                String fin = rs.getString("Fin_contrato");
                String eps = rs.getString("EPS");
                String arl = rs.getString("ARL");
                String caja = rs.getString("Caja_compensacion");

                Conductores C = new Conductores(id, nombre, idconductor, edad, licencia, telefono, contrato, inicio, fin, eps, arl, caja);
                obs.add(C);
                
            }

        } catch (Exception e) {

        }
        return obs;
    }

    @Override
    public String toString() {
        return Nombre;
    }

    public ObservableList<Vehiculo> getVehiculos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
