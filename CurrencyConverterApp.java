package college.CP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class CurrencyConverter {
    private static final String API_URL = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_PBFY01Z7o0dnIgEZukzhxq5IW9PxRM2xGgAY9jpy";
    private static String apiKey;

    static
    {
        try (InputStream input = new FileInputStream("config.properties")){
            Properties prop = new Properties();
            prop.load(input);
            apiKey = prop.getProperty("api.key");
        } catch (IOException ex){
            System.err.println("Failed to read api key");
            ex.printStackTrace();
            apiKey = "";
        }
    }

    public static double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        try {
            String urlStr = API_URL + fromCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONObject rates = jsonObject.getJSONObject("rates");
                double fromRate = rates.getDouble(fromCurrency);
                double toRate = rates.getDouble(toCurrency);

                return amount * (toRate / fromRate);
            } else {
                System.err.println("Error: " + connection.getResponseCode() + " " + connection.getResponseMessage());
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0.0;
    }
}

public class CurrencyConverterApp extends JFrame {

    private JTextField amountField;
    private JComboBox<String> fromCurrencyBox;
    private JComboBox<String> toCurrencyBox;
    private JLabel resultLabel;

    private CurrencyConverterApp() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        panel.add(amountField);

        panel.add(new JLabel("From Currency:"));
        fromCurrencyBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "JPY", "AUD"});
        panel.add(fromCurrencyBox);

        panel.add(new JLabel("To Currency:"));
        toCurrencyBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "JPY", "AUD"});
        panel.add(toCurrencyBox);

        JButton convertButton = new JButton("Convert");
        panel.add(convertButton);

        resultLabel = new JLabel("Result: ");
        panel.add(resultLabel);

        add(panel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromCurrency = fromCurrencyBox.getSelectedItem().toString();
                String toCurrency = toCurrencyBox.getSelectedItem().toString();
                double amount = Double.parseDouble(amountField.getText());

                double result = CurrencyConverter.convertCurrency(fromCurrency, toCurrency, amount);
                resultLabel.setText("Result: " + result);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverterApp app = new CurrencyConverterApp();
            app.setVisible(true);
        });
    }
}