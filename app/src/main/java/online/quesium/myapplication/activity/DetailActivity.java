package online.quesium.myapplication.activity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import online.quesium.myapplication.R;

public class DetailActivity extends AppCompatActivity {
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        // Инициализация GestureDetector для обработки свайпа назад
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // Проверяем, был ли свайп слева направо
                if (e2.getX() > e1.getX()) {
                    onBackPressed();
                    return true;
                }
                return false;
            }
        });

        // Извлечение данных из Intent
        String name = getIntent().getStringExtra("name");
        String fullDescription = getIntent().getStringExtra("full_description");
        String price = getIntent().getStringExtra("price");
        String cover = getIntent().getStringExtra("cover");
        int category = getIntent().getIntExtra("category", -1);

        // Заполнение макета данными
        TextView nameView = findViewById(R.id.name);
        TextView descriptionView = findViewById(R.id.full_description);
        TextView priceView = findViewById(R.id.price);
        ImageView coverView = findViewById(R.id.cover);

        // Установка значений
        nameView.setText(name);
        descriptionView.setText(fullDescription);
        priceView.setText(price);

        // Загрузка изображения cover с помощью библиотеки Glide
        Glide.with(this)
                .load(cover)
                .into(coverView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }
}
