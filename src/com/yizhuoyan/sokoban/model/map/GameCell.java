package com.yizhuoyan.sokoban.model.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.yizhuoyan.sokoban.model.spirit.Boy;

public abstract class GameCell {
    static public final int width=30;
    public int x;
    public int y;
    public GameCell(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public final void paint(Graphics g) {
        g.drawImage(this.getCellImage(), x*width, y*width, width,width,null);
    }
    
    public abstract BufferedImage getCellImage();
}
