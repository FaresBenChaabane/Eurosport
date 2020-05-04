package fr.benchaabane.datalayer.articles

import io.reactivex.Single
import retrofit2.http.GET

interface ArticlesApiService {

    @GET("api/json-storage/bin/edfefba")
    fun getArticles(): Single<ArticlesJson>
}