package com.example.demo;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
     public static Connection getConnection() throws SQLException, URISyntaxException {
        String dbUri = System.getenv("DB_URI");
        String dbPort = System.getenv("DB_PORT");
        String dbName = System.getenv("DB_NAME");

        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");
        String dbUrl = "jdbc:postgresql://" + dbUri + ':' + dbPort + "/" + dbName + "?serverTimezone=UTC";

        System.out.println(dbUrl);

        return DriverManager.getConnection(dbUrl, username, password);
    }
}
