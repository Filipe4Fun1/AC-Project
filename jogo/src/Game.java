import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;


public class Game implements KeyboardHandler {
    //STATES
    private final int playsState = 0;
    private final int overState = 1;
    private final int pauseState = 2;
    private int gameState;

    //SHAPES
    private final Picture background;
    private final Text gamePaused;
    private final Text gameOverP1;
    private final Text gameOverP2;
    private final Text gameOver2;
    private final Rectangle blackScreen;
    private final Picture player1ship;
    private final Picture player2ship;
    public static int PADDING = 10;

    ///players
    private final Players[] players = {
            new Players("Player 1"),
            new Players("Player 2")
    };
    private Players winner = players[0];

    //asteroids
    private final ArrayList<Asteroids> asteroids = new ArrayList<>();

    //bullets
    private final ArrayList<Bullets> bullets1 = new ArrayList<>();
    private final ArrayList<Bullets> bullets2 = new ArrayList<>();


    public Game() {
        //// bg
        background = new Picture(PADDING, PADDING, "resources/background.png");
        background.draw();
        gameState = playsState;

        ///playerships
        player1ship = new Picture(background.getWidth() / 2, PADDING + 11, "resources/player2ship.png");
        player2ship = new Picture(background.getWidth() / 2, (background.getHeight() + 1) - 50, "resources/player1ship.png");
        player2ship.draw();
        player1ship.draw();

        //shapes
        gamePaused = new Text((background.getWidth() / 2) - PADDING, background.getHeight() / 2, "GAME PAUSED");
        gamePaused.setColor(Color.WHITE);
        gamePaused.grow(50, 20);
        gameOverP1 = new Text((background.getWidth() / 2) + 5, background.getHeight() / 2, "CYAN PLAYER WON!");
        gameOverP1.setColor(Color.WHITE);
        gameOverP1.grow(50, 20);
        gameOverP2 = new Text((background.getWidth() / 2) + 8, background.getHeight() / 2, "RED PLAYER WON!");
        gameOverP2.setColor(Color.WHITE);
        gameOverP2.grow(50, 20);
        gameOver2 = new Text((background.getWidth() / 2) - 6, gameOverP1.getY() + 50, "Press ESC to restart");
        gameOver2.setColor(Color.WHITE);
        gameOver2.grow(40, 10);
        blackScreen = new Rectangle(PADDING, PADDING, background.getWidth(), background.getHeight());
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
            if (direction == Direction.LEFT) {
                x = background.getWidth();
            } else {
                x = background.getX();
            }

            asteroids.add(new Asteroids(x, y, "resources/asteroides.png", direction));
            asteroids.get(i).draw();
        }
    }


    public void start() throws InterruptedException {
        while (true) {

            // Pause for a while
            Thread.sleep(20);
            if(players[0].isDead()){
                gameState = overState;
                blackScreen.fill();
                gameOverP1.draw();
                gameOver2.draw();
            }else if(players[1].isDead()){
                gameState = overState;
                blackScreen.fill();
                gameOverP2.draw();
                gameOver2.draw();
            }


            if (gameState == playsState) {
                // Move asteroids && bullets
                moveObjects();

                //check asteroid && bullet limits
                checkLimit();

                //check bullet collisions
                checkCollisions();
            }

        }

    }


    public void checkLimit() {
        Bullets toremoveB1= null;
        Bullets toremoveB2 = null;
        for (Asteroids asteroid : asteroids) {
            if (asteroid.getX() >= background.getWidth() - 50) {
                asteroid.setDirection(Direction.LEFT);
            }
            if (asteroid.getX() <= background.getX() + 20) {
                asteroid.setDirection(Direction.RIGHT);
            }
        }

        for (Bullets bullet : bullets1) {
            if ((bullet.getY() >= background.getHeight() - 20)) {
                bullet.delete();
                toremoveB1 = bullet;
            }
            if (bullet.getY() <= background.getY() + 10) {
                bullet.delete();
                toremoveB1 = bullet;
            }
        }

        for (Bullets bullet : bullets2) {
            if ((bullet.getY() >= background.getHeight() - 20)) {
                bullet.delete();
                toremoveB2 = bullet;
            }
            if (bullet.getY() <= background.getY() + 10) {
                bullet.delete();
                toremoveB2 = bullet;
            }
        }
        bullets1.remove(toremoveB1);
        bullets2.remove(toremoveB2);
    }

    public void checkCollisions() throws InterruptedException {
        Asteroids toRemoveA = null;
        Bullets toRemoveB1 = null;
        Bullets toRemoveB2 = null;

        for (Bullets bullet : bullets1) {
            for (Asteroids asteroid : asteroids) {
                if (bullet.intersects(asteroid)) {
                    toRemoveB1 = bullet;
                    bullet.delete();
                    toRemoveA = asteroid;
                    asteroid.delete();
                }
            }
        }
        for (Bullets bullet : bullets2) {
            for (Asteroids asteroid : asteroids) {
                if (bullet.intersects(asteroid)) {
                    toRemoveB2 = bullet;
                    bullet.delete();
                    toRemoveA = asteroid;
                    asteroid.delete();
                }
            }
        }

        for (Bullets bullet : bullets1) {
            if (bullet.intersects(player2ship)) {
                toRemoveB1 = bullet;
                players[0].hit();
                Thread.sleep(20);
                bullet.delete();

            }
        }

        for (Bullets bullet : bullets2) {
            if (bullet.intersects(player1ship)) {
                toRemoveB2 = bullet;
                players[1].hit();
                Thread.sleep(20);
                bullet.delete();

            }
        }


        bullets1.remove(toRemoveB1);
        bullets2.remove(toRemoveB2);
        asteroids.remove(toRemoveA);


        if (asteroids.size() == 6) {
            createAsteroids();
        }
    }

    private void moveObjects() {
        for (Bullets bullet : bullets1) {
            bullet.move();
        }
        for (Bullets bullet : bullets2) {
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
                if(bullets2.size() == 0 && gameState == playsState) {
                    bullets2.add(new Bullets(player2ship.getX() + (player2ship.getWidth() / 2) - 5, player2ship.getY(), 10, 30, Direction.DOWN));
                    bullets2.get(0).fill();
                    bullets2.get(0).setColor(Color.RED);
                }
                break;
            case KeyboardEvent.KEY_SHIFT:
                if(bullets1.size() == 0 && gameState == playsState) {
                    bullets1.add(new Bullets(player1ship.getX() + (player1ship.getWidth() / 2) - 5, player1ship.getY() + 20, 10, 30, Direction.UP));
                    bullets1.get(0).fill();
                    bullets1.get(0).setColor(Color.CYAN);
                }
                break;
            case KeyboardEvent.KEY_P:
                if (gameState == playsState) {
                    gameState = pauseState;
                    gamePaused.draw();
                    System.out.println("Game paused");
                } else if (gameState == pauseState) {
                    gamePaused.delete();
                    gameState = playsState;
                    System.out.println("Game resumed");
                }
                break;
            case KeyboardEvent.KEY_ESC:
                if(gameState == overState) {
                    players[0].setDead(false);
                    players[0].resetHp();
                    players[1].setDead(false);
                    players[1].resetHp();
                    blackScreen.delete();
                    gameOver2.delete();
                    gameOverP1.delete();
                    gameOverP2.delete();
                    gameState = playsState;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


}
