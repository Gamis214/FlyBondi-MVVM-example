package com.srappetito.flybonditestmvvm.network.ktor

/*import com.srappetito.flybonditestmvvm.network.ktor.services.KtorApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorBuilder {

    private const val BASE_URL = "https://api.jsonbin.io"

    @Singleton
    @Provides
    fun getKtorClient(): HttpClient {
        return HttpClient(CIO){
            install(ContentNegotiation){
                json(
                    Json{
                        isLenient = true
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    }
                )
            }
            install(Logging){
                level = LogLevel.ALL
            }
            install(DefaultRequest){
                url(BASE_URL)
                header("X-Access-Key"," \$2a\$10\$cmbPTnC25e3ZpbAHP6BvzOtVkwObDpN8rkhdFSSLodlxvUz6pHYHi")
            }
        }
    }

    @Singleton
    @Provides
    fun provideKtorApiServices(ktorClient: HttpClient): KtorApiServices{
        return KtorApiServices(ktorClient)
    }
}*/