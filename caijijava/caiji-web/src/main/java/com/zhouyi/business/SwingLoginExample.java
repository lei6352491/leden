package com.zhouyi.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SwingLoginExample {

	public static void main(String[] args) throws Exception {    
        // 创建 JFrame 实例
        JFrame frame = new JFrame("Login Example");
        // Setting the width and height of frame
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(200, 200);

        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();    
        // 添加面板
        frame.add(panel);
        /* 
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);
        panel.setLocation(50, 20);
        panel.setAlignmentX(50);
        panel.setAlignmentY(100);

        // 设置界面可见
        frame.setVisible(true);
        
        File dir = new File("D:\\voicedictation\\检察院单机版\\pcm\\录音文件");
        File[] files = dir.listFiles();
        File latestFile = files[0];
        for(File file : files)
        {
            if(file.lastModified() > latestFile.lastModified())
            {
                latestFile = file;
            }
        }

        //System.out.println("latest file is " + latestFile);
        //System.out.println( latestFile.getAbsolutePath());
        Pcm2Wav.parse(latestFile.getAbsolutePath(), "d:/qqqqqq.wav");
        
    }

    private static void placeComponents(JPanel panel) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        

        // 创建登录按钮
        JButton loginButton = new JButton("播放录音");
        loginButton.setBounds(10, 80, 180, 25);
        panel.add(loginButton);
        
        loginButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            // display/center the jdialog when the button is pressed
        	  new PlayWav("D:\\qqqqqq.wav");
        	  return;
          }
        });
        
    }
    
}
