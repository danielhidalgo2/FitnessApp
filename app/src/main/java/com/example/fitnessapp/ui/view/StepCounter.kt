package com.example.fitnessapp.ui.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.i
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ActivityStepCounter2Binding
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

lateinit var  idejercicio2:String

class StepCounter : AppCompatActivity(), SensorEventListener{
    private lateinit var binding: ActivityStepCounter2Binding
    private val db = FirebaseFirestore.getInstance()
    lateinit var date: String

    private var sensorManager: SensorManager? = null

    // Creating a variable which will give the running status
    // and initially given the boolean value as false
    private var running = false

    // Creating a variable which will counts total steps
    // and it has been given the value of 0 float
    private var totalSteps = 0f

    // Creating a variable  which counts previous total
    // steps and it has also been given the value of 0 float
    private var previousTotalSteps = 0f
    private val ACTIVITY_RECOGNITION_CODE = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityStepCounter2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        verificarpermis()

    }

    override fun onResume() {
        super.onResume()
        running = true

        // Returns the number of steps taken by the user since the last reboot while activated
        // This sensor requires permission android.permission.ACTIVITY_RECOGNITION.
        // So don't forget to add the following permission in AndroidManifest.xml present in manifest folder of the app.
        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)


        if (stepSensor == null) {
            // This will give a toast message to the user if there is no sensor in the device
            Toast.makeText(this, "No se ha detectado ningun sensor", Toast.LENGTH_SHORT).show()
        } else {
            // Rate suitable for the user interface
            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }

    }

    override fun onSensorChanged(event: SensorEvent?) {

        // Calling the TextView that we made in activity_main.xml
        // by the id given to that TextView


        if (running) {
            totalSteps = event!!.values[0]

            // Current steps are calculated by taking the difference of total steps
            // and previous steps
            val currentSteps = totalSteps.toInt() - previousTotalSteps.toInt()

            // It will show the current steps to the user
            binding.contador.text = ("$currentSteps")

            binding.circularProgressBar.apply {
                setProgressWithAnimation(currentSteps.toFloat())
            }
        }
    }

    fun resetSteps() {

        binding.contador.setOnClickListener {
            // This will give a toast message if the user want to reset the steps
            Toast.makeText(this, "Long tap to reset steps", Toast.LENGTH_SHORT).show()
        }

        binding.contador.setOnLongClickListener {

            previousTotalSteps = totalSteps

            // When the user will click long tap on the screen,
            // the steps will be reset to 0
            binding.contador.text = 0.toString()

            // This will save the data
            saveData()

            true
        }
    }

    private fun saveData() {

        // Shared Preferences will allow us to save
        // and retrieve data in the form of key,value pair.
        // In this function we will save data
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.putFloat("key1", previousTotalSteps)
        editor.apply()
    }

    private fun loadData() {

        // In this function we will retrieve data
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences.getFloat("key1", 0f)

        // Log.d is used for debugging purposes
        Log.d("MainActivity", "$savedNumber")

        previousTotalSteps = savedNumber
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // We do not have to write anything in this function for this app
    }


    private fun verificarpermis() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
            aceptarelpermisousuario()
        } else {
            //los permisos estan aceptados

        }
    }

    private fun aceptarelpermisousuario() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.ACTIVITY_RECOGNITION)) {
            //El usuario ya ha rechazado el permiso anteriormente, debemos informarle que vaya a ajustes.
            val alerta = AlertDialog.Builder(this)
            alerta.setMessage("Los permisos de actividad fisica no han sido aceptados anteriormente.¿Desea activarlo para comenzar a contabilizar los paos?.")
                .setTitle("Alerta")
                .setCancelable(false)//esto es para que clique fuera del popup de alerta
                .setNegativeButton(
                    "Cerrar",
                    DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
                .setNegativeButton(
                    "Confirmar",
                    DialogInterface.OnClickListener { dialog, which ->  ActivityCompat.requestPermissions(this,
                        arrayOf( android.Manifest.permission.ACTIVITY_RECOGNITION),
                        ACTIVITY_RECOGNITION_CODE) })
                .create()
                .show()
        } else {
            //El usuario nunca ha aceptado ni rechazado, así que le pedimos que acepte el permiso.
            ActivityCompat.requestPermissions(this,
                arrayOf( android.Manifest.permission.ACTIVITY_RECOGNITION),
                ACTIVITY_RECOGNITION_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            ACTIVITY_RECOGNITION_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    loadData()
                    resetSteps()


                    // Adding a context of SENSOR_SERVICE aas Sensor Manager
                    sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

                    binding.registrarpasos.setOnClickListener {
                        date= SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                        val numero= Random().ints()

                        idejercicio2=numero.toString()


                        db.collection("Users").document(email.toString()).collection("EjerciciosRegistro").document(
                            idejercicio2).set(
                            hashMapOf("Titulo" to binding.textView.text.toString(),"Fecha" to date+" a las "+ str,"Pasos" to binding.contador.text.toString())

                        )

                        val intent = Intent(this,Ejercicios::class.java)
                        startActivity(intent)

                    }
                } else {
                    //El usuario ha rechazado el permiso, podemos desactivar la funcionalidad o mostrar una vista/diálogo.
                }
                return
            }
            else -> {
                // Este else lo dejamos por si sale un permiso que no teníamos controlado.
            }
        }
    }
}




