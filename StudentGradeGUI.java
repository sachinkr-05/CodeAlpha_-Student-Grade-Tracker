 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Student class
class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class StudentGradeGUI extends JFrame {
    private JTextField nameField, marksField;
    private JTextArea displayArea;
    private ArrayList<Student> students;

    public StudentGradeGUI() {
        students = new ArrayList<>();

        setTitle("Student Grade Manager");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel (Input)
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Marks:"));
        marksField = new JTextField();
        panel.add(marksField);

        JButton addButton = new JButton("Add Student");
        JButton reportButton = new JButton("Show Report");

        panel.add(addButton);
        panel.add(reportButton);

        add(panel, BorderLayout.NORTH);

        // Center Area (Display)
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Button Actions
        addButton.addActionListener(e -> addStudent());
        reportButton.addActionListener(e -> showReport());
    }

    private void addStudent() {
        try {
            String name = nameField.getText();
            double marks = Double.parseDouble(marksField.getText());

            students.add(new Student(name, marks));

            displayArea.append("Added: " + name + " (" + marks + ")\n");

            nameField.setText("");
            marksField.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    private void showReport() {
        if (students.isEmpty()) {
            displayArea.append("\nNo data available!\n");
            return;
        }

        double total = 0;
        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;

        displayArea.append("\n===== REPORT =====\n");

        for (Student s : students) {
            displayArea.append(s.name + " : " + s.marks + "\n");

            total += s.marks;
            if (s.marks > highest) highest = s.marks;
            if (s.marks < lowest) lowest = s.marks;
        }

        double avg = total / students.size();

        displayArea.append("\nAverage: " + avg);
        displayArea.append("\nHighest: " + highest);
        displayArea.append("\nLowest: " + lowest + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentGradeGUI().setVisible(true);
        });
    }
}