import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CalculatorLogic {
    private double result = 0;
    private String operator = "=";
    private boolean start = true;

    public String process(String input, String currentText) {
        if ("0123456789.".contains(input)) {
            if (start) {
                start = false;
                return input;
            } else {
                return currentText + input;
            }
        } else {
            double x = Double.parseDouble(currentText);
            calculate(x);
            operator = input;
            start = true;
            return String.valueOf(result);
        }
    }

    private void calculate(double n) {
        switch (operator) {
            case "+": result += n; break;
            case "-": result -= n; break;
            case "*": result *= n; break;
            case "/": result /= n; break;
            case "=": result = n; break;
        }
    }
}

class CalculatorUI extends JFrame {
    private JTextField display;
    private CalculatorLogic logic;

    public CalculatorUI() {
        logic = new CalculatorLogic();
        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 4));
        String[] buttons = {"7", "8", "9", "/", "4", "5", "6", "*",
                            "1", "2", "3", "-", "0", ".", "=", "+"};
        for (String text : buttons) {
            JButton b = new JButton(text);
            b.addActionListener(e -> {
                String result = logic.process(e.getActionCommand(), display.getText());
                display.setText(result);
            });
            panel.add(b);
        }

        add(panel, BorderLayout.CENTER);
        setTitle("Calculator (OOP)");
        setSize(300, 400);
        setVisible(true);
    }
}

public class CalculatorApp {
    public static void main(String[] args) {
        new CalculatorUI();
    }
}
