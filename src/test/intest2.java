package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class intest2 extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(703, 680);//设置界面宽和高
        frame.setTitle("Jigsaw Puzzle v-1.0");//标题
        frame.setAlwaysOnTop(true);//不被覆盖
        frame.setLocationRelativeTo(null);//取消初始化界面位于左上角，使其居中
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭，结束进程键
        frame.setVisible(true);//显示界面，等所有初始化实现后再展示，写最后
        frame.setLayout(null);//取消图片的初始位置--居中

        JButton button = new JButton("Start");
        button.setBounds(0, 0, 100, 100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("我还在不在");
            }
        });
        frame.add(button);
    }
}
