package eu.napcode.resume.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import eu.napcode.resume.model.Developer

@Entity
data class DeveloperEntity(

        @PrimaryKey
        val id: Int,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("surname")
        val surname: String,

        @field:SerializedName("role")
        val role: String,

        @field:SerializedName("summary")
        val summary: String,

        @field:SerializedName("mail")
        val mail: String,

        @field:SerializedName("home")
        val home: String,

        @field:SerializedName("github")
        val github: String,

        @field:SerializedName("gitlab")
        val gitlab: String,

        @field:SerializedName("playstore")
        val playStore: String,

        @field:SerializedName("highlights")
        val highlights: String,

        @field:SerializedName("skills")
        val skills: String
) {
        constructor(developer: Developer) :
                this(developer.id, developer.name, developer.surname, developer.role,
                        developer.summary, developer.mail, developer.home, developer.github,
                        developer.gitlab, developer.playStore, developer.highlights, developer.skills)
}


