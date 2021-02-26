package com.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.netflixclone.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        VerificarUsuarioLogado()

        binding.txtRegisterNow.setOnClickListener {
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }

        binding.btnEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val msg_erro = binding.msgErroLogin

            if (email.isEmpty() || senha.isEmpty()){
                msg_erro.setText("Preencha todos os campos")
            }else{
                AutenticarUsuario()
            }
        }
    }
    private fun AutenticarUsuario(){
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val msg_erro = binding.msgErroLogin

        // .signInWithEmailAndPassword(email,senha)   <- metodo para entrar com email e senha
        // .addOnCompleteListener {     <- objeto responsável para criar a conta ou autenticar
        // (it.isSuccessful)   <- se usuário foi cadastrado corretamente
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener{
            if(it.isSuccessful) { // se usuário foi logado com sucesso
                Toast.makeText(this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show() //manda a msg
                IrParaTelaFilmes() // vai pra outra tela
            }
        }.addOnFailureListener {

            var erro = it

            when{
                erro is FirebaseAuthInvalidCredentialsException -> msg_erro.setText("E-mail ou Senha estão incorretos")
                erro is FirebaseNetworkException -> msg_erro.setText("Sem conexão com a internet")
                else -> msg_erro.setText("Erro ao logar usuário") //erro global
            }
        }
    }

    private fun VerificarUsuarioLogado(){

        // .currentUser   <- pega o usuário atual que foi logado
        val usuarioLogado = FirebaseAuth.getInstance().currentUser

        if (usuarioLogado != null){
            IrParaTelaFilmes()
        }
    }


    private fun IrParaTelaFilmes(){
        val intent = Intent(this, ListaFilmes::class.java)
        startActivity(intent)
        finish()
    }
}