package org.jacco.upgrades.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private final Connection connection;

    public Database(String path) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        try (Statement statement = connection.createStatement();) {
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS islands (
                    island_id TEXT PRIMARY KEY,
                    range_level VARCHAR(20),
                    hopper_level VARCHAR(20),
                    member_level VARCHAR(20));""");
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
