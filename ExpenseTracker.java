import java.util.Scanner;

public class ExpenseTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Expense Tracker Menu =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addExpense(scanner);
                    break;
                case 2:
                    ExpenseDAO.getExpenses();
                    break;
                case 3:
                    updateExpense(scanner);
                    break;
                case 4:
                    deleteExpense(scanner);
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void addExpense(Scanner scanner) {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        
        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        
        ExpenseDAO.insertExpense(userId, category, amount, date, description);
    }

    private static void updateExpense(Scanner scanner) {
        System.out.print("Enter Expense ID to update: ");
        int expenseId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter New Category: ");
        String category = scanner.nextLine();
        
        System.out.print("Enter New Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter New Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        
        System.out.print("Enter New Description: ");
        String description = scanner.nextLine();
        
        ExpenseDAO.updateExpense(expenseId, category, amount, date, description);
    }

    private static void deleteExpense(Scanner scanner) {
        System.out.print("Enter Expense ID to delete: ");
        int expenseId = scanner.nextInt();
        
        ExpenseDAO.deleteExpense(expenseId);
    }
}
