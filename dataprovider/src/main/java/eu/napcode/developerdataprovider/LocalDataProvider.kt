package eu.napcode.developerdataprovider

import android.content.Context
import com.google.gson.Gson

class LocalDataProvider(context: Context) {

    var rawFileReader = RawFileReader(context)

    fun getDeveloper(objectClass: Class<*>): Any {
        var devString = rawFileReader.readFile(R.raw.developer)

        return Gson().fromJson(devString, objectClass)
    }
}