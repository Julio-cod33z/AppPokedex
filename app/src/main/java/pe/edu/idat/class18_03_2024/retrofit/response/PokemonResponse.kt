package pe.edu.idat.class18_03_2024.retrofit.response

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Pokemon>
)
