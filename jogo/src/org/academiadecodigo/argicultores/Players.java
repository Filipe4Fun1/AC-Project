package org.academiadecodigo.argicultores;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Players{
    private String name;
    private int hitpoints = 5;
    private boolean isDead;

    public Players(String name){
        this.name = name;
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


    public static class Pkeyboard extends Keyboard {
        public Pkeyboard(KeyboardHandler keyboardHandler) {
            super(keyboardHandler);

            KeyboardEvent setaRight = new KeyboardEvent();
            setaRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            setaRight.setKey(KeyboardEvent.KEY_RIGHT);
            this.addEventListener(setaRight);

            KeyboardEvent setaLeft= new KeyboardEvent();
            setaLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            setaLeft.setKey(KeyboardEvent.KEY_LEFT);
            this.addEventListener(setaLeft);

            KeyboardEvent aKey = new KeyboardEvent();
            aKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            aKey.setKey(KeyboardEvent.KEY_A);
            this.addEventListener(aKey);

            KeyboardEvent dKey= new KeyboardEvent();
            dKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            dKey.setKey(KeyboardEvent.KEY_D);
            this.addEventListener(dKey);

            KeyboardEvent wKey= new KeyboardEvent();
            wKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            wKey.setKey(KeyboardEvent.KEY_W);
            this.addEventListener(wKey);

            KeyboardEvent shiftKey= new KeyboardEvent();
            shiftKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            shiftKey.setKey(KeyboardEvent.KEY_SHIFT);
            this.addEventListener(shiftKey);

            KeyboardEvent pKey= new KeyboardEvent();
            pKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            pKey.setKey(KeyboardEvent.KEY_P);
            this.addEventListener(pKey);

            KeyboardEvent escKey= new KeyboardEvent();
            escKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            escKey.setKey(KeyboardEvent.KEY_ESC);
            this.addEventListener(escKey);

            KeyboardEvent leftRelease = new KeyboardEvent();
            leftRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
            leftRelease.setKey(KeyboardEvent.KEY_LEFT);
            this.addEventListener(leftRelease);

            KeyboardEvent rightRelease = new KeyboardEvent();
            rightRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
            rightRelease.setKey(KeyboardEvent.KEY_RIGHT);
            this.addEventListener(rightRelease);

            KeyboardEvent dRelease = new KeyboardEvent();
            dRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
            dRelease.setKey(KeyboardEvent.KEY_D);
            this.addEventListener(dRelease);

            KeyboardEvent aRelease = new KeyboardEvent();
            aRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
            aRelease.setKey(KeyboardEvent.KEY_A);
            this.addEventListener(aRelease);
        }


    }
}
