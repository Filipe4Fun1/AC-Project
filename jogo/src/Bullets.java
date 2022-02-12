import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Bullets extends Rectangle {
    private int speed = 50;
    private Direction direction;

    public Bullets(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }


    public void move(){
        switch (direction) {
            case UP:
                translate(0,speed);
                break;
            case DOWN:
                translate(0,-speed);
                break;
        }
    }

    public boolean intersects(Ellipse ellipse) {
        int tw = getWidth();
        int th = getHeight();
        int ew = ellipse.getWidth();
        int eh = ellipse.getHeight();
        if (ew <= 0 || eh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = getX();
        int ty = getY();
        int rx = ellipse.getX();
        int ry = ellipse.getY();
        ew += rx;
        eh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((ew < rx || ew > tx) &&
                (eh < ry || eh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }
}
