/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author IDEAPAD S330
 */
public class Empleados {
    private int ID;
    private String Nombre;
    private int CC;
    private int Edad;
    private String Direccion;
    private int Telefono; 
    private String Tipo_de_trabajo;
    private String Tipo_de_contrato;
    private String Inicio_contrato;
    private String Fin_contrato;
    private String EPS;
    private String ARL;
    private String caja_compensacion;
    
 
    public Empleados(int ID, String Nombre, int CC, int Edad, int Telefono, int telefono, String Direccion, String Tipo_de_trabajo, String Tipo_de_contrato, String Inicio_contrato, String Fin_contrato, String EPS, String ARL, String caja_compensacion) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.CC = CC;
        this.Edad = Edad;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Tipo_de_trabajo = Tipo_de_trabajo;
        this.Tipo_de_contrato = Tipo_de_contrato;
        this.Inicio_contrato = Inicio_contrato;
        this.Fin_contrato = Fin_contrato;
        this.EPS = EPS;
        this.ARL = ARL;
        this.caja_compensacion = caja_compensacion;
    }


    public Empleados() {
        
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

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public String getTipo_de_trabajo() {
        return Tipo_de_trabajo;
    }

    public void setTipo_de_trabajo(String Tipo_de_trabajo) {
        this.Tipo_de_trabajo = Tipo_de_trabajo;
    }

    public String getTipo_de_contrato() {
        return Tipo_de_contrato;
    }

    public void setTipo_de_contrato(String Tipo_de_contrato) {
        this.Tipo_de_contrato = Tipo_de_contrato;
    }

    public String getInicio_contrato() {
        return Inicio_contrato;
    }

    public void setInicio_contrato(String Inicio_contrato) {
        this.Inicio_contrato = Inicio_contrato;
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
        return caja_compensacion;
    }

    public void setCaja_compensacion(String caja_compensacion) {
        this.caja_compensacion = caja_compensacion;
    }

    java.sql.Connection conn = Conexion.getConnection();

    Statement st;
    ResultSet rs;
    
    public ObservableList<Empleados> getEmpleadosdatos()  {
        ObservableList<Empleados> obs = FXCollections.observableArrayList();

        //abrimos la coneccion
        try {
            String MSQL = "Select ID,Nombre,CC,Edad,Direccion,Telefono,Tipo_de_trabajo,Tipo_de_contrato,Inicio_contrato,Fin_contrato,EPS,ARL,Caja_compensacion from Empleados";
            st = conn.createStatement();
            rs = st.executeQuery(MSQL);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nombre = rs.getString("Nombre");
                int idempleado = rs.getInt("CC");
                int edad = rs.getInt("Edad");
                String direccion = rs.getString("Direccion");
                int telefono = rs.getInt("Telefono");
                String trabajo = rs.getString("Tipo_de_trabajo");
                String contrato = rs.getString("Tipo_de_contrato");
                String inicio = rs.getString("Inicio_contrato");
                String fin = rs.getString("Fin_contrato");
                String eps = rs.getString("EPS");
                String arl = rs.getString("ARL");
                String caja = rs.getString("Caja_compensacion");
                
                Empleados C = new Empleados(id, nombre, idempleado, edad, telefono, telefono, direccion, trabajo, contrato, inicio, fin, eps, arl, caja);
                obs.add(C);
                
            }

        } catch (Exception e) {
            
        }
        return obs;
    }
    
    
}
