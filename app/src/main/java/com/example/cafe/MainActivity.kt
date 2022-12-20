package com.example.cafe

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import com.example.cafe.R
import android.widget.Toast
import android.content.Intent
import android.widget.Button
import com.example.cafe.MakeOrderActivity

class MainActivity : AppCompatActivity() {
    private var EditTextName: EditText? = null
    private var EditTextPassword: EditText? = null
    private var ButtonSignIn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        ButtonSignIn!!.setOnClickListener {
            val username = EditTextName!!.text.toString().trim { it <= ' ' }
            val password = EditTextPassword!!.text.toString().trim { it <= ' ' }
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.error_fields_empty),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                launchNextScreen(username)
            }
        }
    }

    private fun launchNextScreen(userName: String) {
        val intent = MakeOrderActivity.newIntent(this, userName)
        startActivity(intent)
    }

    private fun initViews() {
        EditTextName = findViewById(R.id.EditTextName)
        EditTextPassword = findViewById(R.id.EditTextPassword)
        ButtonSignIn = findViewById(R.id.ButtonSignIn)
    }
}