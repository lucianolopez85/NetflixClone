package com.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.netflixclone.Adapter.FilmesAdapter
import com.netflixclone.Model.addFilmes
import com.netflixclone.Onclick.OnItemClickListener
import com.netflixclone.Onclick.addOnItemClickListener
import com.netflixclone.databinding.ActivityListaFilmesBinding

class ListaFilmes : AppCompatActivity() {

    private lateinit var binding: ActivityListaFilmesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler_filmes = binding.recyclerview
        recycler_filmes.adapter = FilmesAdapter(addFilmes())
        recycler_filmes.layoutManager = GridLayoutManager(applicationContext, 3)

// INICIO evento OnClick na lista de filmes

        // Primeiro tem que criar o pacote Onclick
        // Criar arquivo RecyclerItemClickListener.kt
        // crtl+v do arquivo txt(já esta pronto)
        recycler_filmes.addOnItemClickListener(object: OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {

                // aqui define o item da lista
                when{
                    position == 0 -> DetalhesFilme()
                    //  position == 1 -> DetalhesFilme1()
                    //  pra cada detalhe a mais é só seguir a mesma lógica
                }
            }
        })
    }

    private fun DetalhesFilme(){
        val intent = Intent(this, DetalhesFilme::class.java)
        startActivity(intent)
    }
// FIM evento OnClick na lista de filmes

// INICIO Opção deslogar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_pricipal,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.deslogar -> {
                FirebaseAuth.getInstance().signOut()
                VoltarTelaLogin()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun VoltarTelaLogin(){
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }
// FIM opção deslogar
}