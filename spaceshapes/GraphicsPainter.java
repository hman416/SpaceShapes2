package spaceshapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private Graphics _g;

	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
		_g.setColor(new Color(212, 212, 212));//this is default white colour
	}

	/**
	 * @see spaceshapes.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		_g.drawRect(x, y, width, height);
	}

	/**
	 * @see spaceshapes.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * @see spaeshapes.Painter.drawLine.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
		_g.fillRect(x, y, width, height);
		
	}

	@Override
	public Color getColor() {
		return _g.getColor();
		
	}

	@Override
	public void setColor(Color c) {
		_g.setColor(c);
		
	}

	@Override
	public void translate(int x, int y) {
		_g.translate(x, y);
	}

	@Override
	public void setFont(Font f) {
		_g.setFont(f);
		//allows different fonts to be set for text in painter
	}

	@Override
	public Font getFont() {
		return _g.getFont();
	}

	@Override
	public void drawCenteredText(String text, int x, int y, int width, int height) {
		FontMetrics fm = _g.getFontMetrics(getFont());
		int xPos = ((x+width/2)-(fm.stringWidth(text)/2));
		int yPos = y+height/2;
		
		//adjustment if ascent is not the same as descent
		if(fm.getAscent()>fm.getDescent()){
			yPos += (fm.getAscent()-fm.getDescent())/2;
		}else if(fm.getAscent()<fm.getDescent()){
			yPos -= (fm.getDescent()-fm.getAscent())/2;
		}		
		_g.drawString(text, xPos, yPos);		
	}
}
