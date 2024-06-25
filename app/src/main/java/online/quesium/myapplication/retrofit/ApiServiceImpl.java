package online.quesium.myapplication.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceImpl {
    private static final String URL = "https://questium.online/api/";
    private static ApiService instance;
    private ApiServiceImpl(){};

    public static ApiService getInstance(){
        if (instance != null) return instance;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        instance = retrofit.create(ApiService.class);
        return instance;
    }
}

