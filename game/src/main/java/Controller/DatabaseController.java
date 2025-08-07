package Controller;

import Model.Database;
import Model.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public  class DatabaseController {
    private static Connection connection;

    static {
        try {
            connection = Database.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static void addNewPlayer(Player newPlayer) throws SQLException {

        Statement statement = connection.createStatement();
        String sqlCommand = "INSERT INTO players (id, name , username , password ,email) VALUES" +
                " (" + setId() + "," + String.format("'%s'", newPlayer.getName()) + "," + String.format("'%s'", newPlayer.getUsername()) + ","
                + String.format("'%s'", newPlayer.getPassword()) + "," + String.format("'%s'", newPlayer.getEmail()) + ")";
        statement.execute(sqlCommand);

    }
    public static void addNewPlant(int playerId , String plantName) throws SQLException {

        Statement statement = connection.createStatement();
        String sqlCommand = "INSERT INTO plants (id, name , playerId) VALUES" +
                " (" + setPlantsId() + "," + String.format("'%s'",plantName) + "," +  playerId + ")";
        statement.execute(sqlCommand);

    }
    public static void addNewLevel(int playerId , int level) throws SQLException {

        Statement statement = connection.createStatement();
        String sqlCommand = "INSERT INTO levels (id, level , playerId) VALUES" +
                " (" + setLevelsId() + "," + level + "," +  playerId + ")";
        statement.execute(sqlCommand);

    }


    public static ArrayList<Player> getPlayerList() throws SQLException {
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

    public static ArrayList<String> getPlantList(int playerId) throws SQLException {
        ArrayList<String> plantNameList = new ArrayList<>();
        String sqlCommand = "SELECT name FROM plants where playerId =" + playerId ;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        int columnCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {


            plantNameList.add(resultSet.getString(1));



        }
        return plantNameList;

    }


    public static ArrayList<Integer> getLevelList(int playerId) throws SQLException {
        ArrayList<Integer> levelList = new ArrayList<>();
        String sqlCommand = "SELECT level FROM levels where playerId =" + playerId ;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        int columnCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {


            levelList.add(Integer.parseInt(resultSet.getString(1)));



        }
        return levelList;

    }



    public static String updateInfo(int id, String columnName, String newValue) throws SQLException {
        String sqlCommand;
        Statement statement = connection.createStatement();

        newValue = String.format("'%s'", newValue);
        sqlCommand = "UPDATE players SET " + columnName + " = " + newValue + " WHERE id = " + id;
        statement.execute(sqlCommand);
        return id + " " + columnName + " is updated to " + newValue;
    }

    public static String updateInfo(int id, String columnName, int newValue) throws SQLException {
        String sqlCommand;
        Statement statement = connection.createStatement();


        sqlCommand = "UPDATE players SET " + columnName + " = " + newValue + " WHERE id = " + id;
        statement.execute(sqlCommand);
        return id + " " + columnName + " is updated to " + newValue;
    }




    public static int setId() throws SQLException {
        Statement statement = connection.createStatement();
        String getLastInsertIdQuery = "SELECT MAX(id) FROM players";
        ResultSet resultSet = statement.executeQuery(getLastInsertIdQuery);
        resultSet.next();
        int lastInsertedId = resultSet.getInt(1);
        return lastInsertedId + 1;
    }

    private static int setPlantsId() throws SQLException {
        Statement statement = connection.createStatement();
        String getLastInsertIdQuery = "SELECT MAX(id) FROM plants";
        ResultSet resultSet = statement.executeQuery(getLastInsertIdQuery);
        resultSet.next();
        int lastInsertedId = resultSet.getInt(1);
        return lastInsertedId + 1;
    }

    private static   int setLevelsId() throws SQLException {
        Statement statement = connection.createStatement();
        String getLastInsertIdQuery = "SELECT MAX(id) FROM levels";
        ResultSet resultSet = statement.executeQuery(getLastInsertIdQuery);
        resultSet.next();
        int lastInsertedId = resultSet.getInt(1);
        return lastInsertedId + 1;
    }


}
