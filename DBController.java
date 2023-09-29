import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {
    public void update() {
        // Database connection parameters
        String dbUrl = "jdbc:postgresql://db.svqkitqdhlcpelzuhcqg.supabase.co:5432/postgres";
        String username = "postgres";
        String password = "Fall2023Team7";

        // SQL query to retrieve data
        String query = "INSERT INTO player VALUES(1, 'Evan', 'Joe', 'Hitman')";

        // Register the JDBC Driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish a database connection
            connection = DriverManager.getConnection(dbUrl, username, password);

            // Create a statement for the query
            statement = connection.createStatement();

            // Execute the query
            statement.executeUpdate(query);

            System.out.println("Insertion successful.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        } finally {
            try {
                // Close resources in a finally block
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle any closing exceptions here
            }
        }
    }
}
