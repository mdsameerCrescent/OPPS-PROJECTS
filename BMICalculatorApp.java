import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BMI {
    private double weight;
    private double height;

    public BMI(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    public double calculateBMI() {
        return weight / (height * height);
    }

    public String getCategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24.9) {
            return "Normal weight";
        } else if (bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
}

class BMICalculatorUI extends JFrame {
    private JTextField weightField;
    private JTextField heightField;
    private JButton calculateButton;
    private JLabel resultLabel;

    public BMICalculatorUI() {
        setTitle("BMI Calculator");
        setLayout(new FlowLayout());

        weightField = new JTextField(5);
        heightField = new JTextField(5);
        calculateButton = new JButton("Calculate BMI");
        resultLabel = new JLabel("Result: ");

        calculateButton.addActionListener(e -> {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            BMI bmi = new BMI(weight, height);
            double result = bmi.calculateBMI();
            resultLabel.setText("BMI: " + result + " (" + bmi.getCategory(result) + ")");
        });

        add(new JLabel("Weight (kg):"));
        add(weightField);
        add(new JLabel("Height (m):"));
        add(heightField);
        add(calculateButton);
        add(resultLabel);

        setSize(300, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new BMICalculatorUI();
    }
}
