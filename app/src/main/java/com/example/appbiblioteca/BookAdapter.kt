package com.example.appbiblioteca

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appbiblioteca.domain.Book

class BookAdapter (private val books: List<Book>):
        RecyclerView.Adapter<BookAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        Log.v("LOG", "onCreate view holder")
        val v= LayoutInflater.from(parent.context).inflate(R.layout.livro,parent,false)
        val vh = VH(v)
        vh.itemView.setOnClickListener{
            val book = books[vh.adapterPosition]
            val intent = Intent(parent.context, ListBooks::class.java)
            intent.putExtra("livro", book)
            parent.context.startActivity(intent)
        }
        return vh
    }
    override fun getItemCount(): Int {
        return books.size
    }


    override fun onBindViewHolder(holder: VH, position: Int) {
        Log.v("LOG", "ViewHolder")
        val book = books[position]
        val isBookRead = book.isRead.compareTo(1) == 0

        holder.txtLivroTitulo.text = book.tittle
        holder.txtLivroTipo.text = book.type
        holder.txtLivroAutor.text = book.author
        holder.txtLivroNumeroPaginas.text = book.numberOfPages.toString()
        holder.txtLivroUltimaPagina.text = book.lastPageRead.toString()
        holder.txtLivroLido.text = isBookRead.toString()

    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {

        var txtLivroTitulo = view.findViewById<TextView>(R.id.livro_titulo)
        var txtLivroAutor = view.findViewById<TextView>(R.id.livro_autor)
        var txtLivroTipo = view.findViewById<TextView>(R.id.livro_tipo)
        var txtLivroNumeroPaginas = view.findViewById<TextView>(R.id.livro_numero_de_paginas)
        var txtLivroUltimaPagina = view.findViewById<TextView>(R.id.livro_ultima_pagina)
        var txtLivroLido = view.findViewById<TextView>(R.id.livro_lido)

        init {
            // Define click listener for the ViewHolder's View.
             txtLivroTitulo = view.findViewById(R.id.livro_titulo)
             txtLivroTipo = view.findViewById(R.id.livro_tipo)
             txtLivroAutor = view.findViewById(R.id.livro_autor)
             txtLivroNumeroPaginas = view.findViewById(R.id.livro_numero_de_paginas)
             txtLivroUltimaPagina = view.findViewById(R.id.livro_ultima_pagina)
             txtLivroLido = view.findViewById(R.id.livro_lido)
        }
    }

}