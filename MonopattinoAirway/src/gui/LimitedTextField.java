package gui;

import com.jfoenix.controls.JFXTextField;


public class LimitedTextField extends JFXTextField{
    private final int limit;
    
    public LimitedTextField(int limit) {
        this.limit = limit;
        this.setPrefWidth(60);
        this.setMaxWidth(60);
        this.setStyle("-fx-background-color: #ffffff;-fx-padding: 10px;");
    }
    
    @Override
    public void replaceText(int start, int end, String text) {
        super.replaceText(start, end, text);
        verify();
    }

    @Override
    public void replaceSelection(String text) {
        super.replaceSelection(text);
        verify();
    }
    
    public boolean hasReachedLimit() {
        return this.getText().length() == limit;
    }

    private void verify() {
        if (getText().length() > limit) {
            setText(getText().substring(0, limit));
        }
    }
}
