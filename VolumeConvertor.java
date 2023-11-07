package college.CP;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VolumeConvertor extends JFrame
{
    private JComboBox<String> fromUnitComboBox, toUnitComboBox;
    private JTextField inputField, resultField;
    private JButton converterButton;

    private String[] lengthUnits = {"Milliliter", "Liter", "Gallon", "cubic meter", "cubic centimeter"};
    private double conversionMatrix[][] =
            {
                    {1, 0.001, 0.000001, 0.00328084, 0.0393701},   // Milliliters to [ml, l, gal, m³, cm³]
                    {1000, 1, 0.001, 3.28084, 39.3701},       // Liters to [ml, l, gal, m³, cm³]
                    {1000000, 1000, 1, 3280.84, 39370.1},     // Gallons to [ml, l, gal, m³, cm³]
                    {304.8, 0.03048, 0.0003048, 1, 12},            // cubic meter to [ml, l, gal, m³, cm³]
                    {25.4, 0.00254, 0.0000254, 0.0833333, 1}        // cubic centimeter to [ml, l, gal, m³, cm³]
            };

    public VolumeConvertor()
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
                VolumeConvertor converter = new VolumeConvertor();
                converter.setVisible(true);
            }
        }));
    }
}
