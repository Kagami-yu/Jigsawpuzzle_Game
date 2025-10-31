package com.sziit.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
//添加更换验证码的操作
public class LoadingJFrame extends JFrame implements ActionListener, MouseListener {
    static ArrayList<User> users = new ArrayList<>();
    static{
        users.add(new User("zhang","123456"));
    }

    String saveTestNum;//用来保存和显示验证码
    StringBuilder show = new StringBuilder();
    JLabel showTestNum=new JLabel();//为下文展示和生成验证码的方法提供对象
    JButton showPwd=new JButton();
    JPasswordField inputPassword=new JPasswordField();
    //空参构造
    public LoadingJFrame() {
        intiLoadFrame();
        normalFrame();
        refreshTestNum();//调用方法在界面展示出来
        this.setVisible(true);
    }

    //设置框架属性
    private void intiLoadFrame() {
        this.setSize(488, 430);//设置界面宽和高
        this.setTitle("登录");//标题
        this.setAlwaysOnTop(true);//不被覆盖
        this.setLocationRelativeTo(null);//取消初始化界面位于左上角，使其居中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭，结束进程键
        this.setLayout(null);
    }

    //界面基础内容
    private void normalFrame() {
        JLabel gameTitle=new JLabel("拼图游戏");
        JLabel userName=new JLabel("账户：");
        JLabel toTestNum=new JLabel("验证码");
        JLabel password=new JLabel("密码：");
        JButton loading=new JButton("登录");
        JButton register=new JButton("注册");

        loading.setBounds(122,270,100,50);
        register.setBounds(244,270,100,50);
        showPwd.setBounds(340,100,30,30);

        showTestNum.setBounds(230,150,100,50);
        toTestNum.setBounds(30,160,60,30);
        userName.setBounds(30, 60, 100, 30);
        password.setBounds(30, 100, 100, 30);
        gameTitle.setBounds(0, 0, 200, 30);;

        this.getContentPane().add(userName);
        this.getContentPane().add(password);
        this.getContentPane().add(gameTitle);
        this.getContentPane().add(loading);
        this.getContentPane().add(register);
        this.getContentPane().add(showPwd);//展示密码
        this.getContentPane().add(toTestNum);
        this.getContentPane().add(showTestNum);
        this.getContentPane().add(inputPassword);
        //输入框
        JTextField inputUserName=new JTextField();
//        JPasswordField inputPassword=new JPasswordField();
        JTextField inputTestNum=new JTextField();

        inputTestNum.setBounds(130,160,100,30);
        inputUserName.setBounds(130,60,200,30);
        inputPassword.setBounds(130, 100, 200, 30);
        this.getContentPane().add(inputUserName);
        this.getContentPane().add(inputPassword);
        this.getContentPane().add(inputTestNum);

        showPwd.addMouseListener(this);

        loading.addActionListener(new ActionListener() {
            //inputPassword.getPassword()返回值为char[]类型
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputUserName.getText().equals("")||inputPassword.getPassword().length==0){
                    remind();
                }else{
                    if(inputUserName.getText().equals(users.get(0).getName())&&
                            checkPassword(inputPassword.getPassword())){
                        if(inputTestNum.getText().equalsIgnoreCase(saveTestNum)){
                            LoadingJFrame.this.setVisible(false);
                            new GameJFrame();
                        }else {
                            randCode();
                            refreshTestNum();
                            testNumRed();
                        }
                    }else{
                        remind();
                    }
                }
            }
        });
        register.addMouseListener(this);
    }

    //提示弹窗
    private void remind(){
        JDialog wrongDialog=new JDialog();
        wrongDialog.setSize(200,150);//给弹窗设置大小
        wrongDialog.setLocationRelativeTo(null);//取消默认设置，居中
        wrongDialog.setAlwaysOnTop(true);//置顶
        wrongDialog.setModal(true);//弹窗不关闭永远操作下面的界面
        //创建对象存储提示文本
        JLabel contents=new JLabel("用户名或者密码错误!");
        contents.setBounds(0, 0, 200, 150);//设置弹窗大小
        wrongDialog.getContentPane().add(contents);//添加到界面当中
        wrongDialog.setVisible(true);//让弹窗可视化
    }

    //验证码的提示弹窗
    private void testNumRed(){
        JDialog wrongDialog=new JDialog();
        wrongDialog.setSize(200,150);//给弹窗设置大小
        wrongDialog.setLocationRelativeTo(null);//取消默认设置，居中
        wrongDialog.setAlwaysOnTop(true);//置顶
        wrongDialog.setModal(true);//弹窗不关闭永远操作下面的界面
        //创建对象存储提示文本
        JLabel contents=new JLabel("验证码错误");
        contents.setBounds(0, 0, 200, 150);//设置弹窗大小
        wrongDialog.getContentPane().add(contents);//添加到界面当中
        wrongDialog.setVisible(true);//让弹窗可视化
    }

    //随机验证码
    private String randCode(){
        StringBuilder code=new StringBuilder();
        Random random=new Random();
        int randNum=random.nextInt(10);
        char[] chars=new char[52];
        for (int i = 0; i < chars.length; i++) {
            if(i<26) {
                chars[i] = (char) (65 + i);
            }else{
                chars[i] = (char) (97 + i-26);
            }
        }
        for (int i = 0; i < 5; i++) {
            int randIndex=random.nextInt(52);
            if(i<4) {
                code.append(chars[randIndex]);
            }else{
                code.append(randNum);
            }
        }
        return code.toString();
    }

    //生成和展示新验证码
    private void refreshTestNum(){
        saveTestNum=randCode();//记录上一个方法的随机数
        showTestNum.setText(saveTestNum);//在页面展示
    }

    //检验密码是否正确--JPasswordField返回char[]类型，填入数据时得inputPassword.getPassword()
    private boolean checkPassword(char[] inputPassword){
        for (int i = 0; i < users.size(); i++) {
            char[] passChar=users.get(i).getPassword().toCharArray();
            //Arrays.equals()是一个比较两个同类型数组的方法，长度和元素都相同就返回true，否则false
            if(Arrays.equals(passChar,inputPassword)){
                Arrays.fill(passChar,'0');//擦除临时副本，保证数据安全
                return true;
            }
            Arrays.fill(passChar,'0');//没有匹配也擦除副本，防止堆积内存
        }
        return false;//全部遍历都没有找到相同的直接返回false
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
        if(source==showPwd) {
            inputPassword.setEchoChar((char) 0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();
        if (source == showPwd) {
            inputPassword.setEchoChar('●');
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
