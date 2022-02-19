package org.academiadecodigo.argicultores.Objects;

import org.academiadecodigo.argicultores.Game;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Playership extends Picture {
    private Direction direction;

    public Playership(double var1, double var3, String var5) {
        super(var1, var3, var5);
        this.direction = Direction.STOP;
    }

    public Direction setDirection(Direction direction) {
        return this.direction = direction;
    }

    public void move(){
        switch (direction) {
            case RIGHT:
                if (!(this.getMaxX() >= Game.BACKGROUNDW)) {
                    translate(10, 0);
                }
                break;
            case LEFT:
                if (!(this.getX() <= Game.BACKGROUNDX + Game.PADDING)) {
                    this.translate(-10, 0);
                }
                break;
            case STOP:
                translate(0,0);
                break;
        }
    }
}
