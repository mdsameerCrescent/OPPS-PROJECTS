import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class FileExplorer {
    private JFileChooser fileChooser;

    public FileExplorer() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    }

    public void openFile() {
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "File Opened: " + file.getAbsolutePath());
        }
    }

    public void saveFile() {
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "File Saved: " + file.getAbsolutePath());
        }
    }
}

class FileExplorerUI extends JFrame {
    private FileExplorer explorer;
    private JButton openButton;
    private JButton saveButton;

    public FileExplorerUI() {
        explorer = new FileExplorer();
        openButton = new JButton("Open File");
        saveButton = new JButton("Save File");

        openButton.addActionListener(e -> explorer.openFile());
        saveButton.addActionListener(e -> explorer.saveFile());

        setLayout(new FlowLayout());
        add(openButton);
        add(saveButton);

        setTitle("File Explorer");
        setSize(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FileExplorerUI();
    }
}
