package texteditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


class FontChooser extends JFrame {

	private static final long serialVersionUID = -5824597483000748916L;

	JList <String> fontList;
	JList <Integer> fontSizeList;
	
	JPanel sample;
	JLabel sampleLabel;
	Font selectedFont = new Font("Ariel", Font.PLAIN,8);
	GraphicsEnvironment environment;
	Font[] allFonts;
	GridBagConstraints gbc;
	float selectedSize = 8;
	boolean canceled;
	
    FontChooser() {
		super("Font");
		setLayout(new GridBagLayout());
		setIconImage(Preferences.logo);
		environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		allFonts = environment.getAllFonts();
				
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 15, 10, 15);
		gbc.gridwidth = 1;
		gbc.anchor= GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		
		this.add(new JLabel("Font"),gbc);
		
		gbc.gridx = 0; gbc.gridy =1;
		fontList = getFontList();
		this.add(new JScrollPane(fontList),gbc);		
		
		gbc.gridx = 1; gbc.gridy = 0;
		this.add(new JLabel("Font Size"));
		
		gbc.gridx = 1; gbc.gridy = 1;
		fontSizeList = getFontSizeList();
		
		gbc.gridx = 1; gbc.gridy=1;
		gbc.weightx = 1;
		this.add(fontSizeList);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JScrollPane(fontSizeList),gbc);			
		
		sample = new JPanel(new BorderLayout());
		sample.setPreferredSize(new Dimension(100,100));
		sample.setSize(300, 300);
		sampleLabel = new JLabel("AaBbCc");
		sample.add(sampleLabel,BorderLayout.CENTER);
		sample.setBorder(BorderFactory.createTitledBorder("sample"));
		gbc.gridx = 0; gbc.gridy = 2;
		this.add(sample,gbc);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(1,2,2,5));
		
		JButton okButton = getOkButton();	
	
		buttonsPanel.add(okButton);
		
		JButton cancelButton = getCancelButton();
		
		buttonsPanel.add(cancelButton);
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(buttonsPanel,gbc);
		this.pack();
		this.setResizable(false);
	}
	
    JButton getOkButton() {
    	 JButton okButton = new JButton("Ok");
    	 okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FontChooser.this.dispose();
			}
		});
    	 
    	return okButton;
    }
    
    JButton getCancelButton() {
    	JButton cancelButton = new JButton("Cancel");
    	cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				canceled = true;
				FontChooser.this.dispose();
			}
		});
    	return cancelButton;
    }
    
    Font getSelectedFont() {
    	return selectedFont;
    }
    
	JList<String> getFontList(){
		Vector<String> fontStrings =new Vector<String>(allFonts.length);
		for(Font font : allFonts) {
			fontStrings.add(font.getName());
		}
		
		JList<String> fontList = new JList<String>(fontStrings);
		fontList.setVisibleRowCount(10);
		fontList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) {
					selectedFont = allFonts[fontList.getSelectedIndex()];
					if(selectedFont.getSize()<=8 || selectedFont.getSize()<selectedSize)
						selectedFont = selectedFont.deriveFont(selectedSize);
					updateSample();
				}
			}
		});
		
		return fontList;
	}
	
	JList<Integer> getFontSizeList(){
		JList<Integer> fontSizeList = new JList<Integer>(new Integer[] {8,9,10,11,12,14,16,18,20,22,24,26,28,36,48,72});
		fontSizeList.setVisibleRowCount(10);
		
		fontSizeList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) {
					Integer sizeInteger = fontSizeList.getSelectedValue();
					if(sizeInteger!=null){
						selectedFont = selectedFont.deriveFont((float)sizeInteger);
						selectedSize = sizeInteger;
						updateSample();
					}
				}
			}
		});


		return fontSizeList;
	}
	
	boolean isCanceled() {
		return canceled;
	}
	
	void updateSample() {
		sampleLabel.setFont(selectedFont);
	}
	
}
