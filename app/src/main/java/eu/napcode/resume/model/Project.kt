package eu.napcode.resume.model

import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.Nullable
import com.google.gson.annotations.SerializedName
import eu.napcode.resume.entity.ProjectEntity

data class Project(

        val id: Int,

        @Nullable
        val startYear: Int?,

        @Nullable
        val startMonth: Int?,

        @Nullable
        val name: String?,

        @Nullable
        val description: String?,

        val tech: Array<String>,

        val type: ProjectType = ProjectType.OTHER,

        @Nullable
        val company: String?,

        @Nullable
        val github: String?,

        @Nullable
        @SerializedName("playStore")
        val playstore: String?,

        @Nullable
        val links: Array<String>) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArray(),
            parcel.readParcelable(ProjectType::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArray()) {
    }

    constructor(projectEntity: ProjectEntity) : this(
            id = projectEntity.id,
            startYear = projectEntity.startYear,
            startMonth = projectEntity.startMonth,
            name = projectEntity.name,
            description = projectEntity.description,
            tech = projectEntity.tech,
            type = projectEntity.type,
            company = projectEntity.company,
            github = projectEntity.github,
            links = projectEntity.links,
            playstore = projectEntity.playstore
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeValue(startYear)
        parcel.writeValue(startMonth)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeStringArray(tech)
        parcel.writeParcelable(type, flags)
        parcel.writeString(company)
        parcel.writeString(github)
        parcel.writeString(playstore)
        parcel.writeStringArray(links)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Project> {
        override fun createFromParcel(parcel: Parcel): Project {
            return Project(parcel)
        }

        override fun newArray(size: Int): Array<Project?> {
            return arrayOfNulls(size)
        }
    }
}
