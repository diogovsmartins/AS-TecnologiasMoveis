package com.example.appbiblioteca.database

 class DbConstants {
     companion object {
         const val DATABASE_NAME = "livros.db"
         const val DATABASE_VERSION = 3
         const val TABLE_BOOKS = "BOOKS"
         const val BOOK_ID = "ID"
         const val BOOK_TITTLE = "TITTLE"
         const val BOOK_TYPE = "TYPE"
         const val BOOK_AUTHOR = "AUTHOR"
         const val BOOK_N_OF_PAGES = "NUMBER_OF_PAGES"
         const val BOOK_LAST_PAGE_READ = "LAST_PAGE_READ"
         const val BOOK_IS_READ = "IS_READ"
     }
}