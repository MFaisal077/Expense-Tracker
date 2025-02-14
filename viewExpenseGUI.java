import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class viewExpenseGUI extends JFrame {
    public viewExpenseGUI() {
        setTitle("View Expenses");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "User", "Category", "Amount", "Date", "Description"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT e.id, u.name, e.category, e.amount, e.date, e.description FROM expenses e JOIN users u ON e.user_id = u.id")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getDate("date"),
                        rs.getString("description")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }
}
