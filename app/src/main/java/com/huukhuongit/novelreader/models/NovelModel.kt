package com.huukhuongit.novelreader.models

import java.util.Date

class NovelModel {

    var thumbnail: String? = null
    var name: String? = null
    var author: String? = null
    var description: String? = null
    var chapters: Int? = null
    var isDone: Boolean? = null
    var isPopular: Boolean? = null
    var isRecommended: Boolean? = null
    var uploadedAt: Date? = null
    var updatedAt: Date? = null
    var isDeleted: Boolean? = null

    constructor()
    constructor(
        thumbnail: String,
        name: String,
        author: String,
        description: String,
        chapters: Int,
        isDone: Boolean,
        isPopular: Boolean,
        isRecommended: Boolean,
        uploadedAt: Date,
        updatedAt: Date,
        isDeleted: Boolean
    ) {
        this.thumbnail = thumbnail
        this.name = name
        this.author = author
        this.description = description
        this.chapters = chapters
        this.isDone = isDone
        this.isPopular = isPopular
        this.isRecommended = isRecommended
        this.uploadedAt = uploadedAt
        this.updatedAt = updatedAt
        this.isDeleted = isDeleted
    }
}