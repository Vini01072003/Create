package br.senai.sp.jandira.retrofit_reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.create


class MainActivity : AppCompatActivity() {
    private lateinit var apiServices: ApiServices
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiServices = RetrofitHelper.getInstance().create(ApiServices::class.java)


     findViewById<Button>(R.id.btnGET).setOnClickListener{
        getUserByID()
         Log.e("GETTING-DATA", "TESTE2")
     }
        //ACAO DO BOTAO POST:
        findViewById<Button>(R.id.btnPOST).setOnClickListener{
            createUser()
        }

    }

//Recupera dados do usuário
    private fun getUserByID() {
        lifecycleScope.launch {
            // Chamada do trabalinho para o ENDPOINT
            val result = apiServices.getUserByID("8")

            if (result.isSuccessful){

                Log.e("GETTING-DATA", "${result.body()?.data}")

            } else {

                Log.e("GETTING-DATA", "${result.errorBody()}")

            }
        }
    }
    //Insere dados do usuario
    private fun createUser(){
        lifecycleScope.launch {
            val body = JsonObject().apply {
                addProperty("name", "Vinícius Alves dos Santos")
                addProperty("job", "DEVOPS")
                addProperty("", "")
                addProperty("", "")
            }

            val result = apiServices.createUser(body)

            if (result.isSuccessful){

                Log.e("CREATE-DATA", "${result.body()}")

            } else {

                Log.e("CREATE-DATA", "${result.errorBody()}")

            }
        }
    }



        }
