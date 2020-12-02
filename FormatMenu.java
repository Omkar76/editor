package texteditor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

class FormatMenu extends JMenu {

	private static final long serialVersionUID = 5132069834875672786L;
	JCheckBoxMenuItem wordWrap;
	JMenuItem font;
	
	public FormatMenu() {
		super("Format");
		setFont(Preferences.menuFont);
		
		wordWrap = new JCheckBoxMenuItem("Word Wrap", false);
		this.add(wordWrap);
		
		wordWrap.setFont(Preferences.menuItemFont);
		wordWrap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AppGUI.get().textView.setLineWrap(wordWrap.isSelected());
			}
		});
		
		
		font = new JMenuItem("Font");
		this.add(font);
		
		font.setFont(Preferences.menuItemFont);
		font.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FontChooser fontChooser = new FontChooser();
				fontChooser.setVisible(true);
				fontChooser.addWindowListener(new WindowAdapter() {
					
					@Override
					public void windowClosed(WindowEvent e) {
						if(!fontChooser.isCanceled()) {
							Font font = fontChooser.getSelectedFont();		
							AppGUI.get().textView.setFont(font);
						}
					}
			
				});
			}
		});
		
		
	}
}
