package com.android.carroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.android.carroom.databinding.ActivityMainBinding
import kotlinx.coroutines.runBlocking
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity(), RecyclerViewItemListener {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel
    lateinit var dao : CarDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Liga a UI Controller ao ViewModel
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, MainActivityViewModelFactory(this.application)).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Define o LayoutManager
        binding.listCars.layoutManager = LinearLayoutManager(this)

        // Cria o Adapter e Associa ao Listener
        val adapter = ListCarAdapter()
        adapter.setRecyclerViewItemListener(this)
        binding.listCars.adapter = adapter
        if (viewModel.cars.value != null) {

            adapter.listCars = viewModel.cars.value!!
        }
        viewModel.cars.observe(this, {

            it.let {
                adapter.listCars = it
            }
        })

        // Funcionalidade dos objetos quando ocorre o clique
        binding.btnSave.setOnClickListener {
            var car = Car(
                    0,
                    binding.textName.text.toString(),
                    binding.textPrice.text.toString(),
                    binding.textYear.text.toString(),
                    binding.textDesc.text.toString()
            )
            viewModel.saveCar(car)
            binding.textName.setText("")
            binding.textPrice.setText("")
            binding.textYear.setText("")
            binding.textDesc.setText("")

        }

        binding.btnDel.setOnClickListener {

            viewModel.deleteCar()
            binding.textName.setText("")
            binding.textPrice.setText("")
            binding.textYear.setText("")
            binding.textDesc.setText("")
        }
    }

    override fun recyclerViewItemClicked(view: View, id: Int) {

        viewModel.car.value = viewModel.getById(id)
    }
}