import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
/*
 * Created by JFormDesigner on Wed Apr 14 22:03:14 CST 2021
 */



/**
 * @author Brainrain
 */
public class ui extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ui frame = new ui();
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public ui() {
        initComponents();
    }

    // 查询所有学生信息
    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://rm-bp1oo27t8762xhlob0o.mysql.rds.aliyuncs.com:3306/sct_yutao?&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "lab_1250828523";
            String password = "86771663b371_#@Aa";
            //3.连接成功数据库对象 Connection代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select * from Student;";
            Object[][] allarray = new Object[100][6];
            int i = 0;  // 记录select出的行数
            try {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    allarray[i][0] = resultSet.getString("S#");
                    allarray[i][1] = resultSet.getString("SNAME");
                    allarray[i][2] = resultSet.getString("SEX");
                    allarray[i][3] = resultSet.getInt("AGE");
                    allarray[i][4] = resultSet.getString("DNAME");
                    allarray[i][5] = resultSet.getString("CLASS");
                    i++;
                }
                Object[][] resultarray = new Object[i][];
                for (int a = 0; a < i; a++){  //取出allarray的前i行作为jtable的输出
                    resultarray[a] = allarray[a];
                }
                String[] category = { "Sid", "SNAME", "SEX", "AGE", "DNAME", "CLASS"};
                table1 = new JTable(resultarray, category);
                scrollPane2.setViewportView(table1);
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException | SQLException ea) {
            ea.printStackTrace();
        }
    }

    // 查询成绩在80~90（>=80,<=90）分之间的学生的选课信息(学号、课程号和成绩)
    private void button2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://rm-bp1oo27t8762xhlob0o.mysql.rds.aliyuncs.com:3306/sct_yutao?&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "lab_1250828523";
            String password = "86771663b371_#@Aa";
            //3.连接成功数据库对象 Connection代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select * from SC where SCORE >= 80 and SCORE <= 90;";
            Object[][] allarray = new Object[100][3];
            int i = 0;  // 记录select出的行数
            try {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    allarray[i][0] = resultSet.getString("S#");
                    allarray[i][1] = resultSet.getString("C#");
                    allarray[i][2] = resultSet.getString("SCORE");
                    i++;
                }
                Object[][] resultarray = new Object[i][];
                for (int a = 0; a < i; a++){  //取出allarray的前i行作为jtable的输出
                    resultarray[a] = allarray[a];
                }
                String[] category = { "Sid", "C#", "SCORE"};
                table1 = new JTable(resultarray, category);
                scrollPane2.setViewportView(table1);
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException | SQLException ea) {
            ea.printStackTrace();
        }
    }

    // 查询并列出所有系名（不重复）
    private void button3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://rm-bp1oo27t8762xhlob0o.mysql.rds.aliyuncs.com:3306/sct_yutao?&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "lab_1250828523";
            String password = "86771663b371_#@Aa";
            //3.连接成功数据库对象 Connection代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select distinct DNAME from Student;";
            Object[][] allarray = new Object[100][1];
            int i = 0;  // 记录select出的行数
            try {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    allarray[i][0] = resultSet.getString("DNAME");
                    i++;
                }
                Object[][] resultarray = new Object[i][];
                for (int a = 0; a < i; a++){  //取出allarray的前i行作为jtable的输出
                    resultarray[a] = allarray[a];
                }
                String[] category = { "DNAME"};
                table1 = new JTable(resultarray, category);
                scrollPane2.setViewportView(table1);
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException | SQLException ea) {
            ea.printStackTrace();
        }
    }

    // 查询有多少个同学姓’王’
    private void button4ActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://rm-bp1oo27t8762xhlob0o.mysql.rds.aliyuncs.com:3306/sct_yutao?&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "lab_1250828523";
            String password = "86771663b371_#@Aa";
            //3.连接成功数据库对象 Connection代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select count(SNAME) as SUMWANG from Student where SNAME like '王%%';";
            Object[][] allarray = new Object[100][1];
            int i = 0;  // 记录select出的行数
            try {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    allarray[i][0] = resultSet.getString("SUMWANG");
                    i++;
                }
                Object[][] resultarray = new Object[i][];
                for (int a = 0; a < i; a++){  //取出allarray的前i行作为jtable的输出
                    resultarray[a] = allarray[a];
                }
                String[] category = { "SUMWANG"};
                table1 = new JTable(resultarray, category);
                scrollPane2.setViewportView(table1);
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException | SQLException ea) {
            ea.printStackTrace();
        }
    }

    // 查询数据库课程的最高成绩
    private void button5ActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://rm-bp1oo27t8762xhlob0o.mysql.rds.aliyuncs.com:3306/sct_yutao?&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "lab_1250828523";
            String password = "86771663b371_#@Aa";
            //3.连接成功数据库对象 Connection代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select max(SCORE) as MAXSCORE from SC, Course C where C.CNAME = '数据库' and C.`C#` = SC.`C#`;";
            Object[][] allarray = new Object[100][1];
            int i = 0;  // 记录select出的行数
            try {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    allarray[i][0] = resultSet.getString("MAXSCORE");
                    i++;
                }
                Object[][] resultarray = new Object[i][];
                for (int a = 0; a < i; a++){  //取出allarray的前i行作为jtable的输出
                    resultarray[a] = allarray[a];
                }
                String[] category = { "MAXSCORE"};
                table1 = new JTable(resultarray, category);
                scrollPane2.setViewportView(table1);
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException | SQLException ea) {
            ea.printStackTrace();
        }
    }

    // 将学生的成绩按课号升序，成绩降序排列
    private void button6ActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://rm-bp1oo27t8762xhlob0o.mysql.rds.aliyuncs.com:3306/sct_yutao?&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "lab_1250828523";
            String password = "86771663b371_#@Aa";
            //3.连接成功数据库对象 Connection代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select * from SC order by `C#`, SCORE desc;";
            Object[][] allarray = new Object[100][3];
            int i = 0;  // 记录select出的行数
            try {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    allarray[i][0] = resultSet.getString("S#");
                    allarray[i][1] = resultSet.getString("C#");
                    allarray[i][2] = resultSet.getString("SCORE");
                    i++;
                }
                Object[][] resultarray = new Object[i][];
                for (int a = 0; a < i; a++){  //取出allarray的前i行作为jtable的输出
                    resultarray[a] = allarray[a];
                }
                String[] category = { "Sid", "C#", "SCORE"};
                table1 = new JTable(resultarray, category);
                scrollPane2.setViewportView(table1);
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException | SQLException ea) {
            ea.printStackTrace();
        }
    }

    // 统计每个学生选修的课程数
    private void button7ActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://rm-bp1oo27t8762xhlob0o.mysql.rds.aliyuncs.com:3306/sct_yutao?&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "lab_1250828523";
            String password = "86771663b371_#@Aa";
            //3.连接成功数据库对象 Connection代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select `S#`, count(`C#`) as SUMC from SC group by `S#`;";
            Object[][] allarray = new Object[100][2];
            int i = 0;  // 记录select出的行数
            try {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    allarray[i][0] = resultSet.getString("S#");
                    allarray[i][1] = resultSet.getString("SUMC");
                    i++;
                }
                Object[][] resultarray = new Object[i][];
                for (int a = 0; a < i; a++){  //取出allarray的前i行作为jtable的输出
                    resultarray[a] = allarray[a];
                }
                String[] category = { "S#", "SUMC"};
                table1 = new JTable(resultarray, category);
                scrollPane2.setViewportView(table1);
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException | SQLException ea) {
            ea.printStackTrace();
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        label1 = new JLabel();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {52, 0, 32, 0};
                ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 77, 0};
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- button1 ----
                button1.setText("\u67e5\u8be2\u6240\u6709\u5b66\u751f\u4fe1\u606f");
                button1.addActionListener(e -> button1ActionPerformed(e));
                contentPanel.add(button1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- button2 ----
                button2.setText("\u67e5\u8be2\u6210\u7ee9\u572880~90\uff08>=80,<=90\uff09\u5206\u4e4b\u95f4\u7684\u5b66\u751f\u7684\u9009\u8bfe\u4fe1\u606f(\u5b66\u53f7\u3001\u8bfe\u7a0b\u53f7\u548c\u6210\u7ee9)");
                button2.addActionListener(e -> button2ActionPerformed(e));
                contentPanel.add(button2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- button3 ----
                button3.setText("\u67e5\u8be2\u5e76\u5217\u51fa\u6240\u6709\u7cfb\u540d\uff08\u4e0d\u91cd\u590d\uff09");
                button3.addActionListener(e -> button3ActionPerformed(e));
                contentPanel.add(button3, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- button4 ----
                button4.setText("\u67e5\u8be2\u6709\u591a\u5c11\u4e2a\u540c\u5b66\u59d3\u2019\u738b\u2019");
                button4.addActionListener(e -> button4ActionPerformed(e));
                contentPanel.add(button4, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- button5 ----
                button5.setText("\u67e5\u8be2\u6570\u636e\u5e93\u8bfe\u7a0b\u7684\u6700\u9ad8\u6210\u7ee9");
                button5.addActionListener(e -> button5ActionPerformed(e));
                contentPanel.add(button5, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- button6 ----
                button6.setText("\u5c06\u5b66\u751f\u7684\u6210\u7ee9\u6309\u8bfe\u53f7\u5347\u5e8f\uff0c\u6210\u7ee9\u964d\u5e8f\u6392\u5217");
                button6.addActionListener(e -> button6ActionPerformed(e));
                contentPanel.add(button6, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- button7 ----
                button7.setText("\u7edf\u8ba1\u6bcf\u4e2a\u5b66\u751f\u9009\u4fee\u7684\u8bfe\u7a0b\u6570");
                button7.addActionListener(e -> button7ActionPerformed(e));
                contentPanel.add(button7, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label1 ----
                label1.setText("\u67e5\u8be2\u7ed3\u679c");
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label1, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //======== scrollPane2 ========
                {

                    //---- table1 ----
                    table1.setPreferredScrollableViewportSize(new Dimension(450, 200));
                    scrollPane2.setViewportView(table1);
                }
                contentPanel.add(scrollPane2, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JLabel label1;
    private JScrollPane scrollPane2;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
