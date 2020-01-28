package snake.game;

import java.awt.*;
import java.util.Random;

public class Apple extends GameObject{
	
	private static Random r = new Random();

	public Apple( int fs, int ts) {
		super( r.nextInt( (fs / ts) - 2) + 1 , r.nextInt( (fs / ts) - 2) + 1, ts );
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor( Color.RED );
		g.fillRect( x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE );
		
	}
}
