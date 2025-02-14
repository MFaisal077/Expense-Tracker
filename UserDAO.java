import java.sql.*;

public class UserDAO {
    
    // User Signup
    public static boolean registerUser(String name, String email, String password) {
        String insertQuery = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
             
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password); // Hash the password before storing
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Signup Error: " + e.getMessage());
            return false;
        }
    }

    // User Login
    public static boolean loginUser(String email, String password) {
        String query = "SELECT password FROM users WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password); // Compare passwords (hashing recommended)
            }
        } catch (SQLException e) {
            System.out.println("Login Error: " + e.getMessage());
        }
        return false;
    }
}
