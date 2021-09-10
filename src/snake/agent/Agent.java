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
	
	private float alpha = 0.7f;
	private float gamma = 0.8f; // 0.025
	private float epsilon = 0.99f;
	
	private float reward = 0;
	
	private boolean[] state = new boolean[7];
	private float[] qValues;
	
	private int dist;
	private int oldDist;

	private Game game;
	
	private HashMap<String, float[]> qMap;
	
	public Agent( Snake snake, ArrayList<Apple> apples, Game game ) {
		this.snake = snake;
		this.apples = apples;
		this.game = game;
		r = new Random();
		qValues = new float[3];
		qMap = new HashMap<String, float[]>();
		dist = distanceToApple();
	}
	
	public void tick() {
		
		oldDist = dist;
		
		state[0] = snake.isLeftClear();
		state[1] = snake.isRightClear();
		state[2] = snake.isAheadClear();
		state[3] = snake.isAppleLeft(apples.get(0));
		state[4] = snake.isAppleRight(apples.get(0));
		state[5] = snake.isAppleAhead(apples.get(0));
		state[6] = snake.isAppleBehind(apples.get(0));
				
		
		String key = "";
		
		for( boolean s : state )
			key += Integer.toString((s ? 1 : 0));
		
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
		
		snake.tick();
		
		dist = distanceToApple();
		
		if( snake.intersects( new Point( apples.get(0).getX(), apples.get(0).getY() ) ) ) {
			reward = 50.0f;
		}
		else if( snake.isDead( new Point( snake.getX(), snake.getY() ) ) ) {
			reward = -200.0f;
			snake.reset();
		}
		else if( oldDist > dist ) {
			reward = 0.5f;
		}
		else if( oldDist < dist ) {
			reward = -0.5f;
		}
		else {
			reward = -0.5f;
		}
		
		qValues = qMap.get(key);
		
		qValues[action.ordinal()] = qValues[action.ordinal()] + alpha * ( reward  + ( gamma * 10 ) - qValues[action.ordinal()] );
		
		//qValues[action.ordinal()] = qValues[action.ordinal()] + alpha * ( reward + ( gamma * 10 - qValues[action.ordinal()] ) );
		
		qMap.put(key, qValues);
	}
	

	private int distanceToApple() {
		return Math.abs( apples.get(0).getX() - snake.getX() ) + Math.abs( apples.get(0).getY() - snake.getY() );
	}

	private int getMaxQ( float[] values ) {
		float max = values[0];
		int index = 0;
		for( int i = 1; i < values.length; i++ ) {
			if( values[i] > max ) {
				max = values[i];
				index = i;
			}
		}
		return index;
	}

}
