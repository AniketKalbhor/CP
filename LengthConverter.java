package college.CP;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LengthConverter extends JFrame
{
    private JComboBox<String> fromUnitComboBox, toUnitComboBox;
    private JTextField inputField, resultField;
    private JButton converterButton;

    private String[] lengthUnits = {"Millimeter", "Centimeter", "Kilometer", "Feet", "Inches"};
    private double conversionMatrix[][] =
            {
                    {1, 0.1, 0.000001, 0.00328084, 0.0393701},   // Millimeters to [mm, cm, km, ft, in]
                    {10, 1, 0.00001, 0.0328084, 0.393701},       // Centimeters to [mm, cm, km, ft, in]
                    {1000000, 100000, 1, 3280.84, 39370.1},     // Kilometers to [mm, cm, km, ft, in]
                    {304.8, 30.48, 0.0003048, 1, 12},            // Feet to [mm, cm, km, ft, in]
                    {25.4, 2.54, 0.0000254, 0.0833333, 1}        // Inches to [mm, cm, km, ft, in]
            };

    public LengthConverter()
    {
        setTitle("Length Converter");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel titleLabel = new JLabel("Length Converter", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        fromUnitComboBox = new JComboBox<>(lengthUnits);
        toUnitComboBox = new JComboBox<>(lengthUnits);

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
        try {
            int fromIndex = fromUnitComboBox.getSelectedIndex();
            int toIndex = toUnitComboBox.getSelectedIndex();
            double inputValue = Double.parseDouble(inputField.getText());

            double convertedValue = inputValue*conversionMatrix[fromIndex][toIndex];
            resultField.setText(String.format("%.4f", convertedValue));
        } catch (NumberFormatException ex)
        {
            resultField.setText("invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater((new Runnable() {
            @Override
            public void run() {
                LengthConverter converter = new LengthConverter();
                converter.setVisible(true);
            }
        }));
    }
}