package com.netflixclone.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netflixclone.Model.Filmes
import com.netflixclone.databinding.ListaFilmesBinding
import kotlinx.android.synthetic.main.lista_filmes.view.*

class FilmesAdapter (val filmes: MutableList<Filmes>): RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {

        val binding = ListaFilmesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FilmesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        with(holder){
            with(filmes[position]){
                binding.capaFilme.setImageResource(capaFilme)
            }
        }
    }

    override fun getItemCount() = filmes.size

    inner class FilmesViewHolder(val binding: ListaFilmesBinding): RecyclerView.ViewHolder(binding.root){

    }
}