import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;


public class Game implements KeyboardHandler {
    private int delete;
    private Rectangle background;
    //// player ship logic
    private Picture player1ship;
    private Picture player2ship;
    ///players
    private Players[] players = {
            new Players(),
            new Players()
    };
    //asteroids
    private ArrayList<Asteroids> asteroids = new ArrayList<>();
    //bullets
    private ArrayList<Bullets> bullets = new ArrayList<>();

    public static int PADDING = 10;

    public Game(int x, int y) {
        //// bg
        background = new Rectangle(PADDING, PADDING, x, y);
        background.fill();
        background.setColor(Color.BLACK);
        ///playerships
        player1ship = new Picture(background.getWidth() / 2, PADDING, "resources/player2ship.png");
        player2ship = new Picture(background.getWidth() / 2, (background.getHeight() + PADDING) - 50, "resources/player1ship.png");
        player2ship.draw();
        player1ship.draw();
    }

    public void init() {
        createAsteroids();
    }

    private void createAsteroids() {
        for (int i = 0; i < 10; i++) {
            Direction direction;
            double randomNum = Math.random();
            int x;
            int y = (int) (Math.random() * ((background.getY() + 90) - (background.getHeight() - 90))) + (background.getHeight() - 90);

            if (randomNum < 0.5) {
                direction = Direction.LEFT;
            } else {
                direction = Direction.RIGHT;
            }
            if(direction == Direction.LEFT){
                x = background.getWidth();
            }else{
                x = background.getX();
            }

            asteroids.add(new Asteroids(x, y, 25, 25, direction));
            asteroids.get(i).fill();
            asteroids.get(i).setColor(Color.RED);
        }
    }


    public void start() throws InterruptedException {

        while (true) {

            // Pause for a while
            Thread.sleep(50);

            // Move asteroids && bullets
            moveObjects();

            //check asteroid && bullet limits
            checkLimit();

            //check bullet collisions
            checkCollisions();
        }

    }


    public void checkLimit() {
        Bullets toremoveB = null;
        for (int i = 0; i < asteroids.size(); i++) {
            if (asteroids.get(i).getX() >= background.getWidth() - 50){
                asteroids.get(i).setDirection(Direction.LEFT);
            }
            if(asteroids.get(i).getX() <= background.getX() + 20) {
                asteroids.get(i).setDirection(Direction.RIGHT);
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            if ((bullets.get(i).getY() >= background.getHeight() - 20)){
                bullets.get(i).delete();
                toremoveB = bullets.get(i);
            }
            if(bullets.get(i).getY() <= background.getY() + 10) {
                bullets.get(i).delete();
                toremoveB = bullets.get(i);
            }
        }
        bullets.remove(toremoveB);
    }

    public void checkCollisions(){
        ArrayList<Asteroids> toRemoveA = new ArrayList<>();
        ArrayList<Bullets> toRemoveB = new ArrayList<>();
        Asteroids toremoveA = null;
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < asteroids.size(); j++){
                if(bullets.get(i).intersects(asteroids.get(j))){
                    toRemoveB.add(bullets.get(i));
                    bullets.get(i).delete();
                    toRemoveA.add(asteroids.get(j));
                    asteroids.get(j).delete();
                }
            }
        }
        for (int i2 = 0; i2 < bullets.size(); i2++) {
            for (int i3 = 0; i3 < toRemoveB.size(); i3++) {
                if(bullets.get(i2).equals(toRemoveB.get(i3))){
                    bullets.remove(i2);
                }
            }
        }

        for (int i4 = 0; i4 < asteroids.size(); i4++) {
            for (int i5 = 0; i5 < toRemoveA.size(); i5++) {
                if(asteroids.get(i4).equals(toRemoveA.get(i5))){
                    toremoveA =  asteroids.get(i4);
                }
            }
        }
        
        asteroids.remove(toremoveA);
        if(asteroids.size() == 3){
            createAsteroids();
        }
    }

    private void moveObjects() {
        for (Bullets bullet : bullets) {
            bullet.move();
        }
        for (Asteroids asteroid : asteroids) {
            asteroid.move();
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                if (!(player1ship.getMaxX() >= background.getWidth())) {
                    player1ship.translate(10, 0);
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                if (!(player1ship.getX() <= background.getX() + PADDING)) {
                    player1ship.translate(-10, 0);
                }
                break;
            case KeyboardEvent.KEY_A:
                if (!(player2ship.getX() <= background.getX() + PADDING)) {
                    player2ship.translate(-10, 0);
                }
                break;
            case KeyboardEvent.KEY_D:
                if (!(player2ship.getMaxX() >= background.getWidth())) {
                    player2ship.translate(10, 0);
                }
                break;
            case KeyboardEvent.KEY_W:
                bullets.add(new Bullets(player2ship.getX() + (player2ship.getWidth() / 2) - 5, player2ship.getY(), 10, 30));
                    int i1 = bullets.size() - 1;
                    bullets.get(i1).fill();
                    bullets.get(i1).setColor(Color.BLUE);
                    bullets.get(i1).setDirection(Direction.DOWN);
                    break;
                    case KeyboardEvent.KEY_SHIFT:
                        bullets.add(new Bullets(player1ship.getX() + (player1ship.getWidth() / 2) - 5, player1ship.getY() + 20, 10, 30));
                        int i2 = bullets.size() - 1;
                        bullets.get(i2).fill();
                        bullets.get(i2).setColor(Color.BLUE);
                        bullets.get(i2).setDirection(Direction.UP);
                        break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


}
