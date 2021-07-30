import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class insert_data {

    public static void main(String[] args){
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
            //5.执行SQL的对象去执行SQL，可能存在结果，查看返回结果
            List<String> allsql = new ArrayList<>();
            String sql = "insert into Student values ('98030101', '张三', '男', 20, '计算机', '980301');";
            allsql.add(sql);
            sql = "insert into Student values ('98030102', '张四', '女', 20, '计算机', '980301');";
            allsql.add(sql);
            sql = "insert into Student values ('98030103', '张五', '男', 19, '计算机', '980301');";
            allsql.add(sql);
            sql = "insert into Student values ('98040201', '王三', '男', 20, '自动控制', '980402');";
            allsql.add(sql);
            sql = "insert into Student values ('98040202', '王四', '男', 21, '自动控制', '980402');";
            allsql.add(sql);
            sql = "insert into Student values ('98040203', '王五', '女', 19, '自动控制', '980402');";
            allsql.add(sql);
            sql = "insert into Student values ('98020101', '李三', '女', 18, '能源', '980201');";
            allsql.add(sql);
            sql = "insert into Student values ('98020102', '李四', '男', 19, '能源', '980201');";
            allsql.add(sql);
            for (String temp : allsql){
                statement.execute(temp);
            }

            allsql = new ArrayList<>();
            sql = "insert into Course values ('001', '数据库', 40, 6, 1);";
            allsql.add(sql);
            sql = "insert into Course values ('002', '数据结构', 40, 6, 3);";
            allsql.add(sql);
            sql = "insert into Course values ('003', '编译原理', 40, 6, 7);";
            allsql.add(sql);
            sql = "insert into Course values ('004', 'C 语言', 30, 4.5, 6);";
            allsql.add(sql);
            sql = "insert into Course values ('005', '高等数学', 80, 12, 2);";
            allsql.add(sql);
            sql = "insert into Course values ('006', '计算机网络', 20, 3, 4);";
            allsql.add(sql);
            for (String temp : allsql){
                statement.execute(temp);
            }

            allsql = new ArrayList<>();
            sql = "insert into SC values ('98030101', '001', 90);";
            allsql.add(sql);
            sql = "insert into SC values ('98030101', '002', 86);";
            allsql.add(sql);
            sql = "insert into SC values ('98030101', '006', 62);";
            allsql.add(sql);
            sql = "insert into SC values ('98030102', '002', 78);";
            allsql.add(sql);
            sql = "insert into SC values ('98030102', '004', 66);";
            allsql.add(sql);
            sql = "insert into SC values ('98030102', '001', 82);";
            allsql.add(sql);
            sql = "insert into SC values ('98030102', '005', 92);";
            allsql.add(sql);
            sql = "insert into SC values ('98030102', '006', 50);";
            allsql.add(sql);
            sql = "insert into SC values ('98030103', '002', 68);";
            allsql.add(sql);
            sql = "insert into SC values ('98030103', '006', 62);";
            allsql.add(sql);
            sql = "insert into SC values ('98020101', '001', 80);";
            allsql.add(sql);
            sql = "insert into SC values ('98020101', '005', 95);";
            allsql.add(sql);
            sql = "insert into SC values ('98020102', '005', 85);";
            allsql.add(sql);
            for (String temp : allsql){
                statement.execute(temp);
            }
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
