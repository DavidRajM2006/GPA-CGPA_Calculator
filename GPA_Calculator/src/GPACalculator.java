import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GPACalculator {
    private JFrame frame;
    private JPanel panel;
    private JTextField courseNameField, creditsField, gradeField;
    private JTextArea courseListArea;
    private JButton addCourseButton, calculateGPAButton;
    private ArrayList<Course> courses;

    public GPACalculator() {
        courses = new ArrayList<>();
        frame = new JFrame("GPA / CGPA Calculator");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        frame.add(panel);

        JLabel courseLabel = new JLabel("Course Name:");
        c.gridx = 0; c.gridy = 0; c.insets = new Insets(5,5,5,5);
        panel.add(courseLabel, c);

        courseNameField = new JTextField(15);
        c.gridx = 1; c.gridy = 0;
        panel.add(courseNameField, c);

        JLabel creditsLabel = new JLabel("Credits:");
        c.gridx = 0; c.gridy = 1;
        panel.add(creditsLabel, c);

        creditsField = new JTextField(5);
        c.gridx = 1; c.gridy = 1;
        panel.add(creditsField, c);

        JLabel gradeLabel = new JLabel("Grade (0-10):");
        c.gridx = 0; c.gridy = 2;
        panel.add(gradeLabel, c);

        gradeField = new JTextField(5);
        c.gridx = 1; c.gridy = 2;
        panel.add(gradeField, c);

        addCourseButton = new JButton("Add Course");
        c.gridx = 0; c.gridy = 3;
        c.gridwidth = 2;
        panel.add(addCourseButton, c);

        courseListArea = new JTextArea(8, 30);
        courseListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(courseListArea);
        c.gridx = 0; c.gridy = 4; c.gridwidth = 2;
        panel.add(scrollPane, c);

        calculateGPAButton = new JButton("Calculate GPA");
        c.gridx = 0; c.gridy = 5; c.gridwidth = 2;
        panel.add(calculateGPAButton, c);

        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });

        calculateGPAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGPA();
            }
        });

        frame.setVisible(true);
    }

    private void addCourse() {
        String name = courseNameField.getText();
        try {
            double credits = Double.parseDouble(creditsField.getText());
            double grade = Double.parseDouble(gradeField.getText());

            if(grade < 0 || grade > 10 || credits <= 0) {
                JOptionPane.showMessageDialog(frame, "Enter valid grade (0-10) and positive credits.");
                return;
            }

            Course course = new Course(name, credits, grade);
            courses.add(course);
            courseListArea.append(name + " | Credits: " + credits + " | Grade: " + grade + "\n");

            courseNameField.setText("");
            creditsField.setText("");
            gradeField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers for credits and grade.");
        }
    }

    private void calculateGPA() {
        if(courses.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No courses added.");
            return;
        }
        double totalCredits = 0;
        double totalWeightedGrades = 0;

        for(Course c : courses) {
            totalCredits += c.getCredits();
            totalWeightedGrades += c.getGrade() * c.getCredits();
        }

        double gpa = totalWeightedGrades / totalCredits;
        JOptionPane.showMessageDialog(frame, "Your GPA/CGPA is: " + String.format("%.2f", gpa));
    }

    public static void main(String[] args) {
        new GPACalculator();
    }
}