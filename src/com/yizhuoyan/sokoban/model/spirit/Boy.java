package com.yizhuoyan.sokoban.model.spirit;

import java.awt.image.BufferedImage;

import com.yizhuoyan.sokoban.model.Direction;
import com.yizhuoyan.sokoban.model.map.GameCell;
import com.yizhuoyan.sokoban.util.R;

public class Boy extends GameCell implements Direction {
    private static final BufferedImage IMG_BOY_UP       = R.getImage("boy-up.gif");
    private static final BufferedImage IMG_BOY_DOWN     = R.getImage("boy-down.gif");
    private static final BufferedImage IMG_BOY_LEFT     = R.getImage("boy-left.gif");
    private static final BufferedImage IMG_BOY_RIGHT    = R.getImage("boy-right.gif");

    public int                        currentDirection = DOWN;

    
    public Boy(int x, int y) {
        super(x, y);
    }

    public void moveUp() {
        this.y--;
        this.currentDirection=UP;
    }
    public void reset(int x,int y,int direction) {
        this.x=x;
        this.y=y;
        this.currentDirection=direction;
    }
    public void moveDown() {
        this.y++;
        this.currentDirection=DOWN;
    }

    public void moveLeft() {
        this.x--;
        this.currentDirection=LEFT;
    }

    public void moveRight() {
        this.x++;
        this.currentDirection=RIGHT;
    }

    @Override
    public BufferedImage getCellImage() {
        switch (currentDirection) {
        case UP:
            return IMG_BOY_UP;
        case RIGHT:
            return IMG_BOY_RIGHT;
        case DOWN:
            return IMG_BOY_DOWN;
        case LEFT:
            return IMG_BOY_LEFT;
        }
        return null;
    }

}
