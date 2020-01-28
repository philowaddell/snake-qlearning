package snake.agent;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import snake.game.Apple;
import snake.game.Game;
import snake.game.Snake;

public class Agent {
	
	private Snake snake;
	private ArrayList<Apple> apples;
	private Random r;
	
	private boolean isAppleLeft;
	private boolean isAppleRight;
	private boolean isAppleAhead;
	
	private boolean isLeftClear;
	private boolean isRightClear;
	private boolean isAheadClear;
	
	private Action action;
	
	private float alpha = 0.8f;
	private float gamma = 1.0f;
	private float epsilon = 0.7f;
	
	private float reward = 0.0f;
	
	private boolean[] state = new boolean[6];
	private float[] qValues;

	private Game game;
	
	private HashMap<String, float[]> qMap;
	
	
	
	public Agent( Snake snake, ArrayList<Apple> apples, Game game ) {
		this.snake = snake;
		this.apples = apples;
		this.game = game;
		r = new Random();
		qMap = new HashMap<String, float[]>();
	}
	
	public void tick() {
		
		isLeftClear = snake.intersects( snake.isLeftClear() );
		isRightClear = snake.intersects( snake.isRightClear() );
		isAheadClear = snake.intersects( snake.isAheadClear() );
		isAppleLeft = snake.isAppleLeft( apples.get(0) );
		isAppleRight = snake.isAppleRight( apples.get(0) );
		if( !isAppleLeft && !isAppleRight )
			isAppleAhead = true;
		
		state[0] = isLeftClear;
		state[1] = isRightClear;
		state[2] = isAheadClear;
		state[3] = isAppleLeft;
		state[4] = isAppleRight;
		state[5] = isAppleAhead;
		
		String key = state.toString();
		
		if( qMap.containsKey( key ) == false ) {
			qMap.put( key, new float[3] );
		}
		
		if( r.nextFloat() > epsilon ) {
			action = Action.valueOf(r.nextInt(3));
		}
		else {
			action = Action.valueOf( getMaxQ( qMap.get( key ) ) );
		}
		
		if( action == Action.LEFT ) {
			snake.turnLeft();
		}
		else if( action == Action.RIGHT ) {
			snake.turnRight();
		}
		else if( action == Action.NOTHING ) {
			
		}
		
		if( snake.intersects( new Point( apples.get(0).getX(), apples.get(0).getY() ) ) ) {
			reward = 10;
		}
		else if( snake.intersects( new Point( snake.getX(), snake.getY() ) ) ) {
			reward = -1;
		}
		
		
		
				
				
				
				
				
				
		
		
		/*if( isAppleLeft && isLeftClear) {
			snake.turnLeft();
		}
		else if( isAppleRight && isRightClear ) {
			snake.turnRight();
		}
		else if( isAppleAhead && isAheadClear ) {

		}
		else if( isLeftClear ){
			snake.turnLeft();
		}
		else if( isRightClear ){
			snake.turnRight();
		}
		else {
			game.gameOver();
		}*/
			

			
	}
	
	private int getMaxQ( float[] values ) {
		float max = -1.0f;
		int index = -1;
		for( int i = 0; i < values.length; i++ ) {
			if( values[i] > max ) {
				max = values[i];
				index = i;
			}
		}
		return index;
	}

}
