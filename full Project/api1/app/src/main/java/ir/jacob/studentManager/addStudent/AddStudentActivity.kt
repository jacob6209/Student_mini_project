package ir.jacob.studentManager.addStudent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ir.jacob.studentManager.model.Student
import ir.jacob.studentManager.databinding.ActivityMain2Binding
import ir.jacob.studentManager.model.MainRepository
import ir.jacob.studentManager.util.asyncRequest
import ir.jacob.studentManager.util.showToast

class AddStudentActivity : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var addStudentViewModel: AddStudentViewModel
    private val compositeDisposable = CompositeDisposable()
    var isInserting = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMain2)
        addStudentViewModel = AddStudentViewModel(MainRepository())

        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.edtFirstName.requestFocus()


        val testMode = intent.getParcelableExtra<Student>("student")
        isInserting = (testMode == null)
        if (!isInserting) {
            logicUpdateStudent()
        }

        binding.btnDone.setOnClickListener {

            if (isInserting) {
                addNewStudent()
            } else {
                updateStudent()
            }

        }


    }
    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun logicUpdateStudent() {
        binding.btnDone.text = "update"

        val dataFromIntent = intent.getParcelableExtra<Student>("student")!!
        binding.edtScore.setText(dataFromIntent.score.toString())
        binding.edtCourse.setText(dataFromIntent.course)

        val splitedName = dataFromIntent.name.split(" ")
        binding.edtFirstName.setText(splitedName[0])
        binding.edtLastName.setText(splitedName[(splitedName.size - 1)])
    }
    private fun updateStudent() {

        val firstName = binding.edtFirstName.text.toString()
        val lastName = binding.edtLastName.text.toString()
        val score = binding.edtScore.text.toString()
        val course = binding.edtCourse.text.toString()

        if (
            firstName.isNotEmpty() &&
            lastName.isNotEmpty() &&
            course.isNotEmpty() &&
            score.isNotEmpty()
        ) {

            addStudentViewModel
                .updateStudent(
                    Student(firstName + " " + lastName, course, score.toInt())
                )
                .asyncRequest()
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onComplete() {
                        showToast("student updated :)")
                        onBackPressed()
                    }

                    override fun onError(e: Throwable) {
                        showToast("error -> " + e.message ?: "null")
                    }
                })

        } else {
            showToast("لطفا اطلاعات را کامل وارد کنید")
        }
    }
    private fun addNewStudent() {

        val firstName = binding.edtFirstName.text.toString()
        val lastName = binding.edtLastName.text.toString()
        val score = binding.edtScore.text.toString()
        val course = binding.edtCourse.text.toString()

        if (
            firstName.isNotEmpty() &&
            lastName.isNotEmpty() &&
            course.isNotEmpty() &&
            score.isNotEmpty()
        ) {

            addStudentViewModel
                .insertNewStudent(
                    Student(firstName + " " + lastName, course, score.toInt())
                )
                .asyncRequest()
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onComplete() {
                        showToast("student inserted :)")
                        onBackPressed()
                    }

                    override fun onError(e: Throwable) {
                        showToast("error -> " + e.message ?: "null")
                    }

                })


        } else {
            showToast("لطفا اطلاعات را کامل وارد کنید")
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }

        return true
    }

}