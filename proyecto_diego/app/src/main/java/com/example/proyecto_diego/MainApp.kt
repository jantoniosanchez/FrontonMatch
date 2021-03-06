package com.example.proyecto_diego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Request.*
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.proyecto_diego.Login.MainLogin
import com.example.proyecto_diego.Login.SingUp
import com.example.proyecto_diego.Tournament.AddTournament
import com.example.proyecto_diego.Tournament.InfoTournament
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject

class MainApp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_app)

        val infoTournament = InfoTournament()
        supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_open_enter,R.anim.fragment_fade_exit).add(R.id.mainFragment,infoTournament).commit()

    }



}