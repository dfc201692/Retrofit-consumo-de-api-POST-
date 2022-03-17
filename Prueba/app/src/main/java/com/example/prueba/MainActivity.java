package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.prueba.Interface.JsonPlaceHolderApi;
import com.example.prueba.Model.posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTxtView = findViewById(R.id.jsonText);
        getPosts();


    }


    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<posts>> call = jsonPlaceHolderApi.getposts();
        call.enqueue(new Callback<List<posts>>() {
            @Override
            public void onResponse(Call<List<posts>> call, Response<List<posts>> response) {

                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+response.code());
                    return;
                }

                List<posts> postsList = response.body();
                for (posts post: postsList){
                    String content = "";
                    content += "userId: "+post.getUserId() + "\n";
                    content += "id: "+post.getId() + "\n";
                    content += "title: "+post.getTitle() + "\n";
                    content += "body: "+post.getBody() + "\n\n";
                    mJsonTxtView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<posts>> call, Throwable t) {
                mJsonTxtView.setText(t.getMessage());
            }
        });


    }



}