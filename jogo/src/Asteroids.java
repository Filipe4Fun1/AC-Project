import org.academiadecodigo.simplegraphics.graphics.Ellipse;

public class Asteroids extends Ellipse {
    private int speed;
    private Direction direction;

    public Asteroids(double v, double v1, double v2, double v3, Direction direction) {
        super(v, v1, v2, v3);
        speed = (int) (Math.random() * (35 - 10) + 10);
        this.direction = direction;
    }

    public Direction setDirection(Direction direction) {
        return this.direction = direction;
    }

    public void move(){
        switch (direction) {
            case RIGHT:
                translate(speed,0);
                break;
            case LEFT:
                translate(-speed,0);
                break;
        }
    }
}
