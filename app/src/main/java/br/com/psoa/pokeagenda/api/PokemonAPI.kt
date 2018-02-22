package br.com.psoa.pokeagenda.api

import br.com.psoa.pokeagenda.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by logonrm on 21/02/2018.
 */
interface PokemonAPI {

    @GET("/api/v2/pokemon/{numero}")
    fun getPokemon(@Path("numero") pokemonNumber: Int) : Call<Pokemon>


}