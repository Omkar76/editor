package texteditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

class Preferences {
	static Font menuFont = new Font("Sansserif",Font.PLAIN,15);
	static Font menuItemFont = new Font("Sansserif",Font.PLAIN,14);
	static Image logo = Toolkit
			.getDefaultToolkit()
			.getImage(AppGUI.class.getResource("images/editor.png"));
	static Color colorPrimary = new Color(161, 203, 212);
	static Color colorSecondary = new Color(211, 218, 237);
	
	static Font textViewFont = new Font("Sansserif",Font.PLAIN,14);
	private Preferences() {}
}
