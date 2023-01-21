import custom.ErrorLabel;
import custom.PasswordFieldCustom;
import custom.TextFieldCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterGui extends JFrame implements ActionListener, FocusListener {
    private ErrorLabel usernameErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel, emailErrorLabel;
    private TextFieldCustom usernameField, emailField;
    private PasswordFieldCustom passwordField, confirmPasswordField;
    private Font customFont;

    public RegisterGui(){
        super("TapTap Ind. Register");
        setSize(CommonConstants.FRAME_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);

        // create custom font
        customFont = CustomTools.createFont(CommonConstants.FONT_PATH);

        addGuiComponent();
    }

    private void addGuiComponent(){
        // register label
        JLabel registerLabel = new JLabel("Register");
        registerLabel.setFont(customFont.deriveFont(68f));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setForeground(CommonConstants.SECONDARY_COLOR);
        registerLabel.setBounds(
                0,
                0,
                CommonConstants.REGISTER_LABEL_SIZE.width, CommonConstants.REGISTER_LABEL_SIZE.height
        );

        // username field
        usernameField = new TextFieldCustom("Enter Username", 30);
        usernameField.setFont(customFont.deriveFont(32f));
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(Color.WHITE);
        usernameField.setBounds(
                50,
                registerLabel.getY() + 150,
                CommonConstants.TEXTFIELD_SIZE.width, CommonConstants.TEXTFIELD_SIZE.height
        );
        usernameField.addFocusListener(this);

        // username error label
        usernameErrorLabel = new ErrorLabel("*Invalid: Can't be less than 6 characters");
        usernameErrorLabel.setBounds(
                50,
                usernameField.getY() + 50,
                CommonConstants.TEXTFIELD_SIZE.width, 25
        );

        // password field
        passwordField = new PasswordFieldCustom("Enter Password", 30);
        passwordField.setFont(customFont.deriveFont(32f));
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(Color.WHITE);
        passwordField.setBounds(
                50,
                usernameField.getY() + 100,
                CommonConstants.TEXTFIELD_SIZE.width, CommonConstants.TEXTFIELD_SIZE.height
        );
        passwordField.addFocusListener(this);

        // password error label
        passwordErrorLabel = new ErrorLabel("*Invalid: Size > 6, 1 Upper and Lower, 1 #, and 1 Special Char");
        passwordErrorLabel.setBounds(
                50,
                passwordField.getY() + 50,
                CommonConstants.TEXTFIELD_SIZE.width, 25
        );

        // confirm password field
        confirmPasswordField = new PasswordFieldCustom("Confirm Password", 30);
        confirmPasswordField.setFont(customFont.deriveFont(32f));
        confirmPasswordField.setBackground(CommonConstants.SECONDARY_COLOR);
        confirmPasswordField.setForeground(Color.WHITE);
        confirmPasswordField.setBounds(
                50,
                passwordField.getY() + 100,
                CommonConstants.TEXTFIELD_SIZE.width, CommonConstants.TEXTFIELD_SIZE.height
        );
        confirmPasswordField.addFocusListener(this);

        // confirm password error label
        confirmPasswordErrorLabel = new ErrorLabel("*Invalid: Passwords are not the same");
        confirmPasswordErrorLabel.setBounds(
                50,
                confirmPasswordField.getY() + 50,
                CommonConstants.TEXTFIELD_SIZE.width, 25
        );

        // email field
        emailField = new TextFieldCustom("Enter E-Mail", 30);
        emailField.setFont(customFont.deriveFont(32f));
        emailField.setBackground(CommonConstants.SECONDARY_COLOR);
        emailField.setForeground(Color.WHITE);
        emailField.setBounds(
                50,
                confirmPasswordField.getY() + 100,
                CommonConstants.TEXTFIELD_SIZE.width, CommonConstants.TEXTFIELD_SIZE.height
        );
        emailField.addFocusListener(this);

        // email error label
        emailErrorLabel = new ErrorLabel("*Invalid: Not a valid format");
        emailErrorLabel.setBounds(
                50,
                emailField.getY() + 50,
                CommonConstants.TEXTFIELD_SIZE.width, 25
        );

        // register button
        JButton registerButton = new JButton("Register");
        registerButton.setFont(customFont.deriveFont(32f));
        registerButton.setBackground(CommonConstants.BUTTON_COLOR);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBounds(
                50,
                emailField.getY() + 100,
                CommonConstants.TEXTFIELD_SIZE.width, CommonConstants.TEXTFIELD_SIZE.height
        );
        registerButton.addActionListener(this);

        // register -> login label
        JLabel loginLabel = new JLabel("Already a user? Login Here");
        loginLabel.setFont(customFont.deriveFont(32f));
        loginLabel.setForeground(CommonConstants.SECONDARY_COLOR);
        loginLabel.setBounds(
                (CommonConstants.FRAME_SIZE.width - loginLabel.getPreferredSize().width)/2,
                registerButton.getY() + 100,
                loginLabel.getPreferredSize().width, loginLabel.getPreferredSize().height
        );
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new LoginGui().setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                loginLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginLabel.setForeground(CommonConstants.SECONDARY_COLOR);
            }
        });

        // add to frame
        getContentPane().add(registerLabel);

        // username
        getContentPane().add(usernameField);
        getContentPane().add(usernameErrorLabel);

        // password
        getContentPane().add(passwordField);
        getContentPane().add(passwordErrorLabel);

        // confirm password
        getContentPane().add(confirmPasswordField);
        getContentPane().add(confirmPasswordErrorLabel);

        // email
        getContentPane().add(emailField);
        getContentPane().add(emailErrorLabel);

        getContentPane().add(registerButton);
        getContentPane().add(loginLabel);
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        Object fieldSource = e.getSource();
        if(fieldSource == usernameField){
            // valid username has to be greater or eequal to 6
            if(usernameField.getText().length() < 6 || usernameField.isHasPlaceHolder()){
                usernameErrorLabel.setVisible(true);
            }else{
                usernameErrorLabel.setVisible(false);
            }
        }else if(fieldSource == passwordField){
            // if password isn't 6 charactres long, has 1 uppercase and 1 lowercase, and a special character it is invalid
            String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&-+=()<>~`])(?=\\S+$).{6,30}$";
            Pattern p = Pattern.compile(passwordRegex);
            Matcher m = p.matcher(passwordField.getText());
            if(!m.find()) passwordErrorLabel.setVisible(true);
            else passwordErrorLabel.setVisible(false);
        }else if(fieldSource == confirmPasswordField){
            // if passwords don't match it is invalid
            if(!passwordField.getText().equals(confirmPasswordField.getText())){
               confirmPasswordErrorLabel.setVisible(true);
            }else{
                confirmPasswordErrorLabel.setVisible(false);
            }
        }else if(fieldSource == emailField){
            // checks email if it is in valid format
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern p = Pattern.compile(emailRegex);
            Matcher m = p.matcher(emailField.getText());
            if(!m.find()) emailErrorLabel.setVisible(true);
            else emailErrorLabel.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("Register")){
            // validate that no error labels are present and that they aren't left in placeholder state
            boolean isValid = !usernameErrorLabel.isVisible() && !passwordErrorLabel.isVisible() && !confirmPasswordErrorLabel.isVisible()
                    && !emailErrorLabel.isVisible() && !usernameField.isHasPlaceHolder() && !passwordField.isHasPlaceHolder()
                    && !confirmPasswordField.isHasPlaceHolder() && !emailField.isHasPlaceHolder();

            // result dialog
            JDialog resultDialog = new JDialog();
            resultDialog.setSize(CommonConstants.RESULT_DIALOG_SIZE);
            resultDialog.setLocationRelativeTo(this);
            resultDialog.setModal(true);
            resultDialog.getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);

            // result label
            JLabel resultLabel = new JLabel();
            resultLabel.setFont(customFont.deriveFont(22f));
            resultLabel.setPreferredSize(CommonConstants.RESULT_DIALOG_SIZE);
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
            resultDialog.add(resultLabel);
            resultLabel.setForeground(CommonConstants.SECONDARY_COLOR);

            if(isValid){
                // store the user info into the UserDB
                String username = usernameField.getText();
                String password = passwordField.getText();
                UserDB.addUser(username, password);

                // show a dialog that shows that the user has been added to the UserDB
                resultLabel.setText("Account Registered!");

                // take user back to the login gui (after closing dialog window)
                resultDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        dispose();
                        new LoginGui().setVisible(true);
                    }
                });
            }else{
                // show an error label
                resultLabel.setText("Invalid Credentials");
            }

            resultDialog.setVisible(true);
        }
    }
}