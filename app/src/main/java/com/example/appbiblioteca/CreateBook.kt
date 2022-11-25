package com.example.appbiblioteca

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.appbiblioteca.domain.Book
import com.example.appbiblioteca.persistance.BookDAO

class CreateBook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_livro)
        val saveButton = findViewById<Button>(R.id.save_book)
        val bookDAO = BookDAO(this)

        saveButton.setOnClickListener {
            bookDAO.insert(getValuesFromForms())
            val intent = Intent(this, ListBooks::class.java)
            startActivity(intent)
        }
    }

    private fun getValuesFromForms(): Book {
        val bookTittle = findViewById<EditText>(R.id.edt_titulo_add)
        val bookAuthor = findViewById<EditText>(R.id.edt_autor_add)
        val bookType = findViewById<EditText>(R.id.edt_tipo_add)
        val numberOfPages = findViewById<EditText>(R.id.edt_paginas_add)

        return Book(
            null,
            bookTittle.text.toString(),
            bookAuthor.text.toString(),
            bookType.text.toString(),
            numberOfPages.text.toString().toInt(),
            null,
            0,
        )
    }
}