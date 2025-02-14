import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExpenseGUI extends JFrame {
    private JTextField userEmailField, categoryField, amountField, dateField, descriptionField;
    
    public AddExpenseGUI() {
        setTitle("Add Expense");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

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
            String userEmail = userEmailField.getText();
            String category = categoryField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String date = dateField.getText();
            String description = descriptionField.getText();

            ExpenseDAO.insertExpense("Unknown", userEmail, category, amount, date, description);
            JOptionPane.showMessageDialog(this, "Expense Added!");
            dispose();
        });

        setVisible(true);
    }
}
