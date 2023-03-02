package ir.dunijet.spring_boot.controller

import com.google.gson.Gson
import ir.dunijet.spring_boot.model.Student
import ir.dunijet.spring_boot.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class StudentController {
    lateinit var studentRepository: StudentRepository

    @Autowired
    fun set_student_repository(studentRepo: StudentRepository) {
        studentRepository = studentRepo
    }

    @GetMapping("/student")
    fun getAllStudents(): ResponseEntity<MutableIterable<Student>> {

        val dataFromDatabase = studentRepository.findAll()
        return ResponseEntity.ok(dataFromDatabase)

    }

    @PostMapping("/student")
    fun insertStudent(@RequestBody data: String) {

        val gson = Gson()
        val newStudent = gson.fromJson<Student>(data, Student::class.java)

        studentRepository.save(newStudent)

    }


    @PutMapping("/student/updating{name}")
    fun updateStudent(
            @PathVariable("name") name: String,
            @RequestBody data: String
    ) {

        val gson = Gson()
        val newStudent: Student = gson.fromJson(data, Student::class.java)

        studentRepository.save(newStudent)

        System.out.println(name)

    }


    @DeleteMapping("/student/deleting{name}")
    fun deleteStudent(@PathVariable("name") name: String) {
        studentRepository.deleteById(name)
    }


}