//Authors: Evan Bonar, David Nystrom
//Use command "java -cp postgresql-42.6.0.jar DBController.java" to run in terminal


import java.sql.*;

public class DBController
{
    String dbUrl;
    String username;
    String password;
    Driver driver;
    Connection connection;
    public DBController()
    {
        // Database connection parameters
        dbUrl = "jdbc:postgresql://db.svqkitqdhlcpelzuhcqg.supabase.co:5432/postgres";
        username = "postgres";
        password = "Fall2023Team7";
        
        // Register the JDBC Driver
         try {
        Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
        // Handle the exception appropriately
        }
        /*
        String query = "INSERT INTO player VALUES(2, 'Hitman');" ;
        String query1 = "SELECT codename FROM player WHERE id = \"\"";
        */
    }

    public boolean establishConnection()
    {
        try
        {
            Driver driver = DriverManager.getDriver(this.dbUrl);
            DriverManager.registerDriver(driver);
            this.connection = DriverManager.getConnection(this.dbUrl, this.username, this.password);
        } 
        catch(SQLException e)
        {
            return false;
        }
        

        return true;
    }

    public boolean closeConnection()
    {
        if(this.connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                return false;
            }
        } 
        else {return false;}

        return true;
    }

    public String queryForCodename(int id)
    {
        String codename = null;
        String query = "SELECT codename FROM player WHERE id=\'"+id+"\';";

        try {
            // Establish a database connection
            if(connection == null)
            {
                this.connection = DriverManager.getConnection(dbUrl, username, password);
            }

            if(connection != null)
            {
                // Create a statement for the query
                Statement statement = this.connection.createStatement();
                
                // Execute the query and get the result set
                ResultSet resultSet = statement.executeQuery(query);
                // Iterate through the result set and print each row
                while (resultSet.next()) {
                    codename = resultSet.getString("codename"); // Replace "name" with your column name
                }
                // Close resources
                resultSet.close();
                statement.close();
            } else {
                System.out.println("No connection to database.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }

        return codename;
    }

    public void insertNewRow(int id, String codename)
    {
        String query = "INSERT INTO player VALUES("+id+", '"+codename+"');" ;
        try {
            // Establish a database connection if one doesn't exist already
            if(connection == null)
            {
                this.connection = DriverManager.getConnection(dbUrl, username, password);
            }
            if(connection != null)
            {
                // Create a statement for the query
                Statement statement = this.connection.createStatement();
                
                // Execute the queryß
                statement.executeUpdate(query);
                
            } else {
                System.out.println("No connection to database.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }

    }




}