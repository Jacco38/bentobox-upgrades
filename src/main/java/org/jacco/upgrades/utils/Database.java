package org.jacco.upgrades.utils;

import org.jacco.upgrades.Upgrades;
import world.bentobox.bentobox.database.objects.Island;

import java.sql.*;

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

    public ResultSet getIsland(Island island) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM islands WHERE island_id = ?;")) {
            preparedStatement.setString(1, island.getUniqueId().toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        }
    }

    public void deleteIsland(Island island) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM islands WHERE island_id = ?;")) {
            preparedStatement.setString(1, island.getUniqueId().toString());
            preparedStatement.executeUpdate();
        }
    }

}
