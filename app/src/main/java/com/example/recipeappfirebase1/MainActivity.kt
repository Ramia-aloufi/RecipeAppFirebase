package com.example.recipeappfirebase1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    lateinit var title: EditText
    lateinit var author: EditText
    lateinit var ingredients: EditText
    lateinit var instructions: EditText
    lateinit var save: Button
    lateinit var view: Button
    private val vm by lazy{ ViewModelProvider(this).get(VM::class.java)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        save.setOnClickListener {
            vm.addNote(gettext())
        }
        view.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    fun init() {
        title = findViewById(R.id.title)
        author = findViewById(R.id.author)
        ingredients = findViewById(R.id.ingredients)
        instructions = findViewById(R.id.instructions)
        view = findViewById(R.id.View)
        save = findViewById(R.id.Save)
    }

    fun gettext(): Receipes {
        val aa = title.text.toString()
        val bb = author.text.toString()
        val cc = ingredients.text.toString()
        val dd = instructions.text.toString()
        return Receipes(null,aa,bb,cc,dd)



    }



    }


