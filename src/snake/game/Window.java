package snake.game;

import java.awt.Dimension;
import javax.swing.JFrame;
import snake.*;

public class Window {
	
	public static JFrame frame;

	public Window( int width, int height, String title, Game game ) {
		
		JFrame frame = new JFrame( title );
		
		frame.setPreferredSize( new Dimension( width, height ) );
		frame.setMaximumSize( new Dimension( width, height ) );
		frame.setMinimumSize( new Dimension( width, height ) );
		
		frame.add( game );
		frame.setResizable( false );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setLocationRelativeTo( null );
		frame.setVisible( true );
	}
	
}
