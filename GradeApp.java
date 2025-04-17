import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Student {
    String name;
    double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String toString() {
        return name + " - " + grade;
    }

    public double getGrade() {
        return grade;
    }
}

class GradeManager {
    private java.util.List<Student> students = new ArrayList<>();

    public void addStudent(String name, double grade) {
        students.add(new Student(name, grade));
    }

    public java.util.List<Student> getStudents() {
        return students;
    }

    public double getAverage() {
        return students.stream().mapToDouble(Student::getGrade).average().orElse(0);
    }

    public double getMax() {
        return students.stream().mapToDouble(Student::getGrade).max().orElse(0);
    }

    public double getMin() {
        return students.stream().mapToDouble(Student::getGrade).min().orElse(0);
    }
}

class GradeManagerUI extends JFrame {
    private GradeManager manager = new GradeManager();
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> list = new JList<>(model);

    public GradeManagerUI() {
        setTitle("Grade Manager (OOP)");
        JTextField nameField = new JTextField();
        JTextField gradeField = new JTextField();
        JButton addBtn = new JButton("Add");

        addBtn.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                double grade = Double.parseDouble(gradeField.getText().trim());
                manager.addStudent(name, grade);
                model.addElement(name + " - " + grade);
                nameField.setText("");
                gradeField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        });

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);
        inputPanel.add(addBtn);

        JButton statsBtn = new JButton("Show Stats");
        statsBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, String.format(
                "Average: %.2f\nMax: %.2f\nMin: %.2f",
                manager.getAverage(), manager.getMax(), manager.getMin()
            ));
        });

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(statsBtn, BorderLayout.SOUTH);
        setSize(400, 300);
        setVisible(true);
    }
}

public class GradeApp {
    public static void main(String[] args) {
        new GradeManagerUI();
    }
}
