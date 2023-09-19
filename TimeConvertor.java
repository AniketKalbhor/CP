package college.CP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeConvertor extends JFrame {
    private JComboBox<String> fromUnitComboBox, toUnitComboBox;
    private JTextField inputField, resultField;
    private JButton converterButton;

    private String[] timeUnits = {"seconds", "minutes", "hours", "days", "weeks"};

    private double conversionMatrix[][] ={
            {1, 0.0166667, 0.000277778, 1.15741e-5, 1.65344e-6},     // Seconds to [s, min, hr, day, week]
            {60, 1, 0.0166667, 0.000694444, 9.9206e-5},               // Minutes to [s, min, hr, day, week]
            {3600, 60, 1, 0.0416667, 0.00595238},                    // Hours to [s, min, hr, day, week]
            {86400, 1440, 24, 1, 0.142857},                          // Days to [s, min, hr, day, week]
            {604800, 10080, 168, 7, 1}                               // Weeks to [s, min, hr, day, week]
    };

    public TimeConvertor()
    {
        setTitle("Time Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel titleLabel = new JLabel("Time Converter", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        fromUnitComboBox = new JComboBox<>(timeUnits);
        toUnitComboBox = new JComboBox<>(timeUnits);

        inputField = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        converterButton = new JButton("Convert");
        converterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });

        panel.add(titleLabel);
        panel.add(new JLabel());
        panel.add(new JLabel("From: "));
        panel.add(fromUnitComboBox);
        panel.add(new JLabel("To: "));
        panel.add(toUnitComboBox);
        panel.add(new JLabel("Enter value: "));
        panel.add(inputField);
        panel.add(new JLabel("Result: "));
        panel.add(resultField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(converterButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void convert()
    {
        try
        {
            int fromIndex = fromUnitComboBox.getSelectedIndex();
            int toIndex = toUnitComboBox.getSelectedIndex();
            double inputValue = Double.parseDouble(inputField.getText());

            double convertedValue = inputValue*conversionMatrix[fromIndex][toIndex];
            resultField.setText(String.format("%.4f", convertedValue));
        } catch (NumberFormatException ex)
        {
            System.out.println("Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TimeConvertor converter = new TimeConvertor();
                converter.setVisible(true);
            }
        });
    }
}