package ir.dunijet.spring_boot.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Student(

        @Id
        var name: String,

        val course: String,
        var score: Int

) {
        constructor() :this("" , "" , -1)
}