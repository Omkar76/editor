package texteditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

class EditMenu extends JMenu {
	private static final long serialVersionUID = -5501848506299304778L;
	JMenuItem undo;
	JMenuItem redo;
	JMenuItem cut;
	JMenuItem copy;
	JMenuItem paste;
	JMenuItem delete;
	JMenuItem selectAll;
	JMenuItem goTo;
	JMenuItem timeDate;
	
	public EditMenu() {
		super("Edit");
		setFont(Preferences.menuFont);
		
		/*undo start*/
		undo = new JMenuItem("Undo");
		this.add(undo);
		undo.setFont(Preferences.menuItemFont);
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AppGUI.get().textView.undo();
			}
			
		});
		
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));	
		
		/*undo end*/
		
		/*redo start*/
		redo = new JMenuItem("Redo");
		this.add(redo);
		redo.setFont(Preferences.menuItemFont);
		redo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AppGUI.get().textView.redo();
			}
			
		});
		
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));	
		
		/*redo end*/
		
		
		/*cut start*/
		cut = new JMenuItem("Cut");
		this.add(cut);
		cut.setFont(Preferences.menuItemFont);
		cut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AppGUI.get().textView.cut();
			}
			
		});
		
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));	
		
		/*cut end*/
		
		
		/*copy start*/
		copy = new JMenuItem("Copy");
		this.add(copy);
		copy.setFont(Preferences.menuItemFont);
		copy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AppGUI.get().textView.copy();
			}
		});
		
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));	
		
		/*copy end*/
		
		
		/*paste start*/
		paste = new JMenuItem("Paste");
		this.add(paste);
		paste.setFont(Preferences.menuItemFont);
		paste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AppGUI.get().textView.paste();
			}
			
		});
		
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));	
		/*paste end*/
		
		/*Delete start*/
		delete = new JMenuItem("Delete");
		this.add(delete);
		delete.setFont(Preferences.menuItemFont);
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AppGUI.get().textView.replaceSelection("");
			}
			
		});
		
		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));
		/*delete end*/
		
		selectAll = new JMenuItem("Select All");
		this.add(selectAll);
		selectAll.setFont(Preferences.menuItemFont);
		selectAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AppGUI.get().textView.selectAll();
			}
			
		});
		
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));	
		
		
		/*goto start*/
		goTo = new JMenuItem("Go To");
		this.add(goTo);
		goTo.setFont(Preferences.menuItemFont);
		goTo.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				AppGUI appGUI = AppGUI.get();
				int maxline = appGUI.textView.getLineCount();

				String numberString = JOptionPane.showInputDialog(appGUI,"Enter line number",maxline);
				if(numberString!=null) {
					try {
						appGUI.textView.setCaretPosition((appGUI.textView.getLineStartOffset(Integer.parseInt(numberString))));
					}catch (BadLocationException exception) {
						AppGUI.get().alert("Invalid line number");
					}
				}
			}
			
		});
		
				
		goTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));	
		/*goto end*/
		
		/*time date start*/
		
		timeDate = new JMenuItem("Time/Date");
		this.add(timeDate);
		timeDate.setFont(Preferences.menuItemFont);
		timeDate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dateTimeString = new Date().toString();
				TextView textView = AppGUI.get().textView;
				textView.insert(dateTimeString,textView.getCaretPosition());
			}
			
		});
				
		timeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.CTRL_MASK| ActionEvent.SHIFT_MASK));	
		
		
		/*time date end*/
		
		
	}
}
