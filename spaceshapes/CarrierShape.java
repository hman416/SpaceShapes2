package spaceshapes;

import java.util.ArrayList;
import java.util.List;
/**
 * Class to represent a rectangular shape that can hold other shapes within 
 * it's bounds. This sub-class of shape is the composite class in a general hierarchy
 * pattern
 * 
 * @author Ho Zhin Man
 *
 */
public class CarrierShape extends Shape{
	private List<Shape> _children;

	/**
	 * Default constructor that creates a CarrierShape instance whose instance
	 * variables are set to default values.
	 */
	public CarrierShape() {
		super();
		_children = new ArrayList<Shape>();
	}
	/**
	 * Creates a RectangleShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 */
	public CarrierShape(int x, int y) {
		super(x, y);
		_children = new ArrayList<Shape>();
	}
	/**
	 * Creates a CarrierShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public CarrierShape(int x, int y, int deltaX, int deltaY){
		super(x, y, deltaX, deltaY);
		_children = new ArrayList<Shape>();
	}
	/**
	 * Creates a RectangleShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 */
	public CarrierShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
		_children = new ArrayList<Shape>();
	}
	/**
	 * changes the CarrierShape's position according to it's velocity, as well as 
	 * changing the children of the CarrierShape's position inside the bounds of the
	 * CarrierShape
	 */
	@Override
	public void move(int width, int height) {
		super.move(width, height);
		for(int i = 0; i<_children.size();i++) {//moving all children
			_children.get(i).move(_width,_height);
		}
	}
	/**
	 * Paints this CarrierShape object as well as its children using the supplied 
	 * Painter object.
	 */
	@Override
	public void paintShape(Painter painter) {
		painter.drawRect(_x,_y,_width,_height);
		painter.translate(_x,_y);
		//paint all children in list according to new origin
		for (int i=0; i<_children.size();i++) {
			_children.get(i).paint(painter);
		}
		//reset origin
		painter.translate(-_x,-_y);
				
	}
	/**
	 * Adds a shape inside this CarrierShape provided this shape does not already have
	 * a parent and it fits within the bounds of the CarrierShape
	 * @param shape - desired shape to be added to CarrierShape object
	 * @throws IllegalArgumentException
	 */
	void add(Shape shape) throws IllegalArgumentException{
		//makes sure shape doesnt have parent
		//makes sure shape does not exceed the bounds of carrier 
		if (shape.parent()!=null || (shape.x() + shape.width()) >= _width || (shape.y()+shape.height()) >= _height) {
			throw new IllegalArgumentException("shape already belongs to a carriershape");
		}else {
			_children.add(shape);
			shape._parent = this;
		}
	}
	/**
	 * Removes specified shape from a CarrierShape instance and removes this 
	 * CarrierShape instance as specified shape's parent 
	 * @param shape - specified shape that is child of this CarrierShape object
	 */
	void remove(Shape shape) {
		_children.remove(shape);
		shape._parent = null;		
	}
	/**
	 * Returns a shape at a particular position in a CarrierShape instance. If the 
	 * specified position is not within the bounds of the CarrierShape instance
	 * an IndexOutOfBoundsException is thrown.
	 * @param index - index of desired shape
	 * @throws IndexOutOfBoundsException
	 */
	public Shape shapeAt(int index) throws IndexOutOfBoundsException{
		if(index<0 || index > (_children.size()-1)) {
			throw new IndexOutOfBoundsException("indexing is outside of list");
		}
		else {
			return _children.get(index);
		}
	}
	
	/**
	 * Returns the index of a specified shape in this instance of CarrierShape
	 * @param shape - desired shape
	 */
	public int indexOf(Shape shape) {
		return _children.indexOf(shape);
				
	}
	/**
	 * returns whether or not the specified shape is contained in an instance of 
	 * CarrierShape
	 * @param shape - desired shape
	 */
	public boolean contains(Shape shape){
		return _children.contains(shape);
	}
	
	/**
	 * Counts the number of shapes that is contained in a CarrierShape instance
	 */
	public int shapeCount() {
		return _children.size();
	}

}
