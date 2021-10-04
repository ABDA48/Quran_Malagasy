package google.abdallah.qurannn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import google.abdallah.qurannn.Model.BookmarkModel;
import google.abdallah.qurannn.R;

public class BookMarcAdapter  extends RecyclerView.Adapter<BookMarcAdapter.viewHolder> {
    List<BookmarkModel> bookmarkModels;
    Context context;

    public BookMarcAdapter(List<BookmarkModel> bookmarkModels, Context context) {
        this.bookmarkModels = bookmarkModels;
        this.context = context;
    }

    @NonNull
    @Override
    public BookMarcAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.itemlove,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookMarcAdapter.viewHolder holder, int position) {
   holder.nameoftitle.setText(bookmarkModels.get(position).getTitle());
   holder.ayat.setText(bookmarkModels.get(position).getVerseNumber());
   holder.surat.setText(bookmarkModels.get(position).getNameofsurat());
    }

    @Override
    public int getItemCount() {
        return bookmarkModels.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder{
        TextView nameoftitle,surat,ayat;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            nameoftitle=itemView.findViewById(R.id.title);
            surat=itemView.findViewById(R.id.nameofsurat);
            ayat=itemView.findViewById(R.id.numberofverse);

        }
    }
}
