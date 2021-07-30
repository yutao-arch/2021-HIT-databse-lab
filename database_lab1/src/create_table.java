import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class create_table {

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
            // 创建Student表
            String sql = "CREATE TABLE IF NOT EXISTS Student(`S#` CHAR(8) PRIMARY KEY COMMENT '学号' NOT NULL, SNAME CHAR(10) COMMENT '姓名' NOT NULL, " +
                    "SEX CHAR(2) COMMENT '性别', AGE INTEGER COMMENT '年龄', DNAME CHAR(10) COMMENT '系别', CLASS CHAR(6) COMMENT '班号');";
            statement.execute(sql);
            // 创建Course表
            sql = "CREATE TABLE IF NOT EXISTS Course(`C#` CHAR(3) PRIMARY KEY COMMENT '课程号' NOT NULL, CNAME CHAR(12) COMMENT '课程名', " +
                    "HOURS INTEGER COMMENT '学时', CREDIT FLOAT(1) COMMENT '学分', SEMSTER INTEGER COMMENT '学期');";
            statement.execute(sql);
            // 创建SC表
            sql = "CREATE TABLE IF NOT EXISTS SC(`S#` CHAR(8) COMMENT '学号' NOT NULL, `C#` CHAR(3) COMMENT '课程号' NOT NULL, " +
                    "SCORE FLOAT(1) COMMENT '成绩', PRIMARY KEY(`S#`, `C#`), FOREIGN KEY(`S#`) REFERENCES Student(`S#`)," +
                    "FOREIGN KEY(`C#`) REFERENCES Course(`C#`));";
            statement.execute(sql);
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
