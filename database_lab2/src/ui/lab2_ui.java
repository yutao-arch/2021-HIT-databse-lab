/*
 * Created by JFormDesigner on Sun May 09 19:23:09 CST 2021
 */

package ui;



import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class lab2_ui extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    lab2_ui frame = new lab2_ui();
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public lab2_ui() {
        initComponents();
    }

    // 插入宿舍房间
    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (textField1.getText().equals("") || textField2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "错误原因：输入不能为空！");
            return;
        }
        String dormitory_num = textField1.getText();
        int bed_sum = Integer.parseInt(textField2.getText());
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://localhost:3306/database_lab2_dormitory?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "root";
            String password = "yutao19981119";
            //3.连接成功数据库对象 Connection代表数据库dormitory_room
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select * from dormitory_room where dormitory_num = '" + dormitory_num + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                JOptionPane.showMessageDialog(null, "错误原因：插入了重复值！");
                return;
            }
            sql = "insert into dormitory_room values ('" + dormitory_num + "',"+ bed_sum + ");";
            statement.execute(sql);
            resultSet.close();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "插入成功！");
        } catch (ClassNotFoundException | SQLException ea) {
            JOptionPane.showMessageDialog(null, "错误原因：使用了外键，而外键约束还未创建！");
//            ea.printStackTrace();
        }
    }

    // 插入床
    private void button2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (textField3.getText().equals("") || textField4.getText().equals("") || textField19.getText().equals("")){
            JOptionPane.showMessageDialog(null, "错误原因：输入不能为空！");
            return;
        }
        String dormitory_num = textField3.getText();
        int bed_num = Integer.parseInt(textField4.getText());
        String bed_type = textField19.getText();
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://localhost:3306/database_lab2_dormitory?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "root";
            String password = "yutao19981119";
            //3.连接成功数据库对象 Connection代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select * from bed where dormitory_num = '" + dormitory_num + "' and " + "bed_num = " + bed_num + ";";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                JOptionPane.showMessageDialog(null, "错误原因：插入了重复值！");
                return;
            }
            sql = "insert into bed values ('"+ dormitory_num + "',"+ bed_num + ",'" + bed_type + "');";
            statement.execute(sql);
            resultSet.close();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "插入成功！");
        } catch (ClassNotFoundException | SQLException ea) {
            JOptionPane.showMessageDialog(null, "错误原因：使用了外键，而外键约束还未创建！");
//            ea.printStackTrace();
        }
    }

    // 插入学生信息
    private void button3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (textField5.getText().equals("") || textField6.getText().equals("") || textField20.getText().equals("") ||
                textField21.getText().equals("") || textField22.getText().equals("") ){
            JOptionPane.showMessageDialog(null, "错误原因：输入不能为空！");
            return;
        }
        String student_num  = textField5.getText();
        String name  = textField6.getText();
        int age  = Integer.parseInt(textField20.getText());
        String id_card_num = textField21.getText();
        String sex = textField22.getText();
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://localhost:3306/database_lab2_dormitory?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "root";
            String password = "yutao19981119";
            //3.连接成功数据库对象 Connection代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select * from student_information where student_num = '" + student_num + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                JOptionPane.showMessageDialog(null, "错误原因：插入了重复值！");
                return;
            }
            sql = "insert into student_information values ('"+ student_num + "','"+ name + "'," + age + ",'" + id_card_num + "','" +  sex + "');";
            statement.execute(sql);
            resultSet.close();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "插入成功！");
        } catch (ClassNotFoundException | SQLException ea) {
            JOptionPane.showMessageDialog(null, "错误原因：使用了外键，而外键约束还未创建！");
//            ea.printStackTrace();
        }
    }

    // 删除宿舍房间中的元组
    private void button4ActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (textField7.getText().equals("")){
            JOptionPane.showMessageDialog(null, "错误原因：输入不能为空！");
            return;
        }
        String dormitory_num = textField7.getText();
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://localhost:3306/database_lab2_dormitory?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "root";
            String password = "yutao19981119";
            //3.连接成功数据库对象 Connection代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select * from dormitory_room where dormitory_num = '" + dormitory_num + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()){
                JOptionPane.showMessageDialog(null, "错误原因：待删除的元组不存在！");
                return;
            }
            sql = "delete from dormitory_room where dormitory_num = '" + dormitory_num + "';";
            statement.execute(sql);
            resultSet.close();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "删除成功！");
        } catch (ClassNotFoundException | SQLException ea) {
            JOptionPane.showMessageDialog(null, "错误原因：删除的元组作为了其他表的元组外键，而外键约束还存在！");
//            ea.printStackTrace();
        }
    }

    // 删除床中的元组
    private void button5ActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (textField8.getText().equals("") || textField9.getText().equals("")){
            JOptionPane.showMessageDialog(null, "错误原因：输入不能为空！");
            return;
        }
        String dormitory_num = textField8.getText();
        int bed_num = Integer.parseInt(textField9.getText());
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://localhost:3306/database_lab2_dormitory?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "root";
            String password = "yutao19981119";
            //3.连接成功数据库对象 Connection代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select * from bed where dormitory_num = '" + dormitory_num + "' and bed_num = " + bed_num + ";";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()){
                JOptionPane.showMessageDialog(null, "错误原因：待删除的元组不存在！");
                return;
            }
            sql = "delete from bed where dormitory_num = '" + dormitory_num + "' and bed_num = " + bed_num + ";";
            statement.execute(sql);
            resultSet.close();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "删除成功！");
        } catch (ClassNotFoundException | SQLException ea) {
            JOptionPane.showMessageDialog(null, "错误原因：删除的元组作为了其他表的元组外键，而外键约束还存在！");
//            ea.printStackTrace();
        }
    }

    // 查询某个宿管的所有工作任务
    private void button6ActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (textField10.getText().equals("")){
            JOptionPane.showMessageDialog(null, "错误原因：输入不能为空！");
            return;
        }
        String dormitory_adm_num = textField10.getText();
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://localhost:3306/database_lab2_dormitory?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "root";
            String password = "yutao19981119";
            //3.连接成功数据库对象 Connection代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select * from dormitory_adm where dormitory_adm_num = '" + dormitory_adm_num +"';";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()){
                JOptionPane.showMessageDialog(null, "错误原因：该宿管不存在！");
                return;
            }
            sql = "select work_task_num, dormitory_num, dormitory_adm_num from dormitory_adm natural join work_task where dormitory_adm_num = '" + dormitory_adm_num + "';";
            textArea1.setText(sql);
            resultSet = statement.executeQuery(sql);
            Object[][] allarray = new Object[100][3];
            int i = 0;  // 记录select出的行数
            while (resultSet.next()){
                allarray[i][0] = resultSet.getString("work_task_num");
                allarray[i][1] = resultSet.getString("dormitory_num");
                allarray[i][2] = resultSet.getString("dormitory_adm_num");
                i++;
            }
            Object[][] resultarray = new Object[i][];
            //取出allarray的前i行作为jtable的输出
            System.arraycopy(allarray, 0, resultarray, 0, i);
            String[] category = { "work_task_num", "dormitory_num", "dormitory_adm_num"};
            table1 = new JTable(resultarray, category);
            scrollPane1.setViewportView(table1);
            resultSet.close();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "查询成功！");
        } catch (ClassNotFoundException | SQLException ea) {
            JOptionPane.showMessageDialog(null, "查询出现错误！");
//            ea.printStackTrace();
        }
    }

    // 查询该学院开具证明的所有学生信息
    private void button7ActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (textField11.getText().equals("")){
            JOptionPane.showMessageDialog(null, "错误原因：输入不能为空！");
            return;
        }
        String sign_prove_college = textField11.getText();
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://localhost:3306/database_lab2_dormitory?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "root";
            String password = "yutao19981119";
            //3.连接成功数据库对象 Connection代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select * from apply_stay_prove where sign_prove_college = '" + sign_prove_college +"';";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()){
                JOptionPane.showMessageDialog(null, "错误原因：不存在该学院开具的证明！");
                return;
            }
            sql = "select * from student_information where student_num in (select student_num from apply_stay_prove where sign_prove_college = '"+ sign_prove_college + "')" + ";";
            textArea2.setText(sql);
            resultSet = statement.executeQuery(sql);
            Object[][] allarray = new Object[100][5];
            int i = 0;  // 记录select出的行数
            while (resultSet.next()){
                allarray[i][0] = resultSet.getString("student_num");
                allarray[i][1] = resultSet.getString("name");
                allarray[i][2] = resultSet.getString("age");
                allarray[i][3] = resultSet.getString("id_card_num");
                allarray[i][4] = resultSet.getString("sex");
                i++;
            }
            Object[][] resultarray = new Object[i][];
            //取出allarray的前i行作为jtable的输出
            System.arraycopy(allarray, 0, resultarray, 0, i);
            String[] category = { "student_num", "name", "age", "id_card_num", "sex"};
            table2 = new JTable(resultarray, category);
            scrollPane2.setViewportView(table2);
            resultSet.close();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "查询成功！");
        } catch (ClassNotFoundException | SQLException ea) {
            JOptionPane.showMessageDialog(null, "查询出现错误！");
            ea.printStackTrace();
        }
    }

    // 查询每个学院的入住人数
    private void button8ActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.用户信息和URL
            String url = "jdbc:mysql://localhost:3306/database_lab2_dormitory?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "root";
            String password = "yutao19981119";
            //3.连接成功数据库对象 Connection代表数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            //4.执行SQL的对象
            Statement statement = connection.createStatement();
            String sql = "select sign_prove_college, count(student_num) as sum_student from apply_stay_prove group by sign_prove_college"+ ";";
            textArea3.setText(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            Object[][] allarray = new Object[100][2];
            int i = 0;  // 记录select出的行数
            while (resultSet.next()){
                allarray[i][0] = resultSet.getString("sign_prove_college");
                allarray[i][1] = resultSet.getString("sum_student");
                i++;
            }
            Object[][] resultarray = new Object[i][];
            //取出allarray的前i行作为jtable的输出
            System.arraycopy(allarray, 0, resultarray, 0, i);
            String[] category = { "sign_prove_college", "sum_student"};
            table3 = new JTable(resultarray, category);
            scrollPane6.setViewportView(table3);
            resultSet.close();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "查询成功！");
        } catch (ClassNotFoundException | SQLException ea) {
            JOptionPane.showMessageDialog(null, "查询出现错误！");
            ea.printStackTrace();
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        tabbedPane2 = new JTabbedPane();
        panel2 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        button1 = new JButton();
        panel6 = new JPanel();
        label4 = new JLabel();
        textField3 = new JTextField();
        label5 = new JLabel();
        textField4 = new JTextField();
        label28 = new JLabel();
        textField19 = new JTextField();
        button2 = new JButton();
        panel7 = new JPanel();
        label6 = new JLabel();
        textField5 = new JTextField();
        label7 = new JLabel();
        textField6 = new JTextField();
        label29 = new JLabel();
        textField20 = new JTextField();
        label30 = new JLabel();
        textField21 = new JTextField();
        label31 = new JLabel();
        textField22 = new JTextField();
        button3 = new JButton();
        panel14 = new JPanel();
        tabbedPane3 = new JTabbedPane();
        panel10 = new JPanel();
        label3 = new JLabel();
        textField7 = new JTextField();
        button4 = new JButton();
        panel11 = new JPanel();
        label8 = new JLabel();
        textField8 = new JTextField();
        label9 = new JLabel();
        textField9 = new JTextField();
        button5 = new JButton();
        panel3 = new JPanel();
        label10 = new JLabel();
        textField10 = new JTextField();
        button6 = new JButton();
        label14 = new JLabel();
        scrollPane3 = new JScrollPane();
        textArea1 = new JTextArea();
        label11 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel4 = new JPanel();
        label12 = new JLabel();
        textField11 = new JTextField();
        button7 = new JButton();
        label15 = new JLabel();
        scrollPane4 = new JScrollPane();
        textArea2 = new JTextArea();
        label13 = new JLabel();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        panel5 = new JPanel();
        button8 = new JButton();
        label16 = new JLabel();
        scrollPane5 = new JScrollPane();
        textArea3 = new JTextArea();
        label17 = new JLabel();
        scrollPane6 = new JScrollPane();
        table3 = new JTable();

        //======== this ========
        setMinimumSize(new Dimension(500, 250));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== tabbedPane1 ========
        {

            //======== panel1 ========
            {
                panel1.setLayout(new GridBagLayout());
                ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {659, 0, 0};
                ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};
                ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

                //======== tabbedPane2 ========
                {

                    //======== panel2 ========
                    {
                        panel2.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 0, 156, 0, 0};
                        ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                        ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                        ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                        //---- label1 ----
                        label1.setText("\u5bbf\u820d\u53f7");
                        label1.setHorizontalAlignment(SwingConstants.CENTER);
                        panel2.add(label1, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                        panel2.add(textField1, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                        //---- label2 ----
                        label2.setText("\u5e8a\u7684\u4e2a\u6570");
                        label2.setHorizontalAlignment(SwingConstants.CENTER);
                        panel2.add(label2, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                        panel2.add(textField2, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                        //---- button1 ----
                        button1.setText("\u63d2\u5165");
                        button1.addActionListener(e -> button1ActionPerformed(e));
                        panel2.add(button1, new GridBagConstraints(3, 7, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                    }
                    tabbedPane2.addTab("\u5bbf\u820d\u623f\u95f4", panel2);

                    //======== panel6 ========
                    {
                        panel6.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel6.getLayout()).columnWidths = new int[] {0, 0, 0, 156, 0, 0};
                        ((GridBagLayout)panel6.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                        ((GridBagLayout)panel6.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                        ((GridBagLayout)panel6.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                        //---- label4 ----
                        label4.setText("\u5bbf\u820d\u53f7");
                        label4.setHorizontalAlignment(SwingConstants.CENTER);
                        panel6.add(label4, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                        panel6.add(textField3, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                        //---- label5 ----
                        label5.setText("\u5e8a\u53f7");
                        label5.setHorizontalAlignment(SwingConstants.CENTER);
                        panel6.add(label5, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                        panel6.add(textField4, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                        //---- label28 ----
                        label28.setText("\u5e8a\u7684\u7c7b\u578b");
                        label28.setHorizontalAlignment(SwingConstants.CENTER);
                        panel6.add(label28, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                        panel6.add(textField19, new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                        //---- button2 ----
                        button2.setText("\u63d2\u5165");
                        button2.addActionListener(e -> button2ActionPerformed(e));
                        panel6.add(button2, new GridBagConstraints(3, 8, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                    }
                    tabbedPane2.addTab("\u5e8a", panel6);

                    //======== panel7 ========
                    {
                        panel7.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel7.getLayout()).columnWidths = new int[] {0, 0, 0, 156, 0, 0};
                        ((GridBagLayout)panel7.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                        ((GridBagLayout)panel7.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                        ((GridBagLayout)panel7.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                        //---- label6 ----
                        label6.setText("\u5b66\u53f7");
                        label6.setHorizontalAlignment(SwingConstants.CENTER);
                        panel7.add(label6, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                        panel7.add(textField5, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                        //---- label7 ----
                        label7.setText("\u59d3\u540d");
                        label7.setHorizontalAlignment(SwingConstants.CENTER);
                        panel7.add(label7, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                        panel7.add(textField6, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                        //---- label29 ----
                        label29.setText("\u5e74\u9f84");
                        label29.setHorizontalAlignment(SwingConstants.CENTER);
                        panel7.add(label29, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                        panel7.add(textField20, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                        //---- label30 ----
                        label30.setText("\u8eab\u4efd\u8bc1\u53f7");
                        label30.setHorizontalAlignment(SwingConstants.CENTER);
                        panel7.add(label30, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                        panel7.add(textField21, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                        //---- label31 ----
                        label31.setText("\u6027\u522b");
                        label31.setHorizontalAlignment(SwingConstants.CENTER);
                        panel7.add(label31, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                        panel7.add(textField22, new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                        //---- button3 ----
                        button3.setText("\u63d2\u5165");
                        button3.addActionListener(e -> button3ActionPerformed(e));
                        panel7.add(button3, new GridBagConstraints(3, 8, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                    }
                    tabbedPane2.addTab("\u5b66\u751f\u4fe1\u606f", panel7);
                }
                panel1.add(tabbedPane2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));
            }
            tabbedPane1.addTab("\u63d2\u5165", panel1);

            //======== panel14 ========
            {
                panel14.setLayout(new GridBagLayout());
                ((GridBagLayout)panel14.getLayout()).columnWidths = new int[] {335, 0};
                ((GridBagLayout)panel14.getLayout()).rowHeights = new int[] {0, 0};
                ((GridBagLayout)panel14.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                ((GridBagLayout)panel14.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

                //======== tabbedPane3 ========
                {

                    //======== panel10 ========
                    {
                        panel10.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel10.getLayout()).columnWidths = new int[] {0, 0, 0, 140, 0, 0};
                        ((GridBagLayout)panel10.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
                        ((GridBagLayout)panel10.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                        ((GridBagLayout)panel10.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                        //---- label3 ----
                        label3.setText("\u5bbf\u820d\u53f7");
                        label3.setHorizontalAlignment(SwingConstants.CENTER);
                        panel10.add(label3, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                        panel10.add(textField7, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                        //---- button4 ----
                        button4.setText("\u5220\u9664");
                        button4.addActionListener(e -> button4ActionPerformed(e));
                        panel10.add(button4, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                    }
                    tabbedPane3.addTab("\u5bbf\u820d\u623f\u95f4", panel10);

                    //======== panel11 ========
                    {
                        panel11.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel11.getLayout()).columnWidths = new int[] {0, 0, 0, 136, 0};
                        ((GridBagLayout)panel11.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
                        ((GridBagLayout)panel11.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
                        ((GridBagLayout)panel11.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                        //---- label8 ----
                        label8.setText("\u5bbf\u820d\u53f7");
                        label8.setHorizontalAlignment(SwingConstants.CENTER);
                        panel11.add(label8, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                        panel11.add(textField8, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- label9 ----
                        label9.setText("\u5e8a\u53f7");
                        label9.setHorizontalAlignment(SwingConstants.CENTER);
                        panel11.add(label9, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                        panel11.add(textField9, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- button5 ----
                        button5.setText("\u5220\u9664");
                        button5.addActionListener(e -> button5ActionPerformed(e));
                        panel11.add(button5, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    tabbedPane3.addTab("\u5e8a", panel11);
                }
                panel14.add(tabbedPane3, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            tabbedPane1.addTab("\u5220\u9664", panel14);

            //======== panel3 ========
            {
                panel3.setLayout(new GridBagLayout());
                ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0};
                ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 62, 0, 28, 0};
                ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- label10 ----
                label10.setText("\u5bbf\u7ba1\u53f7");
                label10.setHorizontalAlignment(SwingConstants.CENTER);
                panel3.add(label10, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel3.add(textField10, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- button6 ----
                button6.setText("\u67e5\u8be2\u67d0\u4e2a\u5bbf\u7ba1\u7684\u6240\u6709\u5de5\u4f5c\u4efb\u52a1");
                button6.addActionListener(e -> button6ActionPerformed(e));
                panel3.add(button6, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label14 ----
                label14.setText("sql\u8bed\u53e5");
                label14.setHorizontalAlignment(SwingConstants.CENTER);
                panel3.add(label14, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(textArea1);
                }
                panel3.add(scrollPane3, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label11 ----
                label11.setText("\u67e5\u8be2\u7ed3\u679c");
                label11.setHorizontalAlignment(SwingConstants.CENTER);
                panel3.add(label11, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(table1);
                }
                panel3.add(scrollPane1, new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
            }
            tabbedPane1.addTab("\u8fde\u63a5\u67e5\u8be2", panel3);

            //======== panel4 ========
            {
                panel4.setLayout(new GridBagLayout());
                ((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {0, 0, 0, 133, 0};
                ((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 66, 0, 0};
                ((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- label12 ----
                label12.setText("\u5f00\u5177\u8bc1\u660e\u5b66\u9662");
                label12.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(label12, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel4.add(textField11, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- button7 ----
                button7.setText("\u67e5\u8be2\u8be5\u5b66\u9662\u5f00\u5177\u8bc1\u660e\u7684\u6240\u6709\u5b66\u751f\u4fe1\u606f");
                button7.addActionListener(e -> button7ActionPerformed(e));
                panel4.add(button7, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label15 ----
                label15.setText("sql\u8bed\u53e5");
                label15.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(label15, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== scrollPane4 ========
                {
                    scrollPane4.setViewportView(textArea2);
                }
                panel4.add(scrollPane4, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label13 ----
                label13.setText("\u67e5\u8be2\u7ed3\u679c");
                label13.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(label13, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(table2);
                }
                panel4.add(scrollPane2, new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            tabbedPane1.addTab("\u5d4c\u5957\u67e5\u8be2", panel4);

            //======== panel5 ========
            {
                panel5.setLayout(new GridBagLayout());
                ((GridBagLayout)panel5.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0};
                ((GridBagLayout)panel5.getLayout()).rowHeights = new int[] {0, 0, 0, 62, 0, 0};
                ((GridBagLayout)panel5.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel5.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- button8 ----
                button8.setText("\u67e5\u8be2\u6bcf\u4e2a\u5b66\u9662\u7684\u5165\u4f4f\u4eba\u6570");
                button8.addActionListener(e -> button8ActionPerformed(e));
                panel5.add(button8, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label16 ----
                label16.setText("sql\u8bed\u53e5");
                label16.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(label16, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== scrollPane5 ========
                {
                    scrollPane5.setViewportView(textArea3);
                }
                panel5.add(scrollPane5, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label17 ----
                label17.setText("\u67e5\u8be2\u7ed3\u679c");
                label17.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(label17, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //======== scrollPane6 ========
                {
                    scrollPane6.setViewportView(table3);
                }
                panel5.add(scrollPane6, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            tabbedPane1.addTab("\u5206\u7ec4\u67e5\u8be2", panel5);
        }
        contentPane.add(tabbedPane1, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTabbedPane tabbedPane2;
    private JPanel panel2;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JButton button1;
    private JPanel panel6;
    private JLabel label4;
    private JTextField textField3;
    private JLabel label5;
    private JTextField textField4;
    private JLabel label28;
    private JTextField textField19;
    private JButton button2;
    private JPanel panel7;
    private JLabel label6;
    private JTextField textField5;
    private JLabel label7;
    private JTextField textField6;
    private JLabel label29;
    private JTextField textField20;
    private JLabel label30;
    private JTextField textField21;
    private JLabel label31;
    private JTextField textField22;
    private JButton button3;
    private JPanel panel14;
    private JTabbedPane tabbedPane3;
    private JPanel panel10;
    private JLabel label3;
    private JTextField textField7;
    private JButton button4;
    private JPanel panel11;
    private JLabel label8;
    private JTextField textField8;
    private JLabel label9;
    private JTextField textField9;
    private JButton button5;
    private JPanel panel3;
    private JLabel label10;
    private JTextField textField10;
    private JButton button6;
    private JLabel label14;
    private JScrollPane scrollPane3;
    private JTextArea textArea1;
    private JLabel label11;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel4;
    private JLabel label12;
    private JTextField textField11;
    private JButton button7;
    private JLabel label15;
    private JScrollPane scrollPane4;
    private JTextArea textArea2;
    private JLabel label13;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JPanel panel5;
    private JButton button8;
    private JLabel label16;
    private JScrollPane scrollPane5;
    private JTextArea textArea3;
    private JLabel label17;
    private JScrollPane scrollPane6;
    private JTable table3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
