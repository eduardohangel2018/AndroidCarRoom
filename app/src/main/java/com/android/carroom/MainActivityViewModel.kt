package com.android.carroom

import android.app.Application
import androidx.lifecycle.*

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    lateinit private var carRepository : CarRepository
    lateinit var cars : LiveData<List<Car>>
    lateinit var car : MutableLiveData<Car>

    init {

        carRepository = CarRepository(application)
        cars = carRepository.listCarLivedata().asLiveData()
        car = MutableLiveData(Car(0, "", "", "", ""))
    }

    fun getById(id : Int) : Car {

        return carRepository.getById(id)
    }

    fun saveCar(car : Car) {

        return carRepository.saveCar(car)
    }

    fun deleteCar() {

        if (car.value != null) {

            carRepository.deleteCar(car.value!!)
        }
    }
}
// ViewModel sensível ao contexto da aplicação
class MainActivityViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) : T {

        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {

            // Testa se a ViewModel é o contrutor que será usado
            return MainActivityViewModel(application) as T
        }
        // Dispara exceção
        throw IllegalArgumentException("View Model Incompatível")
    }
}
