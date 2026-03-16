package gelabert.ytdlgui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.regex.Pattern;

public class LoginPanel extends JPanel {

    private final MainFrame mainFrame;
    private final MediaPollingComponent mediaPolling;

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblStatus;
    private JCheckBox chkRemember;
    private JProgressBar progress;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{1,}$",
            Pattern.CASE_INSENSITIVE
    );

    // Ajusta aquest valor si el vols una mica més ample o més estret
    private static final int FIELD_WIDTH = 420;
    private static final int FIELD_HEIGHT = 28;

    public LoginPanel(MainFrame mainFrame, MediaPollingComponent mediaPolling) {
        this.mainFrame = mainFrame;
        this.mediaPolling = mediaPolling;
        initUI();
        wireUX();
        updateLoginState();
    }

    private void initUI() {
        // Centra el "bloc" del login a la finestra (sense card blanc)
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        setBackground(UIManager.getColor("Panel.background"));

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // TITLE
        JLabel lblTitle = new JLabel("Login", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        form.add(lblTitle, gbc);

        gbc.gridwidth = 1;

        // EMAIL label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        form.add(new JLabel("Email:"), gbc);

        // EMAIL field
        txtEmail = new JTextField();
        txtEmail.setToolTipText("Enter your email (example@domain.com)");
        txtEmail.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;   // clau: el camp sí que s’estira (fins al preferred)
        gbc.weightx = 1;                            // clau: només la columna del camp guanya espai
        form.add(txtEmail, gbc);

        // PASSWORD label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        form.add(new JLabel("Password:"), gbc);

        // PASSWORD field
        txtPassword = new JPasswordField();
        txtPassword.setToolTipText("Enter your password");
        txtPassword.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        form.add(txtPassword, gbc);

        // REMEMBER
        chkRemember = new JCheckBox("Remember me");
        chkRemember.setToolTipText("Save email + token for next time");
        chkRemember.setOpaque(false);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        form.add(chkRemember, gbc);

        // PROGRESS
        progress = new JProgressBar();
        progress.setIndeterminate(true);
        progress.setVisible(false);
        progress.setPreferredSize(new Dimension(FIELD_WIDTH, 10));

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        form.add(progress, gbc);

        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;

        // LOGIN BUTTON
        btnLogin = new JButton("Login");
        btnLogin.setToolTipText("Sign in");
        btnLogin.setPreferredSize(new Dimension(140, 34));
        btnLogin.setFocusPainted(false);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        form.add(btnLogin, gbc);

        // STATUS LABEL
        lblStatus = new JLabel(" ");
        lblStatus.setForeground(new Color(180, 0, 0));

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        form.add(lblStatus, gbc);

        // Afegeix el form centrat al panell exterior
        add(form, new GridBagConstraints());
    }

    private void wireUX() {
        // Enter -> login (default button)
        SwingUtilities.invokeLater(() -> {
            JRootPane root = SwingUtilities.getRootPane(this);
            if (root != null) root.setDefaultButton(btnLogin);
        });

        // Tab order
        txtEmail.setNextFocusableComponent(txtPassword);
        txtPassword.setNextFocusableComponent(chkRemember);
        chkRemember.setNextFocusableComponent(btnLogin);

        // Live validation -> enable/disable button
        DocumentListener dl = new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { updateLoginState(); }
            @Override public void removeUpdate(DocumentEvent e) { updateLoginState(); }
            @Override public void changedUpdate(DocumentEvent e) { updateLoginState(); }
        };
        txtEmail.getDocument().addDocumentListener(dl);
        txtPassword.getDocument().addDocumentListener(dl);

        btnLogin.addActionListener(e -> doLoginAsync());
    }

    private void updateLoginState() {
        String email = txtEmail.getText().trim();
        String pass = new String(txtPassword.getPassword());

        boolean emailOk = EMAIL_PATTERN.matcher(email).matches();
        boolean passOk = !pass.isBlank();

        btnLogin.setEnabled(emailOk && passOk);

        if (!email.isBlank() && !emailOk) {
            setStatus("Invalid email format (example@domain.com)", StatusType.ERROR);
        } else {
            if ("Invalid email format (example@domain.com)".equals(lblStatus.getText())) {
                setStatus(" ", StatusType.NEUTRAL);
            }
        }
    }

    private void doLoginAsync() {
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (!EMAIL_PATTERN.matcher(email).matches() || password.isBlank()) {
            setStatus("Please enter a valid email and password.", StatusType.ERROR);
            return;
        }

        setBusy(true);
        setStatus("Logging in…", StatusType.NEUTRAL);

        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                return mediaPolling.login(email, password);
            }

            @Override
            protected void done() {
                try {
                    String token = get();

                    if (chkRemember.isSelected()) RememberHelper.save(email, token);
                    else RememberHelper.clear();

                    setStatus("Login OK", StatusType.SUCCESS);
                    mainFrame.onLoginSuccess(token);

                } catch (Exception ex) {
                    String msg = ex.getMessage() == null ? "" : ex.getMessage().toLowerCase();
                    if (msg.contains("connect") || msg.contains("timeout")) {
                        setStatus("API unavailable. Check your connection and try again.", StatusType.ERROR);
                    } else {
                        setStatus("Login failed. Check credentials and try again.", StatusType.ERROR);
                    }
                } finally {
                    setBusy(false);
                    updateLoginState();
                }
            }
        };

        worker.execute();
    }

    private enum StatusType { NEUTRAL, SUCCESS, ERROR }

    private void setStatus(String text, StatusType type) {
        lblStatus.setText(text);
        switch (type) {
            case SUCCESS -> lblStatus.setForeground(new Color(0, 120, 0));
            case ERROR -> lblStatus.setForeground(new Color(180, 0, 0));
            default -> lblStatus.setForeground(Color.DARK_GRAY);
        }
    }

    private void setBusy(boolean busy) {
        progress.setVisible(busy);
        txtEmail.setEnabled(!busy);
        txtPassword.setEnabled(!busy);
        chkRemember.setEnabled(!busy);

        btnLogin.setEnabled(!busy
                && EMAIL_PATTERN.matcher(txtEmail.getText().trim()).matches()
                && !new String(txtPassword.getPassword()).isBlank());

        setCursor(busy ? Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) : Cursor.getDefaultCursor());
    }

    public void setEmail(String email) {
        txtEmail.setText(email);
        updateLoginState();
    }

    public void setRememberMe(boolean remember) {
        chkRemember.setSelected(remember);
    }
}



