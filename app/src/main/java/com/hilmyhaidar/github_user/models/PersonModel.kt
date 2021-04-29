package com.hilmyhaidar.github_user.models

import android.content.Context
import android.os.Parcelable
import com.hilmyhaidar.github_user.helpers.JsonHelper
import kotlinx.parcelize.Parcelize
import org.json.JSONArray
import org.json.JSONObject

@Parcelize
data class PersonModel(
    val username: String,
    val name: String,
    val avatar: String,
    val company: String,
    val location: String,
    val repository: Number,
    val follower: Number,
    val following: Number
) : Parcelable {
    companion object {
        private fun fromJson(jsonObject: JSONObject) : PersonModel {
            return PersonModel(
                username = jsonObject.getString("username"),
                name = jsonObject.getString("name"),
                avatar = jsonObject.getString("avatar"),
                company = jsonObject.getString("company"),
                location = jsonObject.getString("location"),
                repository = jsonObject.getInt("repository"),
                follower = jsonObject.getInt("follower"),
                following = jsonObject.getInt("following"),
            )
        }

        fun loadJsonAssetToModel(context: Context, filename: String) : ArrayList<PersonModel> {
            val persons: ArrayList<PersonModel> = ArrayList()

            val jsonObject = JsonHelper(context).loadFile(filename)

            if(jsonObject is JSONArray)
                for (i in 0 until jsonObject.length())
                    persons.add(fromJson(jsonObject[i] as JSONObject))
            else if(jsonObject is JSONObject)
                    persons.add(fromJson(jsonObject))

            return persons
        }
    }

    fun getResourceId(context: Context): Int {
        return context.resources.getIdentifier(avatar, null, context.packageName)
    }
}