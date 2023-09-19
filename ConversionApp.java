package college.CP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversionApp extends JFrame{
    public ConversionApp()
    {
        setTitle("Unit Conversion App and Transaction manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,3, 10, 10));

        JButton massButton = new JButton("Mass Converter");
        JButton lengthButton = new JButton("Length Converter");
        JButton areaButton = new JButton("Area Converter");
        JButton angleButton = new JButton("Angle Converter");
        JButton timeButton = new JButton("Time Converter");
        JButton transactionButton = new JButton("Transaction Monitor");

        massButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMassConverter();
            }
        });

        lengthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLengthConverter();
            }
        });

        areaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAreaConverter();
            }
        });

        angleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAngleConverter();
            }
        });

        timeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTimeConverter();
            }
        });

        transactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTransactionMonitor();
            }
        });

        panel.add(massButton);
        panel.add(lengthButton);
        panel.add(areaButton);
        panel.add(angleButton);
        panel.add(timeButton);
        panel.add(transactionButton);

        add(panel, BorderLayout.CENTER);
    }
    private void openMassConverter()
    {
        MassConverter massConverter = new MassConverter();
        massConverter.setVisible(true);
    }

    private void openLengthConverter()
    {
        LengthConverter lengthConverter = new LengthConverter();
        lengthConverter.setVisible(true);
    }

    private void openAreaConverter()
    {
        AreaConverter areaConverter = new AreaConverter();
        areaConverter.setVisible(true);
    }

    private void openAngleConverter()
    {
        AngleConverter angleConverter = new AngleConverter();
        angleConverter.setVisible(true);
    }

    private void openTimeConverter()
    {
        TimeConvertor timeConverter = new TimeConvertor();
        timeConverter.setVisible(true);
    }

    private void openTransactionMonitor()
    {
        TransactionMonitorDB transactionMonitorDB = new TransactionMonitorDB();
        TransactionInsertion transactionInsertion = new TransactionInsertion(transactionMonitorDB);
        new TransactionMonitorUI(transactionInsertion).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConversionApp app = new ConversionApp();
                app.setVisible(true);
            }
        });
    }
}