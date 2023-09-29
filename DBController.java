import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {
    public void update() {
=======
    // Database connection parameters
    String dbUrl = "jdbc:postgresql://db.svqkitqdhlcpelzuhcqg.supabase.co:5432/postgres";
    String username = "postgres";
    String password = "Fall2023Team7";

    // SQL query to retrieve data
    String query = "INSERT INTO player VALUES(1, Evan, Joe, Hitman);" ;

    // Register the JDBC Driver
    try {
        Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        // Handle the exception appropriately
    }

    try {
        // Establish a database connection
        Connection connection = DriverManager.getConnection(dbUrl, username, password);

        // Create a statement for the query
        Statement statement = connection.createStatement();

        // Execute the query and get the result set
        ResultSet resultSet = statement.executeQuery(query);

        // Iterate through the result set and print each row
        while (resultSet.next()) {
            int id = resultSet.getInt("id"); // Replace "id" with your column name
            String name = resultSet.getString("name"); // Replace "name" with your column name
            // Add more columns as needed

            System.out.println("ID: " + id + ", Name: " + name);
            // Print additional columns if necessary
        }

        // Close resources
        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle any SQL exceptions here
    }
}
}
>>>>>>> 4af1ad36bfb4aeda02bc35319c7644b5e6163410
