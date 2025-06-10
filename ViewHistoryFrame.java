package quizsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewHistoryFrame extends JFrame {
    private JTable table;
    private String subject;

    public ViewHistoryFrame(String subject) {
        this.subject = subject;

        setTitle("Quiz History - " + subject);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        loadHistory();

        setVisible(true);
    }

    private void loadHistory() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"student_id", "Subject", "Score", "Date"}, 0);
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM quiz_results WHERE subject = ?  ORDER BY score DESC ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, subject);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("student_id"),
                    rs.getString("subject"),
                    rs.getInt("score"),
                    rs.getTimestamp("timestamp")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        table.setModel(model);
    }
}
