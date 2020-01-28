package snake.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import snake.agent.Agent;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private static final int WINDOW_WIDTH = 467, WINDOW_HEIGHT = 490, FRAME_SIZE = 450, TILE_SIZE = 10;
	
	private boolean isRunning = false;
	private Thread thread;
	
	private Snake snake;
	private ArrayList<Apple> apples;
	
	private Agent agent;
	
	private int score = 0;

	private int ticks = 0;
	
	public Game() {
		new Window( WINDOW_WIDTH, WINDOW_HEIGHT, "Snake AI", this );
		
		snake = new Snake( 1, 1, TILE_SIZE );
		apples = new ArrayList<Apple>();
		apples.add( new Apple( FRAME_SIZE, TILE_SIZE ) );
		agent = new Agent( snake, apples, this );
		
		start();
	}
	
	// Implemented as part of Runnable
	private void start() {
		isRunning = true;
		thread = new Thread( this );
		thread.start();
	}

	// Implemented as part of Runnable
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}

	// Implemented as part of Runnable
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		//int frames = 0;
		while( isRunning ) {
			long now = System.nanoTime();
			delta += ( now - lastTime ) / ns;
			lastTime = now;
			while( delta >= 1 ) {
				tick();
				//updates++;
				delta--;
			}
			render();
			//frames++;
			
			if( System.currentTimeMillis() - timer > 1000 ) {
				timer += 1000;
				//frames = 0;
				//updates = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		
			snake.tick();
		
			if( apples.get(0).getX() == snake.getX() && apples.get(0).getY() == snake.getY() ) {
				apples.remove(0);
				snake.grow();
				score++;
			}
			
			if( apples.size() < 2 ) {
				apples.add( new Apple( FRAME_SIZE, TILE_SIZE ) );
			}	
			
			agent.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if( bs == null ) {
			this.createBufferStrategy( 3 );
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor( Color.BLACK );
		g.fillRect( 0, 0, FRAME_SIZE * 2, FRAME_SIZE * 2 );
		
		/*g.setColor( Color.GREEN );
		for( int i = 0; i < FRAME_SIZE; i++ ) {
			g.drawLine( 0, i * TILE_SIZE, FRAME_SIZE, i * TILE_SIZE );
			g.drawLine( i * TILE_SIZE, 0, i * TILE_SIZE, FRAME_SIZE );
		}*/
		
		snake.render(g);
		apples.get(0).render(g);
		
		g.setColor( Color.GREEN );
		g.fillRect(0, 0, FRAME_SIZE, TILE_SIZE);
		g.fillRect(0, 0, TILE_SIZE, FRAME_SIZE);
		g.fillRect(FRAME_SIZE - TILE_SIZE, 0, TILE_SIZE, FRAME_SIZE);
		g.fillRect(0, FRAME_SIZE -TILE_SIZE, FRAME_SIZE, TILE_SIZE);
		
		g.setColor( Color.BLACK );
		g.drawString("Score: " + Integer.toString( score ), TILE_SIZE, TILE_SIZE );
		
		bs.show();
	}
	

	
	public static void main( String args[] ) {
		new Game();
	}

	public void gameOver() {
		JOptionPane.showMessageDialog(this, "Game Over!");
		System.exit(0);
		
	}
}

