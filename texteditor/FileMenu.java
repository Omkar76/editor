package texteditor;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;


class FileMenu extends JMenu {
	private static final long serialVersionUID = -2446677015878643037L;
	
	JMenuItem newFile;
	JMenuItem newWindow;
	JMenuItem open;
	JMenuItem save;
	JMenuItem print;
	public FileMenu() {
		super("File");
		setFont(Preferences.menuFont);
		
		
		/*new file start*/
		newFile = new JMenuItem("New File");
		this.add(newFile);
		
		newFile.setFont(Preferences.menuItemFont);
		newFile.addActionListener(new ActionListener() {		
			int option;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!AppGUI.get().textView.isOnDisk()) {
					option = JOptionPane.showConfirmDialog(AppGUI.get(), "Do you want to save " + AppGUI.get().textView.getName());
					if(option == JOptionPane.OK_OPTION) {
						save.doClick();
					}
				}
				
				if(option == JOptionPane.CANCEL_OPTION)
					return;
				
				AppGUI.get().textView.reset();
			}
		});
		
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		
		/*new file end*/
				
		/*open start*/
		open = new JMenuItem("Open");
		this.add(open);
		open.setFont(Preferences.menuItemFont);
		open.addActionListener(new ActionListener() {
			int option;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!AppGUI.get().textView.isOnDisk() ) {
					option = JOptionPane.showConfirmDialog(AppGUI.get(), "Do you want to save " + AppGUI.get().textView.getName());
					if(option==JOptionPane.OK_OPTION) {
						save.doClick();
					}
				}
				
				if(option == JOptionPane.CANCEL_OPTION)
					return;
				
				FileDialog fileDialog = new FileDialog(AppGUI.get(),"Select a file",FileDialog.LOAD);
				fileDialog.setVisible(true);
				if(fileDialog.getFile()!=null) {
					File file = new File(fileDialog.getDirectory()+fileDialog.getFile());
					try {
						AppGUI.get().textView.setFile(file).readFromFile();
					} catch (Exception e2) {
						JDialog dialog = new JDialog(AppGUI.get(),"Error reading file");
						dialog.setVisible(true);
					}
						
				}
			}
				
		});
		
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		
		/*open end*/
		
		/*save start*/
		save = new JMenuItem("Save");
		this.add(save);
		
		save.setFont(Preferences.menuItemFont);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TextView textView = AppGUI.get().textView;
//				if(textView.isChanged()) {
					if(textView.isOnDisk()) {
						textView.writeToFile();
					}else {
						FileDialog fileDialog = new FileDialog(AppGUI.get(),"Save As",FileDialog.SAVE);
						fileDialog.setVisible(true);
						String filePath = fileDialog.getDirectory()+fileDialog.getFile();
						if(fileDialog.getFile()!=null) {
							File file = new File(filePath);
							textView.setFile(file);
							textView.writeToFile();
						}
					}
				}
//			}
		});
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		/*save end*/
		
		print = new JMenuItem("Print");
		this.add(print);
		print.setFont(Preferences.menuFont);
		print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					AppGUI.get().textView.print();
				} catch (PrinterException e1) {
					AppGUI.get().alert("Error printing file");
				}
			}
		});
	}
	

}
