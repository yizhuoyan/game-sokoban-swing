package com.yizhuoyan.sokoban.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import com.yizhuoyan.sokoban.model.Direction;
import com.yizhuoyan.sokoban.model.Game;
import com.yizhuoyan.sokoban.model.GameLevel;
import com.yizhuoyan.sokoban.model.map.GameCell;

public class GameCanvas extends JComponent {
    private final Game game;
    private static Font f=new Font("宋体", Font.BOLD, 20);
    private static FontRenderContext renderContext=new FontRenderContext(f.getTransform(), false, false);

    public GameCanvas(final Game game) {
        this.game = game;
    }
    @Override
    public void paint(Graphics g) {
        game.getCurrentLevel().paint(g);
        g.setColor(Color.RED);
        g.setFont(f);
        //计算文字高宽
        String title="第"+game.getCurrentLevel().getLevel()+"关";
        Rectangle2D stringBounds = f.getStringBounds(title,renderContext);
        g.drawString(title, (int)((this.getWidth()-stringBounds.getWidth())/2), GameCell.width);
    }

}
