package com.hilmyhaidar.github_user.helpers

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class JsonHelper(private val context: Context) {
    companion object {
        const val EMPTY_OBJECT = "{}"
    }

    fun loadFile(filename: String): Any? {
        val jsonString = loadString(filename)
        return try {
            JSONArray(jsonString)
        } catch (exception: Exception) {
            exception.printStackTrace()
            JSONObject(jsonString)
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

    private fun loadString(filename: String) : String {
        var input: InputStream? = null
        try {
            input = context.assets.open(filename)
            val buffer = ByteArray(input.available())
            input.read(buffer)

            return String(buffer)
        } catch (exception: Exception) {
            exception.printStackTrace()
        } finally {
            input?.close()
        }
        return EMPTY_OBJECT
    }
}