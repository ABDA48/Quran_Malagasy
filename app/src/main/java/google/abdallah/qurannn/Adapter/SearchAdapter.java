package google.abdallah.qurannn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import google.abdallah.qurannn.Model.ModelSearch;

import google.abdallah.qurannn.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>  {
    List<ModelSearch> models;

    Context context;

    public SearchAdapter(List<ModelSearch> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.itemsearch,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
holder.ayat.setText(models.get(position).getAyat());
holder.number.setText(models.get(position).getNumber());
        Toast.makeText(context,models.get(position).getAyat(),Toast.LENGTH_SHORT);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
          TextView number,ayat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number=itemView.findViewById(R.id.number);
            ayat=itemView.findViewById(R.id.ayat);
        }
    }
}
