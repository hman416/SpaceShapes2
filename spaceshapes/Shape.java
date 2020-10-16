

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract superclass to represent the general concept of a Shape. This class
 * defines state common to all special kinds of Shape instances and implements
 * a common movement algorithm. Shape subclasses must override method paint()
 * to handle shape-specific painting.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public abstract class Shape {
	// === Constants for default values. ===
	protected static final int DEFAULT_X_POS = 0;

	protected static final int DEFAULT_Y_POS = 0;

	protected static final int DEFAULT_DELTA_X = 5;

	protected static final int DEFAULT_DELTA_Y = 5;

	protected static final int DEFAULT_HEIGHT = 35;

	protected static final int DEFAULT_WIDTH = 25;
	// ===

	// === Instance variables, accessible by subclasses.
	protected int _x;

	protected int _y;

	protected int _deltaX;

	protected int _deltaY;

	protected int _width;

	protected int _height;
	// ===

	protected CarrierShape _parent;
	protected String _text;

	/**
	 * Creates a Shape object with default values for instance variables.
	 */
	public Shape() {
		this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * Creates a Shape object with a specified x and y position.
	 */
	public Shape(int x, int y) {
		this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * Creates a Shape instance with specified x, y, deltaX and deltaY values.
	 * The Shape object is created with a default width and height.
	 */
	public Shape(int x, int y, int deltaX, int deltaY) {
		this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * Creates a Shape instance with specified x, y, deltaX, deltaY, width and
	 * height values.
	 */
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height) {
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
	}

	/**
	 * Moves this Shape object within the specified bounds. On hitting a 
	 * boundary the Shape instance bounces off and back into the two- 
	 * dimensional world. 
	 * @param width - width of two-dimensional world.
	 * @param height - height of two-dimensional world.
	 */
	public void move(int width, int height) {
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;

		if (nextX <= 0) {
			nextX = 0;
			_deltaX = -_deltaX;
		} else if (nextX + _width >= width) {
			nextX = width - _width;
			_deltaX = -_deltaX;
		}

		if (nextY <= 0) {
			nextY = 0;
			_deltaY = -_deltaY;
		} else if (nextY + _height >= height) {
			nextY = height - _height;
			_deltaY = -_deltaY;
		}

		_x = nextX;
		_y = nextY;
	}

	/**
	 * Method to be implemented by concrete subclasses to handle subclass
	 * specific painting.
	 * @param painter the Painter object used for drawing.
	 */
	protected abstract void doPaint(Painter painter);

	/**
	 * Returns this Shape object's x position.
	 */
	public int x() {
		return _x;
	}

	/**
	 * Returns this Shape object's y position.
	 */
	public int y() {
		return _y;
	}

	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaX() {
		return _deltaX;
	}

	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaY() {
		return _deltaY;
	}

	/**
	 * Returns this Shape's width.
	 */
	public int width() {
		return _width;
	}

	/**
	 * Returns this Shape's height.
	 */
	public int height() {
		return _height;
	}

	/**
	 * Returns a String whose value is the fully qualified name of this class 
	 * of object. E.g., when called on a RectangleShape instance, this method 
	 * will return "spaceshapes.RectangleShape".
	 */
	public String toString() {
		return getClass().getName();
	}
	/**
	 * Returns the _parent field which stores the reference to the parent shape. If 
	 * this shape has no parent shape then it will return null.
	 */
	public CarrierShape parent() {
		return _parent;
	}
	/**
	 * Returns a list of shapes in the containment hierarchy of a carrier shape. The
	 * list starts at the highest hierarchy (root) and goes down to "this" shape. If
	 * this shape is not in a carrier shape then this shape will be the only shape in
	 * the list
	 */
	public List<Shape> path(){
		List<Shape> pHierarchy = new ArrayList<Shape>(); 
		if (parent() != null ) {
			pHierarchy.addAll(parent().path());//recursive call going to root
		}
		pHierarchy.add(this);
		return pHierarchy;
	}
	/**
	 * Paints the shape and it's associated features such as text or color
	 * Makes use of the "Template Method" pattern
	 * @param p - painter object used to paint the shape and its associated features
	 */
	public void paint(Painter p) {
		doPaint(p);
		if (_text != null) {
			p.drawCenteredText(_text, _x, _y, _width, _height);
		}
	}
	/**
	 * Add text to be associated with the shape object
	 * @param text - a string that would be painted with the text
	 */
	public void addText(String text) {
		_text = text;
	}
	/**
	 * Remove any associated text with the shape object
	 */
	public void removeText() {
		_text = null;
	}
}
