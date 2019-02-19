package com.bud.app.di

import com.bud.app.di.qualifiers.AccessToken
import dagger.Module
import dagger.Provides

@Module
class SecurityModule {
    @Provides
    @AccessToken
    fun providesAccessToken(): String =
        "e6146aa90fbef9f4706c13443c7653f48409afd91c8c79e876613840e340252d"
}