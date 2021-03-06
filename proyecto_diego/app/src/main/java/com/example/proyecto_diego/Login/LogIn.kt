package com.example.proyecto_diego.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.android.volley.AuthFailureError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.proyecto_diego.MainApp
import com.example.proyecto_diego.R

class LogIn() : Fragment() {

    private lateinit var etUser : EditText
    private lateinit var etPasswrd : EditText
    private lateinit var btnLogin : Button
    private lateinit var btnSignIn: Button
    private lateinit var txtMsgError: TextView
    private lateinit var transacion : FragmentTransaction


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.screen_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initValues(view)
    }

    fun initValues(v : View ){
        etUser = v.findViewById(R.id.txtUser)
        etPasswrd = v.findViewById(R.id.txtpasswrd)
        btnLogin = v.findViewById(R.id.btnLogin)
        btnSignIn = v.findViewById(R.id.btnSignIn)
        txtMsgError = v.findViewById(R.id.txtMsgError)


        //Boton Login
        btnLogin.setOnClickListener{
            checkUsers(getValuesLogin())
        }

        //Boton Sigin
        btnSignIn.setOnClickListener{
            txtMsgError.visibility = View.INVISIBLE
            println("Registrase")
            openSingUp()

        }

    }


    //Función que devuelve el valor del usuario y la contraseña
    fun getValuesLogin() : Array<String> {
        return arrayOf<String>(etUser.text.toString(), etPasswrd.text.toString())
    }

    //Función para comprobar si un usuario existe o no
    fun checkUsers(valuesLogin: Array<String>): Boolean{

        var chekConection = false
        val url = "http:192.168.0.5/Proyecto_Diego/login_test.php"
        val queue = Volley.newRequestQueue(getContext())
        val request = object : StringRequest(Method.POST,url, { response ->
            if(!response.isEmpty()){
                chekConection = true
                openMainActivity()
            }else{
                println("Usuario erroneo")
                txtMsgError.visibility = View.VISIBLE
            }
        }, { error ->
            println(error)
        }){
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                var params: MutableMap<String, String> = HashMap()
                params["user"] = valuesLogin[0]
                params["passwrd"] = valuesLogin[1]
                return params
            }
        }
        queue.add(request)
        return chekConection
    }

    fun openSingUp(){
        val singUp : Fragment = SingUp()
        transacion = parentFragmentManager.beginTransaction()
        transacion.setCustomAnimations(R.anim.fragment_open_enter,R.anim.fragment_close_exit)
        transacion.replace(R.id.mainFragment,singUp)
        transacion.addToBackStack(null)
        transacion.commit()
    }

    fun openMainActivity(){
        val intent = Intent(context, MainApp::class.java)
        startActivity(intent)

    }

}