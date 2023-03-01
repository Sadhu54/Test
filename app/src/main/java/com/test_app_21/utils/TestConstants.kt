package com.test_app_21.utils

class TestConstants {

    enum class Keys(val value:String)
    {
        GMAIL_SERVER_KEY("488717333656-hl36gneg3c3bnbqdufnfcqdfsbd5csni.apps.googleusercontent.com")
    }

    enum class PrefConstants ( val value :String)
    {
        PREFERENCE_NAME("test_preferences"),
        IS_LOGGED_IN("IS_LOGGED_IN"),
        NAME("NAME"),
    }

    object URL
    {
        const val BASE_URL="https://jsonplaceholder.typicode.com/"
    }

    object Endpoints
    {
        const val  photoList=("photos")
    }
}