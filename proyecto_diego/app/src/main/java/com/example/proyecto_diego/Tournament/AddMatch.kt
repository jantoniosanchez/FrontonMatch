package com.example.proyecto_diego.Tournament

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.proyecto_diego.Objetos.Partidos
import com.example.proyecto_diego.Objetos.Torneos
import com.example.proyecto_diego.R
import java.lang.Integer.parseInt

class AddMatch(_torneos: Torneos): Fragment() {


    private lateinit var txtPartido: TextView
    private lateinit var txtPrimerEquipo: TextView
    private lateinit var txtJugadorUno: EditText
    private lateinit var txtJugadorDos: EditText
    private lateinit var txtSegundoEquipo: TextView
    private lateinit var txtJugadorTres: EditText
    private lateinit var txtJugadorCuatro: EditText
    private lateinit var btnAtras: Button
    private lateinit var btnSiguiente: Button
    private var torneos: Torneos = _torneos
    private var contador: Int = 1
    private val listMatch: MutableList<Partidos> = ArrayList()
    private lateinit var transacion : FragmentTransaction

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.screen_add_match,container,false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniValues(view)
        nameTeams()
        numberMatch()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun iniValues(view: View){
        txtPartido = view.findViewById(R.id.txtPartido)
        txtPrimerEquipo = view.findViewById(R.id.txtPrimerEquipo)
        txtJugadorUno = view.findViewById(R.id.txtJugadorUno)
        txtJugadorDos = view.findViewById(R.id.txtJugadorDos)
        txtSegundoEquipo = view.findViewById(R.id.txtSegundoEquipo)
        txtJugadorTres = view.findViewById(R.id.txtJugadorTres)
        txtJugadorCuatro = view.findViewById(R.id.txtJugadorCuatro)
        btnAtras = view.findViewById(R.id.btnAtras)
        btnSiguiente = view.findViewById(R.id.btnSiguiente)
        listMatch.add(Partidos("","","","",1))
        listMatch.add(Partidos("","","","",2))
        listMatch.add(Partidos("","","","",3))

        btnAtras.setOnClickListener {
            contador--
            showBackPlayer()
            numberMatch()
            if(contador == 1){
                btnAtras.visibility = View.INVISIBLE
            }
        }

        btnSiguiente.setOnClickListener {
            btnNext()
        }

    }

    fun nameTeams(){
        txtPrimerEquipo.text = "Equipo: " + torneos._primerEquipo
        txtSegundoEquipo.text = "Equipo: " + torneos._segundoEquipo

    }

