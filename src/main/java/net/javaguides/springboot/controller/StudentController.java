package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    //http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent(){
        Student student = new Student(
                1,
                "Mona",
                "Xu"
        );
        return student;
    }
    //http://localhost:8080/students
    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "MONA","XU"));
        students.add(new Student(2, "DAVID","XUAN"));
        students.add(new Student(3, "WW","JIU"));
        return students;
    }

    // SpringBoot Rest API with Path Variable
    //{id} URI template variable
    //http://localhost:8080/students/1/Mona/Xu
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        return new Student(studentId, firstName,lastName);
    }

    //SpringBoot REST API with Request Param
    //id=1  : Query Parameter
    //http://localhost:8080/students/query?id=1&firstName=MONA&lastName=XU
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return new Student(id, firstName,lastName);
    }

    //SpringBoot REST API that handles HTTP POST Request - creating new resource
    //@PostMapping and @RequestBody
    //http://localhost:8080/student/create
    @PostMapping("student/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        //@RequestBody is responsible for retrieving the HTTP request body
        // and automatically converting it to the Java object
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //SpringBoot REST API that handles HTTP PUT Request - updating existing resource
    //http://localhost:8080/students/3/update
    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // SpringBoot REST API that handles HTTP DELETE Request - deleting the existing resource
    //http://localhost:8080/students/3/delete
    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId){
        return "Student deleted successfully!";
    }






}
