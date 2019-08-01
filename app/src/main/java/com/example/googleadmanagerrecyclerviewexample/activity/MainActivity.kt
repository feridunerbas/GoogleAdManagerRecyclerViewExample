package com.example.googleadmanagerrecyclerviewexample.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googleadmanagerrecyclerviewexample.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()

    }


    private fun setUpView(){

        textViewRegular.setOnClickListener {
            start(RegularActivity::class.java)
        }

        textViewPreload.setOnClickListener {
            start(PreloadActivity::class.java)
        }
        
        textViewPreloadInParallel.setOnClickListener{
            start(PreloadParallelActivity::class.java)
        }
    }

    private fun start(cls: Class<*>){
        val intent = Intent(this,cls)
        startActivity(intent)
    }

}
