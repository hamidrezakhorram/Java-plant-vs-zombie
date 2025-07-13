package Model;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Database {


    private String url = "jdbc:mysql://localhost:3306/plants_vs_zombies";
    private String username = "root";
    private String password = "";
    private Connection connection;

    public Database() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    public String add(String name, String birthDate, String contractType, int payment) throws SQLException {


        name = String.format("'%s'", name);
        birthDate = String.format("'%s'", birthDate);
        contractType = String.format("'%s'", contractType);
        String sqlCommand = "INSERT INTO programmers (id, name, birthDate, contractType ,payment) VALUES (" + setId() + "," + name + "," + birthDate + "," + contractType + "," + payment + ")";
        Statement statement = connection.createStatement();
        statement.execute(sqlCommand);
        return "new item added to table";
    }

//    public String addNewPlayer(Player newPlayer) throws SQLException {
//       // skillName = String.format("'%s'", skillName);
//        Statement statement = connection.createStatement();
//        String sqlCommand = "INSERT INTO players (id, name , username , password ,) VALUES (" + setSkillId() + "," + skillName + "," + programmerId + ")";
//        statement.execute(sqlCommand);
//        return "new skill added for programmer";
//    }

    public String update(int id, String columnName, String newValue) throws SQLException {
        String sqlCommand;
        Statement statement = connection.createStatement();
        if (columnName.equals("id")) {
            return "you not allowed to update this column";
        } else if (columnName.equals("payment")) {
            sqlCommand = "UPDATE programmers SET payment = " + Integer.parseInt(newValue) + " WHERE id = " + id;
            statement.execute(sqlCommand);
        }
        newValue = String.format("'%s'", newValue);
        sqlCommand = "UPDATE programmers SET " + columnName + " = " + newValue + " WHERE id = " + id;
        statement.execute(sqlCommand);
        return id + " " + columnName + " is updated to " + newValue;
    }

    public String increase(String name1, String name2, int increaspayment) throws SQLException {
        name2 = String.format("'%s'", name2);
        String sqlCommand;
        if (name1.equals("fulltime")) {
            sqlCommand = "UPDATE programmers SET payment = payment + " + increaspayment + " WHERE name = " + name2 + " AND contractType = 'fulltime' ";
        } else {
            name1 = String.format("'%s'", name1);

            sqlCommand = "UPDATE programmers SET payment = payment + " + increaspayment + " WHERE name = " + name1 + " OR name = " + name2;


        }
        Statement statement = connection.createStatement();
        statement.execute(sqlCommand);
        return "payment increased";

    }

    public String delete(int payment) throws SQLException {
        String sqlCommand = "DELETE FROM programmers WHERE payment < " + payment;
        Statement statement = connection.createStatement();
        statement.execute(sqlCommand);
        return "delete from programmers WHERE payment was less than " + payment;
    }

//    public String show(String operation, String extra) throws SQLException {
//        String value = "";
//
//        String sqlCommand = "";
//        if (operation.equals("all")) {
//            sqlCommand = "SELECT * FROM programmers";
//        } else if (operation.equals("fulltime")) {
//            sqlCommand = "SELECT name , payment FROM programmers WHERE contractType = " + "'fulltime'";
//        } else if (operation.equals("parttime")) {
//            sqlCommand = "SELECT name , payment FROM programmers WHERE contractType = " + "'parttime'";
//        } else if (operation.equals("skills")) {
//            sqlCommand = "SELECT programmers.name AS programmer_name , skills.name FROM programmers INNER JOIN skills ON skills.programmerid = programmers.id WHERE skills.programmerId = " + extra;
//
//        } else if (operation.equals("id")) {
//            sqlCommand = "SELECT name , birthDate FROM programmers WHERE id = " + Integer.parseInt(extra);
//        }
//
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(sqlCommand);
//        StringBuilder result = new StringBuilder(" ");
//        int columnCount = resultSet.getMetaData().getColumnCount();
//        while (resultSet.next()) {
//            for (int i = 0; i < columnCount; i++) {
//                String columnName = resultSet.getMetaData().getColumnLabel(i + 1);
//                if (columnName.equals("birthDate") && operation.equals("id")) {
//                    result.append(calculateAge(resultSet.getString("birthDate")));
//                } else {
//                    result.append(resultSet.getString(columnName));
//                }
//
//                result.append(" ");
//
//            }
//            result.append("\n ");
//        }
//
//        return result.toString();
//    }


    private int setId() throws SQLException {
        Statement statement = connection.createStatement();
        String getLastInsertIdQuery = "SELECT MAX(id) FROM players";
        ResultSet resultSet = statement.executeQuery(getLastInsertIdQuery);
        resultSet.next();
        int lastInsertedId = resultSet.getInt(1);
        return lastInsertedId + 1;
    }

    private int setSkillId() throws SQLException {
        Statement statement = connection.createStatement();
        String getLastInsertIdQuery = "SELECT MAX(id) FROM skills";
        ResultSet resultSet = statement.executeQuery(getLastInsertIdQuery);
        resultSet.next();
        int lastInsertedId = resultSet.getInt(1);
        return lastInsertedId + 1;
    }




}
