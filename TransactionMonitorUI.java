package college.CP;

import javax.swing.*;
import java.awt.*;

public class TransactionMonitorUI extends JFrame {

    private JPanel panel;

    public TransactionMonitorUI(TransactionInsertion transactionInsertion)
    {
        setTitle("Transaction Monitor");
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JTextField transactionIdField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField amountField = new JTextField();

        panel.add(new JLabel("Transaction ID: "));
        panel.add(transactionIdField);

        panel.add(new JLabel("Name: "));
        panel.add(nameField);

        panel.add(new JLabel("Amount transacted: "));
        panel.add(amountField);

        JButton addNewDeposit = new JButton("Deposit");
        addNewDeposit.addActionListener(e ->
        {
            String transactionID = transactionIdField.getText();
            String name = nameField.getText();
            double amount = Double.parseDouble(amountField.getText());
            TransactionInsertion.deposit(transactionID, name, amount);
        });

        JButton addNewWithdrawal = new JButton("Withdraw");
        addNewWithdrawal.addActionListener(e ->
        {
            String transactionID = transactionIdField.getText();
            double amount = Double.parseDouble(amountField.getText());
            transactionInsertion.withdraw(transactionID, amount);
        });

        panel.add(addNewDeposit);
        panel.add(addNewWithdrawal);
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TransactionMonitorDB transactionMonitorDB = new TransactionMonitorDB();
            TransactionInsertion transactionInsertion = new TransactionInsertion(transactionMonitorDB);
            new TransactionMonitorUI(transactionInsertion).setVisible(true);
        });
    }

}