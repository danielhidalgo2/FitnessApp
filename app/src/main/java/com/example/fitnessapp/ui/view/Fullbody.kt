package com.example.fitnessapp.ui.view


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fitnessapp.databinding.ActivityFullbodyBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*


lateinit var  idejercicio:String

var formatter = SimpleDateFormat("HH:mm:ss")
var curDate = Date(System.currentTimeMillis())
// Obtener la hora actual
var str = formatter.format(curDate)

class Fullbody : AppCompatActivity() {
    var falso=1
    private val db = FirebaseFirestore.getInstance()
    private lateinit var database: DatabaseReference
    private lateinit var  binding: ActivityFullbodyBinding
    lateinit var date: String



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= ActivityFullbodyBinding.inflate(layoutInflater)
        setContentView(binding.root)


       Glide.with(this).load("https://www.spotebi.com/wp-content/uploads/2015/08/jump-squat-exercise-illustration.gif").into(binding.imagenejercicio)
        binding.comenzar.setOnClickListener {
            object : CountDownTimer(1000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    binding.cuentaatras.setText("" + millisUntilFinished / 1000+" seg")
                }

                override fun onFinish() {
                    binding.cuentaatras.setText("done!")
                    binding.siguiente.visibility= View.VISIBLE
                }
            }.start()
            binding.comenzar.visibility= View.INVISIBLE
        }

        binding.siguiente.setOnClickListener {

            if (falso==1) {
                Glide.with(this).load("https://i.gifer.com/T8TA.gif").into(binding.imagenejercicio)
                object : CountDownTimer(1000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        binding.cuentaatras.setText(""+ millisUntilFinished / 1000+" seg")
                        binding.siguiente.visibility = View.INVISIBLE
                    }

                    override fun onFinish() {
                        binding.cuentaatras.setText("done!")
                        binding.siguiente.visibility = View.VISIBLE
                        falso = 2
                    }
                }.start()
            }
            if (falso==2){
                Glide.with(this).load("https://media.vogue.es/photos/5eb13d7f7b65139871179b7b/master/w_1600,c_limit/tabla-de-ejercicios2-4.jpg").into(binding.imagenejercicio)
                object : CountDownTimer(1000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        binding.cuentaatras.setText(""+ millisUntilFinished / 1000+" seg")
                        binding.siguiente.visibility= View.INVISIBLE
                    }

                    override fun onFinish() {
                        binding.cuentaatras.setText("done!")
                        binding.siguiente.visibility= View.VISIBLE
                        falso=3
                    }
                }.start()
            }
            if (falso==3){
                Glide.with(this).load("https://4.bp.blogspot.com/-NJSk3weT0Ds/WoikpGx2ziI/AAAAAAAAbFE/sAix6msp-QMkeN6BryRDX3aBWnb52TPzwCLcBGAs/s1600/step%2Bby%2Bstep%2Bexercises%2Bpuente.gif").into(binding.imagenejercicio)
                object : CountDownTimer(1000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        binding.cuentaatras.setText("" + millisUntilFinished / 1000+" seg")
                        binding.siguiente.visibility= View.INVISIBLE
                    }

                    override fun onFinish() {
                        binding.cuentaatras.setText("done!")
                        binding.siguiente.visibility= View.VISIBLE
                        falso=4
                    }
                }.start()
            }
            if (falso==4){
                Glide.with(this).load("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/workouts/2016/03/highkneerun-1457044203.gif?resize=768:*").into(binding.imagenejercicio)
                object : CountDownTimer(1000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        binding.cuentaatras.setText(""+ millisUntilFinished / 1000+" seg")
                        binding.siguiente.visibility= View.INVISIBLE
                    }

                    override fun onFinish() {
                        binding.cuentaatras.setText("done!")
                        binding.siguiente.visibility= View.VISIBLE
                        falso=5
                    }
                }.start()
            }
            if (falso==5){
                Glide.with(this).load("https://2.bp.blogspot.com/-89XQn-i9jkM/WoslsnsiH_I/AAAAAAAAbIA/AJpIZMuPEFQYr1qhm4k1W74SCvpH4QafgCLcBGAs/s1600/step%2Bby%2Bstep%2Bexercises%2Bflexiones.gif").into(binding.imagenejercicio)
                object : CountDownTimer(1000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        binding.cuentaatras.setText("" + millisUntilFinished / 1000 +" seg")
                        binding.siguiente.visibility= View.INVISIBLE
                    }

                    override fun onFinish() {
                        binding.cuentaatras.setText("done!")
                        binding.siguiente.visibility= View.VISIBLE
                        falso=6
                    }
                }.start()
            }
            if (falso==6){
                Glide.with(this).load("https://www.spotebi.com/wp-content/uploads/2014/10/jumping-jacks-exercise-illustration.gif").into(binding.imagenejercicio)
                object : CountDownTimer(1000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        binding.cuentaatras.setText("" + millisUntilFinished / 1000+" seg")
                        binding.siguiente.visibility= View.INVISIBLE
                    }

                    override fun onFinish() {
                        binding.cuentaatras.setText("done!")
                        binding.registrarejercicio.visibility= View.VISIBLE

                    }



                }.start()



                date= SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

                Log.i(TAG_LOGS,date)


            }


        }

        binding.registrarejercicio.setOnClickListener {
            val numero=Random().ints()

            idejercicio=numero.toString()
            val calorias ="400"


            db.collection("Users").document(email.toString()).collection("EjerciciosRegistro").document(
                idejercicio).set(
                hashMapOf("Titulo" to binding.tituloejercicio.text.toString(),"Fecha" to date+" a las "+ str, "Pasos" to calorias+" Kcl")

            )

            val intent =Intent(this,Ejercicios::class.java)
            startActivity(intent)
            finish()


        }


    }
    override fun onBackPressed() {
        super.onBackPressed()



    }


}