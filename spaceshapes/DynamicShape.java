package spaceshapes;

import java.awt.Color;
/**
 * Class to represent a rectangular that can change to a colored shape.
 * 
 * @author Ho Zhin Man
 *
 */
public class DynamicShape extends Shape{//dynamic shape is rectangular
	//indicates whether or not dynamic shape should be colored
	protected boolean _isColored = false;
	
	//this will be the color used to fill the shapes
	protected final Color _dynamicColor;
	
	//this is the default color that is used to draw the shapes
	private Color _defaultColor = new Color(212, 212, 212);
	
	
	public DynamicShape() {
		super();
		_dynamicColor = new Color(212, 212, 212);
	}
	public DynamicShape(Color c) {
		super();
		_dynamicColor = c;
	}
	public DynamicShape(int x, int y) {
		super(x,y);
		_dynamicColor = new Color(212, 212, 212);
	}
	
	public DynamicShape(int x, int y, Color c) {
		super(x,y);
		_dynamicColor = c;
	}
	
	public DynamicShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
		_dynamicColor = new Color(212, 212, 212);
	}
	
	public DynamicShape(int x, int y, int deltaX, int deltaY, Color c) {
		super(x,y,deltaX,deltaY);
		_dynamicColor = c;
	}
	
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
		_dynamicColor = new Color(212, 212, 212);
	}

	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height, Color c) {
		super(x,y,deltaX,deltaY,width,height);
		_dynamicColor = c;
	}
	
	/**
	 * move method which changes the position of this instance of DynamicShape and checks
	 * for bouncing by comparing the change in speed in the x and y direction. The bounces
	 * dictate the current state of the DynamicShape instance. If a corner is hit then the
	 * DynamicShape will remain/become an empty shape (no color). This is a design decision
	 * based off the order which the bounces in x and y direction is checked.
	 */
	@Override
	public void move(int width, int height) { 
		int dXBefore = _deltaX;
		int dYBefore = _deltaY;
		super.move(width, height);
		
		//test for bounces using change in direction and assigns appropriate behaviour
		if (dXBefore != _deltaX) {
			_isColored = true;
		}
		if (dYBefore != _deltaY) {
			_isColored = false;
		}
	}
	/**
	 * Paints this DynamicShape object using the supplied Painter object.
	 */
	public void paintShape(Painter painter) {
		if(_isColored) {//checks if shape should be colored
			painter.setColor(_dynamicColor); //changing painter color
			painter.fillRect(_x, _y, _width, _height);//create solid colored shape
			painter.setColor(_defaultColor);//resets color to default for next shape
		}
		else {
			painter.drawRect(_x, _y, _width, _height);//draws empty rectangle shape
		}
	}
		
}

