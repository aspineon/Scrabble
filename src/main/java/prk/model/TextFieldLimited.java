package prk.model;

import javafx.beans.NamedArg;
import javafx.scene.control.TextField;
import prk.controller.MainWindowController;

public class TextFieldLimited extends TextField {

	private final int limit = 1;
	
	public TextFieldLimited(){
		super();
	}
	
	public TextFieldLimited(String text){
		super(text);
		
	}

	 @Override
	    public void replaceText(int start, int end, String text) {
	        super.replaceText(start, end, text);
	        verify();
	        setText(getText().toUpperCase());
	    }

	    @Override
	    public void replaceSelection(String text) {
	        super.replaceSelection(text);
	        verify();
	        setText(getText().toUpperCase());
	    }

	    private void verify() {
	        if (getText().length() > limit) {
	            setText(getText().substring(0, limit));
	        }
	        if (!getText().matches("[a-zA-Z ]"));
	    }
	    
}
