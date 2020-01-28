package snake.agent;

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
	
	private float alpha = 0;
	private float gamma = 0;
	
	private boolean[] state = new boolean[6];
	private Action action;
	
	
	
	private Game game;
	
	private HashMap<SAP> policy;
	
	
	
	public Agent( Snake snake, ArrayList<Apple> apples, Game game ) {
		this.snake = snake;
		this.apples = apples;
		this.game = game;
		r = new Random();
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
		
		
		if( isAppleLeft && isLeftClear) {
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
		}
			

			
	}

}
