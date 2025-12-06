package gelabert.ytdlgui;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private MainFrame mainFrame;
    private ApiClient apiClient;

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblStatus;
    private JCheckBox chkRemember;

    public LoginPanel(MainFrame mainFrame, ApiClient apiClient) {
        this.mainFrame = mainFrame;
        this.apiClient = apiClient;
        initUI();
    }

    private void initUI() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Login", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitle, gbc);

        gbc.gridwidth = 1;

        // EMAIL
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Email:"), gbc);

        txtEmail = new JTextField();
        gbc.gridx = 1;
        add(txtEmail, gbc);

        // PASSWORD
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        txtPassword = new JPasswordField();
        gbc.gridx = 1;
        add(txtPassword, gbc);

        // REMEMBER
        chkRemember = new JCheckBox("Remember me");
        gbc.gridx = 1; gbc.gridy = 3;
        add(chkRemember, gbc);

        // LOGIN BUTTON
        btnLogin = new JButton("Login");
        gbc.gridx = 1; gbc.gridy = 4;
        add(btnLogin, gbc);

        // STATUS LABEL
        lblStatus = new JLabel("");
        lblStatus.setForeground(Color.RED);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        add(lblStatus, gbc);

        // ACTION
        btnLogin.addActionListener(e -> doLogin());
    }

    private void doLogin() {
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (email.isBlank() || password.isBlank()) {
            lblStatus.setText("Fields cannot be empty");
            return;
        }

        try {
            String token = apiClient.login(email, password);

            lblStatus.setText("Login OK");
            lblStatus.setForeground(Color.GREEN);

// Remember me: guardar o esborrar
if (chkRemember.isSelected()) {
    RememberHelper.save(email, token);
} else {
    RememberHelper.clear();
}


            mainFrame.onLoginSuccess(token);

        } catch (Exception ex) {
            lblStatus.setText("Login failed: " + ex.getMessage());
            lblStatus.setForeground(Color.RED);
        }
    }
        public void setEmail(String email) {
        txtEmail.setText(email);
    }

    public void setRememberMe(boolean remember) {
        chkRemember.setSelected(remember);
    }
}

