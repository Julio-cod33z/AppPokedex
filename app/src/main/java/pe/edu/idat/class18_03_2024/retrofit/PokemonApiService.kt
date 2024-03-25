package pe.edu.idat.class18_03_2024.retrofit

import pe.edu.idat.class18_03_2024.retrofit.response.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon")
    fun getPokemon(@Query("offset")offset:Int, @Query("limit")limit:Int):Call<PokemonResponse>
    /*
    @POST("pokemon")
    fun postPokemon(@Body pokemon: Pokemon)

    @PUT("pokemon")
    fun putPokemon(@Body pokemon: Pokemon)
    */
}