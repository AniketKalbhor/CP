package college.CP;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class TransactionTable extends JFrame {
    private JTable table;
    private TransactionMonitorDB transactionMonitorDB;

    public TransactionTable() {
        setTitle("SQL Table Display");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        transactionMonitorDB = new TransactionMonitorDB();
    }

    public void displayTable() {
        setTitle("Transaction Table Display");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Connect to the database and populate the table with data
        try {
            Connection connection = transactionMonitorDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM transactions");

            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"transactionID", "name", "amount"}, 0);

            while (resultSet.next()) {
                String transactionID = resultSet.getString("transactionID");
                String name = resultSet.getString("name");
                double amount = resultSet.getDouble("amount");
                tableModel.addRow(new Object[]{transactionID, name, amount});
            }

            table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            getContentPane().add(scrollPane, BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TransactionMonitorDB transactionMonitorDB = new TransactionMonitorDB(); // Initialize your TransactionMonitorDB
            TransactionTable app = new TransactionTable();
            app.displayTable(); // Call the displayTable method
            app.setVisible(true);
        });
    }
}
