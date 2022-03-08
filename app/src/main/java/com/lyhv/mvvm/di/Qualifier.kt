package com.lyhv.mvvm.di

import javax.inject.Qualifier

class Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BasicAuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TokenAuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoshiBase