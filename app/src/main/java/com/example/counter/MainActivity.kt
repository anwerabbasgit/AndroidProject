package com.example.counter

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var txtCounter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnPlus : Button = findViewById(R.id.btn_plus)
        val btnMinus : Button = findViewById(R.id.btn_minus)
        val btnReset : Button = findViewById(R.id.btn_reset)
        txtCounter  = findViewById(R.id.txt_counter)
        val et : EditText = findViewById(R.id.ed)


        btnPlus.setOnClickListener{
            val counter = txtCounter.text.toString()
            val result = counter.toInt() + 1
            val target = et.text.toString()
            txtCounter.text = result.toString()


            if (target == result.toString())
            {
                val color =R.color.color_2
                txtCounter.setBackgroundColor(getColor(color))


            }



        }
        btnMinus.setOnClickListener{
            val counter = txtCounter.text.toString()
            if (counter > "0") {
                val result = counter.toInt() - 1
                txtCounter.text = result.toString()
            }

        }
        btnReset.setOnClickListener{
            et.setText("0")
            et.clearFocus()
            val color =R.color.white
            txtCounter.setBackgroundColor(getColor(color))

        }

        onBackPressedDispatcher.addCallback(this@MainActivity,object : OnBackPressedCallback(enabled = true)

        {
            override fun handleOnBackPressed() {
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Click Counter App")
                alert.setMessage("Are You Sure You Want To Exit")
                alert.setIcon(R.mipmap.ic_launcher_round)
                alert.setPositiveButton("Yes"){_,_ -> finish()}
                alert.setNeutralButton("No"){_,_ ->}
                alert.show()


            }

        }

        )



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.optionsmenu_reset -> {txtCounter.text ="0"}
            R.id.optionsmenu_about -> {
                val i = Intent(this,AboutActivity::class.java)
                startActivity(i)
            }
            R.id.optionsmenu_exit -> {finish()}

        }
        return super.onOptionsItemSelected(item)
    }
}