package google.abdallah.qurannn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import google.abdallah.qurannn.Activity.JuzActivity;
import google.abdallah.qurannn.Activity.Main2Activity;
import google.abdallah.qurannn.Activity.SurahActivity;
import google.abdallah.qurannn.Model.ModelSurat;
import google.abdallah.qurannn.R;

public class RecyclerSuratAdapter extends RecyclerView.Adapter<RecyclerSuratAdapter.VHSurah> {
    List<ModelSurat>modelSurats;
    Context context;

    public RecyclerSuratAdapter(List<ModelSurat> modelSurats, Context context) {
        this.modelSurats = modelSurats;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerSuratAdapter.VHSurah onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layoutsurah,parent,false);
        return new VHSurah(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerSuratAdapter.VHSurah holder, final int position) {
        holder.suraname.setText(modelSurats.get(position).getSurahName());
        holder.cityname.setText(modelSurats.get(position).getSurahCity());
        holder.numberayat.setText(modelSurats.get(position).getNumberVerse());
        holder.numberSurah.setText(modelSurats.get(position).getNumberSurah());

        holder.downup.setImageResource(modelSurats.get(position).getDownorup());
        holder.suranameAr.setText(modelSurats.get(position).getSurahNameAr());


if (modelSurats.size()>30) {
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, SurahActivity.class);
            intent.putExtra("pos", position);
            context.startActivity(intent);

        }
    });
}else{
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, JuzActivity.class);
            intent.putExtra("pos", position);
            System.out.print(modelSurats.size());
            context.startActivity(intent);
        }
    });
}

         /*   holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!holder.cityname.getText().toString().isEmpty()) {
                        Intent intent = new Intent(context, Main2Activity.class);
                        intent.putExtra("int", position);
                        intent.putExtra("name", "Surah");
                        String []surat_fr=context.getResources().getStringArray(R.array.surat_fr);
                        intent.putExtra("suratName",surat_fr[position]);
                        Toast.makeText(context, surat_fr[position], Toast.LENGTH_SHORT).show();
                        context.startActivity(intent);
                    }else{
                        Intent intent = new Intent(context, Main2Activity.class);
                        intent.putExtra("int", position);
                        intent.putExtra("name", "Juz");
                        context.startActivity(intent);
                    }

                }
            });*/

    }

    @Override
    public int getItemCount() {
        return modelSurats.size();
    }
    public class VHSurah  extends RecyclerView.ViewHolder{
           TextView numberSurah;
           TextView suraname;
           TextView cityname;
           TextView numberayat;
           TextView suranameAr;
           ImageView downup;
           CardView cardView;
        public VHSurah(@NonNull View itemView) {
            super(itemView);
            numberSurah=itemView.findViewById(R.id.numberSurah);
            suraname=itemView.findViewById(R.id.suraname);
            cityname=itemView.findViewById(R.id.cityname);
            numberayat=itemView.findViewById(R.id.numberayat);
            suranameAr=itemView.findViewById(R.id.suranameAr);
            downup=itemView.findViewById(R.id.downup);
            cardView=itemView.findViewById(R.id.cardview);


        }
    }
}
