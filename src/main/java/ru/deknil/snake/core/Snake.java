package ru.deknil.snake.core;

import ru.deknil.snake.Config;
import ru.deknil.snake.core.enums.MoveDirection;

/**
 * @Author Deknil
 * @GitHub <a href=https://github.com/Deknil>https://github.com/Deknil</a>
 * @Date 06.02.2024
 * @Description A class that implements a snake object
 * <p></p>
 * Snake © 2024. All rights reserved.
 */
public class Snake {
    public int[][] segmentPosition; // Position of snake segments
    public MoveDirection direction = MoveDirection.RIGHT; // Direction of snake movement

    /**
     * Snake constructor
     * @param x Horizontal position of head
     * @param y Vertical position of head
     */
    public Snake(int x, int y) {
        segmentPosition = new int[Config.SNAKE_START_LENGTH][2];

        for (int segmentIndex = 0; segmentIndex < Config.SNAKE_START_LENGTH; segmentIndex++) {
            segmentPosition[segmentIndex] = new int[]{Math.clamp(x, 0, Config.MAX_GRID_INDEX_X - 1), Math.clamp(y,0, Config.MAX_GRID_INDEX_Y - 1)};
        }
    }

    /**
     * Changing the direction of the snake movement
     * @param direction direction of movement
     */
    public void changeMoveDirection(MoveDirection direction) {
        switch (direction) {
            case UP ->{
                if (this.direction == MoveDirection.BOTTOM) return;
            }
            case LEFT -> {
                if (this.direction == MoveDirection.RIGHT) return;
            }
            case RIGHT -> {
                if (this.direction == MoveDirection.LEFT) return;
            }
            case BOTTOM -> {
                if (this.direction == MoveDirection.UP) return;
            }
        }
        this.direction = direction;
    }

    /**
     * Setting new coordinates for the snake's head
     * @param x Horizontal position of head
     * @param y Vertical position of head
     */
    public void setSnakeHeadPosition(int x, int y){
        segmentPosition[0] = new int[]{Math.clamp(x, 0, Config.MAX_GRID_INDEX_X - 1), Math.clamp(y,0, Config.MAX_GRID_INDEX_Y - 1)};
    }

    /**
     * Getting coordinates for the snake's head
     * @return one-dimensional array with two coordinates: x and y
     */
    public int[] getHeadPosition() {
        return segmentPosition[0];
    }

    /**
     * Adding a new segment to the snake
     */
    public void addSegment() {
        int[][] temp = segmentPosition.clone();
        segmentPosition = new int[segmentPosition.length + 1][];
        System.arraycopy(temp, 0, segmentPosition, 0, temp.length);

        segmentPosition[segmentPosition.length - 1] = segmentPosition[segmentPosition.length - 2];
    }
}
