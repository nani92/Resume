package eu.napcode.resume.entity

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class DeveloperEntity(

        val id: Int,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("surname")
        val surname: String,

        @field:SerializedName("role")
        val role: String,

        @field:SerializedName("mail")
        val mail: String,

        @field:SerializedName("home")
        val home: String,

        @field:SerializedName("github")
        val github: String,

        @field:SerializedName("gitlab")
        val gitlab: String
)


