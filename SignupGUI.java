import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sign Up");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailText = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField();

        JButton signupButton = new JButton("Sign Up");

        frame.add(userLabel); frame.add(userText);
        frame.add(emailLabel); frame.add(emailText);
        frame.add(passLabel); frame.add(passText);
        frame.add(new JLabel()); frame.add(signupButton);

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String email = emailText.getText();
                String password = new String(passText.getPassword());

                boolean success = UserDAO.registerUser(username, email, password);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "User registered successfully!");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Signup failed. Try again.");
                }
            }
        });

        frame.setVisible(true);
    }
}
