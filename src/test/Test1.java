package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test1 extends JFrame {
    public Test1() {
        intiFrame();
//        testButton();
    }

    private void intiFrame() {
        this.setSize(703, 680);//设置界面宽和高
        this.setTitle("Jigsaw Puzzle v-1.0");//标题
        this.setAlwaysOnTop(true);//不被覆盖
        this.setLocationRelativeTo(null);//取消初始化界面位于左上角，使其居中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭，结束进程键
        this.setVisible(true);//显示界面，等所有初始化实现后再展示，写最后
        this.setLayout(null);//取消图片的初始位置--居中
    }

//    private void testButton(){
//        JButton jbt = new JButton("Test");
//        jbt.setBounds(0,0,100,100);
//        jbt.addActionListener(this);
//        this.add(jbt);
//    }

    //重写按钮方法
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        System.out.println("看我执行了吗");
//    }
}
