import javax.swing.*;
import java.awt.*;

public class ExpenseTrackerGUI extends JFrame {
    private Image backgroundImage;

    public ExpenseTrackerGUI() {
        setTitle("Expense Tracker");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window on screen

        // Load Background Image
        backgroundImage = new ImageIcon("resources\\pexels-karolina-grabowska-4386339.jpg").getImage(); // Ensure the image is in the project folder

        // Main Panel with Background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Header Panel
        JLabel titleLabel = new JLabel("Expense Tracker", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonPanel.setOpaque(false); // Make buttons transparent over background
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Buttons
        JButton addExpenseButton = createStyledButton("Add Expense");
        JButton viewExpensesButton = createStyledButton("View Expenses");
        JButton updateExpenseButton = createStyledButton("Update Expense");
        JButton deleteExpenseButton = createStyledButton("Delete Expense");

        addExpenseButton.addActionListener(e -> new AddExpenseGUI());
        viewExpensesButton.addActionListener(e -> new viewExpenseGUI());
        updateExpenseButton.addActionListener(e -> new UpdateExpenseGUI());
        deleteExpenseButton.addActionListener(e -> new DeleteExpenseGUI());

        buttonPanel.add(addExpenseButton);
        buttonPanel.add(viewExpensesButton);
        buttonPanel.add(updateExpenseButton);
        buttonPanel.add(deleteExpenseButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

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
        new ExpenseTrackerGUI();
    }
}
