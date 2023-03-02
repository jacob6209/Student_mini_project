package ir.jacob.studentManager.addStudent

import io.reactivex.Completable
import ir.jacob.studentManager.model.MainRepository
import ir.jacob.studentManager.model.Student

class AddStudentViewModel
    (private val mainRepository: MainRepository) {

    fun insertNewStudent(student: Student) :Completable {
        return mainRepository.insertStudent(student)
    }

    fun updateStudent(student: Student) :Completable {
        return mainRepository.updateStudent(student)
    }

}