import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExpenseGUI extends JFrame {
    private JTextField userIdField, userNameField, userEmailField, categoryField, amountField, dateField, descriptionField;
    
    public AddExpenseGUI() {
        setTitle("Add Expense");
        setSize(400, 400); // Increased size to accommodate new fields
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2)); // Added two more rows for ID and name fields

        add(new JLabel("User ID:"));
        userIdField = new JTextField();
        add(userIdField);

        add(new JLabel("User Name:"));
        userNameField = new JTextField();
        add(userNameField);

        add(new JLabel("User Email:"));
        userEmailField = new JTextField();
        add(userEmailField);

        add(new JLabel("Category:"));
        categoryField = new JTextField();
        add(categoryField);

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        add(dateField);

        add(new JLabel("Description:"));
        descriptionField = new JTextField();
        add(descriptionField);

        JButton addButton = new JButton("Add Expense");
        add(addButton);
        
        addButton.addActionListener(e -> {
            String userId = userIdField.getText();
            String userName = userNameField.getText();
            String userEmail = userEmailField.getText();
            String category = categoryField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String date = dateField.getText();
            String description = descriptionField.getText();

            // Modified to pass the user name instead of "Unknown"
            ExpenseDAO.insertExpense(userName, userEmail, category, amount, date, description);
            JOptionPane.showMessageDialog(this, "Expense Added!");
            dispose();
        });

        setVisible(true);
    }
}