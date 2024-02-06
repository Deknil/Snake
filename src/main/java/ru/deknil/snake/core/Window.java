package ru.deknil.snake.core;

import ru.deknil.snake.Config;

import javax.swing.*;

/**
 * @Author Deknil
 * @GitHub <a href=https://github.com/Deknil>https://github.com/Deknil</a>
 * @Date 06.02.2024
 * @Description This class represents a custom window for the Snake application.
 *              It extends JFrame and implements the Runnable interface.
 * <p></p>
 * Snake © 2024. All rights reserved.
 */
public class Window extends JFrame implements Runnable {
    private final Canvas canvas; // Game Canvas

    /**
     * Game window constructor
     */
    public Window() {
        setTitle(Config.WINDOW_TITLE);
        setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        canvas = new Canvas();
        add(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        GameLogic.update();
        canvas.repaint();
    }
}