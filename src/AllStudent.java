import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class AllStudent {
    private static ArrayList<Student> globalStudentList = new ArrayList<>(); // origin AllStudent
    private static ArrayList<Course> globalCoursesList = new ArrayList<>(); // origin course
    private static ArrayList<Student> nowClassStudent = new ArrayList<>();  //origin nowClassStudent

    // ouput: just for text if it works
    public double getGPA(Double[] scores) {//算gpa

        double gradePointTotal = 0;
        double gradeOfEachCourseTotal = 0;
        for (int i = 0; i < globalCoursesList.size(); i++) {
            if (scores[i] == null) continue;
            gradePointTotal += pointOfEachCourse(scores[i]) * globalCoursesList.get(i).getCredit();
            gradeOfEachCourseTotal += globalCoursesList.get(i).getCredit();
        }

        return Double.parseDouble(new java.text.DecimalFormat("#.00").format(gradePointTotal / gradeOfEachCourseTotal));
    }

    public int getSizeOfNowClassStudents() {
        return nowClassStudent.size();
    }

    public int getSizeOfGlobalStudentList() {
        return globalStudentList.size();
    }

    public void showInfo() {
        StdOut.println("**Students Name**");
        for (Student s : globalStudentList)
            StdOut.println(s);

        StdOut.println("**Course List**");
        for (Course c : globalCoursesList)
            StdOut.println(c);

        StdOut.println("**now Class Students**");
        for (Student s : nowClassStudent)
            StdOut.println(s);
    }

    public void addGlobalStudentList(String buf_info, int index) {
        // 对文件的解析工作放在这一层（中间层）进行  IO层-> 容器层-> 定义类层
        String[] analysis_string = buf_info.split(",");
        String firstName = analysis_string[0];
        String lastName = analysis_string[1];
        String studentID = analysis_string[2];
        String score = analysis_string[3];

        // checking if the student is exist before adding
        // The same function as class (muted): AddStudentList
        int temp_index = isExistGlobalStudents(studentID);
        if (temp_index != -1) {
//            updatecorrespondingScore(getSelectedStudent(temp_index), score, readClassAmount); 分数和课程平行的功能还要再思考后设计过
            updatecorrespondingScore(getSelectedStudent(temp_index), score, index);
        } else {
            globalStudentList.add(new Student(buf_info, index));
//            addStudent(allStudent, student[0], student[1], student[2], Double.parseDouble(student[3]), readClassAmount);
        }
    }

    public void addGlobalCoursesList(Course course) {
        globalCoursesList.add(course);
    }

    public void addNowClassStudentList(String buf_info) {
        String[] analysis_string = buf_info.split(",");
        String firstName = analysis_string[0];
        String lastName = analysis_string[1];
        String studentID = analysis_string[2];
        String score = analysis_string[3];

        // checking if the student is exist before adding
        // The same function as class (muted): AddStudentList
        int temp_index = isExistNowClassStudent(studentID);
        if (temp_index != -1) {
//            updatecorrespondingScore(getSelectedStudent(temp_index), score, readClassAmount); 分数和课程平行的功能还要再思考后设计过
            updatecorrespondingScore(getSelectedStudent(temp_index), score, 0);
        } else {
            nowClassStudent.add(new Student(buf_info, 0));
//            addStudent(allStudent, student[0], student[1], student[2], Double.parseDouble(student[3]), readClassAmount);
        }
    }

    public int isExistNowClassStudent(String id) {
        for (int i = 0; i < nowClassStudent.size(); i++) {
            if (id.equals(nowClassStudent.get(i).getStudentID())) {
                return i;
            }
        }
        return -1;
    }

    public int isExistGlobalStudents(String StuID) {
        for (int i = 0; i < globalStudentList.size(); i++) {
            if (StuID.equals(globalStudentList.get(i).getStudentID())) {
                return i;
            }
        }
        return -1;
    }
//    public void addNowClassStudentList(Student) {

//        nowClassStudent.add();

//    }

    public void setNowClassStudentList(ArrayList<Student> arrayList) {
        nowClassStudent = arrayList;
    }

    public static void updatecorrespondingScore(Student student, String score, int index) {
        student.setScore(Double.parseDouble(score), index);
    }

    public Student getSelectedStudent(int index) {
        return globalStudentList.get(index);
    }

    public ArrayList<Student> getGlobalStudentList() {
        return globalStudentList;
    }

    public ArrayList<Student> getNowClassStudentList() {
        return nowClassStudent;
    }

    public ArrayList<Course> getGlobalCoursesList() {
        return globalCoursesList;
    }

//    public void sortNowClassStudentGPA() {
//        quickSortGPA(nowClassStudent.size());
//    }
//
//    // update version
//    public static void quickSortGPA(int length) {
//        quickSortInternally(0, length - 1);
//    }
//
//    //update version
//    private static void quickSortInternally(int start, int end) {
//        if (start >= end) return;
////            StdRandom.shuffle(allStudent.globalStudentList.toArray()); seems doens't work
//
//        int q = partition(start, end); // 获取分区点
//        quickSortInternally(start, q - 1);
//        quickSortInternally(q + 1, end);
//    }
//
//    //update version
//    private static int partition(int start, int end) {
//        double pivot = getGPA(globalStudentList.get(end).getScores());
//        int i = start;
//        for (int j = start; j < end; ++j) {
//            if (getGPA(globalStudentList.get(j).getScores()) < pivot) {
//                if (i == j) {
//                    ++i;
//                } else {
//                    Student mid = globalStudentList.get(i);
//                    globalStudentList.set(i++, globalStudentList.get(j));
//                    globalStudentList.set(j, mid);
//                }
//            }
//        }
//        Student mid = globalStudentList.get(i);
//        globalStudentList.set(i, globalStudentList.get(end));
//        globalStudentList.set(end, mid);
//        return i;

//    }

    // update version

    public static double pointOfEachCourse(double grade) {//根据实际成绩判断学分方法
        double point = 0.0;
        if (grade >= 93) point = 4.0;
        else if (grade >= 88) point = 3.7;
        else if (grade >= 83) point = 3.3;
        else if (grade >= 78) point = 3.0;
        else if (grade >= 72) point = 2.7;
        else if (grade >= 68) point = 2.3;
        else if (grade >= 63) point = 2.0;
        else if (grade >= 58) point = 1.7;
        else if (grade >= 53) point = 1.3;
        else if (grade >= 50) point = 1.0;
        else point = 0.0;

        return point;
    }
}
















