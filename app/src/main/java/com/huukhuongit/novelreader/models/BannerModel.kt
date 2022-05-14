package com.huukhuongit.novelreader.models

class BannerModel {

    var id: Int? = null
    var thumbnail: String? = null
    var description: String? = null
    var isShow: Boolean? = true

    constructor()
    constructor(id: Int?, thumbnail: String?, isShow: Boolean?) {
        this.id = id
        this.thumbnail = thumbnail
        this.isShow = isShow
    }

}