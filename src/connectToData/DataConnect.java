package connectToData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ConsumeAPI.Fact;

public class DataConnect {
    public static void main(String[] args) throws Exception {
        Fact m1 = new Fact();

        String fact = (String) m1.object().get("fact");

        long length = (long) m1.object().get("length");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/api_data";
            String username = "root";
            String password = "";
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) {
                System.out.println("Successful connection!");

                PreparedStatement statement;
                statement = conn.prepareStatement("Insert into statement( Fact, length) values (?, ?)");
                statement.setString(1, fact);
                statement.setLong(2, length);

                statement.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}