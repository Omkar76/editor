package texteditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

class AppGUI extends JFrame {
	private final Dimension minSize = new Dimension(800,400);
	private static AppGUI appGUI;
	private static final long serialVersionUID = -7884992396982971255L;
	TextView textView;
	
	private AppGUI() {
		super("Editor - Untitled");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize();
		setJMenuBar(new AppMenuBar());
		setIconImage(Preferences.logo);
		textView = new TextView();
		add(new JScrollPane(textView));
	}
	
	static AppGUI get() {
		if(appGUI==null) {
			appGUI = new AppGUI();
		}
		return appGUI;
	}
	
	
	void setSize() {
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(minSize);
		
		//to fix bug in setMinimumSize() that is present in old versions of JDK
		addComponentListener(new MinResizeHandler(minSize));
	}
	
	void alert(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
}
