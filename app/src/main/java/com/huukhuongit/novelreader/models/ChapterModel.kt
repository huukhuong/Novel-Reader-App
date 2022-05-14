package com.huukhuongit.novelreader.models

import java.util.Date

class ChapterModel {

    var id: Int? = null
    var title: String? = null
    var content: String? = null
    var novelId: Int? = null
    var createdAt: Date? = null
    var isDeleted: Boolean? = null

    constructor()
    constructor(
        id: Int?,
        title: String?,
        content: String?,
        novelId: Int?,
        createdAt: Date?,
        isDeleted: Boolean?
    ) {
        this.id = id
        this.title = title
        this.content = content
        this.novelId = novelId
        this.createdAt = createdAt
        this.isDeleted = isDeleted
    }
}