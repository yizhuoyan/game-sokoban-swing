package com.yizhuoyan.sokoban.model;

import com.yizhuoyan.sokoban.model.spirit.Box;
import com.yizhuoyan.sokoban.model.spirit.Boy;

public class GameMove {
    final public int boyX,boyY;
    final public int boyDirection;
    final public Box moveBox;
    int boxX,boxY;
    private GameMove(Boy boy, Box moveBox) {
        super();
        this.boyX = boy.x;
        this.boyY = boy.y;
        this.boyDirection=boy.currentDirection;
        this.moveBox=moveBox;
        if(moveBox!=null) {
            this.boxX = moveBox.x;
            this.boxY = moveBox.y;
        }
    }
    public static GameMove of(Boy boy) {
        return new GameMove(boy,null);
    }
    public static GameMove of(Boy boy,Box box) {
        return new GameMove(boy,box);
    }
    @Override
    public String toString() {
        return "GameMove [boyX=" + boyX + ", boyY=" + boyY + ", boxX=" + boxX + ", boxY=" + boxY + "]";
    }
    
}
