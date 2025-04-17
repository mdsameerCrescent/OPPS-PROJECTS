import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

class DigitalClock {
    private JLabel timeLabel;

    public DigitalClock(JLabel label) {
        this.timeLabel = label;
        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();
    }

    private void updateClock() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String time = formatter.format(new Date());
        timeLabel.setText(time);
    }
}

class DigitalClockUI extends JFrame {
    private JLabel timeLabel;

    public DigitalClockUI() {
        timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 50));

        setLayout(new FlowLayout());
        add(timeLabel);

        setTitle("Digital Clock");
        setSize(250, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        new DigitalClock(timeLabel);
    }

    public static void main(String[] args) {
        new DigitalClockUI();
    }
}
