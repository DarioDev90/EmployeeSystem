package com.dariodev.pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PruebaConexion {

    public static void main(String[] args) throws SQLException {

        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/employee_system?useTimeZone=true&serverTimeZone=UTC",
                "root",
                "1234");

        System.out.println("Cerrando la conexión");

        conexion.close();
    }
}
