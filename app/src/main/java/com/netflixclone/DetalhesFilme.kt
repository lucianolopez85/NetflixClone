package com.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.netflixclone.Adapter.FilmesAdapter
import com.netflixclone.Model.addFilmes
import com.netflixclone.databinding.ActivityDetalhesFilmeBinding
import com.netflixclone.databinding.ActivityListaFilmesBinding
import com.squareup.picasso.Picasso as Picasso

class DetalhesFilme : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesFilmeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toolbar()

        // lista de filmes no detalhe
        val recycler_detalhe_outros = binding.recycleDetalheOutros
        recycler_detalhe_outros.adapter = FilmesAdapter(addFilmes())
        recycler_detalhe_outros.layoutManager = GridLayoutManager(applicationContext, 3)

        val capaTheWhitcherURL = "https://firebasestorage.googleapis.com/v0/b/netflix-clone-d9af4.appspot.com/o/video.jpg?alt=media&token=9b7c7064-c665-4613-bced-9844acc85b79"
        //metodo fit() ajusta o tamanho dentro da largura e altura da imageView
        // .into() onde é pra ser carregado a imagem
        Picasso.get().load(capaTheWhitcherURL).fit().into(binding.capaVideo)

        binding.playVideo.setOnClickListener {
            val intent = Intent(this, Video::class.java)
            startActivity(intent)
        }

    }
    private fun Toolbar(){
        val toolbar_detalhes = binding.toolbarDetalhes
        toolbar_detalhes.setNavigationIcon(getDrawable(R.drawable.ic_volar))
        toolbar_detalhes.setNavigationOnClickListener {
            val intent = Intent(this, ListaFilmes::class.java)
            startActivity(intent)
            finish()
        }
    }
}