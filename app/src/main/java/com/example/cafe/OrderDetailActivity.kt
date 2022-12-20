package com.example.cafe

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.example.cafe.R
import android.content.Intent
import com.example.cafe.OrderDetailActivity

class OrderDetailActivity : AppCompatActivity() {
    private var textViewName: TextView? = null
    private var textViewDrink: TextView? = null
    private var TextViewDrinkType: TextView? = null
    private var TextViewAdditives: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        initViews()
        val intent = intent
        textViewName!!.text = intent.getStringExtra(EXTRA_USER_NAME)
        textViewDrink!!.text = intent.getStringExtra(EXTRA_DRINK)
        TextViewDrinkType!!.text = intent.getStringExtra(EXTRA_DRINK_TYPE)
        TextViewAdditives!!.text = intent.getStringExtra(EXTRA_ADDITIVES)
    }

    private fun initViews() {
        textViewName = findViewById(R.id.textViewName)
        textViewDrink = findViewById(R.id.textViewDrink)
        TextViewDrinkType = findViewById(R.id.TextViewDrinkType)
        TextViewAdditives = findViewById(R.id.TextViewAdditives)
    }

    companion object {
        private const val EXTRA_USER_NAME = "userName"
        private const val EXTRA_DRINK = "drink"
        private const val EXTRA_DRINK_TYPE = "drinkType"
        private const val EXTRA_ADDITIVES = "additives"
        fun newIntent(
            context: Context?,
            userName: String?,
            drink: String?,
            drinkType: String?,
            additives: String?
        ): Intent {
            val intent = Intent(context, OrderDetailActivity::class.java)
            intent.putExtra(EXTRA_USER_NAME, userName)
            intent.putExtra(EXTRA_DRINK, drink)
            intent.putExtra(EXTRA_DRINK_TYPE, drinkType)
            intent.putExtra(EXTRA_ADDITIVES, additives)
            return intent
        }
    }
}