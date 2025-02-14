import javax.swing.*;
import java.awt.*;

public class UpdateExpenseGUI extends JFrame {
    private JTextField expenseIdField, categoryField, amountField, dateField, descriptionField;
    
    public UpdateExpenseGUI() {
        setTitle("Update Expense");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Expense ID:"));
        expenseIdField = new JTextField();
        add(expenseIdField);

        add(new JLabel("New Category:"));
        categoryField = new JTextField();
        add(categoryField);

        add(new JLabel("New Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("New Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        add(dateField);

        add(new JLabel("New Description:"));
        descriptionField = new JTextField();
        add(descriptionField);

        JButton updateButton = new JButton("Update Expense");
        add(updateButton);
        
        updateButton.addActionListener(e -> {
            int expenseId = Integer.parseInt(expenseIdField.getText());
            String category = categoryField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String date = dateField.getText();
            String description = descriptionField.getText();

            ExpenseDAO.updateExpense(expenseId, category, amount, date, description);
            JOptionPane.showMessageDialog(this, "Expense Updated!");
            dispose();
        });

        setVisible(true);
    }
}
