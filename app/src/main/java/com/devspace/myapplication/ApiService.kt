    package com.devspace.myapplication

    import retrofit2.http.GET
    import retrofit2.http.Query

    interface ApiService {

        @GET("recipes/random")
        suspend fun getRandomRecipes(
            @Query("number") number: Int = 20
        ): RecipeResponse

    }