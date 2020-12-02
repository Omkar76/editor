package texteditor;

public class Main {

	public static void main(String[] args) {
		 
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AppGUI editorGUI = AppGUI.get();
				editorGUI.setVisible(true);
			}
		});
	}

}
