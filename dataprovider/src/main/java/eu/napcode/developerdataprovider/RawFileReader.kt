package eu.napcode.developerdataprovider

import android.content.Context
import java.io.IOException

class RawFileReader(val context: Context) {

    fun readFile(rawId: Int) : String? {
        var inputStream = context.resources.openRawResource(rawId)

        return try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            null
        }
    }
}