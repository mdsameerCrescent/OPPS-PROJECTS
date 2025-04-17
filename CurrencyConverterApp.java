import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class CurrencyConverter {
    private Map<String, Double> rates;

    public CurrencyConverter() {
        rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("EUR", 0.85);
        rates.put("INR", 75.0);
        rates.put("GBP", 0.75);
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        double fromRate = rates.get(fromCurrency);
        double toRate = rates.get(toCurrency);
        return amount * (toRate / fromRate);
    }

    public Set<String> getCurrencies() {
        return rates.keySet();
    }
}

class ConverterUI extends JFrame {
    private CurrencyConverter converter;
    private JTextField amountField;
    private JComboBox<String> fromCurrencyBox;
    private JComboBox<String> toCurrencyBox;
    private JLabel resultLabel;

    public ConverterUI() {
        converter = new CurrencyConverter();

        setLayout(new FlowLayout());
        amountField = new JTextField(10);
        fromCurrencyBox = new JComboBox<>(converter.getCurrencies().toArray(new String[0]));
        toCurrencyBox = new JComboBox<>(converter.getCurrencies().toArray(new String[0]));
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ");

        convertButton.addActionListener(e -> {
            double amount = Double.parseDouble(amountField.getText());
            String fromCurrency = (String) fromCurrencyBox.getSelectedItem();
            String toCurrency = (String) toCurrencyBox.getSelectedItem();
            double result = converter.convert(amount, fromCurrency, toCurrency);
            resultLabel.setText("Result: " + result);
        });

        add(new JLabel("Amount:"));
        add(amountField);
        add(new JLabel("From:"));
        add(fromCurrencyBox);
        add(new JLabel("To:"));
        add(toCurrencyBox);
        add(convertButton);
        add(resultLabel);

        setTitle("Currency Converter (OOP)");
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ConverterUI();
    }
}
