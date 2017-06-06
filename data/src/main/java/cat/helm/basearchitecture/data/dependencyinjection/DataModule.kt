package cat.helm.basearchitecture.data.dependencyinjection

import cat.helm.basearchitecture.data.entity.TvShowDataEntity
import cat.helm.basearchitecture.data.repository.datasource.CacheDataSource
import cat.helm.basearchitecture.data.repository.datasource.ReadableDataSource
import cat.helm.basearchitecture.data.repository.query.Query
import cat.helm.basearchitecture.data.repository.tvshow.TvShowApiDataSource
import cat.helm.basearchitecture.data.repository.tvshow.TvShowCacheDataSource
import cat.helm.basearchitecture.data.repository.tvshow.TvShowDataRepository
import cat.helm.basearchitecture.repository.TvShowRepository
import cat.helm.data.basearchitecture.BuildConfig
import cat.helm.ureentool.data.dependencyinjection.qualifier.queries.DefaultQueries
import cat.helm.ureentool.data.repository.datasource.SystemTimeProvider
import cat.helm.ureentool.data.repository.datasource.TimeProvider
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Borja on 4/1/17.
 */
@Module
class DataModule {

    @Provides
    @ElementsIntoSet
    @Singleton
    @DefaultQueries
    fun provideDefaultQueries(): MutableSet<Query> {
        return LinkedHashSet()
    }

    @Singleton
    @Provides
    fun providesOkhttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor {
                    chain ->

                    val request = chain.request()
                    val originalUrl = request.url()
                    val url = originalUrl.newBuilder()
                            .addQueryParameter("api_key", BuildConfig.API_KEY)
                            .build()

                    val authenticatedRequest = request.newBuilder().url(url).build()
                    chain.proceed(authenticatedRequest)

                }

                .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providesTvShowReadableDataSource(tvShowApiDataSource: TvShowApiDataSource): ReadableDataSource<Int, TvShowDataEntity> = tvShowApiDataSource

    @Provides
    @Singleton
    fun providesTvShowRepository(tvShowDataRepository: TvShowDataRepository): TvShowRepository = tvShowDataRepository

    @Singleton
    @Provides
    fun providesTvShowCacheDataSource(tvShowCacheDataSource: TvShowCacheDataSource): CacheDataSource<Int, TvShowDataEntity> = tvShowCacheDataSource

    @Provides
    @Singleton
    fun providesTTLCache(): Long {
        return 60000
    }
    @Provides
    @Singleton
    fun providesTimeProvider(): TimeProvider {
        return SystemTimeProvider()
    }

}
