package com.example.registrationform

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val information : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val favoritesGenre : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val _errorMesagge: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMesagge

    fun validateEntriesAndPassword(name: String, email: String, password: String, repPassword: String, birthday: String, genre: String, city: String) {

        if ( name.isEmpty() || email.isEmpty() || password.isEmpty()  || repPassword.isEmpty()  || birthday.isEmpty() ) {
            _errorMesagge.value = "Verifique que todos los campos estén llenos"
        }
        else {
            Log.d("verifiacion","Todos están llenos")
            if (password == repPassword) {
                information.value = name + "\n" + email + "\n" + password + "\n" + genre + "\n" + birthday + "\n" + city
            }
            else {
                _errorMesagge.value = "Las contraseñas no son iguales"
            }
        }
    }

    fun ValidateFavGenres(adventure: Boolean, comic: Boolean, fiction:Boolean, love:Boolean, terror:Boolean, suspense:Boolean) {
        var favoritesGenre = ""
        if(adventure) {
            favoritesGenre += "Aventura\n"
        }
        if(comic) {
            favoritesGenre += "Humor\n"
        }
        if(fiction) {
            favoritesGenre += "Ficcion\n"
        }
        if(love) {
            favoritesGenre += "Romance\n"
        }
        if(terror) {
            favoritesGenre += "Terror\n"
        }
        if(suspense) {
            favoritesGenre += "Suspenos\n"
        }
        Log.d("Rev",favoritesGenre)
    }

}