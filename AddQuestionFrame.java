package quizsystem;

import javax.swing.*;
import java.sql.*;

public class AddQuestionFrame extends JFrame {
    private JTextField questionField, optionA, optionB, optionC, optionD, correctOption;
    private JButton saveButton;
    private String subject;

    public AddQuestionFrame(String subject) {
        this.subject = subject;

        setTitle("Add Question - " + subject);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel qLabel = new JLabel("Question:");
        qLabel.setBounds(30, 30, 100, 25);
        add(qLabel);

        questionField = new JTextField();
        questionField.setBounds(130, 30, 200, 25);
        add(questionField);

        JLabel aLabel = new JLabel("Option A:");
        aLabel.setBounds(30, 70, 100, 25);
        add(aLabel);

        optionA = new JTextField();
        optionA.setBounds(130, 70, 200, 25);
        add(optionA);

        JLabel bLabel = new JLabel("Option B:");
        bLabel.setBounds(30, 110, 100, 25);
        add(bLabel);

        optionB = new JTextField();
        optionB.setBounds(130, 110, 200, 25);
        add(optionB);

        JLabel cLabel = new JLabel("Option C:");
        cLabel.setBounds(30, 150, 100, 25);
        add(cLabel);

        optionC = new JTextField();
        optionC.setBounds(130, 150, 200, 25);
        add(optionC);

        JLabel dLabel = new JLabel("Option D:");
        dLabel.setBounds(30, 190, 100, 25);
        add(dLabel);

        optionD = new JTextField();
        optionD.setBounds(130, 190, 200, 25);
        add(optionD);

        JLabel correctLabel = new JLabel("Correct Option:");
        correctLabel.setBounds(30, 230, 100, 25);
        add(correctLabel);

        correctOption = new JTextField();
        correctOption.setBounds(130, 230, 50, 25);
        add(correctOption);

        saveButton = new JButton("Save");
        saveButton.setBounds(150, 280, 100, 30);
        add(saveButton);

        saveButton.addActionListener(e -> saveQuestion());

        setVisible(true);
    }

    private void saveQuestion() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO questions (subject, question, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, subject);
            pst.setString(2, questionField.getText());
            pst.setString(3, optionA.getText());
            pst.setString(4, optionB.getText());
            pst.setString(5, optionC.getText());
            pst.setString(6, optionD.getText());
            pst.setString(7, correctOption.getText().toUpperCase());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Question Added!");
            dispose();
            new TeacherFrame(subject);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
