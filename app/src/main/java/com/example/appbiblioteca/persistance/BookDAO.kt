package com.example.appbiblioteca.persistance

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.appbiblioteca.database.DbConstants
import com.example.appbiblioteca.database.DbHelper
import com.example.appbiblioteca.domain.Book

class BookDAO(context: Context) {
    var db = DbHelper(context)

    fun insert(book: Book): String {
        val db = db.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DbConstants.BOOK_TITTLE, book.tittle)
        contentValues.put(DbConstants.BOOK_TYPE, book.type)
        contentValues.put(DbConstants.BOOK_AUTHOR, book.author)
        contentValues.put(DbConstants.BOOK_N_OF_PAGES, book.numberOfPages)
        contentValues.put(DbConstants.BOOK_LAST_PAGE_READ, book.lastPageRead)
        contentValues.put(DbConstants.BOOK_IS_READ, book.isRead)
        var resp_id = db.insert(DbConstants.TABLE_BOOKS, null, contentValues)
        val msg = if (resp_id != -1L) {
            "Inserido com sucesso"
        } else {
            "Erro ao inserir"
        }
        db.close()
        return msg
    }

    fun update(book: Book):
            Boolean {
        val db = db.writableDatabase
        val isBookRead = if (book.lastPageRead == book.numberOfPages) 1 else 0
        val contentValues = ContentValues()
        contentValues.put(DbConstants.BOOK_ID, book.id)
        contentValues.put(DbConstants.BOOK_TITTLE, book.tittle)
        contentValues.put(DbConstants.BOOK_TYPE, book.type)
        contentValues.put(DbConstants.BOOK_AUTHOR, book.author)
        contentValues.put(DbConstants.BOOK_N_OF_PAGES, book.numberOfPages)
        contentValues.put(DbConstants.BOOK_LAST_PAGE_READ, book.lastPageRead)
        contentValues.put(DbConstants.BOOK_IS_READ, isBookRead)
        //db.update()
        db.insertWithOnConflict(
            DbConstants.TABLE_BOOKS,
            null,
            contentValues,
            SQLiteDatabase.CONFLICT_REPLACE
        )
        db.close()
        return true
    }

    fun remove(id: Int): Int {
        val db = db.writableDatabase
        return db.delete(DbConstants.TABLE_BOOKS, "ID =?", arrayOf(id.toString()))
    }

    fun getAll(): ArrayList<Book> {
        Log.v("LOG", "GetAll")
        val db = db.writableDatabase
        val sql = "SELECT *  from " + DbConstants.TABLE_BOOKS
        val cursor = db.rawQuery(sql, null)
        val books = ArrayList<Book>()

        while (cursor.moveToNext()) {
            val livro = clienteFromCursor(cursor)
            books.add(livro)
        }
        cursor.close()
        db.close()
        Log.v("LOG", "Get ${books.size}")
        return books
    }


    fun getByName(titulo: String): ArrayList<Book> {
        Log.v("LOG", "Get By Titulo")
        val db = db.writableDatabase
        val sql =
            "SELECT *" +
                    "  from " + DbConstants.TABLE_BOOKS + "" +
                    " where ${DbConstants.BOOK_TITTLE}" +
                    " like '%$titulo%'"
        val cursor = db.rawQuery(sql, null)
        val books = ArrayList<Book>()
        while (cursor.moveToNext()) {
            val cliente = clienteFromCursor(cursor)
            books.add(cliente)
        }
        cursor.close()
        db.close()
        Log.v("LOG", "Get ${books.size}")
        return books
    }

    private fun clienteFromCursor(cursor: Cursor): Book {
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.BOOK_ID))
        val titulo = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.BOOK_TITTLE))
        val tipo = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.BOOK_TYPE))
        val autor = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.BOOK_AUTHOR))
        val numeroDePaginas =
            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.BOOK_N_OF_PAGES))
        val ultimaPaginaLida =
            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.BOOK_LAST_PAGE_READ))
        val lido = cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.BOOK_IS_READ))

        return Book(id, titulo, tipo, autor, numeroDePaginas, ultimaPaginaLida, lido)
    }
}