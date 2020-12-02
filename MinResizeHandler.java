package texteditor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class MinResizeHandler extends ComponentAdapter {
	int mWidth;
	int mHeight;
	
	public MinResizeHandler(Dimension size) {
		super();
		mWidth = size.width;
		mHeight = size.height;
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		super.componentResized(e);
		Component source =(Component) e.getSource();
		Dimension dimension = source.getSize();
	    if(dimension.height<mWidth || dimension.width<mHeight) {
	    	dimension.height = 400;
	    	dimension.width = 400;
	    }
	    
	}
}
