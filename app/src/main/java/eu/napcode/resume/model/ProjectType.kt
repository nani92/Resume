package eu.napcode.resume.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException

import java.lang.reflect.Type

enum class ProjectType : Parcelable {
    OWN,
    COMMERCIAL,
    OPEN_SOURCE,
    OTHER,
    ALL;

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ordinal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProjectType> {
        override fun createFromParcel(parcel: Parcel): ProjectType {
            return ProjectType.values()[parcel.readInt()]
        }

        override fun newArray(size: Int): Array<ProjectType?> {
            return arrayOfNulls(size)
        }
    }
}

internal class ProjectTypeDeserializer : JsonDeserializer<ProjectType> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ProjectType? {

        val types = ProjectType.values()

        for (type in types) {

            if (json.asString.equals(type.toString(), ignoreCase = true)) {
                return type
            }
        }

        return ProjectType.OTHER
    }
}