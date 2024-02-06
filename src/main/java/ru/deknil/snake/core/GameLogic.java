package ru.deknil.snake.core;

import ru.deknil.snake.Config;
import ru.deknil.snake.core.enums.GameState;

/**
 * @Author Deknil
 * @GitHub <a href=https://github.com/Deknil>https://github.com/Deknil</a>
 * @Date 06.02.2024
 * @Description Main game logic handler
 * <p></p>
 * Snake © 2024. All rights reserved.
 */
public class GameLogic {
    public static Snake snake; // Snake object
    public static Apple apple; // Apple object
    public static GameState gameState = GameState.MENU; // Game state

    public static int score = 0; // Player score


    /**
     * Game logic update
     */
    public static void update() {
        updateState();

        moveSnake();

        checkCollision();
    }

    /**
     * Game state update
     */
    private static void updateState() {
        if (gameState == GameState.PLAY) {
            if (snake == null) spawnSnake();
            if (apple == null) spawnApple();
        }
    }

    /**
     * Spawning a new snake
     */
    private static void spawnSnake() {
        int randPosX = (int) (Math.random() * Config.MAX_GRID_INDEX_X);
        int randPosY = (int) (Math.random() * Config.MAX_GRID_INDEX_X);

        snake = new Snake(randPosX, randPosY);
    }

    /**
     * Spawning a new apple
     */
    private static void spawnApple() {
        int randPosX = (int) (Math.random() * Config.MAX_GRID_INDEX_X);
        int randPosY = (int) (Math.random() * Config.MAX_GRID_INDEX_X);

        if (snake == null) return;

        while (randPosX == snake.getHeadPosition()[0] && randPosY == snake.getHeadPosition()[1]) {
            randPosX = (int) (Math.random() * Config.MAX_GRID_INDEX_X);
            randPosY = (int) (Math.random() * Config.MAX_GRID_INDEX_X);
        }

        apple = new Apple(randPosX, randPosY);
    }

    /**
     * Changing game state
     * @param state new state
     */
    public static void changeState(GameState state) {
        gameState = state;
        snake = null;
        apple = null;
    }

    /**
     * Snake Collision Checking
     */
    private static void checkCollision() {
        if (snake == null) return;

        int[] headPos = snake.getHeadPosition();

        // Apple Collision Test
        if (headPos[0] == apple.posX && headPos[1] == apple.posY) {
            score++;
            snake.addSegment();
            spawnApple();
        }

        // Checking collision with other segments
        for (int segmentIndex = snake.segmentPosition.length - 1; segmentIndex > 0; segmentIndex--) {
            int[] segmentPos = snake.segmentPosition[segmentIndex];

            if (headPos[0] == segmentPos[0] && headPos[1] == segmentPos[1]) {
                changeState(GameState.END);
                break;
            }
        }
    }

    /**
     * Logic of snake movement
     */
    private static void moveSnake() {
        if (snake == null) return;

        // Changing head position
        int[] headPos = snake.getHeadPosition();
        int[] nextHeadPos = new int[2];

        // Calculating new head coordinates
        switch (snake.direction) {
            case UP -> {
                nextHeadPos = new int[] {headPos[0], headPos[1] - 1};
                if (nextHeadPos[1] < 0) {
                    nextHeadPos[1] = Config.MAX_GRID_INDEX_Y;
                }
            }
            case RIGHT -> {
                nextHeadPos = new int[] {headPos[0] + 1, headPos[1]};
                if (nextHeadPos[0] > Config.MAX_GRID_INDEX_X - 1) {
                    nextHeadPos[0] = 0;
                }
            }
            case BOTTOM -> {
                nextHeadPos = new int[] {headPos[0], headPos[1] + 1};
                if (nextHeadPos[1] > Config.MAX_GRID_INDEX_Y - 1) {
                    nextHeadPos[1] = 0;
                }
            }
            case LEFT -> {
                nextHeadPos = new int[] {headPos[0] - 1, headPos[1]};
                if (nextHeadPos[0] < 0) {
                    nextHeadPos[0] = Config.MAX_GRID_INDEX_X;
                }
            }
        }

        // Movement of snake segments
        for (int segmentIndex = snake.segmentPosition.length - 1; segmentIndex > 0; segmentIndex--) {
            snake.segmentPosition[segmentIndex] = snake.segmentPosition[segmentIndex - 1];
        }

        // Setting new head coordinates
        snake.setSnakeHeadPosition(nextHeadPos[0], nextHeadPos[1]);
    }
}
