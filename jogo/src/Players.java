import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;

public class Players{
    private String name;
    private int hitpoints = 5;
    private boolean isDead;

    public Players(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void resetHp(){
        hitpoints = 5;
    }
    public boolean isDead() {
        return isDead;
    }

    public void hit(){
        hitpoints--;
        if(hitpoints <= 0){
            isDead = true;
            System.out.println(name + " died!");
            return;
        }
        System.out.println(name + " got hit! Hitpoints left: " + hitpoints);
    }


}
