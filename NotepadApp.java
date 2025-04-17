import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class NotepadLogic {
    private JTextArea textArea;

    public NotepadLogic(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void saveToFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(textArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            textArea.read(reader, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class NotepadUI extends JFrame {
    private JTextArea textArea;
    private NotepadLogic logic;

    public NotepadUI() {
        textArea = new JTextArea();
        logic = new NotepadLogic(textArea);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");

        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                logic.openFile(file);
            }
        });

        saveItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                logic.saveToFile(file);
            }
        });

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        add(scrollPane, BorderLayout.CENTER);
        setSize(400, 400);
        setVisible(true);
        setTitle("Notepad (OOP)");
    }

    public static void main(String[] args) {
        new NotepadUI();
    }
}
