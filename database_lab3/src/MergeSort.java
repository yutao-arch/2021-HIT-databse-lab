import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MergeSort {
    private int block_number = 16;  // 分成的子文件个数
    //    private int block_number = 10;
    private int memory_size = 1024 * 1024;  // 内存大小
    private int page_size = 4096;  // 内存页大小
    // page_number = memory_size / page_size = 256页 1M内存有256页 子集合数<256 且每个子集合小于1024*1024B即可
    private int data_num = 1000000;  // 数据总数
    private int file_size = 16 * data_num;  // 所有数据的大小（字节）
    private int part_file_size = (int) (file_size / block_number);  // 每一子文件的大小（字节）
    //  private int part_file_size = 160;
    String prefix = "src/merge_sort_result/part_";
    private int R_problem = 1000000;
    private int R_block = 256;  // 4096B / 16B
    private int B_memory = 256; // 1MB / 4KB

    // 每次读16个字节
    private Data readData(InputStream ips) throws IOException {
        byte[] read_data = new byte[16];
        byte[] key_bytes = new byte[4];
        byte[] value = new byte[12];
        int flag = ips.read(read_data);
        if (flag < 0)
            return null;
        System.arraycopy(read_data, 0, key_bytes, 0, 4);  // 前4个字节作为A
        System.arraycopy(read_data, 4, value, 0, 12);  // 后12个字节作为B
        return new Data(CreateData.bytesToInt(key_bytes), new String(value, StandardCharsets.UTF_8));
    }


    // 划分小文件
    public void split_file(String filename) {
        File myfile = new File(filename);
        if (myfile.isFile() && myfile.exists()) {
            try {
                FileInputStream fi = new FileInputStream(myfile);
                for (int i = 0; i < block_number; i++) {  // 16个子文件
                    List<Data> templist = new ArrayList<>();
                    for (int j = 0; j < part_file_size / 16; j++) {  // 每个文件的记录总数
                        Data tempdata = readData(fi);
                        if (tempdata != null) {
                            templist.add(tempdata);
                        }
                    }
                    Collections.sort(templist, new Comparator<Data>() {  // 对子文件内按照A进行排序
                        @Override
                        public int compare(Data o1, Data o2) {
                            return o1.getA() - o2.getA();
                        }
                    });
                    FileOutputStream os = new FileOutputStream(prefix + i);
                    for (Data tempdata : templist) {  // 将templist中的所有记录读入一个小文件
                        os.write(CreateData.intToBytes(tempdata.getA()));
//                        System.out.println(tempdata.getA());
                        os.write(tempdata.getB().getBytes());
//                        System.out.println(tempdata.getB());
                    }
                    os.close();
//                    System.out.println("————————————————————————————————————————————————————————————");
                }
                fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // 多路归并，数据出现丢失, 丢失了几十个
    public void merge_sort() {
        try {
            int num = 1;
//            int all = 1;
            Data[][] M = new Data[block_number][256];
            Data[] M_compare = new Data[block_number];  // 比较缓冲区
            Data[] M_output = new Data[block_number];  // 输出缓冲区
            int[] Mi_next = new int[block_number];  // 记录Mi中元素移动的指针,即Mi中移到第几个元素了
            int[] Si_next = new int[block_number];  // 记录Si中读到第几块了
            int[] flag = new int[block_number];  // 记录是否还有下一块
            Data finished = new Data(-1, "finished");  // 记录已经读完的Data
            FileOutputStream os = new FileOutputStream("src/merge_sort_result/result");  // 输出文件

            int i;
            for (i = 0; i < block_number; i++) {
                FileInputStream fi = new FileInputStream(prefix + i);
                for (int j = 0; j < page_size / 16; j++) {  // 读Si的第一块存到Mi中
                    M[i][j] = readData(fi);
                }
                M_compare[i] = M[i][0];  // 第一个元素
                fi.close();
            }
            int P_output = 0;  // 输出指针起始位置
            while (getmin(M_compare) != -1) {  // 如果M_compare有最小值
                i = getmin(M_compare);  // M_compare最小值的位置
                Data min_i = M_compare[i];  // M_compare最小值
                M_output[P_output] = min_i;  // 将第i个元素存入M_output的P_output位置
                P_output++;  // P_output指向下一个位置
                Mi_next[i] = Mi_next[i] + 1;  // 记录Mi中的位置的指针指向下一个
                if (min_i.getA() == -1) {  // 为finished跳出循环
                    break;
                }
                if (P_output == M_output.length) {  // 如果P_output指向结束位置，输出M_output的内容
                    for (Data data : M_output) {
                        System.out.println(num + ":" + data.getA());
//                        System.out.println(data.getA());
                        os.write(CreateData.intToBytes(data.getA()));
                        System.out.println(num + ":" + data.getB());
//                        System.out.println(data.getA());
                        os.write(data.getB().getBytes());
                        num++;
                    }
                    P_output = 0;  // P_output置于输出缓冲区起始位置
                }
                if (Mi_next[i] < M[i].length) {  // 如果Mi有下一个元素，将Mi的下一个元素放入M_compare的第i位置
                    M_compare[i] = M[i][Mi_next[i]];
                } else {
                    FileInputStream fi = new FileInputStream(prefix + i);
                    fi.read(new byte[page_size * (Si_next[i] + 1)]);  // 先读入已经读过的块，跳过
                    Si_next[i] = Si_next[i] + 1;
                    if (flag[i] == 0) {  // 如果还有下一块
                        for (int j = 0; j < page_size / 16; j++) {  // 重新读入新的一块中所有内容到M
                            Data tempdata = readData(fi);
                            if (tempdata == null) {  // 没有下一块
                                flag[i] = 1;
                                M[i][j] = finished;
                                break;
                            }
                            M[i][j] = tempdata;
                        }
                        Mi_next[i] = 0;
                        M_compare[i] = M[i][0];
                    } else {
                        M_compare[i] = finished;
                    }
                    fi.close();
                }
            }  // M_compare中所有值均为Finished，没有最小值，算法结束
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 多路归并,数据未出现丢失
    public void merge_sort(int num) {
        try {
            InputStream[] readers = new InputStream[num];
            OutputStream ops = new FileOutputStream("src/merge_sort_result/result"); //输出到文件
            Data[][] M = new Data[num][256]; //每一个分组一块内存页，一块内存页4KB，存256条记录
            int[] flag = new int[num];
            int[] point = new int[num]; //存储每一个分组对应块当前读取数据的指针
            Data[] M_compare = new Data[num]; //一块内存页用于比较
            Data[] M_output = new Data[256]; //一块内存页用于输出
            int p_output = 0; //输出指针
            Data Finished = new Data(Integer.MAX_VALUE, "finished");
            int count = 0;

            //依次打开每一个文件,读取每一个分组的第一块到内存中，每一块的第一个数据存入M_compare中
            for (int i = 0; i < num; i++) {
                readers[i] = new FileInputStream(prefix + i);
                for (int j = 0; j < 256; j++) {
                    M[i][j] = readData(readers[i]);
                }
                M_compare[i] = M[i][0];
            }

            //求M_compare中最小元素
            while (true) {
                Data min = M_compare[0];
                int index = 0;
                for (int k = 1; k < num; k++) {
                    if (min.getA() > M_compare[k].getA()) {
                        min = M_compare[k];
                        index = k;
                    }
                }
                if (!min.equals(Finished)) {
                    count++;
//                    System.out.println(count + ":" + min.getA());
                    M_output[p_output] = min;
                    p_output++;
                    if (p_output == 256) {
                        writeOut(ops, M_output);
                        p_output = 0;
                    }
                    //当前块有下一个元素
                    if (point[index] < 255) {
                        point[index]++;
                        M_compare[index] = M[index][point[index]];
                    } else {
                        point[index] = 0;
                        // 如果有下一块
                        if (flag[index] == 0) {
                            for (int j = 0; j < 256; j++) {
                                Data temp = readData(readers[index]);
                                if (temp == null) {
                                    flag[index] = 1;
                                    M[index][j] = Finished;
                                    break;
                                }
                                M[index][j] = temp;
                            }
                            M_compare[index] = M[index][0];
                        } else {
                            M_compare[index] = Finished;
                        }
                    }
                }
                // M_compare中所有值均为Finished，没有最小值，算法结束
                else {
                    if (p_output > 0) {
                        for (int i = 0; i < p_output; i++) {
                            ops.write(CreateData.intToBytes(M_output[i].getA()));
                            ops.write(M_output[i].getB().getBytes());
                        }
                    }
                    break;
                }
            }
            ops.close();
            for (int j = 0; j < num; j++)
                readers[j].close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void writeOut(OutputStream ops, Data[] M_output) throws IOException {
        for (int i = 0; i < 256; i++) {
            ops.write(CreateData.intToBytes(M_output[i].getA()));
            ops.write(M_output[i].getB().getBytes());
        }
    }


    // 返回数组中A最小的下标
    public int getmin(Data[] M_compare) {
        boolean flag = true;
        for (Data data : M_compare) {
            if (data.getA() != -1) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return -1;
        }
        int min_index = 0;
        int min_value = M_compare[0].getA();
        for (int i = 0; i < M_compare.length; i++) {
            if (M_compare[i].getA() < min_value) {
                min_index = i;
                min_value = M_compare[i].getA();
            }
        }
        return min_index;
    }

    public static void main(String[] args) {
        MergeSort oo = new MergeSort();
        long startTime = System.currentTimeMillis();
        oo.split_file("src/data");
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("第一趟运行时间：" + (double) (endTime - startTime) / 1000 + "秒");    //输出第一趟分组运行时间
        startTime = System.currentTimeMillis();
//        oo.merge_sort();
        oo.merge_sort(16);
        endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("第二趟运行时间：" + (double) (endTime - startTime) / 1000 + "秒");    //输出第二趟归并运行时间
    }
}
