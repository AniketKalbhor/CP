package college.CP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TransactionMonitorUI extends JFrame {

    private JPanel panel;
    private static TransactionMonitorDB transactionMonitorDB;

    public TransactionMonitorUI(TransactionInsertion transactionInsertion)
    {
        setTitle("Transaction Monitor");
        setSize(400, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JTextField transactionIdField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField amountField = new JTextField();

        transactionIdField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = transactionIdField.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_DELETE) {
                    transactionIdField.setEditable(true);
                } else {
                    transactionIdField.setEditable(false);
                    JOptionPane.showMessageDialog(panel, "Enter integer only");
                }
            }
        });

        amountField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = amountField.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_DELETE) {
                    amountField.setEditable(true);
                } else {
                    amountField.setEditable(false);
                    JOptionPane.showMessageDialog(panel, "Enter integer only");
                }
            }
        });

        setVisible(true);
        panel.add(new JLabel("      Transaction ID: "));
        panel.add(transactionIdField);

        panel.add(new JLabel("      Name/Purpose: "));
        panel.add(nameField);

        panel.add(new JLabel("      Amount transacted: "));
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

        JButton displayTable = new JButton("Display Transactions");
        displayTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayT();
            }
            void displayT() {
                TransactionTable tTable = new TransactionTable();
                tTable.displayTable(); // Call the displayTable method
                tTable.setVisible(true);
            }

        });

        panel.add(addNewDeposit);
        panel.add(addNewWithdrawal);
        panel.add(displayTable);
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