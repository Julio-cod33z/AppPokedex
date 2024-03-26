package pe.edu.idat.class18_03_2024.retrofit.response

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonType>
)

data class PokemonType(
    val slot: Int,
    val type: TypeName
)

data class TypeName(
    val name: String
)