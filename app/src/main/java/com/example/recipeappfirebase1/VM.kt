package com.example.recipeappfirebase1

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VM(application: Application): AndroidViewModel(application) {
    var app = application
    var TAG = "VM"
    val db = Firebase.firestore
    private var note: MutableLiveData<MutableList<Receipes>> = MutableLiveData()


    fun addNote(note: Receipes) {
        db.collection("receipe")
            .add(note)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(app, "${note.author} Saved Successfully ", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
        retrive()
    }

    fun retrive(): MutableLiveData<MutableList<Receipes>> {
        var title :String = ""
        var ingredients:String= ""
        var author :String? = ""
        var instructions:String?= ""
        var id:String? = null
        CoroutineScope(Dispatchers.IO).launch {
            var mynotes: MutableList<Receipes> = mutableListOf()
            db.collection("receipe")
                .get()
                .addOnSuccessListener { notes ->
                    for (note in notes) {
                        note.data.map { (key, value) ->
                            when(key){
                                "title" -> title = value.toString()
                                "ingredients" -> ingredients = value.toString()
                                "author" -> author = value.toString()
                                "instructions" -> instructions = value.toString()
                            }


                        }
                        mynotes.add(Receipes(note.id,title!!,author!!,ingredients!!,instructions!!))

                    }
                    Log.d(TAG,"value:$mynotes")

                    note.postValue(mynotes)
                }
        }
            Log.d(TAG, "$note")
            return note

        }




//    fun update(edittextString: String, note: Receipes) {
//        CoroutineScope(Dispatchers.IO).launch {
//
//            db.collection("receipe")
//                .get()
//                .addOnSuccessListener { res ->
//                    for (i in res) {
//                        if (i.id == note.id) {
//                            db.collection("receipe").document(note.id!!)
//                                .update("note", edittextString)
//                            Toast.makeText(app, "Updating successfuly", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//
//            .addOnFailureListener {
//                Log.d(TAG, "error Update")
//                Toast.makeText(app, "Updating Failure", Toast.LENGTH_SHORT).show()
//            }}
//        retrive()
//    }
//
//    fun delete(note: Receipes) {
//        CoroutineScope(Dispatchers.IO).launch {
//            db.collection("receipe")
//                .get()
//                .addOnSuccessListener { res ->
//                    for (i in res) {
//                        if (i.id == note.id) {
//                            db.collection("receipe").document(note.id!!).delete()
//                            Toast.makeText(app, "deleting successfuly", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//                .addOnFailureListener {
//                    Log.d(TAG, "error deleting")
//                    Toast.makeText(app, "deleting Failure", Toast.LENGTH_SHORT).show()
//                }
//            retrive()
//        }
//    }
}


