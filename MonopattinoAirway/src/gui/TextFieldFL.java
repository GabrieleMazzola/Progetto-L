package gui;

import com.jfoenix.controls.JFXTextField;


public class TextFieldFL extends JFXTextField{
    private final double width = 100;
    
    public TextFieldFL(String prompt) {
        super();
        setLabelFloat(true);
        setPromptText(prompt);
        setPrefWidth(width);
    }
}
