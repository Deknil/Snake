package ru.deknil.snake.core;

import ru.deknil.snake.Config;
import ru.deknil.snake.core.enums.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author Deknil
 * @GitHub <a href=https://github.com/Deknil>https://github.com/Deknil</a>
 * @Date 06.02.2024
 * @Description The canvas class on which game elements are drawn
 * <p></p>
 * Snake © 2024. All rights reserved.
 */
public class Canvas extends JPanel {
    private final BufferedImage image = new BufferedImage(Config.MAX_GRID_INDEX_X * Config.CELL_SIZE,
            Config.MAX_GRID_INDEX_Y * Config.CELL_SIZE,
            BufferedImage.TYPE_INT_RGB);
    private final Font textFont = new Font("Arial", Font.PLAIN, 32);

    /**
     * Game map constructor
     */
    public Canvas() {
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        setFocusable(true);
        requestFocus();
        addKeyListener(new KeyboardHandler());
    }

    /**
     * Drawing a game map
     */
    private void render() {
        Graphics2D g = image.createGraphics();

        // Clear the canvas
        clear(g);

        if (GameLogic.gameState == GameState.PLAY) {
            // Draw apple
            drawApple(g);

            // Draw snake
            drawSnake(g);

            // Draw canvas grid
            drawGrid(g);
        }

        // Draw UI
        drawUI(g);
    }

    /**
     * Draw snake
     */
    private void drawSnake(Graphics2D g) {
        Snake snake = GameLogic.snake;

        if (snake == null) return;

        for (int segmentIndex = 0; segmentIndex < snake.segmentPosition.length; segmentIndex++) {
            int posX = snake.segmentPosition[segmentIndex][0];
            int posY = snake.segmentPosition[segmentIndex][1];

            g.setColor(Config.SNAKE_COLOR);

            if (segmentIndex == 0) g.setColor(Config.SNAKE_COLOR.darker().darker());

            g.fillRect(posX * Config.CELL_SIZE,
                    posY * Config.CELL_SIZE,
                    Config.CELL_SIZE,
                    Config.CELL_SIZE);
        }
    }

    /**
     * Draw apple
     */
    private void drawApple(Graphics2D g) {
        Apple apple = GameLogic.apple;

        if (apple == null) return;

        g.setColor(Config.APPLE_COLOR);
        g.fillRect(apple.posX * Config.CELL_SIZE,
                apple.posY * Config.CELL_SIZE,
                Config.CELL_SIZE,
                Config.CELL_SIZE);

    }

    /**
     * Rendering the grid
     */
    private void drawGrid(Graphics2D g) {
        g.setColor(Config.GRID_COLOR);
        for (int x = 0; x < Config.MAX_GRID_INDEX_X; x++) {
            for (int y = 0; y < Config.MAX_GRID_INDEX_Y; y++) {
                // Left border
                g.drawLine(x * Config.CELL_SIZE + Config.CELL_SIZE,
                        y * Config.CELL_SIZE,
                        x * Config.CELL_SIZE + Config.CELL_SIZE,
                        y * Config.CELL_SIZE + Config.CELL_SIZE);

                // Bottom border
                g.drawLine(x * Config.CELL_SIZE,
                        y * Config.CELL_SIZE + Config.CELL_SIZE,
                        x * Config.CELL_SIZE + Config.CELL_SIZE,
                        y * Config.CELL_SIZE + Config.CELL_SIZE);

            }
        }
    }

    /**
     * Draw UI
     */
    private void drawUI(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(textFont);

        FontMetrics fontMetrics = g.getFontMetrics(textFont);
        String endTitleText = "END GAME!";
        String menuTitleText = "Snake";
        String scoreText = String.format("Score: %d", GameLogic.score);
        String tipToPlay = "Press 'Space' to play";

        if (GameLogic.gameState == GameState.PLAY) {
            g.drawString(scoreText, (int) (Config.WINDOW_CENTER_X - fontMetrics.getStringBounds(scoreText, g).getWidth() / 2), 20 + fontMetrics.getHeight());
        }

        if (GameLogic.gameState == GameState.END) {
            g.drawString(endTitleText, (int) (Config.WINDOW_CENTER_X - fontMetrics.getStringBounds(endTitleText, g).getWidth() / 2), Config.WINDOW_CENTER_Y - fontMetrics.getHeight() );
            g.drawString(scoreText, (int) (Config.WINDOW_CENTER_X - fontMetrics.getStringBounds(scoreText, g).getWidth() / 2), Config.WINDOW_CENTER_Y + fontMetrics.getHeight() );
            g.drawString(tipToPlay, (int) (Config.WINDOW_CENTER_X - fontMetrics.getStringBounds(tipToPlay, g).getWidth() / 2), Config.WINDOW_CENTER_Y + fontMetrics.getHeight() * 3);

        }

        if (GameLogic.gameState == GameState.MENU) {
            g.drawString(menuTitleText, (int) (Config.WINDOW_CENTER_X - fontMetrics.getStringBounds(menuTitleText, g).getWidth() / 2), Config.WINDOW_CENTER_Y - fontMetrics.getHeight() );
            g.drawString(tipToPlay, (int) (Config.WINDOW_CENTER_X - fontMetrics.getStringBounds(tipToPlay, g).getWidth() / 2), Config.WINDOW_CENTER_Y + fontMetrics.getHeight() );
        }
    }

    /**
     * Clearing the Canvas
     */
    private void clear(Graphics2D g) {
        g.setColor(Config.BACKGROUND_COLOR);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * Canvas drawing logic
     */
    @Override
    public void paint(Graphics g) {
        render();
        ((Graphics2D)g).drawImage(image, null, 0, 0);
    }
}