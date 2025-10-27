package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class intest3 extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new intest3();
    }

    public intest3() {
        JFrame jFrame = new JFrame();
        jFrame.setSize(703, 680);//设置界面宽和高
        jFrame.setTitle("Jigsaw Puzzle v-1.0");//标题
        jFrame.setAlwaysOnTop(true);//不被覆盖
        jFrame.setLocationRelativeTo(null);//取消初始化界面位于左上角，使其居中
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭，结束进程键
        jFrame.setVisible(true);//显示界面，等所有初始化实现后再展示，写最后
        jFrame.setLayout(null);//取消图片的初始位置--居中

        JButton jtb1 = new JButton("第一个");
        JButton jtb2 = new JButton("第二个");
        jtb1.setBounds(0, 0, 100, 50);
        jtb2.setBounds(100, 0, 100, 50);
        jtb1.addActionListener(this);
        jtb2.addActionListener(this);
        jFrame.add(jtb1);
        jFrame.add(jtb2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("看看我还在不在");
    }
}
