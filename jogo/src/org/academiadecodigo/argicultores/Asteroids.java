package org.academiadecodigo.argicultores;

import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Asteroids extends Picture {
    private int speed;
    private Direction direction;

    public Asteroids(double var1, double var3, String var5, Direction direction) {
        super(var1, var3, var5);
        speed = (int) (Math.random() * (20 - 10) + 10);
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
