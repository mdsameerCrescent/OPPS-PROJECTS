import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;
import javax.swing.Timer;

class AlarmClock {
    private LocalTime alarmTime;
    private Timer timer;

    public AlarmClock() {
        this.alarmTime = null;
        this.timer = new Timer(1000, e -> checkAlarm());
    }

    public void setAlarm(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.alarmTime = LocalTime.parse(time, formatter);
        timer.start();
    }

    private void checkAlarm() {
        if (alarmTime != null && LocalTime.now().isAfter(alarmTime)) {
            JOptionPane.showMessageDialog(null, "Alarm ringing!");
            timer.stop();
        }
    }

    public void cancelAlarm() {
        alarmTime = null;
        timer.stop();
    }
}

class AlarmClockUI extends JFrame {
    private AlarmClock alarmClock;
    private JTextField timeField;
    private JButton setButton;
    private JButton cancelButton;

    public AlarmClockUI() {
        alarmClock = new AlarmClock();

        setLayout(new FlowLayout());
        timeField = new JTextField(5);
        setButton = new JButton("Set Alarm");
        cancelButton = new JButton("Cancel Alarm");

        setButton.addActionListener(e -> {
            String time = timeField.getText();
            alarmClock.setAlarm(time);
        });

        cancelButton.addActionListener(e -> alarmClock.cancelAlarm());

        add(new JLabel("Enter Alarm Time (HH:mm):"));
        add(timeField);
        add(setButton);
        add(cancelButton);

        setTitle("Alarm Clock");
        setSize(300, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AlarmClockUI();
    }
}
