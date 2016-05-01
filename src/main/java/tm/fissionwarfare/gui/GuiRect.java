package tm.fissionwarfare.gui;

public class GuiRect {

	private int x, y, width, height;
	
	public GuiRect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean contains(int px, int py) {
		return px > x && px < (x + width) && py > y && py < (y + height);
	}
}
