package com.example.proyecto_diego.Tournament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.proyecto_diego.Adapter.TorneosAdapter
import com.example.proyecto_diego.Objetos.Torneos
import com.example.proyecto_diego.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray

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

        getTournament(view)

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
        println(listaTorneos.size)
        val torneosAdapter: TorneosAdapter = TorneosAdapter(listaTorneos)
        mRecyclerView.adapter = torneosAdapter

    }

    fun getTournament(view: View){
        listaTorneos.clear()
        val url = "http:192.168.0.5/Proyecto_Diego/web_service.php"
        val queue = Volley.newRequestQueue(getContext())
        val request = object : StringRequest(Method.POST, url, { response ->
            var jsonArray: JSONArray = JSONArray(response)

            for(i in 0 until jsonArray.length()){
                val id = jsonArray.getJSONObject(0).get("PK_ID").toString().toInt()
                val tiempoSet = jsonArray.getJSONObject(0).get("FK_USUARIO").toString()
                val tiempoPartido = jsonArray.getJSONObject(0).get("NUM_TIEMPO_PARTIDO").toString()
                val numeroPartido = jsonArray.getJSONObject(0).get("NUM_TIEMPO_SET").toString().toInt()
                val numeroSet = jsonArray.getJSONObject(0).get("NUM_SET").toString().toString().toInt()
                val primerEquipo = jsonArray.getJSONObject(0).get("DES_PRIMER_EQUIPO").toString()
                val segundoEquipo = jsonArray.getJSONObject(0).get("DES_SEGUNDO_EQUIPO").toString()
                val resultadoPrimerEquipo = "10"
                val resultadoSegundoEquipo = "0"

                var torneos: Torneos = Torneos(id,tiempoSet,tiempoPartido,numeroPartido,numeroSet,primerEquipo,segundoEquipo,resultadoPrimerEquipo,resultadoSegundoEquipo)
                listaTorneos.add(torneos)
            }

            loadRecycler(view)

        }, { error ->
            println(error.message)

        }){
            override fun getParams(): Map<String, String> {
                var paramas: MutableMap<String, String> = HashMap()

                paramas["funcion"] = "3"
                paramas["parametro1"] = "1"

                return paramas
            }
        }
        queue.add(request)

    }

}