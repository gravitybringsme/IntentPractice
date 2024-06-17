package com.neppplus.intentpractice

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val REQUEST_FOR_NICKNAME = 1005

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }

        findViewById<Button>(R.id.dialBtn).setOnClickListener{

            //phoneNumEdit에 입력한 전화번호로 연결
            val inputPhoneNum = findViewById<EditText>(R.id.phoneNumEdt).text.toString()
            val myUri = Uri.parse("tel:${inputPhoneNum}")
            val myIntent = Intent(Intent.ACTION_DIAL, myUri)
            startActivity(myIntent)
        }

        findViewById<Button>(R.id.callBtn).setOnClickListener{

            //phoneNumEdit에 입력한 전화번호로 연결
            val inputPhoneNum = findViewById<EditText>(R.id.phoneNumEdt).text.toString()
            val myUri = Uri.parse("tel:${inputPhoneNum}")
            val myIntent = Intent(Intent.ACTION_CALL, myUri)
            startActivity(myIntent)
        }

        findViewById<Button>(R.id.smsBtn).setOnClickListener{

            //phoneNumEdit에 입력한 전화번호로 연결
            val inputPhoneNum = findViewById<EditText>(R.id.phoneNumEdt).text.toString()
            val myUri = Uri.parse("smsto:${inputPhoneNum}")
            val myIntent = Intent(Intent.ACTION_SENDTO, myUri)
            myIntent.putExtra("sms_body", "미리 내용 입력")
            startActivity(myIntent)
        }

        findViewById<Button>(R.id.naverWebBtn).setOnClickListener{

            //phoneNumEdit에 입력한 전화번호로 연결
//            val inputPhoneNum = findViewById<EditText>(R.id.phoneNumEdt).text.toString()
            val myUri = Uri.parse("https://naver.com")
            val myIntent = Intent(Intent.ACTION_VIEW, myUri)
//            myIntent.putExtra("sms_body", "미리 내용 입력")
            startActivity(myIntent)
        }

        findViewById<Button>(R.id.kakaoStoreBtn).setOnClickListener{

            //phoneNumEdit에 입력한 전화번호로 연결
//            val inputPhoneNum = findViewById<EditText>(R.id.phoneNumEdt).text.toString()
            val myUri = Uri.parse("market://details?id=com.kakao.talk")
            val myIntent = Intent(Intent.ACTION_VIEW, myUri)
//            myIntent.putExtra("sms_body", "미리 내용 입력")
            startActivity(myIntent)
        }

        findViewById<Button>(R.id.editNicknameBtn).setOnClickListener{

            val myIntent = Intent(this, EditNicknameActivity::class.java)
            startActivityForResult(myIntent, REQUEST_FOR_NICKNAME)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 돌아온 이유가 닉네임을 받으러 다녀온게 맞는지?
        if (requestCode == REQUEST_FOR_NICKNAME){

            // 추가질문 : 확인을 눌러서 돌아온게 맞는가?
            if(resultCode == Activity.RESULT_OK){

                // 실제 첨부된 새 닉네임을 꺼내서
                val newNickname = data?.getStringExtra("nickname")
                findViewById<TextView>(R.id.nicknameTxt).text = newNickname
            }
        }
    }
}