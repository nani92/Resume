package eu.napcode.resume.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException

import java.lang.reflect.Type

enum class ProjectType {
    OWN,
    COMMERCIAL,
    OPEN_SOURCE,
    OTHER,
    ALL
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