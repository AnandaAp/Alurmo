package com.anlian.alurmo.dll

import android.content.Context
import com.anlian.alurmo.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideGoogleSignInRequest(@ApplicationContext context: Context): BeginSignInRequest.Builder =
        BeginSignInRequest
            .builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(context.resources.getString(R.string.web_client_id))
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            )

    @Provides
    @Singleton
    fun provideGoogleSignInOption(@ApplicationContext context: Context)
            : GoogleSignInOptions = GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.resources.getString(R.string.web_client_id))
        .requestEmail()
        .build()

    @Provides
    @Singleton
    fun provideGoogleSignInClient(
        @ApplicationContext context: Context,
        gso: GoogleSignInOptions
    ): GoogleSignInClient = GoogleSignIn.getClient(context, gso)

    @Provides
    @Singleton
    fun provideGoogleSignInAccount(@ApplicationContext context: Context): GoogleSignInAccount? =
        GoogleSignIn
            .getLastSignedInAccount(context)

    @Provides
    @Singleton
    fun provideGoogleCredentials(account: GoogleSignInAccount?): AuthCredential {
        val id = account?.idToken
        return GoogleAuthProvider.getCredential(id, null)
    }
}