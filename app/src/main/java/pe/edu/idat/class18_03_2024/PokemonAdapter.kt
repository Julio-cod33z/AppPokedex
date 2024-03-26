package pe.edu.idat.class18_03_2024

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.idat.class18_03_2024.databinding.ItemPokemonBinding
import pe.edu.idat.class18_03_2024.retrofit.response.Pokemon
import java.util.ArrayList

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){
    private var lista = ArrayList<Pokemon>()

    inner class ViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(lista[position]){
                binding.tvNomPokemon.text = name
                val arrayUrl = url.split("/")
                Glide.with(itemView.context)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${arrayUrl[arrayUrl.size - 2]}.png")
                    .into(binding.ivPokemon)

                // Manejar el clic en el elemento del RecyclerView
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DataPokemonActivity::class.java)
                    intent.putExtra("pokemonUrl", url)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }


    override fun getItemCount() = lista.size

    fun postPokemones(nuevosPokemones: List<Pokemon>){
        lista.addAll(nuevosPokemones)
        notifyDataSetChanged()
    }
}