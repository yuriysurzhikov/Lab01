package com.yuriysurzhikov.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var greetingsOutput: TextView
    private var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.submit_button)
        editText = findViewById<TextInputLayout>(R.id.input_name_layout).editText
        greetingsOutput = findViewById(R.id.greetings_text)
        button.setOnClickListener(readClickListener)
        editText?.setOnEditorActionListener(onDoneOptionClick)
        showGreetings(getString(R.string.default_name))
    }

    private val readClickListener = View.OnClickListener {
        showGreetings(editText?.text?.toString())
    }

    private val onDoneOptionClick = TextView.OnEditorActionListener { _, actionId, _ ->
        when (actionId) {
            EditorInfo.IME_ACTION_DONE -> {
                showGreetings(editText?.text?.toString())
                KeyboardUtils.closeSoftKeyboard(editText!!)
                return@OnEditorActionListener true
            }
        }
        return@OnEditorActionListener false
    }

    private fun showGreetings(name: String?) {
        val template = getString(R.string.greetings_template)
        val formattedText = String.format(template, name)
        greetingsOutput.text = formattedText
    }
}