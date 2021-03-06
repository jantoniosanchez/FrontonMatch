package com.example.proyecto_diego.Tournament

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.proyecto_diego.Objetos.Torneos
import com.example.proyecto_diego.R
import com.example.proyecto_diego.R.color
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt
import java.lang.NumberFormatException

@Suppress("DEPRECATION")
class AddTournament(): Fragment() {

    private lateinit var txtTiempoSet : EditText
    private lateinit var txtTiempoMuerto : EditText
    private lateinit var txtNumPartidos : EditText
    private lateinit var txtNumSet : EditText
    private lateinit var txtPrimerEquipo : EditText
    private lateinit var txtSegundoEquipo : EditText
    private lateinit var btnCrearLiga : Button



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.screen_add_torneo,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            initValues(view)
        }

    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun initValues(view : View){
        txtTiempoSet = view.findViewById(R.id.txtTiempoSet)
        txtTiempoMuerto = view.findViewById(R.id.txtTiempoMuerto)
        txtNumPartidos = view.findViewById(R.id.txtNumPartidos)
        txtNumSet = view.findViewById(R.id.txtNumSet)
        txtPrimerEquipo = view.findViewById(R.id.txtPrimerEquipo)
        txtSegundoEquipo = view.findViewById(R.id.txtSegundoEquipo)
        btnCrearLiga = view.findViewById(R.id.btnCrearLiga)

        btnCrearLiga.setOnClickListener {

            if(cheKDates() == true){
                var torneos : Torneos = Torneos(1,txtTiempoSet.text.toString(),txtTiempoMuerto.text.toString(), parseInt(txtNumPartidos.text.toString()),
                        parseInt(txtNumSet.text.toString()),txtPrimerEquipo.text.toString(),txtSegundoEquipo.text.toString(),"","")
                println(torneos._numeroSet)
            }
        }


    }



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun cheKDates() : Boolean{

        var estado = true

        if(transformTime(txtTiempoSet.text.toString()) == 0.0){
            txtTiempoSet.setHintTextColor(resources.getColor(R.color.red))
            txtTiempoSet.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, color.red) })
            estado = false
        }else{
            txtTiempoSet.setHintTextColor(resources.getColor(R.color.white))
            txtTiempoSet.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, color.white) })
        }

        if(transformTime(txtTiempoMuerto.text.toString()) == 0.0){
            txtTiempoMuerto.setHintTextColor(resources.getColor(R.color.red))
            txtTiempoMuerto.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, color.red) })
            estado = false
        }else{
            txtTiempoMuerto.setHintTextColor(resources.getColor(R.color.white))
            txtTiempoMuerto.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, color.white) })
        }

        if(txtNumPartidos.text.toString().equals("") || transformTextToNumber(txtNumPartidos.text.toString())<=0){
            txtNumPartidos.setHintTextColor(resources.getColor(R.color.red))
            txtNumPartidos.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, color.red) })
            estado = false
        }else{
            txtNumPartidos.setHintTextColor(resources.getColor(R.color.white))
            txtNumPartidos.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, color.white) })
        }

        if(txtNumSet.text.toString().equals("") || transformTextToNumber(txtNumSet.text.toString())<=0){
            txtNumSet.setHintTextColor(resources.getColor(R.color.red))
            txtNumSet.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, color.red) })
            estado = false
        }else{
            txtNumSet.setHintTextColor(resources.getColor(R.color.white))
            txtNumSet.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, color.white) })
        }

        if(txtPrimerEquipo.text.toString().equals("")){
            txtPrimerEquipo.setHintTextColor(resources.getColor(R.color.red))
            txtPrimerEquipo.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, color.red) })
            estado = false
        }else{
            txtPrimerEquipo.setHintTextColor(resources.getColor(R.color.white))
            txtPrimerEquipo.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, color.white) })
        }

        if(txtSegundoEquipo.text.toString().equals("")){
            txtSegundoEquipo.setHintTextColor(resources.getColor(R.color.red))
            txtSegundoEquipo.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, color.red) })
            estado = false
        }else{
            txtSegundoEquipo.setHintTextColor(resources.getColor(R.color.white))
            txtSegundoEquipo.setBackgroundTintList(activity?.let { ContextCompat.getColorStateList(it.applicationContext, color.white) })
        }



        return estado
    }



    fun transformTime(tiempo : String) : Double{
        val tiempoSplit = tiempo.split(":").toTypedArray()

        try {
            var minutos = parseDouble(tiempoSplit[0])
            var segundos = parseDouble(tiempoSplit[1])
            return minutos + (segundos/60)
        }catch (e: NumberFormatException){
            println("Error")
        }
        return 0.0
    }

    fun transformTextToNumber(texto : String) : Int{
        return parseInt(texto)
    }


}