package com.yizhuoyan.sokoban.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.yizhuoyan.sokoban.model.map.GameMap;
import com.yizhuoyan.sokoban.model.map.GameMapCell;
import com.yizhuoyan.sokoban.model.spirit.Box;
import com.yizhuoyan.sokoban.model.spirit.Boy;
import com.yizhuoyan.sokoban.util.R;

public class GameLevel implements Direction {
    private static final String LEVEL_MAP_PATH = "/asset/maps/%d.map";
    private static final int    BOY            = 5, BOX = 3;
    private int                 level;
    private Box[]               boxes;
    private Boy                 boy;
    private GameMap             map;
    final private Game game;
    private LinkedList<GameMove> moveStack=new LinkedList<>();

    public GameLevel(Game game,int level) {
        this.game=game;
        this.setLevel(level);
        //放入一个初始位置
        moveStack.add(null);
    }
    
    public void reload() {
        this.setLevel(this.level);
    }

    public int getLevel() {
        return level;
    }

    public void handleKeyPress(int keyCode) {
        switch (keyCode) {
        case UP:
            handleUp();
            break;
        case RIGHT:
            handleRight();
            break;
        case DOWN:
            handleDown();
            break;
        case LEFT:
            handleLeft();
            break;
            
        }
        
        //判断是否有箱子在目标位置上
        int totalBox=boxes.length;
        for(Box box:boxes) {
            GameMapCell homeCell=map.get(box.x, box.y);
            if(homeCell.type==GameMapCell.TYPE_HOME) {
                box.setWin();
                totalBox--;
            }
        }
        
        //判断是否胜利
        if(totalBox==0) {
            game.passCurrentLevel();
        }
        
    }

    private Box getBoxAt(int x, int y) {
        for (Box box : boxes) {
            if (box.x == x && box.y == y) {
                return box;
            }
        }
        return null;
    }

    public void previousMove() {
        if(moveStack.isEmpty())return;
        GameMove previous=moveStack.pollLast();
        if(previous!=null) {
            boy.reset(previous.boyX,previous.boyY,previous.boyDirection);
            if(previous.moveBox!=null) {
                Box b=previous.moveBox;
                b.moveTo(previous.boxX, previous.boxY);
                GameMapCell homeCell=map.get(b.x, b.y);
                if(homeCell.type==GameMapCell.TYPE_HOME) {
                    b.setWin();
                }
            }
        }else {
            //重新加载
            this.setLevel(this.level);
        }
    }
    private boolean canBoxMoveIn(int x, int y) {
        // 获取类型
        GameMapCell cell = map.get(x, y);
        if (cell != null) {
            switch (cell.type) {
            case GameMapCell.TYPE_GRASS:
            case GameMapCell.TYPE_HOME:
                //看是否有箱子
                return getBoxAt(x, y)==null;
            }
        }
        return false;
    }

