package gui;

import com.jfoenix.controls.JFXPasswordField;


public class PasswordFieldFL extends JFXPasswordField{
    private final double width = 100;
    
    public PasswordFieldFL(String prompt) {
        super();
        setLabelFloat(true);
        setPromptText(prompt);
        setPrefWidth(width);
    }
}
