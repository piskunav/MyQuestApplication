package online.quesium.myapplication.retrofit;

import online.quesium.myapplication.model.Excursion;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface ApiService {
    @GET("quest/")
    Call<List<Excursion>> getExcursions();
}