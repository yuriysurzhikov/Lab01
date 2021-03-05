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
    private var editText: TextInputLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.submit_button)
        editText = findViewById(R.id.input_name_layout)
        greetingsOutput = findViewById(R.id.greetings_text)
        button.setOnClickListener(readClickListener)
        editText?.editText?.setOnEditorActionListener(onDoneOptionClick)
        showGreetings(getString(R.string.default_name))
    }

    private val readClickListener = View.OnClickListener {
        showGreetings(editText?.editText?.text?.toString())
    }

    private val onDoneOptionClick = TextView.OnEditorActionListener { _, actionId, _ ->
        when (actionId) {
            EditorInfo.IME_ACTION_DONE -> {
                showGreetings(editText?.editText?.text?.toString())
                KeyboardUtils.closeSoftKeyboard(editText!!)
                return@OnEditorActionListener true
            }
        }
        return@OnEditorActionListener false
    }

    private fun showGreetings(name: String?) {
        if (name.isNullOrBlank()) {
            editText?.error = getString(R.string.name_input_length_error)
        } else {
            editText?.error = null
            val template = getString(R.string.greetings_template)
            val formattedText = String.format(template, name)
            greetingsOutput.text = formattedText
        }
    }
}