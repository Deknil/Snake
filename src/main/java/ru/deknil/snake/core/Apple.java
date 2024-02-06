package ru.deknil.snake.core;

import ru.deknil.snake.Config;

/**
 * @Author Deknil
 * @GitHub <a href=https://github.com/Deknil>https://github.com/Deknil</a>
 * @Date 06.02.2024
 * @Description A class that implements an apple object
 * <p></p>
 * Snake © 2024. All rights reserved.
 */
public class Apple {
    public int posX; // Horizontal position
    public int posY; // Vertical position

    /**
     * Apple constructor
     * @param x Horizontal position
     * @param y Vertical position
     */
    public Apple(int x, int y) {
        this.posX = Math.clamp(x, 0, Config.MAX_GRID_INDEX_X - 1);
        this.posY = Math.clamp(y,0, Config.MAX_GRID_INDEX_Y - 1);
    }
}
