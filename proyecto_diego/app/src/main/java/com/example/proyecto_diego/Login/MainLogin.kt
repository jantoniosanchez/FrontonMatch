package com.example.proyecto_diego.Login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.proyecto_diego.R

class MainLogin : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragmetSingup = LogIn()

        supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_open_enter,R.anim.fragment_fade_exit).add(R.id.mainFragment,fragmetSingup).commit()

    }
}