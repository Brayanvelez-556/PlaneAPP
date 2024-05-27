package com.example.planeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class ActividadesAdapter(private val itemClickListener: OnItemClickListener): ListAdapter<Actividad, ActividadesAdapter.ViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<Actividad>() {
        override fun areItemsTheSame(oldItem: Actividad, newItem: Actividad): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Actividad, newItem: Actividad): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_actividades, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val tvNombre = itemView.findViewById(R.id.tvNombre) as TextView
        private val tvInicio = itemView.findViewById(R.id.tvInicio) as TextView
        private val tvFin = itemView.findViewById(R.id.tvFin) as TextView
        private val tvHora = itemView.findViewById(R.id.tvHora) as TextView
        private val tvDescripcion = itemView.findViewById(R.id.tvDescripcion) as TextView
        private val btnEditar = itemView.findViewById(R.id.btnEdit) as Button
        private val btnBorrar = itemView.findViewById(R.id.btnDelete) as Button


        fun bind(item: Actividad, clickListener: OnItemClickListener) {
            tvNombre.text = item.nombre
            tvInicio.text = item.inicio
            tvFin.text = item.fin
            tvHora.text = item.hora
            tvDescripcion.text = item.descripcion

            btnEditar.setOnClickListener { clickListener.onItemEditar(adapterPosition, item) }
            btnBorrar.setOnClickListener { clickListener.onItemBorrar(adapterPosition) }
        }
    }

    interface OnItemClickListener {
        fun onItemEditar(position: Int, item: Actividad)
        fun onItemBorrar(position: Int)
    }

}