package com.huukhuongit.novelreader.models

import com.google.gson.annotations.SerializedName
import java.util.Date

class NovelModel {

    var id: Int? = null
    var thumbnail: String? = null
    var name: String? = null
    var author: String? = null
    var description: String? = null
    var chapters: Int? = null
    var isDone: Boolean? = null
    var uploadedAt: Date? = null
    var updatedAt: Date? = null
    var isDeleted: Boolean? = null
    var listChapters: ArrayList<ChapterModel>? = null
    var reads: Int? = 0

    constructor()
    constructor(
        id: Int,
        thumbnail: String,
        name: String,
        author: String,
        description: String,
        chapters: Int,
        isDone: Boolean,
        uploadedAt: Date,
        updatedAt: Date,
        isDeleted: Boolean,
        reads: Int
    ) {
        this.id = id
        this.thumbnail = thumbnail
        this.name = name
        this.author = author
        this.description = description
        this.chapters = chapters
        this.isDone = isDone
        this.uploadedAt = uploadedAt
        this.updatedAt = updatedAt
        this.isDeleted = isDeleted
        this.reads = reads
    }
}