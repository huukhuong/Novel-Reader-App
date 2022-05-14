package com.huukhuongit.novelreader.models

class CategoryModel {

    var id: Int? = null
    var name: String? = null
    var description: String? = null
    var isDeleted: Boolean? = null
    var listNovels: ArrayList<NovelModel>? = null

    constructor()
    constructor(
        id: Int,
        name: String,
        description: String,
        isDeleted: Boolean
    ) {
        this.id = id
        this.name = name
        this.description = description
        this.isDeleted = isDeleted
    }

}