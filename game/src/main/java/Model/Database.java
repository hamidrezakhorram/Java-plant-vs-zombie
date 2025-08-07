package Model;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {

    private static Database database;
    private String url = "jdbc:mysql://localhost:3306/plants_vs_zombies";
    private String username = "root";
    private String password = "";
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private Database() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    public static Database getInstance() throws SQLException {
        if (database == null) {
            database = new Database();
        }
        return database;
    }








}
