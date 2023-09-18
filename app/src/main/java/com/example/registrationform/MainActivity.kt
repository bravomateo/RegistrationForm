package com.example.registrationform

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.CalendarAlerts
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.registrationform.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private  lateinit var registerBinding: ActivityMainBinding
    private  lateinit var mainViewModel: MainViewModel
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

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]


        val informationObserver = Observer<String> {information ->
            registerBinding.infoTextView.text = information
        }
        mainViewModel.information.observe(this, informationObserver)


        val favoritesGenreObserver = Observer<String> {favoritesGenre ->
            registerBinding.infoTextView.text = favoritesGenre
        }
        mainViewModel.information.observe(this, favoritesGenreObserver)


        val errorMesaggeObserver = Observer<String> {errorMesagge ->
            Toast.makeText(this, errorMesagge, Toast.LENGTH_LONG).show()
        }
        mainViewModel.errorMessage.observe(this, errorMesaggeObserver)


        registerBinding.registerButton.setOnClickListener{

            val name        = registerBinding.nameEditText.text.toString()
            val email       = registerBinding.emailEditText.text.toString()
            val password    = registerBinding.passwordEditText.text.toString()
            val repPassword = registerBinding.repPasswordEditText.text.toString()
            var city        = registerBinding.spCiudad.selectedItem.toString()

            var genre = "Masculino"
            if(registerBinding.femaleRadioButton.isChecked) {
                genre = "Femenino"
            }
            val adventure = registerBinding.adventureCheckBox.isChecked
            val comic = registerBinding.adventureCheckBox.isChecked
            val fiction = registerBinding.adventureCheckBox.isChecked
            val love = registerBinding.adventureCheckBox.isChecked
            val terror = registerBinding.adventureCheckBox.isChecked
            val suspense = registerBinding.adventureCheckBox.isChecked

            mainViewModel.ValidateFavGenres(adventure, comic, fiction, love, terror, suspense)

            mainViewModel.validateEntriesAndPassword(name, email, password, repPassword, fechaNacimiento, genre, city)


        }
    }
}