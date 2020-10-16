package spaceshapes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * A class that implements test cases aimed at identifying bugs in the 
 * implementations of class OvalShape.
 * 
 * @author Ho Zhin Man
 * 
 */
public class TestOval {
	private MockPainter _painter;

	@Before
	public void setUp() {
		_painter = new MockPainter();
	}
	/**
	 * Test the creation of a OvalShape object and check it's associated characteristics, 
	 * color and text. 
	 */
	@Test
	public void testCreationNoText() {
		OvalShape oval = new OvalShape(250, 150, 20, 10, 50, 30);
		assertEquals(250,oval.x());
		assertEquals(150,oval.y());
		assertEquals(20,oval.deltaX());
		assertEquals(10,oval.deltaY());
		assertEquals(50,oval.width());
		assertEquals(30,oval.height());
		assertNull(oval._text);
	}
	/**
	 * Test the creation of a OvalShape object and check it's associated characteristics, 
	 * color and text. 
	 */
	@Test
	public void testCreationWithText() {
		OvalShape oval = new OvalShape(250, 150, 20, 10, 50, 30);
		oval.addText("hello");
		assertEquals(250,oval.x());
		assertEquals(150,oval.y());
		assertEquals(20,oval.deltaX());
		assertEquals(10,oval.deltaY());
		assertEquals(50,oval.width());
		assertEquals(30,oval.height());
		assertNotNull(oval._text);
	}
	/**
	 * Tests OvalShape Object movement without bouncing off bounds
	 */
	@Test
	public void testSimpleMove() {
		OvalShape oval = new OvalShape(100, 100, 20, 10, 50, 30);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		assertEquals("(oval 100,100,50,30)(oval 120,110,50,30)", _painter.toString());		
	}
	/**
	 * Following tests test OvalShape object bouncing off each of the individual 
	 * four bounds
	 */	
	@Test
	public void testBounceOffRightBound() {
		OvalShape oval = new OvalShape(220, 150, 20, 10, 50, 30);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		assertEquals("(oval 220,150,50,30)(oval 240,160,50,30)(oval 250,170,50,30)(oval 230,180,50,30)", _painter.toString());
	}
	@Test
	public void testBounceOffLeftBound() {
		OvalShape oval = new OvalShape(10, 150, -20, 10, 50, 30);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		assertEquals("(oval 10,150,50,30)(oval 0,160,50,30)(oval 20,170,50,30)", _painter.toString());
	}
	@Test
	public void testBounceOffBottomBound() {
		OvalShape oval = new OvalShape(150, 250, 20, 30, 50, 30);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		assertEquals("(oval 150,250,50,30)(oval 170,270,50,30)(oval 190,240,50,30)", _painter.toString());
	}
	@Test
	public void testBounceOffTopBound() {
		OvalShape oval = new OvalShape(150, 40, 20, -30, 50, 30);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		assertEquals("(oval 150,40,50,30)(oval 170,10,50,30)(oval 190,0,50,30)(oval 210,30,50,30)", _painter.toString());
	}
	/**
	 * The following tests test OvalShape object bouncing off of each of the four
	 * corners
	 */
	@Test
	public void testBounceOffTopRight() {
		OvalShape oval = new OvalShape(210, 40, 20, -30, 50, 30);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		assertEquals("(oval 210,40,50,30)(oval 230,10,50,30)(oval 250,0,50,30)(oval 230,30,50,30)", _painter.toString());
	}
	@Test
	public void testBounceOffTopLeft() {
		OvalShape oval = new OvalShape(40, 40, -20, -30, 50, 30);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		assertEquals("(oval 40,40,50,30)(oval 20,10,50,30)(oval 0,0,50,30)(oval 20,30,50,30)", _painter.toString());
	}
	@Test
	public void testBounceOffBottomLeft() {
		OvalShape oval = new OvalShape(40, 210, -20, 30, 50, 30);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		assertEquals("(oval 40,210,50,30)(oval 20,240,50,30)(oval 0,270,50,30)(oval 20,240,50,30)", _painter.toString());
	}
	@Test
	public void testBounceOffBottomRight() {
		OvalShape oval = new OvalShape(210, 210, 20, 30, 50, 30);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		oval.move(300, 300);
		oval.paint(_painter);
		assertEquals("(oval 210,210,50,30)(oval 230,240,50,30)(oval 250,270,50,30)(oval 230,240,50,30)", _painter.toString());
	}
}
