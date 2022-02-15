package org.academiadecodigo.argicultores;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Bullets extends Rectangle {
    private int speed = 30;
    private Direction direction;

    public Bullets(double v, double v1, double v2, double v3, Direction d) {
        super(v, v1, v2, v3);
        direction = d;
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

    public boolean intersects(Picture pic) {
        int tw = getWidth();
        int th = getHeight();
        int ew = pic.getWidth();
        int eh = pic.getHeight();
        if (ew <= 0 || eh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = getX();
        int ty = getY();
        int rx = pic.getX();
        int ry = pic.getY();
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
