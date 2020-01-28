package snake.agent;

import snake.game.Direction;

public class SAP {
	
	private boolean[] state = new boolean[6];
	private Action action;
	private int value;
	
	public SAP( boolean[] state, float[] qValues ) {
		this.state = state;
	}

}
