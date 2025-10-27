package com.sziit.ui;

import javax.swing.*;

public class LoadingJFrame extends JFrame {
    //空参构造
    public LoadingJFrame(){}

    //设置框架属性
    private void intiLoadFrame(){
        this.setSize(488, 430);//设置界面宽和高
        this.setTitle("Jigsaw Puzzle v-1.0");//标题
        this.setAlwaysOnTop(true);//不被覆盖
        this.setLocationRelativeTo(null);//取消初始化界面位于左上角，使其居中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭，结束进程键
        this.setVisible(true);
    }
}
