package com.sibb.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class Keyboard extends KeyAdapter {

    private static final ConcurrentHashMap<Integer, Character> keysPressed;

    static {
        keysPressed = new ConcurrentHashMap<>();
    }

    /**
     * Method getKeysPressed.
     *
     * @return Object[]
     */
    public static synchronized Object[] getKeysPressed() {
        return keysPressed.entrySet().toArray();
    }

    /**
     * Method keyPressed.
     *
     * @param e KeyEvent
     * @see java.awt.event.KeyListener#keyPressed(KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (!keysPressed.containsKey(e.getKeyCode()))
            keysPressed.put(e.getKeyCode(), e.getKeyChar());
    }

    /**
     * Method keyReleased.
     *
     * @param e KeyEvent
     * @see java.awt.event.KeyListener#keyReleased(KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent e) {
        remove(e.getKeyCode());
    }

    /**
     * Method remove.
     *
     * @param key int
     */
    public static void remove(int key) {
        if (keysPressed.containsKey(key))
            keysPressed.remove(key);
    }

}
