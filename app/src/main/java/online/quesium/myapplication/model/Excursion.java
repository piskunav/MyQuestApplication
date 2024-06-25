package online.quesium.myapplication.model;
import com.google.gson.annotations.SerializedName;

import java.util.Random;

public class Excursion {
    @SerializedName("id")
    private int id;

    @SerializedName("is_visible")
    private boolean isVisible;

    @SerializedName("name")
    private String name;

    @SerializedName("short_description")
    private String shortDescription;

    @SerializedName("cover")
    private String cover;

    private float rating; // рандомный параметр рейтинга, чтобы потренироваться писать конструкторы

    private float generateRandomRating() {
        Random random = new Random();
        // Генерация случайного числа от 1 до 5 и округление до двух знаков после запятой
        return Math.round((1.0 + (5.0 - 1.0) * random.nextFloat()) * 100.0) / 100.0f;
    }

    public Excursion() {
        // Генерация случайного рейтинга при создании объекта
        this.rating = generateRandomRating();
    }

    // Геттеры и сеттеры
    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    public int getId() {
        return id;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getCover() {
        return cover;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
