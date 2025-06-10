package quizsystem;

import javax.swing.*;

public class StudentFrame extends JFrame {
    private JComboBox<String> subjectBox;
    private JButton startQuizButton;
    private int studentId;

    public StudentFrame(int studentId) {
        this.studentId = studentId;
        setTitle("Student Dashboard");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel subjectLabel = new JLabel("Select Subject:");
        subjectLabel.setBounds(30, 30, 100, 25);
        add(subjectLabel);

        String[] subjects = {"Python", "Java", "C", "C++", "CSS"};
        subjectBox = new JComboBox<>(subjects);
        subjectBox.setBounds(140, 30, 120, 25);
        add(subjectBox);

        startQuizButton = new JButton("Start Quiz");
        startQuizButton.setBounds(90, 80, 120, 30);
        add(startQuizButton);

        startQuizButton.addActionListener(e -> {
            String selectedSubject = (String) subjectBox.getSelectedItem();
            new QuizFrame(selectedSubject,studentId);
            dispose();
        });

        setVisible(true);
    }
}
