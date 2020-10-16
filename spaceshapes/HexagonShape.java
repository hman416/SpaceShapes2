package spaceshapes;
/**
 * Class to represent a simple Hexagon space-shape.
 * 
 * @author Ho Zhin Man
 *
 */
public class HexagonShape extends Shape{
	/**
	 * Construct HexagonShapeShape using default x position, y position, deltaX, deltaY, width 
	 * and height. If the height or width is odd, it will be adjusted by +1.
	 */
	public HexagonShape() {
		super();
		if (_height%2 == 1) {
			_height = _height+1;
		}
		if (_width%2 == 1) {
			_width = _width+1;
		}
	}
	/**
	 * Creating HexagonShapeShape with some specified specifications and default height and width
	 * If the height or width is odd, it will be adjusted by +1.
	 * @param x - x position of the top left corner of the shape's bounding box
	 * @param y - y position of the top left corner of the shape's bounding box
	 */
	public HexagonShape(int x, int y) {
		super(x,y);
		if (_height%2 == 1) {
			_height = _height+1;
		}
		if (_width%2 == 1) {
			_width = _width+1;
		}
	}
	/**
	 * Creating HexagonShapeShape with some specified specifications and default height and width
	 * If the height or width is odd, it will be adjusted by +1.
	 * @param x - x position of the top left corner of the shape's bounding box
	 * @param y - y position of the top left corner of the shape's bounding box
	 * @param deltaX - change in x position
	 * @param deltaY - change in y position
	 */
	public HexagonShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);//if odd,
		if (_height%2 == 1) {
			_height = _height+1;
		}
		if (_width%2 == 1) {//odd number width must be adjusted
			_width = _width+1;
		}
	}
	
	/**
	 * Creating HexagonShapeShape with all specified specifications and default height and width
	 * If the height or width is odd, it will be adjusted by +1.
	 * @param x - x position of the top left corner of the shape's bounding box
	 * @param y - y position of the top left corner of the shape's bounding box
	 * @param deltaX - change in x position
	 * @param deltaY - change in y position
	 * @param width - max width of the HexagonShape
	 * @param height - max height of the HexagonShape
	 */
	public HexagonShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
		if (height%2 == 1) {
			_height = height+1;
		}
		if (_width%2 == 1) {//odd number width must be adjusted by +1
			_width = _width+1;
		}
	}
	/**
	 * Paints this HexagonShape using the supplied Painter object
	 * if the width of the HexagonShape is less than 40 then HexagonShape will be a four sided 
	 * shape instead
	 */
	@Override
	public void paintShape(Painter painter) {
		if(_width>40) {
			painter.drawLine(_x, _y+(_height/2), _x+20, _y);
			painter.drawLine(_x+20,_y, _x+_width-20, _y);
			painter.drawLine(_x+_width-20, _y, _x+_width, _y+_height/2);
			painter.drawLine(_x+_width, _y+_height/2, _x+_width-20, _y+_height);
			painter.drawLine(_x+_width-20, _y+_height, _x+20, _y+_height);
			painter.drawLine(_x+20, _y+_height, _x, _y+(_height/2));
		}
		else {
			painter.drawLine(_x, _y+(_height/2), _x+_width/2, _y);
			painter.drawLine(_x+_width/2, _y, _x+_width, _y+_height/2);
			painter.drawLine(_x+_width, _y+_height/2, _x+_width/2, _y+_height);
			painter.drawLine(_x+_width/2, _y+_height, _x, _y+(_height/2));
		}
	}
	

}
