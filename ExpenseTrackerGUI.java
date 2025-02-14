import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpenseTrackerGUI extends JFrame {
    public ExpenseTrackerGUI() {
        setTitle("Expense Tracker");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton addExpenseButton = new JButton("Add Expense");
        JButton viewExpensesButton = new JButton("View Expenses");
        JButton updateExpenseButton = new JButton("Update Expense");
        JButton deleteExpenseButton = new JButton("Delete Expense");

        addExpenseButton.addActionListener(e -> new AddExpenseGUI());
        viewExpensesButton.addActionListener(e -> new viewExpenseGUI());
        updateExpenseButton.addActionListener(e -> new UpdateExpenseGUI());
        deleteExpenseButton.addActionListener(e -> new DeleteExpenseGUI());

        add(addExpenseButton);
        add(viewExpensesButton);
        add(updateExpenseButton);
        add(deleteExpenseButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ExpenseTrackerGUI();
    }
}
