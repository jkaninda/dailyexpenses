package com.jkantech.dailyexpenses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.jkantech.dailyexpenses.data.LoginData
import com.jkantech.dailyexpenses.data.LoginResponseData
import com.jkantech.dailyexpenses.data.ResponseData
import com.jkantech.dailyexpenses.databinding.ActivityLoginBinding
import com.jkantech.dailyexpenses.model.User
import com.jkantech.dailyexpenses.retrofiit.APIService
import com.jkantech.dailyexpenses.utils.CustomDataStore
import com.jkantech.dailyexpenses.utils.Utils
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var password:EditText
    private lateinit var userName:EditText
    private lateinit var loginButton: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        password=binding.include.password
        userName=binding.include.username
        loginButton=binding.include.loginButton
        val root=binding.root
        CustomDataStore.init(this)

        binding.cancelAction.setOnClickListener {
            finish()
        }

        loginButton.setOnClickListener {
            when{
                userName.text.toString() ==""->Utils.showToast(this,"Veuillez entre le nom d'utilisateur")
                password.text.toString() ==""->Utils.showToast(this,"Veuillez entre le mot de passe")
                else->login()
            }
        }
    }


    private fun login(){
        binding.include.progressBar.visibility=View.VISIBLE
        val data:HashMap<String?,String?> = HashMap();
        data["username"] =userName.text.toString()
        data["password"] =password.text.toString()

        Log.d("Daily",data.toString())
        APIService.invoke().login(data).enqueue(object :retrofit2.Callback<LoginResponseData>{
            override fun onResponse(
                call: Call<LoginResponseData>,
                response: Response<LoginResponseData>
            ) {
                binding.include.progressBar.visibility=View.GONE

                if (response.isSuccessful) {
                    response.body().let {

                        if (it != null) {
                            if (it.code == 1) {
                                CustomDataStore.storeBoolean("isConnected",true)
                                CustomDataStore.store("userId",it.data.userID.toString())
                                CustomDataStore.store("name",it.data.name)
                                CustomDataStore.store("role",it.data.role)
                                CustomDataStore.store("token",it.data.token)
                                successLogin()


                            }else{
                                Utils.showToast(this@LoginActivity, it.message)

                            }
                        }

                    }


                    }else {

                        Utils.showToast(this@LoginActivity, getString(R.string.server_not_found))

                    }


            }


            override fun onFailure(call: Call<LoginResponseData>, t: Throwable) {
                Log.d("NetworkError",t.message.toString())
                binding.include.progressBar.visibility=View.GONE
                Utils.showToast(this@LoginActivity,getString(R.string.server_not_found))
            }

        })




    }

    private fun successLogin(){
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}