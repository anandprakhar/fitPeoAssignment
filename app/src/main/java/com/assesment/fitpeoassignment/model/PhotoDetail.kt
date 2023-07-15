package com.assesment.fitpeoassignment.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class for photo detail
 */
@Entity(tableName = "photo")
data class PhotoDetail(
    val albumId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int, val title: String?,
    val url: String?, val thumbnailUrl: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(albumId)
        dest.writeInt(id)
        dest.writeString(title)
        dest.writeString(url)
        dest.writeString(thumbnailUrl)
    }

    companion object CREATOR : Parcelable.Creator<PhotoDetail> {
        override fun createFromParcel(parcel: Parcel): PhotoDetail {
            return PhotoDetail(parcel)
        }

        override fun newArray(size: Int): Array<PhotoDetail?> {
            return arrayOfNulls(size)
        }
    }

}
