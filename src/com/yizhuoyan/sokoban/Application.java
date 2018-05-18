package com.yizhuoyan.sokoban;

import javax.swing.SwingUtilities;

import com.yizhuoyan.sokoban.model.Game;
import com.yizhuoyan.sokoban.view.GameWindow;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game g=new Game();
                g.start();
            }
        });
    }
}
