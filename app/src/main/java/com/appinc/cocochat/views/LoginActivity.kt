package com.appinc.cocochat.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appinc.cocochat.R
import com.appinc.cocochat.tools.Empty
import com.appinc.cocochat.tools.HideKeyBoard
import com.appinc.cocochat.tools.Style
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    internal lateinit var tiNombre: TextInputEditText
    internal lateinit var tiSala: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        layout_main.setOnTouchListener { v, _ ->
            if (tiNombre.isFocused) {
                tiNombre.clearFocus()
                tiNombre.HideKeyBoard()
            }
            if (tiSala.isFocused) {
                tiSala.clearFocus()
                tiSala.HideKeyBoard()
            }
            return@setOnTouchListener v.performClick()
        }

        val layoutNombre = findViewById<TextInputLayout>(R.id.layoutNombre)
        layoutNombre.hint = getString(R.string.nombre)
        this.tiNombre = layoutNombre.findViewById(R.id.tiEditText)
        this.tiNombre.Style(R.drawable.ic_person_24, 10)

        val layoutSala = findViewById<TextInputLayout>(R.id.layoutSala)
        layoutSala.hint = getString(R.string.sala)
        this.tiSala = layoutSala.findViewById(R.id.tiEditText)
        this.tiSala.Style(R.drawable.ic_group_24, 10)
        this.tiSala.setText(getString(R.string.sala))

        btnIngresar.setOnClickListener {
            if (this.tiNombre.Empty()) return@setOnClickListener
            if (this.tiSala.Empty()) return@setOnClickListener

            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("nombre", this.tiNombre.text.toString())
            intent.putExtra("sala", this.tiSala.text.toString())
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("Nombre", this.tiNombre.text.toString())
        outState.putString("Sala", this.tiSala.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if (this.tiNombre.isFocused) this.tiNombre.clearFocus()
        if (this.tiSala.isFocused) this.tiSala.clearFocus()

        this.tiNombre.setText(savedInstanceState.getString("Nombre"))
        this.tiSala.setText(savedInstanceState.getString("Sala"))
    }
}