package spaceshapes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * A class that implements test cases aimed at identifying bugs in the 
 * implementations of class HexagonShape.
 * 
 * @author Ho Zhin Man
 * 
 */
public class TestHexagon {
	private MockPainter _painter;

	@Before
	public void setUp(){
		_painter = new MockPainter();
	}
	/**
	 * Test HexagonShape object is not associated with any text
	 */
	@Test
	public void testCreationNoText() {
		HexagonShape shape = new HexagonShape(250, 150, 20, 10, 60, 60);
		assertNull(shape._text);
	}
	/**
	 * Test HexagonShape object is associated with any text and it is stored correctly
	 */
	@Test
	public void testCreationWithText() {
		HexagonShape shape = new HexagonShape(250, 150, 20, 10, 60, 60);
		shape.addText("hello");
		assertNotNull(shape._text);
	}
	/**
	 * Tests the construction of HexagonShape object with width greater than 40
	 */
	@Test
	public void testBigHexConstruction() {
		HexagonShape hexagon = new HexagonShape(150, 150, 20, 10, 60, 60);
		hexagon.paint(_painter);
		assertEquals("(line 150,180,170,150)(line 170,150,190,150)(line 190,150,210,180)"
				+ "(line 210,180,190,210)(line 190,210,170,210)(line 170,210,150,180)",_painter.toString());			
	}
	/**
	 * Tests the construction of HexagonShape object with width less than 40
	 */
	@Test 
	public void testSmallHexConstruction() {//includes width <40
		HexagonShape hexagon = new HexagonShape(150, 150, 20, 10, 30, 60);
		hexagon.paint(_painter);
		assertEquals("(line 150,180,165,150)(line 165,150,180,180)(line 180,180,165,210)"
				+ "(line 165,210,150,180)",_painter.toString());	
	}
	/**
	 * Tests HexagonShape Object movement without bouncing off bounds
	 */
	@Test
	public void testSimpleMove() {
		HexagonShape hexagon = new HexagonShape(150, 150, 20, 10, 60, 60);
		hexagon.paint(_painter);
		hexagon.move(500, 500);
		hexagon.paint(_painter);
		assertEquals("(line 150,180,170,150)(line 170,150,190,150)(line 190,150,210,180)"
				+ "(line 210,180,190,210)(line 190,210,170,210)(line 170,210,150,180)" + 
				"(line 170,190,190,160)(line 190,160,210,160)(line 210,160,230,190)"
				+ "(line 230,190,210,220)(line 210,220,190,220)(line 190,220,170,190)",_painter.toString());
	}
	/**
	 * Following tests test HexagonShape object bouncing off each of the individual 
	 * four bounds
	 */
	@Test
	public void testBounceOffLeft() {
		HexagonShape hexagon = new HexagonShape(230, 150, 20, 0, 60, 60);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		assertEquals("(line 230,180,250,150)(line 250,150,270,150)(line 270,150,290,180)"
				+ "(line 290,180,270,210)(line 270,210,250,210)(line 250,210,230,180)" + 
				"(line 240,180,260,150)(line 260,150,280,150)(line 280,150,300,180)"
				+ "(line 300,180,280,210)(line 280,210,260,210)(line 260,210,240,180)"+ 
				"(line 220,180,240,150)(line 240,150,260,150)(line 260,150,280,180)"
				+ "(line 280,180,260,210)(line 260,210,240,210)(line 240,210,220,180)" ,_painter.toString());
	}
	@Test
	public void testBounceOffRight() {
		HexagonShape hexagon = new HexagonShape(10, 150, -20, 0, 60, 60);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		assertEquals("(line 10,180,30,150)(line 30,150,50,150)(line 50,150,70,180)"
				+ "(line 70,180,50,210)(line 50,210,30,210)(line 30,210,10,180)" + 
				"(line 0,180,20,150)(line 20,150,40,150)(line 40,150,60,180)"
				+ "(line 60,180,40,210)(line 40,210,20,210)(line 20,210,0,180)" + 
				"(line 20,180,40,150)(line 40,150,60,150)(line 60,150,80,180)"
				+ "(line 80,180,60,210)(line 60,210,40,210)(line 40,210,20,180)"
				,_painter.toString());
	}
	@Test
	public void testBounceOffBottom() {
		HexagonShape hexagon = new HexagonShape(150, 230, 0, 20, 60, 60);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		assertEquals("(line 150,260,170,230)(line 170,230,190,230)(line 190,230,210,260)"
				+ "(line 210,260,190,290)(line 190,290,170,290)(line 170,290,150,260)" + 
				"(line 150,270,170,240)(line 170,240,190,240)(line 190,240,210,270)"
				+ "(line 210,270,190,300)(line 190,300,170,300)(line 170,300,150,270)" + 
				"(line 150,250,170,220)(line 170,220,190,220)(line 190,220,210,250)"
				+ "(line 210,250,190,280)(line 190,280,170,280)(line 170,280,150,250)"	
				,_painter.toString());
	}
	@Test
	public void testBounceOffTop() {
		HexagonShape hexagon = new HexagonShape(150, 10, 0, -20, 60, 60);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		assertEquals("(line 150,40,170,10)(line 170,10,190,10)(line 190,10,210,40)"
				+ "(line 210,40,190,70)(line 190,70,170,70)(line 170,70,150,40)" + 
				"(line 150,30,170,0)(line 170,0,190,0)(line 190,0,210,30)"
				+ "(line 210,30,190,60)(line 190,60,170,60)(line 170,60,150,30)" + 
				"(line 150,50,170,20)(line 170,20,190,20)(line 190,20,210,50)"
				+ "(line 210,50,190,80)(line 190,80,170,80)(line 170,80,150,50)"	
				,_painter.toString());
	}
	/**
	 * The following tests test HexagonShape object bouncing off of each of the four
	 * corners
	 */
	@Test
	public void testBounceOffTopRight() {
		HexagonShape hexagon = new HexagonShape(220, 10, 20, -20, 60, 60);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		assertEquals("(line 220,40,240,10)(line 240,10,260,10)(line 260,10,280,40)"
				+ "(line 280,40,260,70)(line 260,70,240,70)(line 240,70,220,40)" + 
				"(line 240,30,260,0)(line 260,0,280,0)(line 280,0,300,30)"
				+ "(line 300,30,280,60)(line 280,60,260,60)(line 260,60,240,30)" + 
				"(line 220,50,240,20)(line 240,20,260,20)(line 260,20,280,50)"
				+ "(line 280,50,260,80)(line 260,80,240,80)(line 240,80,220,50)"	
				,_painter.toString());
	}
	@Test
	public void testBounceOffTopLeft() {
		HexagonShape hexagon = new HexagonShape(10, 10, -20, -20, 60, 60);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		assertEquals("(line 10,40,30,10)(line 30,10,50,10)(line 50,10,70,40)"
				+ "(line 70,40,50,70)(line 50,70,30,70)(line 30,70,10,40)" + 
				"(line 0,30,20,0)(line 20,0,40,0)(line 40,0,60,30)"
				+ "(line 60,30,40,60)(line 40,60,20,60)(line 20,60,0,30)" + 
				"(line 20,50,40,20)(line 40,20,60,20)(line 60,20,80,50)"
				+ "(line 80,50,60,80)(line 60,80,40,80)(line 40,80,20,50)"	
				,_painter.toString());
	}
	@Test
	public void testBounceOffBottomLeft() {
		HexagonShape hexagon = new HexagonShape(10, 220, -20, 20, 60, 60);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		assertEquals("(line 10,250,30,220)(line 30,220,50,220)(line 50,220,70,250)"
				+ "(line 70,250,50,280)(line 50,280,30,280)(line 30,280,10,250)" + 
				"(line 0,270,20,240)(line 20,240,40,240)(line 40,240,60,270)"
				+ "(line 60,270,40,300)(line 40,300,20,300)(line 20,300,0,270)" + 
				"(line 20,250,40,220)(line 40,220,60,220)(line 60,220,80,250)"
				+ "(line 80,250,60,280)(line 60,280,40,280)(line 40,280,20,250)"	
				,_painter.toString());
	}
	@Test
	public void testBounceOffBottomRight() {
		HexagonShape hexagon = new HexagonShape(220, 220, 20, 20, 60, 60);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		hexagon.move(300, 300);
		hexagon.paint(_painter);
		assertEquals("(line 220,250,240,220)(line 240,220,260,220)(line 260,220,280,250)"
				+ "(line 280,250,260,280)(line 260,280,240,280)(line 240,280,220,250)" + 
				"(line 240,270,260,240)(line 260,240,280,240)(line 280,240,300,270)"
				+ "(line 300,270,280,300)(line 280,300,260,300)(line 260,300,240,270)" + 
				"(line 220,250,240,220)(line 240,220,260,220)(line 260,220,280,250)"
				+ "(line 280,250,260,280)(line 260,280,240,280)(line 240,280,220,250)"	
				,_painter.toString());
	}
	/**
	 * Tests that HexagonShape object with odd specified widths are adjusted 
	 * appropriately
	 */
	@Test
	public void testOddWidth() {
		HexagonShape hexagon = new HexagonShape(150, 150, 0, 20, 59, 60);
		hexagon.paint(_painter);
		assertEquals("(line 150,180,170,150)(line 170,150,190,150)(line 190,150,210,180)"
				+ "(line 210,180,190,210)(line 190,210,170,210)(line 170,210,150,180)"
				,_painter.toString()); 
	}
	/**
	 * Tests that HexagonShape object with odd specified heights are adjusted 
	 * appropriately
	 */
	@Test 
	public void testOddHeight() {
		HexagonShape hexagon = new HexagonShape(150, 150, 0, 20, 60, 59);
		hexagon.paint(_painter);
		assertEquals("(line 150,180,170,150)(line 170,150,190,150)(line 190,150,210,180)"
				+ "(line 210,180,190,210)(line 190,210,170,210)(line 170,210,150,180)"
				,_painter.toString());
	}
	
}
