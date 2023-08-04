package com.example.nearbyplaces.helper

object Constants {
    //https://api.foursquare.com/v2/venues/search?ll=53.9057644,27.5582305&radius=4000&limit=30&client_id=RW2KJZ100XEDBNHIUKEJJ2G0RSS2FNBLTFZAW2W4EL3LPIOY&client_secret=E23E1IMQDOZTC4ZX5AY4UYIIQZZ5OE2M0ZPKVOBDDHDLWHLF&v=20233107
    const val AUTHORIZATION = "fsq3MVO6HpccxvsMqOVRtpwEjI28YIM1rteqNl6TjCRMRKU="
    const val BASE_URL = "https://api.foursquare.com/"
    const val CLIENT_ID = "TMXHGIJKV0JA4YIHXU0O1I0FFGQ0NTQX5LDZAQRMZBIM1AIF"

    const val REDIRECT_URL = "foursquareapplication://foursquare_auth"

    const val SHARED_PREF_NAME = "auth_prefs"
    const val TOKEN_KEY = "auth_token"

}