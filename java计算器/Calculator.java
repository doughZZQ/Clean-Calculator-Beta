/**
 *  Description: 基于 AWT 实现的计算器
 *
 *  @author: zzq
 *  @Date: 2022 -12 -07
 *  @Time: 14:17
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

public class Calculator
{

    private JFrame frame;

    private ImageIcon icon;

    private JTextField textField;

    private JButton[] button;

    private JPanel panel;

    private JLabel label;

    /**
     * data：当前输入的数据
     */
    private String data = "";

    /**
     * isLeftAvailable：判断数据应该向哪一个操作数中存储
     */
    private boolean isLeftAvailable;

    /**
     * left, right：左右操作数
     */
    private double left, right;

    private String prevOperaotor = "";


    /**
     * init：初始化 frame
     */
    public void init()
    {
        setMyFrame();
        setMyTextField();
        setMyButton();
        //setMyLabel();
        display();
    }

    /**
     *  setMyFrame：
     *  @description: 设置窗体
     */
    private void setMyFrame()
    {
        frame = new JFrame();

        // 设置 frame 的坐标
        frame.setLocation(700, 150);
        // 设置 frame 的大小
        frame.setSize(450, 540);
        // 设置 frame 的标题
        frame.setTitle("Shepard's Calculator");
        // 禁用窗口大小调整
        frame.setResizable(false);
        //设置布局，自定义
        frame.setLayout(null);
        // 关闭窗体
    }

    /**
     *  setMyTextField：
     *  @description: 设置文本域
     */
    private void setMyTextField() //文本框
    {
        textField = new JTextField("0");

        // 设置文本框大小位置
        textField.setBounds(20,15,400,60);
        // 设置文本框字体
        textField.setFont(new Font("黑体", Font.BOLD, 35));
        // 设置背景颜色
        textField.setBackground(new Color(	230, 230, 250));

        frame.add(textField);
    }

    /**
     *  setMyButton：
     *  @description: 设置按键事件
     */
    private void setMyButton()
    {
        // 按钮文本
        String[] arr =
                          { "del","cls","%","/",
                            "7","8","9","*",
                            "4","5","6","+",
                            "1","2","3","-",
                            "+/-","0",".","=", };
        // 按钮
        button = new JButton[arr.length];

        // 创建面板（画布）
        panel = new JPanel();

        // 设置面板的布局方式
        panel.setBounds(20, 90, 400, 350);

        // 网格布局
        panel.setLayout(new GridLayout(5, 4, 8, 8));

        for(int i = 0; i < button.length; i++)
        {
            // 创建按钮
            button[i] = new JButton(arr[i]);

            // 设置按钮字体
            button[i].setFont(new Font("黑体", Font.CENTER_BASELINE, 20));
            // 设置按钮背景颜色
            button[i].setBackground(new Color(242, 240, 235));

            // 设置 = 号的特殊颜色
            if(button.length - 1 == i)
            {
                button[i].setBackground(new Color(211, 120, 129));
            }

            // 添加事件
            int idx = i;
            // 设置鼠标监听
            button[i].addMouseListener(new MouseAdapter() {
                // 点击事件
                @Override
                public void mouseClicked(MouseEvent e) {
                    // 获取按钮上的内容
                    click(button[idx].getText());
                }

                // 鼠标进入组件事件
                @Override
                public void mouseEntered(MouseEvent e) {
                    button[idx].setFont(new Font("黑体", Font.CENTER_BASELINE, 35));
                    button[idx].setBackground(new Color(240, 255, 255));
                    button[idx].setForeground(new Color(255, 99, 71));
                }

                // 鼠标离开组件事件
                @Override
                public void mouseExited(MouseEvent e) {
                    button[idx].setFont(new Font("黑体", Font.CENTER_BASELINE, 20));
                    button[idx].setBackground(new Color(242, 240, 235));
                    button[idx].setForeground(new Color(0, 0, 0));
                }
            });

            // 按钮添加到面板
            panel.add(button[i]);
        }

        frame.add(panel);
    }

    /**
     *  setMyLabel：
     *  @description: 设置标签
     */
