import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;

import java.nio.file.Watchable;

public class main {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();

        Keyboard kb = new Keyboard(game);

        KeyboardEvent setaRight = new KeyboardEvent();
        setaRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        setaRight.setKey(KeyboardEvent.KEY_RIGHT);
        kb.addEventListener(setaRight);

        KeyboardEvent setaLeft= new KeyboardEvent();
        setaLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        setaLeft.setKey(KeyboardEvent.KEY_LEFT);
        kb.addEventListener(setaLeft);

        KeyboardEvent aKey = new KeyboardEvent();
        aKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        aKey.setKey(KeyboardEvent.KEY_A);
        kb.addEventListener(aKey);

        KeyboardEvent dKey= new KeyboardEvent();
        dKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        dKey.setKey(KeyboardEvent.KEY_D);
        kb.addEventListener(dKey);

        KeyboardEvent wKey= new KeyboardEvent();
        wKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        wKey.setKey(KeyboardEvent.KEY_W);
        kb.addEventListener(wKey);

        KeyboardEvent shiftKey= new KeyboardEvent();
        shiftKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        shiftKey.setKey(KeyboardEvent.KEY_SHIFT);
        kb.addEventListener(shiftKey);

        KeyboardEvent pKey= new KeyboardEvent();
        pKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        pKey.setKey(KeyboardEvent.KEY_P);
        kb.addEventListener(pKey);

        KeyboardEvent escKey= new KeyboardEvent();
        escKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        escKey.setKey(KeyboardEvent.KEY_ESC);
        kb.addEventListener(escKey);

        game.init();
        game.start();

    }
}
