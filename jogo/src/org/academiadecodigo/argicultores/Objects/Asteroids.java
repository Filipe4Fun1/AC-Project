package org.academiadecodigo.argicultores.Objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Asteroids extends Picture {
    private String type;
    private int speed;
    private Direction direction;

    public Asteroids(double var1, double var3, String var5, Direction direction, String type) {
        super(var1, var3, var5);
        speed = (int) (Math.random() * (20 - 10) + 10);
        this.direction = direction;
        this.type = type;
    }

    public String getType() {
        return type;
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
