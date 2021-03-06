import edu.princeton.cs.algs4.StdOut;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.*;
import java.util.Objects;


public class StudentFileIO {
    @SuppressFBWarnings({"NP_DEREFERENCE_OF_READLINE_VALUE", "NP_ALWAYS_NULL", "NP_NULL_PARAM_DEREF_NONVIRTUAL", "OS_OPEN_STREAM", "DM_DEFAULT_ENCODING"})
    public static void readFile(String classPath, int numberOfClass, AllStudent allStudent) throws IOException {//处理班级文件
        // update pass parameter: numberOfClass
        try {
            String pathname = Objects.requireNonNull(_2.class.getClassLoader().getResource(classPath)).getPath();
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(pathname))));
            assert bf != null;

            String[] buf_info = new String[2]; // 麻痹 我跟你说要是写成String[] buf_info = null; 后面bf写死你，根本就读不了，很惨搞了一下午找不到问题
            for (int i = 0; i < 2; i++) {
                buf_info[i] = bf.readLine();
            }
            allStudent.addGlobalCoursesList(new Course(buf_info));

            String buf_string = bf.readLine();
            while (buf_string != null) {
                allStudent.addGlobalStudentList(buf_string, numberOfClass); // update: use numberOfClass
                buf_string = bf.readLine();
            }

        } catch (Exception ex) {
            return;
        }

        for (Student student : allStudent.getNowClassStudentList()) {
            space(student);
            StdOut.println(student.Grade(0));
        }

    }

    private static void space(Student student) {

        System.out.printf("%-20s", student.getName());

        System.out.printf("%-20s", student.getStudentID());

        System.out.printf("%-20s", student.getScore(0));

//        System.out.print(Student.Grade(student.getScore(0))); update at Line 66 in this class

    }


//    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
//    public static BufferedReader ReadTxt(String classPath) {/* 读班级TXT文件 */
//        try {
//            String pathname = Objects.requireNonNull(cin_txt.class.getClassLoader().getResource(classPath)).getPath();// 相对路径
//            File filename = new File(pathname); // 要读取以上路径的input。txt文件
//            InputStreamReader reader = new InputStreamReader(
//                    new FileInputStream(filename)); // 建立一个输入流对象reader
//            return new BufferedReader(reader);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;

//    }
//
//    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
//    public static void WriteTxt() throws IOException {
//        /* 写入Txt文件 */
//        try {
//            File writeName = new File(".\\src\\output.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
//            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
//            try (FileWriter writer = new FileWriter(writeName);
//                 BufferedWriter out = new BufferedWriter(writer)
//            ) {
//                out.write("我会写入文件啦1\r\n"); // \r\n即为换行
//                out.write("我会写入文件啦2\r\n"); // \r\n即为换行
//                out.flush(); // 把缓存区内容压入文件
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void ChooseStudent(String classPath, int readClassAmount, AllStudent allStudent) throws IOException {//处理班级文件
////        for (int i = 0; i < number; i++) {
////            line = br.readLine();
////            String[] student = FileIO.spiltString(line);
////            int temp_index = isExist(student[2], allStudent);
////            if (temp_index != -1) {
////                // the student has existd in the list, then we update his score.
////                updatecorrespondingScore(allStudent.getSelectedStudent(temp_index), student[3], readClassAmount);
////
////            } else {
////                addStudent(allStudent, student[0], student[1], student[2], Double.parseDouble(student[3]), readClassAmount);
////            }
////        }
//    }

//    public static void updatecorrespondingScore(Student student, String score, int index) {
//        student.setScore(Double.parseDouble(score), index);
//    }


//    public static int isExist(String id, AllStudent allStudent) {
//        for (int i = 0; i < allStudent.globalStudentList.size(); i++) {
//            if (id.equals(allStudent.globalStudentList.get(i).getStudentID())) {
//                return i;
//            }
//        }
//        return -1;
//    }


//    public static void addStudent(AllStudent allStudent, String surnName, String givenName, String ID, double score, int index) {
//        allStudent.globalStudentList.add(new Student(surnName, givenName, ID, score, index));
//    }


//    public static String[] spiltString(String str) {
//        return str.split(",");
//    }

//    public static String[] spiltString2(String ok) {
//        return ok.split("\\.");
//    }

}
