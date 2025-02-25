import javax.swing.*;
import java.awt.*;

public class UpdateExpenseGUI extends JFrame {
    private JTextField expenseIdField, categoryField, amountField, dateField, descriptionField;
    private Image backgroundImage;

    public UpdateExpenseGUI() {
        setTitle("Update Expense");
        setSize(4500, 4500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center window

        // Load Background Image
        backgroundImage = new ImageIcon("resources\6836.jpg").getImage(); // Ensure the image is in your project folder

        // Custom Panel for Background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Add Components
        mainPanel.add(createStyledLabel("Expense ID:"), gbc);
        gbc.gridx = 1;
        expenseIdField = createStyledTextField();
        mainPanel.add(expenseIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(createStyledLabel("New Category:"), gbc);
        gbc.gridx = 1;
        categoryField = createStyledTextField();
        mainPanel.add(categoryField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(createStyledLabel("New Amount:"), gbc);
        gbc.gridx = 1;
        amountField = createStyledTextField();
        mainPanel.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(createStyledLabel("New Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        dateField = createStyledTextField();
        mainPanel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(createStyledLabel("New Description:"), gbc);
        gbc.gridx = 1;
        descriptionField = createStyledTextField();
        mainPanel.add(descriptionField, gbc);

        // Update Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton updateButton = createStyledButton("Update Expense");
        mainPanel.add(updateButton, gbc);

        updateButton.addActionListener(e -> {
            try {
                int expenseId = Integer.parseInt(expenseIdField.getText());
                String category = categoryField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String date = dateField.getText();
                String description = descriptionField.getText();

                ExpenseDAO.updateExpense(expenseId, category, amount, date, description);
                JOptionPane.showMessageDialog(this, "Expense Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please check your values.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    // Method to create styled labels
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.WHITE); // White text for readability on background
        return label;
    }

    // Method to create styled text fields
    private JTextField createStyledTextField() {
        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        textField.setOpaque(false); // Transparent text field to blend with background
        textField.setForeground(Color.WHITE); // White text
        return textField;
    }

    // Method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(60, 130, 200)); // Blue shade
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        return button;
    }

    public static void main(String[] args) {
        new UpdateExpenseGUI();
    }
}
