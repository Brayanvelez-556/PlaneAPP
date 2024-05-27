package com.example.planeapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.planeapp.databinding.ActivityListBinding


class ListActivity : AppCompatActivity(), ActividadesAdapter.OnItemClickListener {

    var dataSet: MutableList<Actividad> = arrayListOf()
    lateinit var mAdapter: ActividadesAdapter

    lateinit var binding: ActivityListBinding

    var isEditar = false
    var posicion = -1

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAdapter = ActividadesAdapter(this)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = mAdapter

        binding.btnAddEdit.setOnClickListener {
            if(!isEditar) {
                dataSet.add(Actividad(binding.etNombre.text.toString().trim(), binding.etInicio.text.toString().trim(), binding.etFin.text.toString().trim(), binding.etHora.text.toString().trim(), binding.etDescripcion.text.toString().trim()))
                mAdapter.submitList(dataSet)
                mAdapter.notifyDataSetChanged()

                binding.etNombre.setText("Nombre")
                binding.etInicio.setText("Inicio")
                binding.etFin.setText("Fin")
                binding.etHora.setText("Hora")
                binding.etDescripcion.setText("Descripción")
            } else {
                dataSet[posicion].nombre = binding.etNombre.text.toString()
                dataSet[posicion].inicio = binding.etInicio.text.toString()
                dataSet[posicion].fin = binding.etFin.text.toString()
                dataSet[posicion].hora = binding.etHora.text.toString()
                dataSet[posicion].descripcion = binding.etDescripcion.text.toString()
                posicion = -1
                isEditar = false
                mAdapter.submitList(dataSet)
                mAdapter.notifyDataSetChanged()

                binding.etNombre.setText("Nombre")
                binding.etInicio.setText("Inicio")
                binding.etFin.setText("Fin")
                binding.etHora.setText("Hora")
                binding.etDescripcion.setText("Descripción")
            }
        }

        val btnMain: ImageButton = findViewById(R.id.imageButton2)
        btnMain.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }


    }



    override fun onItemEditar(position: Int, item: Actividad) {
        isEditar = true
        posicion = position
        binding.etNombre.setText(item.nombre)
        binding.etInicio.setText(item.inicio)
        binding.etFin.setText(item.fin)
        binding.etHora.setText(item.hora)
        binding.etDescripcion.setText(item.descripcion)
    }

    override fun onItemBorrar(position: Int) {
        dataSet.removeAt(position)
        mAdapter.submitList(dataSet)
        mAdapter.notifyDataSetChanged()
    }
}