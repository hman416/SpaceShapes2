package spaceshapes;

import static org.junit.Assert.*;
import java.awt.Color;


import org.junit.Before;
import org.junit.Test;

/**
 * A class that implements test cases aimed at identifying bugs in the 
 * implementations of class DynamicShape.
 * 
 * @author Ho Zhin Man
 * 
 */
public class TestDynamicShape {
private MockPainter _painter; 
	
	@Before
	public void setUp(){
		_painter = new MockPainter(); 
	}
	/**
	 * Test the creation of a DynamicShape object and check it's associated characteristics, 
	 * color and text. 
	 */
	@Test
	public void testDynamicShapeCreationNoText() {
		DynamicShape shape = new DynamicShape(100,50,20,10,60,60,new Color(32,100,90));
		assertEquals(100,shape.x());
		assertEquals(50,shape.y());
		assertEquals(20,shape.deltaX());
		assertEquals(10,shape.deltaY());
		assertEquals(60,shape.width());
		assertEquals(60,shape.height());
		assertEquals("java.awt.Color[r=32,g=100,b=90]",shape._dynamicColor.toString());
		assertFalse(shape._isColored);
		assertNull(shape._text);
	}
	/**
	 * Test the creation of a DynamicShape object and check it's associated characteristics, 
	 * color and text. 
	 */
	@Test
	public void testDynamicShapeCreationWithText() {
		DynamicShape shape = new DynamicShape(100,50,20,10,60,60,new Color(32,100,90));
		shape.addText("Hello");
		assertEquals(100,shape.x());
		assertEquals(50,shape.y());
		assertEquals(20,shape.deltaX());
		assertEquals(10,shape.deltaY());
		assertEquals(60,shape.width());
		assertEquals(60,shape.height());
		assertEquals("java.awt.Color[r=32,g=100,b=90]",shape._dynamicColor.toString());
		assertFalse(shape._isColored);
		assertNotNull(shape._text);
	}
	/**
	 * Test the DynamicShape object is capable of changing color when a specific color is 
	 * assigned
	 */
	@Test
	public void testDynamicShapeColor() {
		DynamicShape shape = new DynamicShape(100,50,20,0,60,60,new Color(32,100,90));
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		assertEquals("(rectangle 100,50,60,60)(change color to: java.awt.Color[r=32,g=100,b=90])"
				+ "(colored rectangle 120,50,60,60)(change color to: java.awt.Color[r=212,g=212,b=212])" 
				, _painter.toString());
	}
	/**
	 * Test the DynamicShape object is capable of changing color when no specific color is 
	 * assigned
	 */
	@Test
	public void testDynamicShapeDefaultColor() {
		DynamicShape shape = new DynamicShape(100,50,20,0,60,60);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		assertEquals("(rectangle 100,50,60,60)(change color to: java.awt.Color[r=212,g=212,b=212])"
				+ "(colored rectangle 120,50,60,60)(change color to: java.awt.Color[r=212,g=212,b=212])" 
				, _painter.toString());
	}
	/**
	 * Tests DynamicShape object movement without bouncing off bounds
	 */
	@Test
	public void testSimpleMove() {
		RectangleShape shape = new RectangleShape(100, 20, 12, 15);
		shape.paint(_painter);
		shape.move(500, 500);
		shape.paint(_painter);
		assertEquals("(rectangle 100,20,25,35)(rectangle 112,35,25,35)", 
				_painter.toString());
	}
	/**
	 * Following tests test DynamicShape object bouncing off each of the individual 
	 * four bounds
	 */
	@Test
	public void testChangeColorOnRightBounce(){
		DynamicShape shape = new DynamicShape(100,50,20,0,60,60,new Color(32,100,90));
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);//colored rectangle should be maintained
		assertEquals("(rectangle 100,50,60,60)(change color to: java.awt.Color[r=32,g=100,b=90])"
				+ "(colored rectangle 120,50,60,60)(change color to: java.awt.Color[r=212,g=212,b=212])"
				+ "(change color to: java.awt.Color[r=32,g=100,b=90])(colored rectangle 100,50,60,60)"
				+ "(change color to: java.awt.Color[r=212,g=212,b=212])"
				+ "(change color to: java.awt.Color[r=32,g=100,b=90])(colored rectangle 80,50,60,60)"
				+ "(change color to: java.awt.Color[r=212,g=212,b=212])", _painter.toString());
	}
	
	@Test
	public void testMaintainColorOnLeftBounce(){
		DynamicShape shape = new DynamicShape(20,50,-20,0,60,60,new Color(32,100,90));
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);//colored rectangle should be maintained
		assertEquals("(rectangle 20,50,60,60)(change color to: java.awt.Color[r=32,g=100,b=90])"
				+ "(colored rectangle 0,50,60,60)(change color to: java.awt.Color[r=212,g=212,b=212])"
				+ "(change color to: java.awt.Color[r=32,g=100,b=90])(colored rectangle 20,50,60,60)"
				+ "(change color to: java.awt.Color[r=212,g=212,b=212])"
				+ "(change color to: java.awt.Color[r=32,g=100,b=90])(colored rectangle 40,50,60,60)"
				+ "(change color to: java.awt.Color[r=212,g=212,b=212])", _painter.toString());
	}
	
	
	@Test
	public void testBounceOffTop(){
		DynamicShape shape = new DynamicShape(100,20,0,-20,60,60,new Color(32,100,90));
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);//shape should not undergo any color change and stay like a normal rectangle
		assertEquals("(rectangle 100,20,60,60)(rectangle 100,0,60,60)"
				+ "(rectangle 100,20,60,60)(rectangle 100,40,60,60)", _painter.toString());
	}
	@Test
	public void testBounceOffBottom(){
		DynamicShape shape = new DynamicShape(100,100,0,20,60,60,new Color(32,100,90));
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		assertEquals("(rectangle 100,100,60,60)(rectangle 100,120,60,60)"
				+ "(rectangle 100,100,60,60)(rectangle 100,80,60,60)", _painter.toString());
	}
	/**
	 * Following tests test a DynamicShape shape bouncing off all four corners 
	 */
	@Test
	public void testBounceOffBottomRight(){
		DynamicShape shape = new DynamicShape(100,100,20,20,60,60,new Color(32,100,90));
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		assertEquals("(rectangle 100,100,60,60)(rectangle 120,120,60,60)"
				+ "(rectangle 100,100,60,60)(rectangle 80,80,60,60)", _painter.toString());
	}
	@Test
	public void testBounceOffBottomLeft(){
		DynamicShape shape = new DynamicShape(20,100,-20,20,60,60,new Color(32,100,90));
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		assertEquals("(rectangle 20,100,60,60)(rectangle 0,120,60,60)"
				+ "(rectangle 20,100,60,60)(rectangle 40,80,60,60)", _painter.toString());
	}
	@Test
	public void testBounceOffTopLeft(){
		DynamicShape shape = new DynamicShape(20,20,-20,-20,60,60,new Color(32,100,90));
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		assertEquals("(rectangle 20,20,60,60)(rectangle 0,0,60,60)"
				+ "(rectangle 20,20,60,60)(rectangle 40,40,60,60)", _painter.toString());
	}
	@Test
	public void testBounceOffTopRight(){
		DynamicShape shape = new DynamicShape(100,20,20,-20,60,60,new Color(32,100,90));
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		shape.move(180,180);
		shape.paint(_painter);
		assertEquals("(rectangle 100,20,60,60)(rectangle 120,0,60,60)"
				+ "(rectangle 100,20,60,60)(rectangle 80,40,60,60)", _painter.toString());
	}


}
