package dao;

import java.util.List;

import model.Student;

public interface StudentDAO {
    void addStudent(Student student);

    Student getStudentById(int id);

    List<Student> getAllStudents();

    void updateStudent(Student student);

    void deleteStudent(int id);
}
