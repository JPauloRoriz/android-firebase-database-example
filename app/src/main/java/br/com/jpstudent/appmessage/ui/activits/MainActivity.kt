package br.com.jpstudent.appmessage.ui.activits

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}