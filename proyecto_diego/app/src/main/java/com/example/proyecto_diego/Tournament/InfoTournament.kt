package com.example.proyecto_diego.Tournament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_diego.Adapter.TorneosAdapter
import com.example.proyecto_diego.Objetos.Torneos
import com.example.proyecto_diego.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InfoTournament : Fragment() {

    private lateinit var btnAdd : FloatingActionButton
    private lateinit var transacion : FragmentTransaction
    private lateinit var mRecyclerView: RecyclerView
    private var listaTorneos: MutableList<Torneos> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.screen_info_tournament,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniValues(view)

    }

    fun iniValues(view: View){
        btnAdd = view.findViewById(R.id.btnAdd)
        loadRecycler(view)

        btnAdd.setOnClickListener {
            openAddTournament()
        }

    }

    /*fun openAddTournament(){
        val addTournament : Fragment = AddTournament()
        transacion = parentFragmentManager.beginTransaction()
        transacion.setCustomAnimations(R.anim.fragment_open_enter,R.anim.fragment_close_exit)
        transacion.replace(R.id.mainFragment,addTournament)
        transacion.addToBackStack(null)
        transacion.commit()
    }*/

    //Funcion temporal
    fun openAddTournament(){
        var torneos: Torneos = Torneos(1,"","",3,5,"Cordillera","Guadalupe","","")
        val addMatch : Fragment = AddMatch(torneos)
        transacion = parentFragmentManager.beginTransaction()
        transacion.setCustomAnimations(R.anim.fragment_open_enter,R.anim.fragment_close_exit)
        transacion.replace(R.id.mainFragment,addMatch)
        transacion.addToBackStack(null)
        transacion.commit()
    }

    fun loadRecycler(view: View){
        mRecyclerView = view.findViewById(R.id.rvTorneos)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)

        llenarLista()

        val torneosAdapter: TorneosAdapter = TorneosAdapter(listaTorneos)
        mRecyclerView.adapter = torneosAdapter

    }

    fun llenarLista(){
        var torneos: Torneos = Torneos(1,"","",3,5,"Cordillera","Guadalupe","0","0")
        var torneos2: Torneos = Torneos(1,"","",3,5,"Cordillera","Guadalupe","2","10")
        var torneos3: Torneos = Torneos(1,"","",3,5,"Cordillera","Guadalupe","100","100")
        listaTorneos.add(torneos)
        listaTorneos.add(torneos2)
        listaTorneos.add(torneos3)
    }




}