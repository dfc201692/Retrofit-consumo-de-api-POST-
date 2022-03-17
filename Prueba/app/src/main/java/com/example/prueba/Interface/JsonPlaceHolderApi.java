package com.example.prueba.Interface;

import com.example.prueba.Model.posts;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<posts>> getposts();


}
