import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class CreateData {
    private int data_num = 1000000;

    // 产生数据
    public void create_data(String filename) {
        try {
            FileOutputStream os = new FileOutputStream(filename);
            Random rm = new Random();
            for (int i = 0; i < data_num; i++) {
                int A = rm.nextInt(Integer.MAX_VALUE);  // 生成4字节int
//                System.out.println(i + "    " + A);
                os.write(intToBytes(A));
                String B = getRandomStr(12);  // 生成12位String
//                System.out.println(i + "...." + B);
                os.write(B.getBytes());
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 产生bytes长度的字符数组
    public String getRandomStr(int bytes) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < bytes; i++) {
            //随机判断判断该字符是数字还是字母
            String choice = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(choice)) {
                //随机判断是大写字母还是小写字母
                int start = random.nextInt(2) % 2 == 0 ? 65 : 97;
                sb.append((char) (start + random.nextInt(26)));
            } else if ("num".equalsIgnoreCase(choice)) {
                sb.append(random.nextInt(10));
            }
        }
        return sb.toString();
    }

    /**
     * 描述：把一个整数转为4字节的byte数组
     * @param num 待转化的数字
     * @return 转化得到的byte数组
     */
    public static byte[] intToBytes(int num) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (num >> 8 * (3 - i) & 0xFF);
        }
        return b;
    }

    public static int bytesToInt(byte[] b) {
        int intValue = 0;
        for (int i = 0; i < b.length; i++) {
            intValue += (b[i] & 0xFF) << (8 * (3 - i));
        }
        return intValue;
    }

    public static void main(String[] args) {
        CreateData y = new CreateData();
//        System.out.println(y.sumHex(1)[0]);
        y.create_data("src/data");
    }
}
