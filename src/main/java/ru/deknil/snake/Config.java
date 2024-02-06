package ru.deknil.snake;

import java.awt.*;

/**
 * @Author Deknil
 * @GitHub <a href=https://github.com/Deknil>https://github.com/Deknil</a>
 * @Date 06.02.2024
 * @Description Main application configuration file
 * <p></p>
 * Snake © 2024. All rights reserved.
 */
public class Config {
    public static final String WINDOW_TITLE = "Snake"; // Window title
    public static final int WINDOW_WIDTH = 512; // Window width
    public static final int WINDOW_HEIGHT = 512; // Window height
    public static final int WINDOW_CENTER_X = WINDOW_WIDTH / 2; // Window center horizontally
    public static final int WINDOW_CENTER_Y = WINDOW_HEIGHT / 2; // Vertical window center
    public static final int GAME_SPEED = 250; // Game update speed, in ms.

    public static final int CELL_SIZE = 32; // Grid cell size
    public static final int MAX_GRID_INDEX_X = WINDOW_WIDTH / CELL_SIZE; // Maximum horizontal grid index
    public static final int MAX_GRID_INDEX_Y = WINDOW_HEIGHT / CELL_SIZE; // Maximum vertical grid index

    public static final int SNAKE_START_LENGTH = 3; // Starting snake length

    public final static Color BACKGROUND_COLOR = new Color(10, 10, 10, 255); // Canvas background color
    public final static Color GRID_COLOR = new Color(255, 255, 255, 10); // Canvas grid color
    public final static Color SNAKE_COLOR = new Color(80, 169, 110, 255); // Snake color
    public final static Color APPLE_COLOR = new Color(243, 45, 144, 255); // Apple color
}
