package com.zoer.vidvideo.models

import android.os.Parcel
import android.os.Parcelable

data class VidVideoModel(val id: String, val title: String, val thumbnail: String, val videoUrl: String, var score: Int, val formats: List<Format>) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.createTypedArrayList(Format)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(thumbnail)
        parcel.writeString(videoUrl)
        parcel.writeInt(score)
        parcel.writeTypedList(formats)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VidVideoModel> {
        override fun createFromParcel(parcel: Parcel): VidVideoModel {
            return VidVideoModel(parcel)
        }

        override fun newArray(size: Int): Array<VidVideoModel?> {
            return arrayOfNulls(size)
        }
    }
}

data class Format(val type: String, val uri: String, val version: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(uri)
        parcel.writeInt(version)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Format> {
        override fun createFromParcel(parcel: Parcel): Format {
            return Format(parcel)
        }

        override fun newArray(size: Int): Array<Format?> {
            return arrayOfNulls(size)
        }
    }

}