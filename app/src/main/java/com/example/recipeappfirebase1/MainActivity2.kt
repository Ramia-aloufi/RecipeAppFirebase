package com.example.recipeappfirebase1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity() {
    lateinit var al:ArrayList<Receipes>
    lateinit var rv:RecyclerView
    lateinit var btn:Button
    private val vm by lazy{ ViewModelProvider(this).get(VM::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        init()
        adapt()

        btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun init(){
        al = arrayListOf()
        rv = findViewById(R.id.rv)
        btn = findViewById(R.id.vi)

    }

    fun adapt(){
        vm.retrive().observe(this) {
                note ->
        rv.adapter = MyAdapter(note)
        rv.layoutManager = GridLayoutManager(this,2)
        }
    }
}
