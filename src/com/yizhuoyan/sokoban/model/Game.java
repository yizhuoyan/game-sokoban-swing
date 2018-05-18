package com.yizhuoyan.sokoban.model;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.yizhuoyan.sokoban.util.R;
import com.yizhuoyan.sokoban.view.GameWindow;

public class Game {
    private final GameWindow win;
    
    private GameLevel currentLevel;
    
    private JDialog dialog;
    
    public Game() {
        //加载资源
        R.loadAsset();
        //加载游戏关卡
        this.currentLevel=new GameLevel(this,1);
        //设置游戏窗口
        win=new GameWindow(this);
        resizeWindow();
    }
    
    
    public GameLevel getCurrentLevel() {
        return currentLevel;
    }
    /**
     * 悔一步
     */
    public void previousMove() {
        //删除当前
        this.getCurrentLevel().previousMove();
        this.win.repaint();
    }
    public void reloadLevel() {
        this.currentLevel.reload();
        this.win.repaint();
    }
    public void showAbout() {
        StringBuilder message=new StringBuilder();
        message.append("-------推箱子-------\n");
        message.append("@copyright 2010-2018 yizhuoyan@gmail.com");
        JOptionPane.showMessageDialog(win, message);
    }
    public void previousLevel() {
        GameLevel current=this.currentLevel;
        if(current.getLevel()>1) {
            this.currentLevel=new GameLevel(this,current.getLevel()-1);
            resizeWindow();
        }
    }
    public void selectLevel() {
        String levelString=JOptionPane.showInputDialog("请输入关卡1-50");
        try {
            int level=Integer.parseInt(levelString);
            this.currentLevel=new GameLevel(this, level);
            resizeWindow();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(win, "请输入1-50");
            selectLevel();
        }
    }
    public void passCurrentLevel() {
        this.win.repaint();
        //弹窗
        JOptionPane.showMessageDialog(this.win,String.format("恭喜通过第%d关，进入下一关",currentLevel.getLevel()));
        this.nextLevel();
    }
    public void nextLevel() {
        GameLevel current=this.currentLevel;
        this.currentLevel=new GameLevel(this,current.getLevel()+1);
        resizeWindow();
    }
    private void resizeWindow() {
        win.setSize(this.currentLevel.getMap().width, this.currentLevel.getMap().height);
        this.win.repaint();
    }
    
    public void start() {
        this.win.setLocationRelativeTo(null);
        this.win.setVisible(true);
        this.restart();
    }
    
    public void restart() {
        
    }
    
    
}
