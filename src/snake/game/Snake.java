package snake.game;

import java.awt.*;
import java.util.ArrayList;

public class Snake extends GameObject{
	
	private ArrayList<BodyPart> body;
	private int size;
	private Direction direction;
	
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

	public Point isLeftClear() {
		if( direction == Direction.UP ) {
			return new Point( x - 1 , y );
		}
		else if( direction == Direction.RIGHT ) {
			return new Point( x , y - 1 );
		}
		else if( direction == Direction.DOWN ) {
			return new Point( x + 1 , y );
		}
		else if( direction == Direction.LEFT ) {
			return new Point( x , y + 1 );
		}
		
		return null;
	}

	public Point isRightClear() {
		if( direction == Direction.UP ) {
			return new Point( x + 1 , y );
		}
		else if( direction == Direction.RIGHT ) {
			return new Point( x , y + 1 );
		}
		else if( direction == Direction.DOWN ) {
			return new Point( x - 1 , y );
		}
		else if( direction == Direction.LEFT ) {
			return new Point( x , y - 1 );
		}
		
		return null;
	}

	public Point isAheadClear() {
		if( direction == Direction.UP ) {
			return new Point( x , y - 1 );
		}
		else if( direction == Direction.RIGHT ) {
			return new Point( x + 1, y );
		}
		else if( direction == Direction.DOWN ) {
			return new Point( x , y + 1 );
		}
		else if( direction == Direction.LEFT ) {
			return new Point( x - 1, y );
		}
		
		return null;
	}

	public boolean intersects( Point p ) {
		
		if( p.x < 1 || p.x >= 44 ||  p.y < 1 || p.y >= 44 ) {
			return false;
		}
		
		for( BodyPart b : body ) {
			if( p.x == b.getX() && p.y == b.getY() )
				return false;
		}
		
		return true;
	}

	public boolean isAppleLeft(Apple apple) {
		if( direction == Direction.UP ) {
			if( apple.getX() < x )
				return true;
			return false;
		}
		else if( direction == Direction.RIGHT ) {
			if( apple.getY() < y )
				return true;
			return false;
		}
		else if( direction == Direction.DOWN ) {
			if( apple.getX() > x )
				return true;
			return false;
		}
		else if( direction == Direction.LEFT ) {
			if( apple.getY() > y )
				return true;
			return false;
		}
		
		return false;
	}

	public boolean isAppleRight(Apple apple) {
		if( direction == Direction.UP ) {
			if( apple.getX() > x )
				return true;
			return false;
		}
		else if( direction == Direction.RIGHT ) {
			if( apple.getY() > y )
				return true;
			return false;
		}
		else if( direction == Direction.DOWN ) {
			if( apple.getX() < x )
				return true;
			return false;
		}
		else if( direction == Direction.LEFT ) {
			if( apple.getY() < y )
				return true;
			return false;
		}
		
		return false;
	}

	public void grow() {
		size++;	
	}
}
