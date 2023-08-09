package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
// @RequestMapping: base URL for all the method
@RequestMapping("students")
public class StudentController {
    //http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                "Mona",
                "Xu"
        );
        //same ok status
        //return new ResponseEntity<>(student, HttpStatus.OK);
        //return ResponseEntity.ok(student);
        //passing a header using ResponseEntity
        return ResponseEntity.ok().
                header("custom-header","Mona").body(student);
    }
    //http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "MONA","XU"));
        students.add(new Student(2, "DAVID","XUAN"));
        students.add(new Student(3, "WW","JIU"));
        return ResponseEntity.ok(students);
    }

    // SpringBoot Rest API with Path Variable
    //{id} URI template variable
    //http://localhost:8080/students/1/Mona/Xu
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        Student student = new Student(studentId, firstName,lastName);
        return ResponseEntity.ok(student);
    }

    //SpringBoot REST API with Request Param
    //id=1  : Query Parameter
    //http://localhost:8080/students/query?id=1&firstName=MONA&lastName=XU
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        Student student = new Student(id, firstName,lastName);
        return ResponseEntity.ok(student);
    }

    //SpringBoot REST API that handles HTTP POST Request - creating new resource
    //@PostMapping and @RequestBody
    //http://localhost:8080/student/create
    @PostMapping("student/create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        //@RequestBody is responsible for retrieving the HTTP request body
        // and automatically converting it to the Java object
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    //SpringBoot REST API that handles HTTP PUT Request - updating existing resource
    //http://localhost:8080/students/3/update
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    // SpringBoot REST API that handles HTTP DELETE Request - deleting the existing resource
    //http://localhost:8080/students/3/delete
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        return ResponseEntity.ok("Student deleted successfully!");
    }






}
