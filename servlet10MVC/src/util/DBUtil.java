package util;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {
    private DBUtil(){

    }
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.jdbc");
    private static String driver = resourceBundle.getString("driver");
    private static String url = resourceBundle.getString("url");
    private static String user = resourceBundle.getString("user");
    private static String password = resourceBundle.getString("password");

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url,user,password);
        return connection;
    }

    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
