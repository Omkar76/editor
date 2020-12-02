package texteditor;

import javax.swing.JMenuBar;
import javax.swing.border.EmptyBorder;

class AppMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1320851096441936925L;
	
	AppMenuBar() {
		setBackground(Preferences.colorSecondary);
		setBorder(new EmptyBorder(3, 0, 3, 0));
		add(new FileMenu());
		add(new EditMenu());
		add(new FormatMenu());
	}
	
}
