//Authors: Evan Bonar, David Nystrom
//Use command "java -cp postgresql-42.6.0.jar DBController.java" to run in terminal


import java.sql.*;

public class DBController
    {
    public static void main(String[] args) {
        // Database connection parameters
        String dbUrl = "jdbc:postgresql://db.svqkitqdhlcpelzuhcqg.supabase.co:5432/postgres";
        String username = "postgres";
        String password = "Fall2023Team7";
        String query = "INSERT INTO player VALUES(1, 'Evan', 'Joe', 'Hitman');" ;
        //String query = "INSERT INTO player VALUES(4, 'Player3', 'Threesie', 'Killer');" ;
        //String query = "INSERT INTO player VALUES(5, 'PlayerFour', 'Foursie', 'Queen');" ;
        // Register the JDBC Driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        
        try {
            // Establish the connection
            Driver driver = DriverManager.getDriver(dbUrl);

            DriverManager.registerDriver(driver);
            
            Connection connection = DriverManager.getConnection(dbUrl, username, password);

            if (connection != null) {
                System.out.println("Connected to Supabase!");
                // Perform database operations here
                // Don't forget to close the connection when you're done

                connection.close();
            } else {
                System.out.println("Failed to connect to Supabase.");
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to Supabase.");
            e.printStackTrace();
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