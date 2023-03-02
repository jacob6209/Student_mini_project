package ir.dunijet.spring_boot.repository

import ir.dunijet.spring_boot.model.Student
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : CrudRepository<Student , String> {


}