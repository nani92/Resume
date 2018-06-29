package eu.napcode.developerdataprovider

import android.content.Context

class LocalDataProvider(context: Context) {

    var rawFileReader = RawFileReader(context)

    fun getDeveloperJson(): String? {
        return rawFileReader.readFile(R.raw.developer)
    }
}