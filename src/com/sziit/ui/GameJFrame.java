package com.sziit.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.util.Random;

//游戏界面
public class GameJFrame extends JFrame {
    int[][] Arr=new int[4][4];
    public GameJFrame() {
        //初始化菜单
        intiMenu();
        //打乱照片
        mixPhoto();
        //初始化照片
        intiPhoto();
        //背景图片，先放置的图片显示在最上面，所以背景图片慢添加
        bgphoto();
        //初始化界面
        intiFrame();
        //展示界面
        this.setVisible(true);//显示界面，等所有初始化实现后再展示，写最后
    }
    //背景图片
    private void bgphoto() {
        JLabel bgJLabel=new JLabel(new ImageIcon("src/resources/ph/bg-totle/bg1-star.png"));
        bgJLabel.setBounds(51, 15, 600, 750);
        this.getContentPane().add(bgJLabel);
    }

    //初始化照片
    private void intiPhoto() {
        //先创图片对象-->创储存的容器JLabel-->把容器添加到界面中
        //打乱图片-->添加图片-->指定图片位置
//        JLabel jLabel = new JLabel(new ImageIcon());
        int num=1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //将图片添加到容器中
                JLabel jLabel = new JLabel(new ImageIcon("src/resources/ph/shenyuan1/images/shenyuan_0"+num+".png"));
                num++;
                //指定图片位置
                jLabel.setBounds(147*j+57,147*i+171,147,147);
                jLabel.setBorder(new BevelBorder(1));
                //将储存图片的容器加载到界面
                this.getContentPane().add(jLabel);
            }
        }
    }

    //初始化导航栏菜单
    private void intiMenu() {
        //创建菜单JMenuBar-->JMenu-->JMenuItem
        JMenuBar menuBar = new JMenuBar();
        JMenu navMenu1 = new JMenu("功能");
        JMenu navAboutUs = new JMenu("关于我们");
        //将JMenu添加到JMenuBar
        menuBar.add(navMenu1);
        menuBar.add(navAboutUs);

        JMenuItem replyItem = new JMenuItem("重新开始");
        JMenuItem reloadItem = new JMenuItem("重新登陆");
        JMenuItem exitItem = new JMenuItem("退出游戏");
        JMenuItem publicItem = new JMenuItem("公众号");
        //将条目添加到JMenu
        navMenu1.add(replyItem);
        navMenu1.add(reloadItem);
        navMenu1.add(exitItem);
        navAboutUs.add(publicItem);
        //将菜单栏添加到GameJFrame界面
        this.setJMenuBar(menuBar);
    }

    //初始化界面
    private void intiFrame() {
        this.setSize(703, 840);//设置界面宽和高
        this.setTitle("Jigsaw Puzzle v-1.0");//标题
        this.setAlwaysOnTop(true);//不被覆盖
        this.setLocationRelativeTo(null);//取消初始化界面位于左上角，使其居中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭，结束进程键
        this.setLayout(null);//取消图片的初始位置--居中
    }

    //打乱照片
    private void mixPhoto() {
        Random random = new Random();
        int temp;
        int[] arr={1,2,3,4,5,6,7,8,9,11,12,13,14,15,16};
        //打乱一维数组的顺序
        for (int i = 0; i < arr.length; i++) {
            int Index = random.nextInt(arr.length);
            temp = arr[i];
            arr[i] = arr[Index];
            arr[Index] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            Arr[i/4][i%4]=arr[i];
        }
    }
}
