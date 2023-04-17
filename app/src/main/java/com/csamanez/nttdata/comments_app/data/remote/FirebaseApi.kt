package com.csamanez.nttdata.comments_app.data.remote

import com.google.firebase.auth.FirebaseAuth

interface FirebaseApi {

    suspend fun getInstanceFirebaseAuth() : FirebaseAuth

}