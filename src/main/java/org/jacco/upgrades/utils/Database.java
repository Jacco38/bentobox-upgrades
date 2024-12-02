package org.jacco.upgrades.utils;

import world.bentobox.bentobox.database.objects.Island;

import java.sql.*;

public class Database {

    private final Connection connection;

    public Database(String path) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + path);
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

}
