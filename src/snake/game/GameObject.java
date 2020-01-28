package snake.game;

import java.awt.*;
import snake.*;

public abstract class GameObject {
	
	protected final int TILE_SIZE;
	protected int x, y;
	
	public GameObject( int x, int y, int ts ) {
		this.x = x;
		this.y = y;
		this.TILE_SIZE = ts;
	}

	public abstract void tick();
	public abstract void render( Graphics g );

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}

