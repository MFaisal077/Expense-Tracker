import java.util.Scanner;

public class ExpenseTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Welcome to Expense Tracker =====");
        System.out.print("Enter your Name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter your Email: ");
        String userEmail = scanner.nextLine();

        int choice;
        do {
            System.out.println("\n===== Expense Tracker Menu =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addExpense(scanner, userName, userEmail);
                    break;
                case 2:
                    ExpenseDAO.getExpenses();
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void addExpense(Scanner scanner, String userName, String userEmail) {
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        
        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        
        ExpenseDAO.insertExpense(userName, userEmail, category, amount, date, description);
    }
}
