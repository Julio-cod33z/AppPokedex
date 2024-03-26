package pe.edu.idat.class18_03_2024.retrofit

import pe.edu.idat.class18_03_2024.retrofit.response.PokemonDetailResponse
import pe.edu.idat.class18_03_2024.retrofit.response.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApiService {

    @GET("pokemon")
    fun getPokemon(@Query("offset")offset:Int, @Query("limit")limit:Int):Call<PokemonResponse>

    @GET
    fun getPokemonDetail(@Url url: String): Call<PokemonDetailResponse>
    /*
    @POST("pokemon")
    fun postPokemon(@Body pokemon: Pokemon)

    @PUT("pokemon")
    fun putPokemon(@Body pokemon: Pokemon)
    */
}