//    private void setMyLabel()
//    {
//        // 标签
//        label = new JLabel();
//        label.setText("<html><span style='font-size:14px;color:red;font-family:宋体'></span></html>");
//        label.setBounds(40, 460, 300, 40);
//
//        frame.add(label);
//    }

    public void click(String content)
    {
        String operator = "";

        if("1".equals(content)) {
            data += "1";
            textField.setText(data);
        }else if("2".equals(content)){
            data += "2";
            textField.setText(data);
        }else if("3".equals(content)){
            data += "3";
            textField.setText(data);
        }else if("4".equals(content)){
            data += "4";
            textField.setText(data);
        }else if("5".equals(content)){
            data += "5";
            textField.setText(data);
        }else if("6".equals(content)){
            data += "6";
            textField.setText(data);
        }else if("7".equals(content)){
            data += "7";
            textField.setText(data);
        }else if("8".equals(content)){
            data += "8";
            textField.setText(data);
        }else if("9".equals(content)){
            data += "9";
            textField.setText(data);
        }else if("0".equals(content)){
            data += "0";
            textField.setText(data);
        }else if(".".equals(content)){
            data += ".";
            textField.setText(data);
        }else if("+/-".equals(content)){
            // 找不到负号，代表这是正数
            if(data.indexOf('-') < 0)
            {
                data = "-" + data;
            }
            // 负数
            else
            {
                data = data.substring(1);
            }
            textField.setText(data);
        }else if("%".equals(content)){
            data = Double.parseDouble(data) / 100 + "";
            textField.setText(data);
        }else if("+".equals(content)){
            operator = "+";
            cal(operator);
        }else if("-".equals(content)){
            operator = "-";
            cal(operator);
        }else if("*".equals(content)){
            operator = "*";
            cal(operator);
        }else if("/".equals(content)){
            operator = "/";
            cal(operator);
        }else if("=".equals(content)){
            operator = "=";
            cal(operator);
        }else if("del".equals(content)){
            if(data.length() != 0)
            {
                data = data.substring(0, data.length() - 1);
            }
            textField.setText(data);
        }else if("cls".equals(content)){
            data = "";
            isLeftAvailable = false;
            textField.setText(data);
        }

    }

    /**
     * @method: display
     * @description: 设置 frame
     * @note: 如果把 display 函数内的语句写在 setMyFrame 方法中，frame 中显示不出来 label
     *        且需要点击一下 文本域 后才能显示文本域
     */
    public void display()
    {
        // 设置关闭 frame 窗口时退出程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置 frame 可见，默认值为 false
        frame.setVisible(true);
    }

    private void cal(String operator)
    {
        // 如果 data 为空，不执行任何操作
        if("".equals(data))
        {
            // 用户只要输入 = ，无论前面的输入是否合法，结束此轮计算
            if("=".equals(operator))
            {
                isLeftAvailable = false;
            }
            return;
        }

        /**
         * 第一次把 data 中的数据赋给 left
         * 此后，每次运算都将 right 与 left 运算的结果放入 left 中，并显示 left
         * 运算符 = 使用后可以重新向 left 中输入值
         * 每次获取到 data 中的值后清空 data
         *
         * 每次运算使用上一次传入的运算符，等号出现重新开始新的计算
         */

        if(isLeftAvailable)
        {
            right = Double.parseDouble(data);
            data = "";

            // 使用前一个运算符
            if("+".equals(prevOperaotor))
            {
                left += right;
            }
            else if("-".equals(prevOperaotor))
            {
                left -= right;
            }
            else if("*".equals(prevOperaotor))
            {
                left *= right;
            }
            else if("/".equals(prevOperaotor))
            {
                left /= right;
            }

            // 如果运算结果为整数，就用整数输出
            if(left == (int)left)
            {
                left = (int)left;
            }

        }
        else
        {
            left = Double.parseDouble(data);
            // 清空 data
            data = "";
            isLeftAvailable = true;
        }

        // 将运算结果转换为字符串
        String result = left + "";

        // "=" 操作符清理右操作数，重新开始计算
        if("=".equals(operator))
        {
            isLeftAvailable = false;
        }
        // 更新运算符
        prevOperaotor = operator;
        textField.setText(textFormat(result) + " " +operator);
    }

    private String textFormat(String s)
    {
        //创建一个格式化数字的对象
        NumberFormat nf = NumberFormat.getInstance();
        //        //格式化
        String fomatResult = nf.format(Double.parseDouble(s));
        return fomatResult;
    }

}
