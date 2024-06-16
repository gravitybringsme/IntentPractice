package com.neppplus.intentpractice

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }

        val sendMessageBtn : Button = findViewById<Button>(R.id.sendMessageBtn)
        sendMessageBtn.setOnClickListener{

            // 입력할 내용을 변수에 저장

            val inputMessage = findViewById<EditText>(R.id.messageEdt).text.toString()
            val myIntent = Intent(this, MessageActivity::class.java)

            myIntent.putExtra("message", inputMessage)
            startActivity(myIntent)
        }

        val moveToOtherBtn : Button = findViewById<Button>(R.id.moveToOtherBtn)
        moveToOtherBtn.setOnClickListener {
            val myIntent = Intent(this, OtherActivity::class.java) // 출발지, 도착지
            startActivity(myIntent)
        }
    }
}