    fun numberMatch(){
        txtPartido.text = "Partido: " + (contador)
    }



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun checkPlayer() : Boolean{
        var check = true

        if(txtJugadorUno.text.toString().equals("")){
            txtJugadorUno.setHintTextColor(resources.getColor(R.color.red))
            txtJugadorUno.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, R.color.red) })
            check = false
        }else{
            txtJugadorUno.setHintTextColor(resources.getColor(R.color.white))
            txtJugadorUno.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, R.color.white) })
        }

        if(txtJugadorDos.text.toString().equals("")){
            txtJugadorDos.setHintTextColor(resources.getColor(R.color.red))
            txtJugadorDos.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, R.color.red) })
            check = false
        }else{
            txtJugadorDos.setHintTextColor(resources.getColor(R.color.white))
            txtJugadorDos.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, R.color.white) })
        }

        if(txtJugadorTres.text.toString().equals("")){
            txtJugadorTres.setHintTextColor(resources.getColor(R.color.red))
            txtJugadorTres.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, R.color.red) })
            check = false
        }else{
            txtJugadorTres.setHintTextColor(resources.getColor(R.color.white))
            txtJugadorTres.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, R.color.white) })
        }

        if(txtJugadorCuatro.text.toString().equals("")){
            txtJugadorCuatro.setHintTextColor(resources.getColor(R.color.red))
            txtJugadorCuatro.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, R.color.red) })
            check = false
        }else{
            txtJugadorCuatro.setHintTextColor(resources.getColor(R.color.white))
            txtJugadorCuatro.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, R.color.white) })
        }

        return check
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun btnNext(){
        if(checkPlayer() && contador<torneos._numeroPartidos){
            listMatch[contador-1] = addMatch(contador+1)
            contador++
            showNextPlayer()
            numberMatch()
            btnAtras.visibility = View.VISIBLE
            if(contador == torneos._numeroPartidos){
                btnSiguiente.text = "Finalizar"
            }else{
                btnSiguiente.text = "Siguiente"
            }
        }else if(checkPlayer() && contador==torneos._numeroPartidos){
            listMatch[contador-1] = addMatch(contador+1)
            showDialog()
        }
    }

    fun addMatch(contador: Int): Partidos{
        var partido: Partidos = Partidos(txtJugadorUno.text.toString(),txtJugadorDos.text.toString(),
                txtJugadorTres.text.toString(),txtJugadorCuatro.text.toString(),contador)

        return partido
    }

    fun showBackPlayer(){
        txtJugadorUno.setText(listMatch.get(contador-1)._primerJugador)
        txtJugadorDos.setText(listMatch.get(contador-1)._segundoJugador)
        txtJugadorTres.setText(listMatch.get(contador-1)._tercerJugador)
        txtJugadorCuatro.setText(listMatch.get(contador-1)._cuartoJugador)
    }

    fun showNextPlayer(){

            txtJugadorUno.setText(listMatch.get(contador-1)._primerJugador)
            txtJugadorDos.setText(listMatch.get(contador-1)._segundoJugador)
            txtJugadorTres.setText(listMatch.get(contador-1)._tercerJugador)
            txtJugadorCuatro.setText(listMatch.get(contador-1)._cuartoJugador)


    }

    //Falta mensage para aceptar y que se cambie el Boton a finalizar en el ultimo eso, si acepta se crea todo en la BBDD funcion en Volley
    fun showDialog(){
        val dialog = AlertDialog.Builder(requireActivity())
                .setTitle("Crear Torneo")
                .setMessage("¿Quiere crear este Torneo?")
                .setNegativeButton("Atras") { view, _ ->
                    view.dismiss()
                }
                .setPositiveButton("Finalizar") { view, _ ->
                    createTournament()
                }
                .setCancelable(false)
                .create()

        dialog.show()
    }

    fun createTournament(){
        val url = "http:192.168.0.5/Proyecto_Diego/web_service.php"
        val queue = Volley.newRequestQueue(getContext())
        val request = object : StringRequest(Method.POST, url, { response ->

            if(response.equals("OK")){
                if(createMatch()){
                    showDialogAccept()
                }
            }else{
                println("Error")
            }

        }, { error ->
            println(error.message)

        }){
            override fun getParams(): Map<String, String> {
                var paramas: MutableMap<String, String> = HashMap()

                paramas["funcion"] = "1"
                paramas["parametro1"] = "1"
                paramas["parametro2"] = torneos._tiempoPartido
                paramas["parametro3"] = torneos._tiempoSet
                paramas["parametro4"] = torneos._numeroSet.toString()
                paramas["parametro5"] = torneos._primerEquipo
                paramas["parametro6"] = torneos._segundoEquipo

                return paramas
            }
        }
        queue.add(request)
    }

    fun createMatch(): Boolean{
        var estado = true
        for(match in listMatch){
            val url = "http:192.168.0.5/Proyecto_Diego/web_service.php"
            val queue = Volley.newRequestQueue(getContext())
            val request = object : StringRequest(Method.POST, url, { response ->

                if(response.equals("OK")){
                    println("Hola")

                }else{
                    println("Error")
                    estado = false
                }

            }, { error ->
                println(error.message)

            }){
                override fun getParams(): Map<String, String> {
                    var paramas: MutableMap<String, String> = HashMap()

                    paramas["funcion"] = "2"
                    paramas["parametro1"] = "2"
                    paramas["parametro2"] = match._primerJugador
                    paramas["parametro3"] = match._segundoJugador
                    paramas["parametro4"] = match._tercerJugador
                    paramas["parametro5"] = match._cuartoJugador

                    return paramas
                }
            }
            queue.add(request)
        }

        return estado
    }

    fun showDialogAccept(){
        val dialog = AlertDialog.Builder(requireActivity())
                .setTitle("Torneo creado")
                .setMessage("El torneo se ha creado correctamente")

                .setPositiveButton("Aceptar") { view, _ ->
                    val infoTournament : Fragment = InfoTournament()
                    transacion = parentFragmentManager.beginTransaction()
                    transacion.setCustomAnimations(R.anim.fragment_open_enter,R.anim.fragment_close_exit)
                    transacion.replace(R.id.mainFragment,infoTournament)
                    transacion.commit()
                }
                .setCancelable(false)
                .create()

        dialog.show()
    }



}