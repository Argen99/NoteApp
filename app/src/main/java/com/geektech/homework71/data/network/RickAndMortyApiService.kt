package com.geektech.homework71.data.network

import retrofit2.http.GET

interface RickAndMortyApiService {

    @GET("character")
    fun getAllCharacters(): MainResultDto<CharacterDto>
}