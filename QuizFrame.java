package quizsystem;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class QuizFrame extends JFrame {
    private JLabel questionLabel;
    private JRadioButton optionA, optionB, optionC, optionD;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private ArrayList<Question> questions;
    private int currentIndex = 0;
    private int score = 0;
    private String subject;
    private int studentid;

    public QuizFrame(String subject,int student_id) {
        this.studentid = student_id;
        this.subject = subject;
        setTitle("Quiz - " + subject);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        questionLabel = new JLabel("Question");
        questionLabel.setBounds(50, 30, 400, 25);
        add(questionLabel);

        optionA = new JRadioButton();
        optionA.setBounds(50, 70, 400, 25);
        add(optionA);

        optionB = new JRadioButton();
        optionB.setBounds(50, 100, 400, 25);
        add(optionB);

        optionC = new JRadioButton();
        optionC.setBounds(50, 130, 400, 25);
        add(optionC);

        optionD = new JRadioButton();
        optionD.setBounds(50, 160, 400, 25);
        add(optionD);

        optionsGroup = new ButtonGroup();
        optionsGroup.add(optionA);
        optionsGroup.add(optionB);
        optionsGroup.add(optionC);
        optionsGroup.add(optionD);

        nextButton = new JButton("Next");
        nextButton.setBounds(180, 210, 100, 30);
        add(nextButton);

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentIndex++;
                if (currentIndex < questions.size()) {
                    loadQuestion();
                } else {
                    JOptionPane.showMessageDialog(null, "Quiz Completed! Your score: " + score);
                    saveResult();
                    new StudentFrame(student_id);
                    dispose();
                }
            }
        });

        loadQuestions();
        loadQuestion();

        setVisible(true);
    }

    private void loadQuestions() {
        questions = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM questions WHERE subject = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, subject);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Question q = new Question(
                    rs.getString("question"),
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d"),
                    rs.getString("correct_option").charAt(0)
                );
                questions.add(q);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadQuestion() {
        if (questions.size() == 0) {
            JOptionPane.showMessageDialog(this, "No questions available for this subject.");
            new StudentFrame(studentid);
            dispose();
            return;
        }
        Question q = questions.get(currentIndex);
        questionLabel.setText((currentIndex + 1) + ". " + q.getQuestion());
        optionA.setText(q.getOptionA());
        optionB.setText(q.getOptionB());
        optionC.setText(q.getOptionC());
        optionD.setText(q.getOptionD());
        optionsGroup.clearSelection();
    }

    private void checkAnswer() {
        Question q = questions.get(currentIndex);
        String selected = null;

        if (optionA.isSelected()) selected = "A";
        else if (optionB.isSelected()) selected = "B";
        else if (optionC.isSelected()) selected = "C";
        else if (optionD.isSelected()) selected = "D";

        if (selected != null && selected.charAt(0) == q.getCorrectOption()) {
            score += 10;
        }
    }

    private void saveResult() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO quiz_results (student_id, subject, score) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, studentid); // Assuming student id = 1 for now. (You should fetch logged-in user id.)
            pst.setString(2, subject);
            pst.setInt(3, score);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
