/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Conexion {

    private static Connection con = null;

    private Conexion() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
            // conectarme a la base de datos
            // con = DriverManager.getConnection("jdbc:sqlite:e:\bdd\baseventas.db");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\IDEAPAD S330\\Desktop\\Sqlite\\Proyecto.db");
            //En la ruta de arriba Pones la inicial del disco donde descomprimiste el mysqlite studio y afuera
            //de esa carpeta metes la base que te voy a pasar, le copias la ruta y la pegas ahi arriba, al final pones el nombre del archivo
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {

        if (con == null) {
            try {
                new Conexion();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return con;
    }

    public static void Desconectar() throws SQLException {
        if (con != null) {
            con.close();
        }
    }

}

