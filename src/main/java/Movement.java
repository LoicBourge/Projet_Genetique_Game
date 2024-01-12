import java.util.ArrayList;

public enum Movement {

    DOWN(0, 1),
    DOWN_RIGHT(1, 1),
    RIGHT(1, 0),
    UP_RIGHT(1, -1),
    UP(0, -1),
    UP_LEFT(-1, -1),
    LEFT(-1, 0),
    DOWN_LEFT(-1, 1),
    IDLE(0, 0);

    private int x;
    private int y;

    Movement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static ArrayList<Movement> multiplechoiceMovement(String choice) {
        char[] digits1 = choice.toCharArray();
        ArrayList<Movement> movements = new ArrayList<>();
        for (char digit : digits1) {
            movements.add(Movement.values()[Character.digit(digit, 10)]);
        }
        return movements;
    }

    public static ArrayList<Movement> generate(int MaxTic) {
        ArrayList<Movement> movements = new ArrayList<>();
        for (int i = 0; i < MaxTic; i++) {
            movements.add(Movement.values()[(int) (1 + Math.random() * 7)]);
        }
        System.out.println(movements);
        return movements;
    }

}
