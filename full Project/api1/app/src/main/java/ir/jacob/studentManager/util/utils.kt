package ir.jacob.studentManager.util

import com.google.gson.JsonObject
import ir.jacob.studentManager.model.Student

fun studentToJsonObject(student: Student) :JsonObject {

    val jsonObject = JsonObject()
    jsonObject.addProperty("name", student.name)
    jsonObject.addProperty("course", student.course)
    jsonObject.addProperty("score", student.score)
    return jsonObject

}