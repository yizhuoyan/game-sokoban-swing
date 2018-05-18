package com.yizhuoyan.sokoban.model.map;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yizhuoyan.sokoban.util.R;

public class GameMap {
    private static final int     rows = 20;
    private static final int     cols = 20;

    public final GameMapCell[][] data = new GameMapCell[cols][rows];

    final public int             width;
    final public int             height;

    public GameMap() {
        width = rows * GameMapCell.width;
        height = cols * GameMapCell.width;
    }

    public void add(GameMapCell cell) {
        data[cell.y][cell.x] = cell;
    }

    public GameMapCell get(int x, int y) {
        if (0 < x && x < rows && 0 < y && y < cols) {
            return data[y][x];
        }
        return null;
    }
    
    public void paint(Graphics g) {
        GameMapCell[][] data = this.data;
        GameMapCell cell;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                cell = data[i][j];
                cell.paint(g);
            }
        }
    }
}
