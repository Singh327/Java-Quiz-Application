package quizsystem;

import javax.swing.*;

public class TeacherFrame extends JFrame {
    private JButton addQuestionButton;
    private JButton viewHistoryButton;
    private String subject;

    public TeacherFrame(String subject) {
        this.subject = subject;

        setTitle("Subject:"+subject);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        addQuestionButton = new JButton("Add Questions");
        addQuestionButton.setBounds(70, 30, 150, 30);
        add(addQuestionButton);

        viewHistoryButton = new JButton("View Quiz History");
        viewHistoryButton.setBounds(70, 80, 150, 30);
        add(viewHistoryButton);

        addQuestionButton.addActionListener(e -> {
            new AddQuestionFrame(subject);
            dispose();
        });

        viewHistoryButton.addActionListener(e -> {
            new ViewHistoryFrame(subject);
            dispose();
        });

        setVisible(true);
    }
}
