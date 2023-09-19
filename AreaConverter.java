package college.CP;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AreaConverter extends JFrame
{
    private JComboBox<String> fromUnitComboBox, toUnitComboBox;
    private JTextField inputField, resultField;
    private JButton converterButton;

    private String[] areaUnits = {"Square Meter", "Square Kilometer", "Square Mile", "Square Yard", "Square Foot"};
    private double conversionMatrix[][] =
            {
                    {1, 0.000001, 3.861e-7, 1.19599, 10.7639},   // Square Meter to [m², km², mi², yd², ft²]
                    {1000000, 1, 0.386102, 1195990, 10763910.4}, // Square Kilometer to [m², km², mi², yd², ft²]
                    {2589988.11, 2.58998811, 1, 3097600, 27878400}, // Square Mile to [m², km², mi², yd², ft²]
                    {0.836127, 8.36127e-7, 3.2283e-7, 1, 9},   // Square Yard to [m², km², mi², yd², ft²]
                    {0.092903, 9.2903e-8, 3.587e-8, 0.111111, 1}  // Square Foot to [m², km², mi², yd², ft²]
            };

    public AreaConverter()
    {
        setTitle("Area Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel titleLabel = new JLabel("Area Converter", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        fromUnitComboBox = new JComboBox<>(areaUnits);
        toUnitComboBox = new JComboBox<>(areaUnits);

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
                AreaConverter converter = new AreaConverter();
                converter.setVisible(true);
            }
        }));
    }
}