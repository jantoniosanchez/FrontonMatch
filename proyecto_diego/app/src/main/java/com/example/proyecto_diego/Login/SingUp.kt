package com.example.proyecto_diego.Login

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.proyecto_diego.R
import com.example.proyecto_diego.R.color.red

class SingUp(): Fragment() {

    private lateinit var txtNameUser : EditText
    private lateinit var txtMailUser : EditText
    private lateinit var txtPasswrdlUser : EditText
    private lateinit var txtRepitPasswrdlUser : EditText
    private lateinit var chkTerminos : CheckBox
    private lateinit var txtMsgError : TextView
    private lateinit var txtTerminos : TextView
    private lateinit var btnLogin : Button

    //Funcion para realizar el setText en los EditText
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

            return inflater.inflate(R.layout.screen_singup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initValues(view)

    }

    fun initValues(v : View ){
        txtNameUser = v.findViewById(R.id.txtNameUser)
        txtMailUser = v.findViewById(R.id.txtMailUser)
        txtPasswrdlUser = v.findViewById(R.id.txtPasswrdlUser)
        txtRepitPasswrdlUser = v.findViewById(R.id.txtRepitPasswrdlUser)
        chkTerminos = v.findViewById(R.id.chkTerminos)
        txtMsgError = v.findViewById(R.id.txtMsgError)
        btnLogin = v.findViewById(R.id.btnLogin)
        txtTerminos = v.findViewById(R.id.txtTerminos)

        btnLogin.setOnClickListener{
            if(checkDates()){
                println("True")
                singUpUser()
            }else{
                println("False")
            }
        }
    }



    @SuppressLint("ResourceAsColor")
    fun checkDates() : Boolean{

        var estado = true

        if(txtNameUser.text.toString().length<1){
            txtNameUser.setBackgroundResource(R.drawable.edit_text_border_error)
            txtNameUser.hint = "Ponga un nombre de usuario".toEditable()
            estado = false
        }else{
            txtNameUser.setBackgroundResource(R.drawable.edit_text_border)
        }

        if(txtMailUser.text.toString().length<1){
            txtMailUser.setBackgroundResource(R.drawable.edit_text_border_error)
            txtMailUser.hint = "Ponga un correo electrónico valido".toEditable()
            estado =  false
        }else{
            txtMailUser.setBackgroundResource(R.drawable.edit_text_border)
        }

        if(!chkTerminos.isChecked){
            //chkTerminos.buttonTintMode = ColorStateList.valueOf(resources.getColor(R.color.red))
            estado = false
        }else{

        }

        if(!txtPasswrdlUser.text.toString().equals(txtRepitPasswrdlUser.text.toString()) ||
                txtPasswrdlUser.text.toString().length < 6){
            txtMsgError.visibility = View.VISIBLE
            txtPasswrdlUser.setBackgroundResource(R.drawable.edit_text_border_error)
            txtRepitPasswrdlUser.setBackgroundResource(R.drawable.edit_text_border_error)
            estado =  false
        }else{
            txtMsgError.visibility = View.INVISIBLE
            txtPasswrdlUser.setBackgroundResource(R.drawable.edit_text_border)
            txtRepitPasswrdlUser.setBackgroundResource(R.drawable.edit_text_border)
        }

        return estado
    }

    fun singUpUser() {

        val url = "http:192.168.0.5/Proyecto_Diego/registro.php"
        val queue = Volley.newRequestQueue(getContext())
        val request = object : StringRequest(Method.POST, url, { response ->

            if(response.equals("OK")){
                println("Hola")
                getActivity()?.onBackPressed();
            }else{
                println("Error")
            }

        }, { error ->
            println(error.message)

        }){
            override fun getParams(): Map<String, String> {
                var paramas: MutableMap<String, String> = HashMap()

                paramas["user"] = txtNameUser.text.toString()
                paramas["mail"] = txtMailUser.text.toString()
                paramas["passwrd"] = txtPasswrdlUser.text.toString()

                return paramas
            }
        }
        queue.add(request)

    }



}