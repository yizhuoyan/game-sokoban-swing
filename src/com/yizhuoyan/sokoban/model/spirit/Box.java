package com.yizhuoyan.sokoban.model.spirit;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.yizhuoyan.sokoban.model.map.GameCell;
import com.yizhuoyan.sokoban.util.R;

public class Box extends GameCell implements Cloneable {
    private static final int           STATUS_WIN = 1, STATUS_NORMAL = 2;
    // 箱子状态
    private int                        status     = STATUS_NORMAL;
    final static private BufferedImage BOX_NOMAL  = R.getImage("box-normal.gif");
    final static private BufferedImage BOX_WIN    = R.getImage("box-win.gif");

    public Box(int x, int y) {
        super(x, y);
    }

    public void moveUp() {
        this.y--;
    }

    public void moveDown() {
        this.y++;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("Box.moveTo()");
    }

    public void moveLeft() {
        this.x--;
    }

    public void setNomal() {
        this.status = STATUS_NORMAL;
    }

    public void setWin() {
        this.status = STATUS_WIN;
    }

    public boolean isOnHome() {
        return this.status == STATUS_WIN;
    }

    public void moveRight() {
        this.x++;
    }

    @Override
    public Box clone() {
        try {
            return (Box) super.clone();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BufferedImage getCellImage() {
        if (status == STATUS_NORMAL) {
            return BOX_NOMAL;
        }
        return BOX_WIN;
    }

}
