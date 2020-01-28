package snake.agent;

import java.util.HashMap;
import java.util.Map;

public enum Action {

	LEFT(0),
	RIGHT(1),
	NOTHING(2);

    private int value;
    private static HashMap<Object, Object> map = new HashMap<>();

    private Action(int value) {
        this.value = value;
    }

    static {
        for (Action action : Action.values()) {
            map.put(action.value, action);
        }
    }

    public static Action valueOf(int action) {
        return (Action) map.get(action);
    }

    public int getValue() {
        return value;
    }
	
}
