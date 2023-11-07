package college.CP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionMonitorDB {

    private static final String URL = "jdbc:mysql://localhost:3306/unitconverter";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public TransactionMonitorDB()
    {
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failed to connect");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close()
    {
        try{
            if (connection != null)
            {
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}