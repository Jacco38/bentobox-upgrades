package org.jacco.upgrades.utils;

import org.jacco.upgrades.Upgrades;
import world.bentobox.bentobox.database.objects.Island;

import java.sql.*;
import java.util.HashMap;

public class Database {

    private final Connection connection;
    private final Upgrades addon;

    public Database(String path, Upgrades addon) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        this.addon = addon;
        try (Statement statement = connection.createStatement();) {
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS islands (
                    island_id TEXT PRIMARY KEY,
                    range_level INTEGER,
                    hopper_level INTEGER,
                    member_level INTEGER);""");
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void addIsland(Island island) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO islands (island_id, range_level, hopper_level, member_level) VALUES (?, ?, ?, ?);")) {
            preparedStatement.setString(1, island.getUniqueId().toString());
            preparedStatement.setInt(2, 1);
            preparedStatement.setInt(3, 1);
            preparedStatement.setInt(4, 1);
            preparedStatement.executeUpdate();

        }
    }

    public boolean getIsland(Island island) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM islands WHERE island_id = ?;")) {
            preparedStatement.setString(1, island.getUniqueId().toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    public void deleteIsland(Island island) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM islands WHERE island_id = ?;")) {
            preparedStatement.setString(1, island.getUniqueId().toString());
            preparedStatement.executeUpdate();
        }
    }

    public HashMap<String, Integer> getIslandLevels(Island island) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM islands WHERE island_id = ?;")) {
            preparedStatement.setString(1, island.getUniqueId().toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                HashMap<String, Integer> levels = new HashMap<>();
                levels.put("range", resultSet.getInt("range_level"));
                levels.put("hopper", resultSet.getInt("hopper_level"));
                levels.put("member", resultSet.getInt("member_level"));
                return levels;
            }
        }
        return null;
    }

}
