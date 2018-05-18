package com.yizhuoyan.sokoban.view;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.yizhuoyan.sokoban.model.Game;

public class GameMenuBar extends JMenuBar implements ActionListener{

    private final Game game;
    public GameMenuBar(Game game) {
        this.game=game;
        final JMenu gameMenu = new JMenu("游戏");
        gameMenu.add(createJMenuItem("悔一步","previousMove"));
        gameMenu.add(createJMenuItem("重新开始","replayLevel"));
        gameMenu.addSeparator();
        gameMenu.add(createJMenuItem("退出","exit"));
        final JMenu levelMenu = new JMenu("关卡");
        levelMenu.add(createJMenuItem("上一关","previousLevel"));
        levelMenu.add(createJMenuItem("选择关卡","selectLevel"));
        final JMenu helpMenu = new JMenu("帮助");
        helpMenu.add(createJMenuItem("关于","about"));
        
        this.add(gameMenu);
        this.add(levelMenu);
        this.add(helpMenu);
    }
    
    private JMenuItem createJMenuItem(String text,String actionCommand) {
        JMenuItem item=new JMenuItem(text);
        item.setActionCommand(actionCommand);
        item.addActionListener(this);
        return item;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
        case "previousMove":
            game.previousMove();
            break;
        case  "replayLevel":
            game.reloadLevel();
            break;
        case "exit":
            System.exit(0);
            break;
        case "about":
            game.showAbout();
            break;
        case "previousLevel":
            game.previousLevel();
            break;
        case "selectLevel":
            game.selectLevel();
            break;
        } 
          
    }
}
