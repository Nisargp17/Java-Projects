package Main;

import dao.StudentDAO;
import dao.StudentDAOImpl;
import model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentManagementSystem {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAOImpl();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Student by ID");
            System.out.println("3. View All Students");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    dao.addStudent(new Student(name, email, age));
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    Student s = dao.getStudentById(id);
                    System.out.println(s != null ? s : " Student not found.");
                    break;
                case 3:
                    List<Student> list = dao.getAllStudents();
                    for (Student stu : list) {
                        System.out.println(stu);
                    }
                    break;
                case 4:
                    System.out.print("Enter ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New name: ");
                    String uname = sc.nextLine();
                    System.out.print("New email: ");
                    String uemail = sc.nextLine();
                    System.out.print("New age: ");
                    int uage = sc.nextInt();
                    dao.updateStudent(new Student(uid, uname, uemail, uage));
                    break;
                case 5:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    dao.deleteStudent(did);
                    break;
                case 0:
                    System.out.println(" Exiting...");
                    break;
                default:
                    System.out.println(" Invalid choice");
            }
        } while (choice != 0);
        sc.close();
    }
}
