import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Stopwatch {
    private Timer timer;
    private int seconds;
    private JLabel label;

    public Stopwatch(JLabel label) {
        this.label = label;
        this.seconds = 0;
        this.timer = new Timer(1000, e -> {
            seconds++;
            label.setText(formatTime(seconds));
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        seconds = 0;
        label.setText(formatTime(seconds));
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}

class StopwatchUI extends JFrame {
    private Stopwatch stopwatch;
    private JLabel timerLabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;

    public StopwatchUI() {
        setLayout(new FlowLayout());

        timerLabel = new JLabel("00:00");
        stopwatch = new Stopwatch(timerLabel);
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        startButton.addActionListener(e -> stopwatch.start());
        stopButton.addActionListener(e -> stopwatch.stop());
        resetButton.addActionListener(e -> stopwatch.reset());

        add(timerLabel);
        add(startButton);
        add(stopButton);
        add(resetButton);

        setTitle("Stopwatch (OOP)");
        setSize(250, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new StopwatchUI();
    }
}
