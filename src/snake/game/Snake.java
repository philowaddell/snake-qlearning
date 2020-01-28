package snake.game;

import java.awt.*;
import java.util.ArrayList;

public class Snake extends GameObject{
	
	private ArrayList<BodyPart> body;
	private int size;
	private Direction direction;
	private int deaths = 0;
	
	public Snake( int x, int y, int ts ) {
		super( x, y, ts );
		size = 5;
		body = new ArrayList<BodyPart>();
		body.add( new BodyPart( x, y, TILE_SIZE ) );
		direction = Direction.RIGHT;
	}
	
	public void tick() {
		if( body.size() >= size ) {
			body.remove(0);
		}	

		
		if( direction == Direction.UP ) {
			y--;
		}
		else if( direction == Direction.RIGHT ) {
			x++;
		}
		else if( direction == Direction.DOWN ) {
			y++;
		}
		else if( direction == Direction.LEFT ) {
			x--;
		}
		
		if( body.size() != size ) {
			body.add( new BodyPart( x, y, TILE_SIZE ) );
		}
		

	}
	
	public void turnLeft() {
		if( direction == Direction.UP ) {
			direction = Direction.LEFT;
		}
		else if( direction == Direction.RIGHT ) {
			direction = Direction.UP;
		}
		else if( direction == Direction.DOWN ) {
			direction = Direction.RIGHT;
		}
		else if( direction == Direction.LEFT ) {
			direction = Direction.DOWN;
		}
	}
	
	public void turnRight() {
		if( direction == Direction.UP ) {
			direction = Direction.RIGHT;
		}
		else if( direction == Direction.RIGHT ) {
			direction = Direction.DOWN;
		}
		else if( direction == Direction.DOWN ) {
			direction = Direction.LEFT;
		}
		else if( direction == Direction.LEFT ) {
			direction = Direction.UP;
		}
	}
	
	public void render( Graphics g ) {
		for( BodyPart b : body )
			b.render(g);
	}

	public boolean isLeftClear() {
		if( direction == Direction.UP ) {
			return !isDead(new Point( x - 1 , y ));
		}
		else if( direction == Direction.RIGHT ) {
			return !isDead(new Point( x , y - 1 ));
		}
		else if( direction == Direction.DOWN ) {
			return !isDead(new Point( x + 1 , y ));
		}
		else if( direction == Direction.LEFT ) {
			return !isDead(new Point( x , y + 1 ));
		}
		
		return false;
	}

	public boolean isRightClear() {
		if( direction == Direction.UP ) {
			return !isDead(new Point( x + 1 , y ));
		}
		else if( direction == Direction.RIGHT ) {
			return !isDead(new Point( x , y + 1 ));
		}
		else if( direction == Direction.DOWN ) {
			return !isDead(new Point( x - 1 , y ));
		}
		else if( direction == Direction.LEFT ) {
			return !isDead(new Point( x , y - 1 ));
		}
		
		return false;
	}

	public boolean isAheadClear() {
		if( direction == Direction.UP ) {
			return !isDead(new Point( x , y - 1 ));
		}
		else if( direction == Direction.RIGHT ) {
			return !isDead(new Point( x + 1, y ));
		}
		else if( direction == Direction.DOWN ) {
			return !isDead(new Point( x , y + 1 ));
		}
		else if( direction == Direction.LEFT ) {
			return !isDead(new Point( x - 1, y ));
		}
		
		return false;
	}

	public boolean intersects( Point p ) {
		for( int i = 0; i < body.size() - 1; i++ ) {
			BodyPart b = body.get(i);
			if( p.x == b.getX() && p.y == b.getY() )
				return true;
		}
		return false;
	}
	
	public boolean isDead( Point p ) {
		
		if( p.x < 1 || p.x >= 44 ||  p.y < 1 || p.y >= 44 ) {
			return true;
		}
		
		if( intersects(p) ) {
			return true;
		}
		return false;
	}

	public void grow() {
		size++;	
	}

	public void reset() {
		body.clear();
		size = 5;
		Game.deaths++;
		if( Game.score > Game.hiscore )
			Game.hiscore = Game.score;
		Game.score = 0;
		x = 22;
		y = 22;
	}

	public boolean isAppleLeft(Apple apple) {
		if( direction == Direction.UP && apple.getX() < x ) {
			return true;
		}
		else if( direction == Direction.RIGHT && apple.getY() < y ) {
			return true;
		}
		else if( direction == Direction.DOWN && apple.getX() > x ) {
			return true;
		}
		else if( direction == Direction.LEFT && apple.getY() > y ) {
			return true;
		}
		return false;
	}

	public boolean isAppleRight(Apple apple) {
		if( direction == Direction.UP && apple.getX() > x ) {
			return true;
		}
		else if( direction == Direction.RIGHT && apple.getY() > y ) {
			return true;
		}
		else if( direction == Direction.DOWN && apple.getX() < x ) {
			return true;
		}
		else if( direction == Direction.LEFT && apple.getY() < y ) {
			return true;
		}
		return false;
	}

	public boolean isAppleAhead(Apple apple) {
		if( direction == Direction.UP && apple.getX() == x && apple.getY() < y ) {
			return true;
		}
		else if( direction == Direction.RIGHT && apple.getY() == y && apple.getX() > x ) {
			return true;
		}
		else if( direction == Direction.DOWN && apple.getX() == x && apple.getY() > y ) {
			return true;
		}
		else if( direction == Direction.LEFT && apple.getY() == y && apple.getX() < x ) {
			return true;
		}
		return false;
	}

	public boolean isAppleBehind(Apple apple) {
		if( direction == Direction.UP && apple.getX() == x && apple.getY() > y ) {
			return true;
		}
		else if( direction == Direction.RIGHT && apple.getY() == y && apple.getX() < x ) {
			return true;
		}
		else if( direction == Direction.DOWN && apple.getX() == x && apple.getY() < y ) {
			return true;
		}
		else if( direction == Direction.LEFT && apple.getY() == y && apple.getX() > x ) {
			return true;
		}
		return false;
	}
}
