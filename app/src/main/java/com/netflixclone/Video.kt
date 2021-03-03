package com.netflixclone

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.netflixclone.databinding.ActivityVideoBinding

class Video : AppCompatActivity() {

    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val videoURL = Uri.parse("https://firebasestorage.googleapis.com/v0/b/netflix-clone-d9af4.appspot.com/o/THE%20WITCHER%20_%20TRAILER%20FINAL%20_%20NETFLIX.mp4?alt=media&token=f04e6f84-3331-403c-a0c7-f0f6299b4b3f")
        val video = binding.video
        //.setMediaController() -> serve para por os controles do vídeo(play, pause)
        video.setMediaController(MediaController(this))
        video.setVideoURI(videoURL)
        video.requestFocus()
        video.start()
    }
}