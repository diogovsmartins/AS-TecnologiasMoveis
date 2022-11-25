package com.example.appbiblioteca.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper (context: Context) :
    SQLiteOpenHelper(context, DbConstants.DATABASE_NAME , null, DbConstants.DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase) {
        val sql =
            "CREATE TABLE ${DbConstants.TABLE_BOOKS} (${DbConstants.BOOK_ID}  INTEGER PRIMARY KEY " +
                    "AUTOINCREMENT, ${DbConstants.BOOK_TITTLE} TEXT,${DbConstants.BOOK_TYPE} TEXT," +
                    " ${DbConstants.BOOK_AUTHOR} TEXT, ${DbConstants.BOOK_N_OF_PAGES} INTEGER, " +
                    "${DbConstants.BOOK_LAST_PAGE_READ} INTEGER, ${DbConstants.BOOK_IS_READ} TINYINT)"
        db.execSQL(sql)
        Log.e("LOG", "Criando Tabela livros")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.DATABASE_NAME)
        onCreate(db)
    }

}