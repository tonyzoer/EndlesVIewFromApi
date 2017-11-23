package com.zoer.vidvideo.features

import com.zoer.vidvideo.api.RestApi
import com.zoer.vidvideo.models.VidMeAppModel

import rx.Observable
import java.util.*

/**
 * Created by mafio on 11/23/2017.
 */
class VidMeApplicationManager(private val api: RestApi = RestApi()) {
    fun getAppInfo(auth: String = "Basic YXRFanhUeGU3NERaa2J5aUUzZmVla3VwRGtRTmduSTA6NDdWaTVXRXowWFlPWTlIanI5Y1RHYzcwbGxOS1VlQlpwdFk4SmpBZA=="): Observable<VidMeAppModel> {
        return Observable.create { subscriber ->
            val callResponse = api.getApp(auth)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val dataresponse = response.body()!!
                dataresponse.application
                val app = VidMeAppModel(dataresponse.application.application_id, dataresponse.application.client_id, dataresponse.application.name, dataresponse.application.website, dataresponse.application.description, dataresponse.application.organization, dataresponse.application.redirect_uri_prefix)
                subscriber.onNext(app)
                subscriber.onCompleted()
            }else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}