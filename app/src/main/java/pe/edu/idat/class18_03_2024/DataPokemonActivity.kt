package pe.edu.idat.class18_03_2024

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import pe.edu.idat.class18_03_2024.databinding.ActivityDataPokemonBinding
import pe.edu.idat.class18_03_2024.retrofit.PokemonApiService
import pe.edu.idat.class18_03_2024.retrofit.response.PokemonDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataPokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataPokemonBinding
    private lateinit var apiRetrofit: Retrofit
    private lateinit var pokemonUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pokemonUrl = intent.getStringExtra("pokemonUrl")!!

        apiRetrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        obtenerDatosPokemon()
    }

    private fun obtenerDatosPokemon() {
        val apiService = apiRetrofit.create(PokemonApiService::class.java)
        val pokemonDetailCall = apiService.getPokemonDetail(pokemonUrl)
        pokemonDetailCall.enqueue(object : Callback<PokemonDetailResponse> {
            override fun onResponse(
                call: Call<PokemonDetailResponse>,
                response: Response<PokemonDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val pokemonDetail = response.body()
                    pokemonDetail?.let {
                        displayPokemonDetail(it)
                    }
                }
            }

            override fun onFailure(call: Call<PokemonDetailResponse>, t: Throwable) {
                // Manejar el fallo
            }
        })
    }

    private fun displayPokemonDetail(pokemonDetail: PokemonDetailResponse) {
        with(binding) {
            val heightInMeters = pokemonDetail.height.toFloat() / 10 // Conversión a metros
            val weightInKg = pokemonDetail.weight.toFloat() / 10 // Conversión a kilogramos
            tvPokemonName.text = "${pokemonDetail.name} N.° ${pokemonDetail.id}"
            tvHeight.text = "Altura: ${"%.1f".format(heightInMeters)} m" // Mostrar altura en metros con un decimal
            tvWeight.text = "Peso: ${"%.1f".format(weightInKg)} kg" // Mostrar peso en kilogramos con un decimal

            // Para los tipos
            var types = ""
            for (type in pokemonDetail.types) {
                types += "${type.type.name.capitalize()}, "
            }
            if (types.isNotEmpty()) {
                types = types.substring(0, types.length - 2) // Eliminar la coma y el espacio del final
            }
            tvTypes.text = "Tipo: $types"

            // Cargar la imagen del Pokémon
            Glide.with(this@DataPokemonActivity)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonDetail.id}.png")
                .into(ivPokemon)
        }
    }
}