package com.huukhuongit.novelreader.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.huukhuongit.novelreader.models.ChapterModel
import com.huukhuongit.novelreader.models.NovelModel
import java.text.SimpleDateFormat

class NovelReaderSQLiteHelpers(context: Context) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME, null,
        DATABASE_VERSION
    ) {

    companion object {
        private const val DATABASE_NAME = "NovelReaderManager"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NOVELS = "novels"
        private const val NOVEL_ID = "id"
        private const val NOVEL_THUMBNAIL = "thumbnail"
        private const val NOVEL_NAME = "name"
        private const val NOVEL_AUTHOR = "author"
        private const val NOVEL_DESCRIPTION = "description"
        private const val NOVEL_CHAPTERS = "chapters"
        private const val NOVEL_IS_DONE = "isDone"
        private const val NOVEL_UPLOADED_AT = "uploadedAt"
        private const val NOVEL_UPDATED_AT = "updatedAt"
        private const val NOVEL_IS_DELETED = "isDeleted"
        private const val NOVEL_READS = "reads"

        private const val TABLE_CHAPTERS = "chapters"
        private const val CHAPTER_ID = "id"
        private const val CHAPTER_TITLE = "title"
        private const val CHAPTER_CONTENT = "content"
        private const val CHAPTER_NOVEL_ID = "novelId"
        private const val CHAPTER_IS_DELETED = "isDeleted"
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        // create table novels
        var sql = String.format(
            "CREATE TABLE %s(" +
                    "%s INTEGER PRIMARY KEY, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s INTEGER, " +
                    "%s INTEGER, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s INTEGER, " +
                    "%s INTEGER)",
            TABLE_NOVELS,
            NOVEL_ID,
            NOVEL_THUMBNAIL,
            NOVEL_NAME,
            NOVEL_AUTHOR,
            NOVEL_DESCRIPTION,
            NOVEL_CHAPTERS,
            NOVEL_IS_DONE,
            NOVEL_UPLOADED_AT,
            NOVEL_UPDATED_AT,
            NOVEL_IS_DELETED,
            NOVEL_READS
        )
        sqLiteDatabase.execSQL(sql)

        // create table chapters
        sql = String.format(
            "CREATE TABLE %s(" +
                    "%s INTEGER PRIMARY KEY, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s INTEGER, " +
                    "%s INTEGER)",
            TABLE_CHAPTERS,
            CHAPTER_ID,
            CHAPTER_TITLE,
            CHAPTER_CONTENT,
            CHAPTER_NOVEL_ID,
            CHAPTER_IS_DELETED
        )
        sqLiteDatabase.execSQL(sql)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, p1: Int, p2: Int) {
        var sql = String.format("DROP TABLE IF EXISTS %s", TABLE_NOVELS)
        sqLiteDatabase.execSQL(sql)
        sql = String.format("DROP TABLE IF EXISTS %s", TABLE_CHAPTERS)
        sqLiteDatabase.execSQL(sql)

        onCreate(sqLiteDatabase)
    }

    @SuppressLint("SimpleDateFormat")
    fun add(novel: NovelModel) {
        var db = this.writableDatabase

        val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        // add novel to novels table
        val novelValues = ContentValues()
        novelValues.put(NOVEL_ID, novel.id)
        novelValues.put(NOVEL_THUMBNAIL, novel.thumbnail)
        novelValues.put(NOVEL_NAME, novel.name)
        novelValues.put(NOVEL_AUTHOR, novel.author)
        novelValues.put(NOVEL_DESCRIPTION, novel.description)
        novelValues.put(NOVEL_CHAPTERS, novel.chapters)
        novelValues.put(NOVEL_IS_DONE, novel.isDone)
        novelValues.put(NOVEL_UPLOADED_AT, sdf.format(novel.uploadedAt!!))
        novelValues.put(NOVEL_UPDATED_AT, sdf.format(novel.updatedAt!!))
        novelValues.put(NOVEL_IS_DELETED, novel.isDeleted)
        novelValues.put(NOVEL_READS, novel.reads)

        db.insert(TABLE_NOVELS, null, novelValues)

        // add list chapters to chapters table
        for (item in novel.listChapters!!) {
            val chapterValues = ContentValues()
            chapterValues.put(CHAPTER_ID, item.id)
            chapterValues.put(CHAPTER_TITLE, item.title)
            chapterValues.put(CHAPTER_CONTENT, item.content)
            chapterValues.put(CHAPTER_NOVEL_ID, item.novelId)
            chapterValues.put(CHAPTER_IS_DELETED, item.isDeleted)
            db.insert(TABLE_CHAPTERS, null, chapterValues)

            Log.e("ADD CHAP", item.title!!)
        }

        db.close()
    }

    fun getListNovels(): ArrayList<NovelModel> {
        val list = ArrayList<NovelModel>()

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        val db = this.readableDatabase
        val sql = "SELECT * FROM $TABLE_NOVELS ORDER BY $NOVEL_ID DESC"

        val cursor: Cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val novel = NovelModel()
            novel.id = cursor.getInt(0)
            novel.thumbnail = cursor.getString(1)
            novel.name = cursor.getString(2)
            novel.author = cursor.getString(3)
            novel.description = cursor.getString(4)
            novel.chapters = cursor.getInt(5)
            novel.isDone = cursor.getInt(6) == 1
            novel.uploadedAt = sdf.parse(cursor.getString(7))
            novel.updatedAt = sdf.parse(cursor.getString(8))
            novel.isDeleted = cursor.getInt(9) == 1
            novel.reads = cursor.getInt(10)
            list.add(novel)
            cursor.moveToNext()
        }
        return list
    }

    fun getListChaptersByNovelId(novelId: Int): ArrayList<ChapterModel> {
        val list = ArrayList<ChapterModel>()

        val db = this.writableDatabase
        val sql = "SELECT * FROM $TABLE_CHAPTERS WHERE $CHAPTER_NOVEL_ID=$novelId"

//        val cursor: Cursor = db.rawQuery(sql, null)
//        while (!cursor.isAfterLast) {
//            val chapter = ChapterModel()
//            chapter.id = cursor.getInt(0)
//            chapter.title = cursor.getString(1)
//            chapter.content = cursor.getString(2)
//            chapter.novelId = cursor.getInt(3)
//            chapter.isDeleted = cursor.getInt(4) == 1
//            Log.e("CURSOR", cursor.getString(1))st = sqlHelpers.getListNovels()
//        for (item in list) {
//            Log.e("ITEM", item.name!!)
//        }
//            list.add(chapter)
//        }

        return list
    }

}