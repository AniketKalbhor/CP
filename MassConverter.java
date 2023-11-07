package college.CP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MassConverter extends JFrame {
    private JComboBox<String> fromUnitComboBox, toUnitComboBox;
    private JTextField inputField, resultField;
    private JButton convertButton;

    private String[] massUnits = {"Kilograms", "Grams", "Pounds", "Ounces"};
    private double[][] conversionMatrix = {
            {1, 1000, 2.20462, 35.27396},    // Kilograms to [Kg, g, lb, oz]
            {0.001, 1, 0.00220462, 0.03527396}, // Grams to [Kg, g, lb, oz]
            {0.453592, 453.592, 1, 16},       // Pounds to [Kg, g, lb, oz]
            {0.0283495, 28.3495, 0.0625, 1}   // Ounces to [Kg, g, lb, oz]
    };

    public MassConverter() {
        setTitle("Mass Unit Converter");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel titleLabel = new JLabel("Mass Unit Converter", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        fromUnitComboBox = new JComboBox<>(massUnits);
        toUnitComboBox = new JComboBox<>(massUnits);

        inputField = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });

        panel.add(titleLabel);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(new JLabel("From:"));
        panel.add(fromUnitComboBox);
        panel.add(new JLabel("To:"));
        panel.add(toUnitComboBox);
        panel.add(new JLabel("Enter Value:"));
        panel.add(inputField);
        panel.add(new JLabel("Result:"));
        panel.add(resultField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(convertButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void convert() {
        try {
            int fromIndex = fromUnitComboBox.getSelectedIndex();
            int toIndex = toUnitComboBox.getSelectedIndex();
            double inputValue = Double.parseDouble(inputField.getText());

            double convertedValue = inputValue * conversionMatrix[fromIndex][toIndex];
            resultField.setText(String.format("%.4f", convertedValue));
        } catch (NumberFormatException ex) {
            resultField.setText("Invalid input. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MassConverter converter = new MassConverter();
                converter.setVisible(true);
            }
        });
    }
}