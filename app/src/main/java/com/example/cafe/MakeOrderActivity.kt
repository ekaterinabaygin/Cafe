package com.example.cafe

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cafe.R
import android.content.Intent
import android.view.View
import android.widget.*
import com.example.cafe.OrderDetailActivity
import com.example.cafe.MakeOrderActivity
import java.util.ArrayList

class MakeOrderActivity : AppCompatActivity() {
    private var TextViewGreetings: TextView? = null
    private var RadioGroupDrinks: RadioGroup? = null
    private var RadioButtonTea: RadioButton? = null
    private var RadioButtonCoffee: RadioButton? = null
    private var TextViewAdditives: TextView? = null
    private var checkBoxSugar: CheckBox? = null
    private var checkBoxMilk: CheckBox? = null
    private var checkBoxLemon: CheckBox? = null
    private var SpinnerTea: Spinner? = null
    private var SpinnerCoffee: Spinner? = null
    private var ButtonMakeOrder: Button? = null
    private var drink: String? = null
    private var userName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_order)
        initViews()
        setupUserName()
        RadioGroupDrinks!!.setOnCheckedChangeListener { radioGroup, id ->
            if (id == RadioButtonTea!!.id) {
                onUserChoseTea()
            } else if (id == RadioButtonCoffee!!.id) {
                onUserChoseCoffee()
            }
        }
        RadioButtonTea!!.isChecked = true
        ButtonMakeOrder!!.setOnClickListener { onUserMadeOrder() }
    }

    private fun onUserMadeOrder() {
        val additives = ArrayList<String>()
        if (checkBoxSugar!!.isChecked) {
            additives.add(checkBoxSugar!!.text.toString())
        }
        if (RadioButtonTea!!.isChecked && checkBoxLemon!!.isChecked) {
            additives.add(checkBoxLemon!!.text.toString())
        }
        if (checkBoxMilk!!.isChecked) {
            additives.add(checkBoxMilk!!.text.toString())
        }
        var drinkType = ""
        if (RadioButtonTea!!.isChecked) {
            drinkType = SpinnerTea!!.selectedItem.toString()
        } else if (RadioButtonCoffee!!.isChecked) {
            drinkType = SpinnerCoffee!!.selectedItem.toString()
        }
        val intent = OrderDetailActivity.newIntent(
            this,
            userName,
            drink,
            drinkType,
            additives.toString()
        )
        startActivity(intent)
    }

    private fun onUserChoseTea() {
        drink = getString(R.string.tea)
        val additivesLabel = getString(R.string.additives, drink)
        TextViewAdditives!!.text = additivesLabel
        checkBoxLemon!!.visibility = View.VISIBLE
        SpinnerTea!!.visibility = View.VISIBLE
        SpinnerCoffee!!.visibility = View.INVISIBLE
    }

    private fun onUserChoseCoffee() {
        drink = getString(R.string.coffee)
        val additivesLabel = getString(R.string.additives, drink)
        TextViewAdditives!!.text = additivesLabel
        checkBoxLemon!!.visibility = View.INVISIBLE
        SpinnerTea!!.visibility = View.INVISIBLE
        SpinnerCoffee!!.visibility = View.VISIBLE
    }

    private fun initViews() {
        TextViewGreetings = findViewById(R.id.TextViewGreetings)
        RadioGroupDrinks = findViewById(R.id.RadioGroupDrinks)
        RadioButtonTea = findViewById(R.id.RadioButtonTea)
        RadioButtonCoffee = findViewById(R.id.RadioButtonCoffee)
        TextViewAdditives = findViewById(R.id.TextViewAdditives)
        checkBoxSugar = findViewById(R.id.checkBoxSugar)
        checkBoxMilk = findViewById(R.id.checkBoxMilk)
        checkBoxLemon = findViewById(R.id.checkBoxLemon)
        SpinnerTea = findViewById(R.id.SpinnerTea)
        SpinnerCoffee = findViewById(R.id.SpinnerCoffee)
        ButtonMakeOrder = findViewById(R.id.ButtonMakeOrder)
    }

    private fun setupUserName() {
        userName = intent.getStringExtra(EXTRA_USER_NAME)
        val greetings = getString(R.string.greetings, userName)
        TextViewGreetings!!.text = greetings
    }

    companion object {
        private const val EXTRA_USER_NAME = "userName"
        fun newIntent(context: Context?, userName: String?): Intent {
            val intent = Intent(context, MakeOrderActivity::class.java)
            intent.putExtra(EXTRA_USER_NAME, userName)
            return intent
        }
    }
}