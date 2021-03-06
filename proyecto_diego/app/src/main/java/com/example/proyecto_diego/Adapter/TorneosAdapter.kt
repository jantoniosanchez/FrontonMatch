package com.example.proyecto_diego.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_diego.Objetos.Torneos
import com.example.proyecto_diego.R

class TorneosAdapter(private var listaTorneos: MutableList<Torneos>) : RecyclerView.Adapter<TorneosAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtPrimerEquipo: TextView
        val txtSegundoEquipo: TextView
        val txtPrimerResultado: TextView
        val txtSegundoResultado: TextView


        init {
            txtPrimerEquipo = view.findViewById(R.id.txtPrimerEquipo)
            txtSegundoEquipo = view.findViewById(R.id.txtSegundoEquipo)
            txtPrimerResultado = view.findViewById(R.id.txtPrimerResultado)
            txtSegundoResultado = view.findViewById(R.id.txtSegundoResultado)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.cell_torneos, viewGroup, false)

        return ViewHolder(view)
    }



    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.txtPrimerEquipo.text = listaTorneos[position]._primerEquipo
        viewHolder.txtSegundoEquipo.text = listaTorneos[position]._segundoEquipo
        viewHolder.txtPrimerResultado.text = listaTorneos[position]._resultadoPrimerEquipo
        viewHolder.txtSegundoResultado.text = listaTorneos[position]._resultadoSegundoEquipo
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = listaTorneos.size

}