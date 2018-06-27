package eu.napcode.resume.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

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
        val gitlab: String
)


