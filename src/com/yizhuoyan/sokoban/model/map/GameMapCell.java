package com.yizhuoyan.sokoban.model.map;

import java.awt.image.BufferedImage;

import com.yizhuoyan.sokoban.util.R;

public class GameMapCell extends GameCell {
    public static final int TYPE_BLANK=0,TYPE_BLOCK=1,TYPE_GRASS=2,TYPE_HOME=4;
    private static final BufferedImage[] maps=new BufferedImage[5]; 
    static {
        maps[TYPE_BLANK]=R.getImage("blank.gif");
        maps[TYPE_BLOCK]=R.getImage("block.gif");
        maps[TYPE_GRASS]=R.getImage("grass.gif");
        maps[TYPE_HOME]=R.getImage("home.gif");
    }
    public final int type;
    
    public GameMapCell(int x, int y,int type) {
        super(x,y);
        this.type = type;
    }
    @Override
    public BufferedImage getCellImage() {
        return maps[this.type];
    }
}
