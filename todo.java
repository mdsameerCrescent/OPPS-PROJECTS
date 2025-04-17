import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class TaskManager {
    private DefaultListModel<String> taskModel;

    public TaskManager() {
        taskModel = new DefaultListModel<>();
        loadTasks();
    }

    public DefaultListModel<String> getModel() {
        return taskModel;
    }

    public void addTask(String task) {
        taskModel.addElement(task);
    }

    public void removeTask(int index) {
        if (index >= 0) taskModel.remove(index);
    }

    public void saveTasks() {
        try (PrintWriter out = new PrintWriter("tasks.txt")) {
            for (int i = 0; i < taskModel.size(); i++) {
                out.println(taskModel.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        try (Scanner sc = new Scanner(new File("tasks.txt"))) {
            while (sc.hasNextLine()) {
                taskModel.addElement(sc.nextLine());
            }
        } catch (Exception ignored) {}
    }
}

class TodoUI extends JFrame {
    private TaskManager taskManager;
    private JList<String> taskList;
    private JTextField taskInput;

    public TodoUI() {
        taskManager = new TaskManager();
        setTitle("To-Do List (OOP)");
        setLayout(new BorderLayout());

        taskList = new JList<>(taskManager.getModel());
        add(new JScrollPane(taskList), BorderLayout.CENTER);

        taskInput = new JTextField();
        JButton addButton = new JButton("Add");
        JButton delButton = new JButton("Delete");

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);
        add(delButton, BorderLayout.NORTH);

        addButton.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                taskManager.addTask(task);
                taskInput.setText("");
            }
        });

        delButton.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            taskManager.removeTask(selected);
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                taskManager.saveTasks();
                System.exit(0);
            }
        });

        setSize(300, 400);
        setVisible(true);
    }
}

public class TodoApp {
    public static void main(String[] args) {
        new TodoUI();
    }
}
