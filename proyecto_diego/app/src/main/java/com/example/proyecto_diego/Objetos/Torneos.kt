package com.example.proyecto_diego.Objetos

import org.json.JSONObject


class Torneos(var _id:Int ,var _tiempoSet: String, var _tiempoPartido: String, var _numeroPartidos: Int,
              var _numeroSet: Int, var _primerEquipo: String, var _segundoEquipo: String
              , var _resultadoPrimerEquipo: String , var _resultadoSegundoEquipo: String){

    private var tiempoSet = _tiempoSet
    private var tiempoPartido = _tiempoPartido
    private var numeroPartidos = _numeroPartidos
    private var numeroSet = _numeroSet
    private var primerEquipo = _primerEquipo
    private var segundoEquipo = _segundoEquipo


    init {

    }


}