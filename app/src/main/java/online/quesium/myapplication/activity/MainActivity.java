package online.quesium.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import online.quesium.myapplication.R;
import online.quesium.myapplication.adapter.ExcursionListAdapter;
import online.quesium.myapplication.model.Excursion;
import online.quesium.myapplication.retrofit.ApiService;
import online.quesium.myapplication.retrofit.ApiServiceImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    // Объявление переменных для работы с RecyclerView и адаптером
    private RecyclerView recyclerView;
    private ExcursionListAdapter adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация сервиса API
        apiService = ApiServiceImpl.getInstance();

        // Настройка RecyclerView
        recyclerView = findViewById(R.id.excursion_list);

        // Создание экземпляра адаптера с пустым списком экскурсий
        adapter = new ExcursionListAdapter(new ArrayList<>(), this);

        // Установка адаптера и LayoutManager для RecyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Загрузка данных об экскурсиях
        loadExcursions();
    }

    // Метод для загрузки данных об экскурсиях с сервера
    private void loadExcursions() {
        // Создание вызова API
        Call<List<Excursion>> call = apiService.getExcursions();

        // Асинхронный запрос к API
        call.enqueue(new Callback<List<Excursion>>() {
            @Override
            public void onResponse(Call<List<Excursion>> call, Response<List<Excursion>> response) {
                if (response.isSuccessful()) {
                    // Если ответ успешен, получаем список экскурсий и обновляем RecyclerView
                    List<Excursion> excursionList = response.body();
                    setRecycleViewContent(excursionList);
                    Log.d("loadExcursions", "Данные успешно загружены");
                } else {
                    // В случае ошибки выводим сообщение
                    Log.e("loadExcursions", "Ошибка: " + response.errorBody());

                    Toast.makeText(MainActivity.this, "Ошибка при получении данных", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Excursion>> call, Throwable t) {
                // Обработка сбоя при запросе
                Toast.makeText(MainActivity.this, "Ошибка при получении данных", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Метод для обновления содержимого RecyclerView
    private void setRecycleViewContent(List<Excursion> excursionList ){
        // Обновление данных в адаптере на главном потоке
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setData(excursionList);
            }
        });
    }
}