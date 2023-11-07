package college.CP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AngleConverter extends JFrame
{
    private JComboBox<String> fromUnitComboBox, toUnitComboBox;
    private JTextField inputField, resultField;
    private JButton converterButton;

    private String[] angleUnits = {"degree", "radians"};

    private double conversionMatrix[][] =
            {
                    {1, Math.PI / 180},   // Degrees to [degrees, radians]
                    {180 / Math.PI, 1}    // Radians to [degrees, radians]
            };

    public AngleConverter()
    {
        setTitle("Angle Converter");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel titleLabel = new JLabel("Angle converter", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        fromUnitComboBox = new JComboBox<>(angleUnits);
        toUnitComboBox = new JComboBox<>(angleUnits);

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
        panel.add(new JLabel("Enter value"));
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
        } catch(NumberFormatException ex) {
            resultField.setText("invalid input");
    }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater((new Runnable() {
            @Override
            public void run()
            {
                AngleConverter converter = new AngleConverter();
                converter.setVisible(true);
            }
        }));
    }
}
