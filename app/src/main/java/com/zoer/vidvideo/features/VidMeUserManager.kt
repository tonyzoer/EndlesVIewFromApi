package com.zoer.vidvideo.features

import com.zoer.vidvideo.api.RestApi
import com.zoer.vidvideo.models.VidMeAppModel
import com.zoer.vidvideo.models.VidMeUserModel
import rx.Observable

/**
 * Created by mafio on 11/23/2017.
 */
class VidMeUserManager(private val api: RestApi = RestApi()) {
    fun getUser(username:String, password:String):Observable<VidMeUserModel> {
        return Observable.create { subscriber ->
            val callResponse = api.getUser(username,password)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val dataresponse = response.body()!!
                val user = VidMeUserModel(dataresponse.user.user_id, dataresponse.user.username, dataresponse.user.full_url, dataresponse.user.avatar_url, dataresponse.auth.token)
                subscriber.onNext(user)
                subscriber.onCompleted()
            }else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}