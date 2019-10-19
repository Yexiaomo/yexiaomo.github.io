package com.javatest2;

import java.sql.SQLException;
import java.util.Scanner;
/**
 * 
 * @author zd,fxb
 *
 */
public class TestSystem {
    public static void main(String[] args) throws SQLException {
        StudentManage studentManage = new StudentManage();
        CourseManage courseManage = new CourseManage();
        SelectCourse selectCourse = new SelectCourse();
        DataStatistics dataStatistics = new DataStatistics();
        while (true) {
            System.out.println("\n---------------学生选课系统：-------------------");
            System.out.println("1.添加本科生                      2.添加研究生      3.修改学生信息\n"
                              +"4.添加课程信息                  5.修改课程信息\n"
                              +"6.添加某个学生选课信息    7.取消某个学生选课信息\n"
                              +"8.根据学生选课进行统计    9.根据课程进行统计\n"
                              +"#.退出"
                              +"\n请选择功能");
            System.out.println("-----------------学生选课系统：------------------");
            Scanner sc = new Scanner(System.in);
            char choice = sc.next().charAt(0);
            if (choice == '#') {
                System.out.println("\n恭喜你成功退出系统!!!\n");
                break;
            }
            switch (choice) {
                case '1':{
                    System.out.println("1.请输入需要添加的学生信息, 严格按照以下格式输入,添加失败,概不负责!!");
                    System.out.println("本科生依次为:\t学号_姓名_性别_出生年月_年级_专业_班级_班主任");
                    sc.nextLine();
                    studentManage.addStudent(sc.nextLine(), "本科生");
                    break;
                }case '2':{
                    System.out.println("2.请输入需要添加的学生信息, 严格按照以下格式输入,添加失败,概不负责!!");    
                    System.out.println("研究生依次为:\t学号_姓名_性别_出生年月_年级_专业_导师_研究方向");
                    sc.nextLine();
                    studentManage.addStudent(sc.nextLine(), "研究生");
                    break;
                }case '3':{
                    System.out.println("3.请输入需要修改学生的学号_属性_修改后的值, 严格按照以下格式输入,添加失败,概不负责!!");    
                    System.out.println("如: 想要修改一个本科生,学号为'1'的姓名为张三, 输入格式: 1_姓名_张三_本科生");
                    sc.nextLine();
                    studentManage.modifyStuInfo(sc.nextLine());
                    break;
                }case '4':{
                    System.out.println("4.请输入需要添加的課程信息, 严格按照以下格式输入,添加失败,概不负责!!");    
                    System.out.println("格式为:\t课程编号_课程名_授课老师_课程简介_学分");
                    sc.nextLine();
                    courseManage.addCourse(sc.nextLine());
                    break;
                }case '5':{
                    System.out.println("5.请输入需要修改课程的课程号_属性_修改后的值, 严格按照以下格式输入,添加失败,概不负责!!");    
                    System.out.println("如: 想要修改一个课程的学分, 输入格式: A001_学分_10");
                    sc.nextLine();
                    courseManage.modifyCourse(sc.nextLine());
                    break;
                }case '6':{
                    System.out.println("6.请输入需要添加某个学生选课的信息, 严格按照以下格式输入,添加失败,概不负责!!");    
                    System.out.println("  输入格式: 课程号_学号_本科生");
                    sc.nextLine();
                    selectCourse.addSelectCourse(sc.nextLine());
                    break;
                }case '7':{
                    System.out.println("7.请输入需要取消某个学生选课的信息, 严格按照以下格式输入,操作失败,概不负责!!");    
                    System.out.println("  输入格式: 课程号_学号_本科生");
                    sc.nextLine();
                    selectCourse.cancelCourse(sc.nextLine());
                    break;
                }case '8':{
                    System.out.println("8.根据学生进行选课统计");    
                    sc.nextLine();
                    dataStatistics.studentStatistic();
                    break;
                }case '9':{
                    System.out.println("9.根据课程进行选课统计");    
                    sc.nextLine();
                    dataStatistics.courseStatistics();
                    break;
                }default:{
                    System.out.println("输入有误,请重新输入!");
                    break;
                }
            }//switch
        }//while
    }
}