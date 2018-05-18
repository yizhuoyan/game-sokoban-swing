package com.yizhuoyan.sokoban.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import com.yizhuoyan.sokoban.model.Direction;
import com.yizhuoyan.sokoban.model.Game;

public class GameWindow extends JFrame {
    public final GameCanvas canvas;
    public final GameMenuBar   menuBar;
    public final Game       game;

    public GameWindow(Game game) {
        this.game = game;
        menuBar = new GameMenuBar(game);
        canvas = new GameCanvas(game);
        this.init();
    }

    private void init() {
        this.setTitle("推箱子");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(menuBar);
        this.add(canvas);
        this.addKeyListener();
    }

    private void addKeyListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                GameWindow win = GameWindow.this;
                switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    win.handleDirectionPressed(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    win.handleDirectionPressed(Direction.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    win.handleDirectionPressed(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    win.handleDirectionPressed(Direction.RIGHT);
                    break;
                }

            }
        });
    }

    private void handleDirectionPressed(int direction) {
        game.getCurrentLevel().handleKeyPress(direction);
        this.canvas.repaint();
    }

    
}
