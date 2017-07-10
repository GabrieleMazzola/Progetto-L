package gui.ticketmachine;

import javafx.scene.control.TextField;


public class LimitedTextField extends TextField{
    private final int limit;
    
    public LimitedTextField(int limit) {
        this.limit = limit;
        this.setPrefWidth(60);
        this.setMaxWidth(60);
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
