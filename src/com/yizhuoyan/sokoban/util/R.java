package com.yizhuoyan.sokoban.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class R {
    private static final Class TYPE=R.class;
    
    static final private Map<String,BufferedImage> IMAGES_MAP=new HashMap<>(16);
    
    
    public static void loadAsset() {
        try {
        laodImages();
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static void laodImages() throws Exception{
        URL url=TYPE.getResource("/asset/img");
        File dir=new File(url.toURI());
        File[] listFiles = dir.listFiles();
        if(listFiles!=null) {
            for (File file : listFiles) {
                BufferedImage b=ImageIO.read(file);
                IMAGES_MAP.put(file.getName(), b);
            }
        }
    }
    public static void main(String[] args)throws Exception {
        System.out.println(getImage("blank.gif"));
    }
    public static InputStream getResouceAsStream(String path) {
        
        return TYPE.getResourceAsStream(path);
    }
    
    public static BufferedImage getImage(String name) {
        return IMAGES_MAP.get(name);
    }
}
