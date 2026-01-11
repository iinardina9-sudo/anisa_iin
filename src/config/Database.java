package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:surat.db";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Initialize SQLite JDBC driver
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(URL);
                createTable();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS surat (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "nomor_surat TEXT NOT NULL," +
                     "tanggal TEXT NOT NULL," +
                     "pengirim TEXT NOT NULL," +
                     "perihal TEXT NOT NULL," +
                     "jenis_surat TEXT NOT NULL" +
                     ");";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
