package ru.deknil.snake.core;

import ru.deknil.snake.core.enums.GameState;
import ru.deknil.snake.core.enums.MoveDirection;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @Author Deknil
 * @GitHub <a href=https://github.com/Deknil>https://github.com/Deknil</a>
 * @Date 06.02.2024
 * @Description Keyboard listener handler
 * <p></p>
 * Snake © 2024. All rights reserved.
 */
public class KeyboardHandler implements KeyListener {
    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Not used
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (GameLogic.gameState == GameState.MENU || GameLogic.gameState == GameState.END) {
                GameLogic.score = 0;
                GameLogic.changeState(GameState.PLAY);
            }
        }

        if (GameLogic.snake == null) return;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W ->{
                GameLogic.snake.changeMoveDirection(MoveDirection.UP);
            }
            case KeyEvent.VK_A -> {
                GameLogic.snake.changeMoveDirection(MoveDirection.LEFT);
            }
            case KeyEvent.VK_D -> {
                GameLogic.snake.changeMoveDirection(MoveDirection.RIGHT);
            }
            case KeyEvent.VK_S -> {
                GameLogic.snake.changeMoveDirection(MoveDirection.BOTTOM);
            }
        }
    }
}