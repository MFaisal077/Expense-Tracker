import javax.swing.*;
import java.awt.*;

public class DeleteExpenseGUI extends JFrame {
    private JTextField expenseIdField;

    public DeleteExpenseGUI() {
        setTitle("Delete Expense");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Expense ID:"));
        expenseIdField = new JTextField();
        add(expenseIdField);

        JButton deleteButton = new JButton("Delete Expense");
        add(deleteButton);

        deleteButton.addActionListener(e -> {
            int expenseId = Integer.parseInt(expenseIdField.getText());

            // Call DAO method to delete the expense
            ExpenseDAO.deleteExpense(expenseId);
            JOptionPane.showMessageDialog(this, "Expense Deleted Successfully!");
            dispose();
        });

        setVisible(true);
    }
}
