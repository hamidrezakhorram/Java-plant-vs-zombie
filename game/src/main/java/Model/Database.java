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

    private Database() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    public static Database getInstance() throws SQLException {
        if (database == null) {
            database = new Database();
        }
        return database;
    }


    public void addNewPlayer(Player newPlayer) throws SQLException {

        Statement statement = connection.createStatement();
        String sqlCommand = "INSERT INTO players (id, name , username , password ,email) VALUES" +
                " (" + setId() + "," + String.format("'%s'", newPlayer.getName()) + "," + String.format("'%s'", newPlayer.getUsername()) + ","
                + String.format("'%s'", newPlayer.getPassword()) + "," + String.format("'%s'", newPlayer.getEmail()) + ")";
        statement.execute(sqlCommand);

    }

    public ArrayList<Player> getPlayerList() throws SQLException {
        ArrayList<Player> playerList = new ArrayList<>();
        String sqlCommand = "SELECT id ,username , password , score , win , lost FROM players";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        int columnCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            Player player = new Player();
            for (int i = 0; i < columnCount; i++) {

                String columnName = resultSet.getMetaData().getColumnLabel(i + 1);
                if ( player.getId() == null) {
                    player.setId(resultSet.getInt(columnName));
                } else if (player.getUsername() == null) {
                    player.setUsername(resultSet.getString(columnName));
                } else if (player.getPassword() == null) {
                    player.setPassword(resultSet.getString(columnName));
                } else if ((Integer) player.getScore() == null) {
                    player.setScore(Integer.parseInt(resultSet.getString(columnName)));
                } else if ((Integer) player.getWin() == null) {
                    player.setWin(Integer.parseInt(resultSet.getString(columnName)));
                } else if ((Integer) player.getLoss() == null) {
                    player.setLoss(Integer.parseInt(resultSet.getString(columnName)));
                }

            }
            playerList.add(player);

        }
        return playerList;

    }

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
