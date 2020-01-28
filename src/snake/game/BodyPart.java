package snake.game;

import java.awt.*;

public class BodyPart extends GameObject {
	
	public BodyPart(int x, int y, int ts) {
		super(x, y, ts);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor( Color.WHITE );
		g.fillRect( x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE );
		g.setColor( Color.BLACK );
		g.drawRect( x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE );
		
	}

}
