package com.useradgents.domain.usecase

import android.app.Application
import android.media.AudioManager
import android.media.MediaPlayer
import javax.inject.Inject
import javax.inject.Named

class PlaySoundUseCase @Inject constructor(@Named("baseUrl") private val baseUrl: String,
                                           private val app: Application) {

    fun execute(title: String, onStart: (String) -> Unit, onStop: (String) -> Unit) {
        val mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()
            onStart.invoke(title)
        }
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.reset()
            mediaPlayer.release()
            onStop.invoke(title)
        }
        mediaPlayer.setDataSource(baseUrl+"sounds/$title")
        mediaPlayer.prepareAsync()
    }
}