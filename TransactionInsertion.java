package college.CP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionInsertion {

    private static TransactionMonitorDB transactionMonitorDB;

    public TransactionInsertion(TransactionMonitorDB transactionMonitorDB)
    {
        this.transactionMonitorDB = transactionMonitorDB;
    }

    public static void deposit(String transactionID, String name, double amount)
    {
        String query = "INSERT INTO transactions (transactionID, name, amount) VALUES (?, ?, ?)";

        double sum = 0;
        sum = sum + amount;

        try (Connection connection = transactionMonitorDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, transactionID);
            preparedStatement.setString(2, name);
            preparedStatement.setDouble(3, sum);

            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Transaction recorded");
        } catch(SQLException e) {

            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

    }

    public static void withdraw(String transactionID, double amount)
    {
        String query = "UPDATE transactions SET amount = amount - ? WHERE transactionID = ?";

        try (Connection connection = transactionMonitorDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, transactionID);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0)
            {
                connection.commit();
                System.out.println("Withdrawal successful");
            }
            else
            {
                connection.rollback();
                System.out.println("Withdrawal failed");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public void close()
    {
        transactionMonitorDB.close();
    }

    public static void main(String[] args) {
        TransactionMonitorDB transactionMonitorDB = new TransactionMonitorDB();
        TransactionInsertion transactionInsertion = new TransactionInsertion(transactionMonitorDB);
        transactionInsertion.close();
    }

}