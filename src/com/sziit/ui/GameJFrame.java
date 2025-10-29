package com.sziit.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//游戏界面
public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int step;

    int[][] Arr = new int[4][4];
    //胜利使的数组
    int[][] Win = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    //锁定空图片的坐标
    int x;
    int y;
    String path = "src/resources/ph/shenyuan1/images";

    //菜单条目
    JMenuItem replyItem = new JMenuItem("重新开始");
    JMenuItem reloadItem = new JMenuItem("重新登陆");
    JMenuItem exitItem = new JMenuItem("退出游戏");
    JMenuItem publicItem = new JMenuItem("公众号");

    public GameJFrame() {
        //初始化菜单
        intiMenu();
        //打乱照片
        mixPhoto();
        //初始化照片
        intiPhoto();
        //初始化界面
        intiFrame();
        //展示界面
        this.setVisible(true);//显示界面，等所有初始化实现后再展示，写最后
    }

    //背景图片
    private void bgphoto() {
        JLabel bgJLabel = new JLabel(new ImageIcon("src/resources/ph/bg-totle/bg1-star.png"));
        bgJLabel.setBounds(51, 45, 600, 750);
        this.getContentPane().add(bgJLabel);
    }

    //初始化照片/文本
    private void intiPhoto() {
        //每次调用清空上一次的数据
        this.getContentPane().removeAll();
        if (result()) {
            victory();
        }
        showStep();
        //先创图片对象-->创储存的容器JLabel-->把容器添加到界面中
        //打乱图片-->添加图片-->指定图片位置
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //将图片添加到容器中
                JLabel jLabel = new JLabel(new ImageIcon(path + "/shenyuan_0" + Arr[i][j] + ".png"));
                //指定图片位置
                jLabel.setBounds(147 * j + 57, 147 * i + 201, 147, 147);
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //将储存图片的容器加载到界面
                this.getContentPane().add(jLabel);
            }
        }
        //添加背景，先放置的图片显示在最上面，所以背景图片慢添加
        bgphoto();
        //刷新界面
        this.getContentPane().repaint();
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

        //将条目添加到JMenu
        navMenu1.add(replyItem);
        navMenu1.add(reloadItem);
        navMenu1.add(exitItem);
        navAboutUs.add(publicItem);
        //将菜单栏添加到GameJFrame界面
        this.setJMenuBar(menuBar);
        //为条目添加动作监听
        replyItem.addActionListener(this);
        reloadItem.addActionListener(this);
        exitItem.addActionListener(this);
        publicItem.addActionListener(this);
    }

    //初始化界面
    private void intiFrame() {
        this.setSize(703, 890);//设置界面宽和高
        this.setTitle("Jigsaw Puzzle v-1.0");//标题
        this.setAlwaysOnTop(true);//不被覆盖
        this.setLocationRelativeTo(null);//取消初始化界面位于左上角，使其居中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭，结束进程键
        this.setLayout(null);//取消图片的初始位置--居中
        this.addKeyListener(this);//为整个界面添加界面的键盘监听
    }

    //打乱照片&添加空白快的位置
    private void mixPhoto() {
        Random random = new Random();
        int temp;
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //打乱一维数组的顺序
        for (int i = 0; i < arr.length; i++) {
            int Index = random.nextInt(arr.length);
            temp = arr[i];
            arr[i] = arr[Index];
            arr[Index] = temp;
        }
        //监听时没有存入二维数组前的0数值--也就是没有图片的块
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                x = i / 4;//记录空白快的列值
                y = i % 4;//记录空白快的行值
            }
            //加else会导致bug，空图片被上一次记录的0位置所在图片替代
            //解决方法，删去else，让每次0记录空白图片，不要继承重新开始前的图片
            Arr[i / 4][i % 4] = arr[i];
        }
    }

    //胜利展示的图片
    private void victory() {
        JLabel winJLabel = new JLabel(new ImageIcon("src/resources/ph/WIN.png"));
        winJLabel.setBounds(215, 432, 272, 126);
        this.getContentPane().add(winJLabel);
    }

    //判断是否胜利的方法
    private boolean result() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Arr[i][j] != Win[i][j]) {
                    return false;//false代表没有相同
                }
            }
        }
        return true;//true代表都相同
    }

    //记录步数的方法
    private void showStep() {
        JLabel stepJLabel = new JLabel("步 数 :" + step);
        stepJLabel.setBounds(50, 0, 200, 70);
        this.getContentPane().add(stepJLabel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //按下快捷键展示图片还有操作
    @Override
    public void keyPressed(KeyEvent e) {
        //如果已经胜利就不能再使用键盘监听
        if (result()) {
            return;
        }
        //监听键盘按键
        int code = e.getKeyCode();
        //移动前需要解除判断是否超出边界
        //37-左，38-上，39-右。40-下
        //往左移动
        if (code == 37) {
            //交换“坐标”
            if (y == 0) {
                return;
            }
            Arr[x][y] = Arr[x][y - 1];
            Arr[x][y - 1] = 0;
            //这里不是看JFrame的坐标，而是看二维数组对应的行列
            // ---空白块左移一次，列数减少一次
            y--;
            step++;//记录步数
            //按照新数据加载图片
            intiPhoto();
        } else if (code == 38) {
            //往上移
            if (x == 0) {
                return;
            }
            Arr[x][y] = Arr[x - 1][y];
            Arr[x - 1][y] = 0;
            x--;
            step++;//记录步数
            intiPhoto();
        } else if (code == 39) {
            //往右移动
            if (y == 3) {
                return;
            }
            Arr[x][y] = Arr[x][y + 1];
            Arr[x][y + 1] = 0;
            y++;
            step++;//记录步数
            intiPhoto();
        } else if (code == 40) {
            //往下移
            if (x == 3) {
                return;
            }
            Arr[x][y] = Arr[x + 1][y];
            Arr[x + 1][y] = 0;
            x++;
            step++;//记录步数
            intiPhoto();
        } else if (code == 88) {
            //先除去当前界面的照片
            //已有·界面只需更转图片即可，不用重新设置界面信息
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "/shenyuanAll.png"));
            all.setBounds(57, 201, 588, 588);
            this.getContentPane().add(all);
            bgphoto();
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 88) {
            intiPhoto();
        }
        if (e.getKeyCode() == 65) {
            Arr = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            intiPhoto();
        }
    }

    //动作监听(限定鼠标和空格)
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        //重新游戏
        if (obj == replyItem) {
            step = 0;
            mixPhoto();
            intiPhoto();
        }//退出游戏
        else if (obj == exitItem) {
            System.exit(0);
        }//公众号
        else if (obj == publicItem) {
            //区别与JFrame，JFrame是房子,JDialog要与房子绑定
            JDialog publicDialog = new JDialog();
            publicDialog.setBounds(57, 245, 400, 400);
            publicDialog.setLayout(null);
            this.getContentPane().add(publicDialog);
        }//重新登录
        else if (obj == reloadItem) {

        }
    }
}
