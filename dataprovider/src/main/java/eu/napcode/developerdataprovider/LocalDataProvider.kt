package eu.napcode.developerdataprovider

import android.content.Context
import com.google.gson.Gson

class LocalDataProvider(context: Context) {

    var rawFileReader = RawFileReader(context)

    fun getDeveloper(objectClass: Class<*>): Any {
        var devString = rawFileReader.readFile(R.raw.developer)

        return Gson().fromJson(devString, objectClass)
    }

    fun getEducations(objectClass: Class<*>): Any {
        var educationsString = rawFileReader.readFile(R.raw.education)

        return Gson().fromJson(educationsString, objectClass)
    }

    fun getProjects(objectClass: Class<*>) : Any {
        var projectsString = rawFileReader.readFile(R.raw.projects)

        return Gson().fromJson(projectsString, objectClass)
    }
}