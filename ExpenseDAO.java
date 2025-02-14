import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExpenseDAO {
    public static void insertExpense(String userName, String userEmail, String category, double amount, String date, String description) {
        String checkUserQuery = "SELECT id FROM users WHERE email = ?";
        String insertUserQuery = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        String insertExpenseQuery = "INSERT INTO expenses (user_id, category, amount, date, description) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkUserStmt = conn.prepareStatement(checkUserQuery);
             PreparedStatement insertUserStmt = conn.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertExpenseStmt = conn.prepareStatement(insertExpenseQuery)) {

            // Check if user already exists
            checkUserStmt.setString(1, userEmail);
            ResultSet rs = checkUserStmt.executeQuery();
            int userId;

            if (rs.next()) {
                // User exists, get the user ID
                userId = rs.getInt("id");
                System.out.println("User already exists with ID: " + userId);
            } else {
                // User does not exist, create a new user
                insertUserStmt.setString(1, userName);
                insertUserStmt.setString(2, userEmail);
                insertUserStmt.setString(3, "default_password"); // Default password (Should be changed in real applications)
                insertUserStmt.executeUpdate();

                // Get the newly generated user ID
                ResultSet generatedKeys = insertUserStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    userId = generatedKeys.getInt(1);
                    System.out.println("New user created with ID: " + userId);
                } else {
                    System.out.println("Error: Failed to create user.");
                    return;
                }
            }

            // Insert expense for the found/created user
            insertExpenseStmt.setInt(1, userId);
            insertExpenseStmt.setString(2, category);
            insertExpenseStmt.setDouble(3, amount);
            insertExpenseStmt.setString(4, date);
            insertExpenseStmt.setString(5, description);
            insertExpenseStmt.executeUpdate();

            System.out.println("Expense added successfully for user ID: " + userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 public static void getExpenses() {
        String query = "SELECT * FROM Expenses";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Category: " + rs.getString("category") + 
                                   ", Amount: $" + rs.getDouble("amount") + ", Date: " + rs.getDate("date") + 
                                   ", Description: " + rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateExpense(int expenseId, String category, double amount, String date, String description) {
        String query = "UPDATE Expenses SET category=?, amount=?, date=?, description=? WHERE id=?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, category);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, date);
            pstmt.setString(4, description);
            pstmt.setInt(5, expenseId);
    
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Expense updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteExpense(int expenseId) {
        String query = "DELETE FROM Expenses WHERE id=?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, expenseId);
            int rowsDeleted = pstmt.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Expense deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        insertExpense("John Doe", "john@example.com", "Food", 15.99, "2024-02-14", "Lunch with friends");
        ExpenseDAO.getExpenses();
        ExpenseDAO.updateExpense(1, "Groceries", 25.00, "2024-02-15", "Bought vegetables");
        ExpenseDAO.deleteExpense(1);
    }
}
