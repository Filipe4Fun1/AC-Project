

public class Players{
    private int hitpoints = 4;
    private boolean isDead;

    public int getHitpoints() {
        return hitpoints;
    }

    public void hit(){
        hitpoints--;
        if(hitpoints <= 0){
            isDead = true;
        }
    }


}
