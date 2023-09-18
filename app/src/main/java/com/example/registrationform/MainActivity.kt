package com.example.registrationform

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.CalendarAlerts
import android.widget.Toast
import com.example.registrationform.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private  lateinit var registerBinding: ActivityMainBinding
    private var fechaNacimiento: String = ""
    private var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        registerBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = registerBinding.root
        setContentView(view)

        val dataSetListener = DatePickerDialog.OnDateSetListener {view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "MM/dd/yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                fechaNacimiento = sdf.format(cal.time).toString()
                registerBinding.dateBirthButton.setText(fechaNacimiento)

            }

        registerBinding.dateBirthButton.setOnClickListener {
            DatePickerDialog(
                this,
                dataSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        registerBinding.registerButton.setOnClickListener{

            // nombre
            val name = registerBinding.nameEditText.text.toString()
            // email
            val email = registerBinding.emailEditText.text.toString()
            // contraseña
            val password = registerBinding.passwordEditText.text.toString()
            // repetir contraseña
            val repPassword = registerBinding.repPasswordEditText.text.toString()

            // Genero
            var genre = "Masculino"
            if(registerBinding.femaleRadioButton.isChecked) {
                genre = "Femenino"
            }

            // Generos de Pelicula
            var favoritesGenre = ""
            if(registerBinding.adventureCheckBox.isChecked) {
                favoritesGenre += "Aventura\n"
            }
            if(registerBinding.comicCheckBox.isChecked) {
                favoritesGenre += "Humor\n"
            }
            if(registerBinding.fictionCheckBox.isChecked) {
                favoritesGenre += "Ficcion\n"
            }
            if(registerBinding.loveCheckBox.isChecked) {
                favoritesGenre += "Romance\n"
            }
            if(registerBinding.terrorCheckBox.isChecked) {
                favoritesGenre += "Terror\n"
            }
            if(registerBinding.suspenseCheckBox.isChecked) {
                favoritesGenre += "Suspenos\n"
            }

            // ciudad
            var city = registerBinding.spCiudad.selectedItem.toString()

            var information = name + "\n" + email

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || repPassword.isEmpty() || fechaNacimiento.isEmpty()) {
                Toast.makeText(this, "Verifique que todos los campos estén llenos", Toast.LENGTH_LONG).show()
            }
            else {
                if (password == repPassword) {
                    information +=  "\n" +  password + "\n" + genre + "\n" + favoritesGenre  + fechaNacimiento + "\n" + city
                    registerBinding.infoTextView.text = information
                }
                else {
                    Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}