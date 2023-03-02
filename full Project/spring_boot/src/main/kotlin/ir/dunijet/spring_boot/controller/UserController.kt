package ir.dunijet.spring_boot.controller

import ir.dunijet.spring_boot.model.Student
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class UserController {

    @RequestMapping(value = ["/login"] , method = [RequestMethod.GET])
    fun openLoginPage() :String {

        return "login"
    }

}