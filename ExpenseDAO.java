import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ExpenseDAO {
    public static void insertExpense(int userId, String category, double amount, String date, String description) {
        String checkUserQuery = "SELECT id FROM Users WHERE id = ?";
        String insertQuery = "INSERT INTO Expenses (user_id, category, amount, date, description) VALUES (?, ?, ?, ?, ?)";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkUserStmt = conn.prepareStatement(checkUserQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
    
            // Check if user exists
            checkUserStmt.setInt(1, userId);
            ResultSet rs = checkUserStmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Error: User ID " + userId + " does not exist.");
                return;
            }
    
            // Insert expense
            insertStmt.setInt(1, userId);
            insertStmt.setString(2, category);
            insertStmt.setDouble(3, amount);
            insertStmt.setString(4, date);
            insertStmt.setString(5, description);
            insertStmt.executeUpdate();
    
            System.out.println("Expense added successfully!");
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
        insertExpense(1, "Food", 15.99, "2024-02-14", "Lunch with friends");
        ExpenseDAO.getExpenses();
        ExpenseDAO.updateExpense(1, "Groceries", 25.00, "2024-02-15", "Bought vegetables");
        ExpenseDAO.deleteExpense(1);
    }
}
