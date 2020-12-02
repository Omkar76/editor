package texteditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

class TextView extends JTextArea implements DocumentListener{
	private static final long serialVersionUID = 8423338142529331060L;
	private File file;
	private boolean changed;
	private UndoManager manager;
	public TextView() {
		setFont(Preferences.textViewFont);
		setWrapStyleWord(true);
		setBorder(new EmptyBorder(0, 3, 0, 3));
		this.getDocument().addDocumentListener(this);
		manager = new UndoManager();
		this.getDocument().addUndoableEditListener(new UndoableEditListener() {
			
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				manager.addEdit(e.getEdit());
			}
		});
	}
	
	boolean isOnDisk() {
		if(file==null) {
			return false;
		}
		
		return file.exists();
	}
	
	boolean isChanged() {
		return changed;
	}
	
	TextView setFile(File file) {
		this.file = file;
		return this;
	}
	
	File getFile() {
		return this.file;
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		onChange();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		onChange();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		onChange();
	}
	
	@Override
	public String getName() {
		if(file!=null)
			return file.getName();
		return "Untitled";
	}
	
	void onChange() {
		changed = true;
	}
	
	void writeToFile() {
		try(FileWriter writer  = new FileWriter(file)){
			write(writer);
		}catch (IOException e) {
			AppGUI.get().alert(e.getMessage());
		}
		
		changed = false;
	}
	
	void readFromFile() throws IOException {
		try(FileReader reader = new FileReader(file)){
//			StringBuilder builder = new StringBuilder();
//			char buf[] = new char[1024];
//			
//			while(reader.read(buf)!=-1)
//				builder.append(buf,0,1024);
//			
//			setText(builder.toString());
			
			read(reader, this);
			AppGUI.get().setTitle("Editor - "+file.getName());
		}catch (FileNotFoundException e) {
			AppGUI.get().alert("File not found");
		}
	}
	
	
	void reset() {
		file = null;
		setText("");
		AppGUI.get().setTitle("Editor - Untitled");
	}
	
	UndoManager getManager() {
		return manager;
	}
	
	void undo(){
		if(manager.canUndo())
			manager.undo();
	}
	
	void redo() {
		if(manager.canRedo())
			manager.redo();
	}
	
}
