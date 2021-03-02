package com.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.netflixclone.databinding.ActivityFormCadastroBinding

class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding

    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.btnCadastrar.setOnClickListener {
            val email = binding.editEmailCadastro.text.toString() // pega email e converte para string
            val senha = binding.editSenhaCadastro.text.toString()
            val msg_erro = binding.msgErroCadastro
            if (email.isEmpty() || senha.isEmpty()){ //se email ou senha estiver vazio...
                msg_erro.setText("Preencha todos os campos!")
            }else{
                CadastrarUsuario()
            }
        }
    }
    private fun CadastrarUsuario() {

        val email = binding.editEmailCadastro.text.toString() // pega email e converte para string
        val senha = binding.editSenhaCadastro.text.toString()
        val msg_erro = binding.msgErroCadastro

        // .createUserWithEmailAndPassword(email, senha)   <- é um metodo para criar email e senha
        // .addOnCompleteListener {     <- objeto responsável para criar a conta ou recuperar
        // (it.isSuccessful)   <- se usuário foi cadastrado corretamente
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT)
                    .show()
                binding.editEmailCadastro.setText("")
                binding.editSenhaCadastro.setText("")

                msg_erro.setText("")
            }
        }.addOnFailureListener {
            var erro = it  //captura o erro

            when{
                // FirebaseAuthWeakPasswordException  <= exception do Firebase, senha com minimo de 6 caracteres
                erro is FirebaseAuthWeakPasswordException -> msg_erro.setText("Digite uma senha com no mínimo 6 caracteres")
                erro is FirebaseAuthUserCollisionException -> msg_erro.setText("Esta conta já foi cadastrada")
                erro is FirebaseNetworkException -> msg_erro.setText("Sem conexão com internet")
                // é necessário por permisão para internet no manifest (linha5)
                else -> msg_erro.setText("Erro ao cadastrar usuário")
            }
        }
    }
}
