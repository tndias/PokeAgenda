package br.com.psoa.pokeagenda

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.psoa.pokeagenda.api.PokemonAPI
import br.com.psoa.pokeagenda.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//http://square.github.io/retrofit/
//http://square.github.io/picasso/
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSearch.setOnClickListener({
            searchPokemon()
        })
    }

    fun searchPokemon() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co")
                .addConverterFactory(
                        GsonConverterFactory.create())
                .build()
        val api = retrofit.create(PokemonAPI::class.java)
        api.getPokemon(etNumber.text.toString().toInt()).enqueue(object : Callback<Pokemon> {
            override fun onFailure(call: Call<Pokemon>?, t: Throwable?) {
                Toast.makeText(applicationContext, t?.message.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Pokemon>?, response: Response<Pokemon>?) {
                tvName.setText(response?.body()?.name)
                Picasso.with(applicationContext)
                        .load(response?.body()?.sprite?.frontDefault)
                        .into(imageView)
            }
        })

    }
}
