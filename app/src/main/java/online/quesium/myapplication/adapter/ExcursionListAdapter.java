package online.quesium.myapplication.adapter;

import com.bumptech.glide.Glide;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import online.quesium.myapplication.R;
import online.quesium.myapplication.activity.DetailActivity;
import online.quesium.myapplication.model.Excursion;

public class ExcursionListAdapter extends RecyclerView.Adapter<ExcursionListAdapter.ViewHolder> {

    private List<Excursion> itemList;
    private Context context;

    public ExcursionListAdapter(List<Excursion> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public void setData(List<Excursion> excursionList) {
        this.itemList = excursionList;
        notifyDataSetChanged(); // Уведомляем адаптер о новых данных
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Excursion item = itemList.get(position);
        holder.title.setText(item.getName());
        holder.description.setText(item.getShortDescription());
        holder.rating.setRating(item.getRating());
        Glide.with(context).load(item.getCover()).into(holder.image);



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создание Intent для запуска DetailActivity
                Intent intent = new Intent(context, DetailActivity.class);

                // Передача данных в DetailActivity
                intent.putExtra("name", item.getName());
                intent.putExtra("full_description", item.getShortDescription());
                intent.putExtra("cover", item.getCover());

                // Запуск DetailActivity
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        RatingBar rating;
        ImageView image;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            description = itemView.findViewById(R.id.item_description);
            rating = itemView.findViewById(R.id.item_rating);
            image = itemView.findViewById(R.id.item_image);
            cardView = itemView.findViewById(R.id.item_card_view);

        }
    }
}