    private void handleUp() {
        int bx = boy.x, by = boy.y;
        // 获取boy前面类型
        GameMapCell cell = map.get(bx, by - 1);
        if (cell == null) {
            return;
        }
        switch (cell.type) {
        case GameMapCell.TYPE_BLOCK:
            // 障碍物，不能移动
            return;
        case GameMapCell.TYPE_GRASS:
        case GameMapCell.TYPE_HOME:
            // 草地和目标位置上是否有箱子
            Box box = getBoxAt(cell.x, cell.y);
            if (box == null) {
                // 没有，则boy移动
                boy.moveUp();
                moveStack.add(GameMove.of(boy));
            } else {
                // 有，判断盒子前面有什么
                if (canBoxMoveIn(box.x, box.y - 1)) {
                    box.moveUp();
                    boy.moveUp();
                    moveStack.add(GameMove.of(boy,box));
                }
            }
            break;
        }
    }
    private void handleRight() {
        int bx = boy.x, by = boy.y;
        // 获取boy前面类型
        GameMapCell cell = map.get(bx+1, by);
        if (cell == null) {
            return;
        }
        switch (cell.type) {
        case GameMapCell.TYPE_BLOCK:
            // 障碍物，不能移动
            return;
        case GameMapCell.TYPE_GRASS:
        case GameMapCell.TYPE_HOME:
            // 草地和目标位置上是否有箱子
            Box box = getBoxAt(cell.x, cell.y);
            if (box == null) {
                // 没有，则boy移动
                boy.moveRight();
                moveStack.add(GameMove.of(boy));
            } else {
                // 有，判断盒子前面有什么
                if (canBoxMoveIn(box.x+1, box.y)) {
                    box.moveRight();
                    boy.moveRight();
                    moveStack.add(GameMove.of(boy,box));
                }
            }
            break;
        }
    }
    private void handleDown() {
        int bx = boy.x, by = boy.y;
        // 获取boy前面类型
        GameMapCell cell = map.get(bx, by+1);
        if (cell == null) {
            return;
        }
        switch (cell.type) {
        case GameMapCell.TYPE_BLOCK:
            // 障碍物，不能移动
            return;
        case GameMapCell.TYPE_GRASS:
        case GameMapCell.TYPE_HOME:
            // 草地和目标位置上是否有箱子
            Box box = getBoxAt(cell.x, cell.y);
            if (box == null) {
                // 没有，则boy移动
                boy.moveDown();
                moveStack.add(GameMove.of(boy));
            } else {
                // 有，判断盒子前面有什么
                if (canBoxMoveIn(box.x, box.y+1)) {
                    box.moveDown();
                    boy.moveDown();
                    moveStack.add(GameMove.of(boy,box));
                }
            }
            break;
        }
    }
    private void handleLeft() {
        int bx = boy.x, by = boy.y;
        // 获取boy前面类型
        GameMapCell cell = map.get(bx-1, by);
        if (cell == null) {
            return;
        }
        switch (cell.type) {
        case GameMapCell.TYPE_BLOCK:
            // 障碍物，不能移动
            return;
        case GameMapCell.TYPE_GRASS:
        case GameMapCell.TYPE_HOME:
            // 草地和目标位置上是否有箱子
            Box box = getBoxAt(cell.x, cell.y);
            if (box == null) {
                // 没有，则boy移动
                boy.moveLeft();
                moveStack.add(GameMove.of(boy));
            } else {
                // 有，判断盒子前面有什么
                if (canBoxMoveIn(box.x-1, box.y)) {
                    box.moveLeft();
                    boy.moveLeft();
                    moveStack.add(GameMove.of(boy,box));
                }
            }
            break;
        }
    }
    
    private void setLevel(int level) {
        this.level = level;
        try {
            this.loadLevelData(level);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadLevelData(int level) throws IOException {
        GameMap map = this.map = new GameMap();
        List<Box> boxes = new ArrayList<>();
        try (InputStream is = R.getResouceAsStream(String.format(LEVEL_MAP_PATH, level));
                BufferedReader br = new BufferedReader(new InputStreamReader(is));) {
            String line = null;
            int y = 0;
            int c;
            while ((line = br.readLine()) != null) {
                for (int x = line.length(); x-- > 0;) {
                    c = line.charAt(x) - '0';
                    if (c == BOY) {
                        boy = new Boy(x, y);
                        c = GameMapCell.TYPE_GRASS;
                    } else if (c == BOX) {
                        boxes.add(new Box(x, y));
                        c = GameMapCell.TYPE_GRASS;
                    }

                    GameMapCell cell = new GameMapCell(x, y, c);
                    map.add(cell);
                }

                y++;
            }
        }
        this.boxes = boxes.toArray(new Box[boxes.size()]);
    }

    public Box[] getBox() {
        return boxes;
    }

    public void setBox(Box[] boxes) {
        this.boxes = boxes;
    }

    public Boy getBoy() {
        return boy;
    }

    public void setBoy(Boy boy) {
        this.boy = boy;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
    
    
    public void paint(Graphics g) {
        this.map.paint(g);
        this.boy.paint(g);
        for (Box box : boxes) {
            box.paint(g);
        }
    }
}
