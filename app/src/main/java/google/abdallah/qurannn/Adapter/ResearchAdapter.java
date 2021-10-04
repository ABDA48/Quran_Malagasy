package google.abdallah.qurannn.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import google.abdallah.qurannn.Model.ModelAyat;
import google.abdallah.qurannn.Model.ModelSearch;
import google.abdallah.qurannn.Model.ModelSurat;
import google.abdallah.qurannn.R;

public class ResearchAdapter extends RecyclerView.Adapter<ResearchAdapter.ViewHolder> implements Filterable {
    List<ModelSurat> modelSurats;
  List<ModelSurat> copyModels;

    Context context;
    SearchAdapter searchAdapter;
Handler handler=new Handler();




    public ResearchAdapter(List<ModelSurat> modelSurats, Context context) {

        this.modelSurats = modelSurats;
        this.context = context;
        this.modelSurats = modelSurats;

        copyModels=new ArrayList<>(modelSurats);

    }

    @NonNull
    @Override
    public ResearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layoutresearch,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResearchAdapter.ViewHolder holder, int position) {
     holder.title.setText(modelSurats.get(position).getSurahName());
     searchAdapter=new SearchAdapter(modelSurats.get(position).getModelSearchList(),context);
   // modelSearchList.add(modelSurats.get(position).getModelSearchList().get(position).getAyat());
     holder.expandable.setLayoutManager(new LinearLayoutManager(context));
     holder.expandable.setAdapter(searchAdapter);
     boolean isExpanded=modelSurats.get(position).isExpanded();
     holder.expandable.setVisibility(isExpanded ? View.VISIBLE:View.GONE);
    }

    @Override
    public int getItemCount() {
        return modelSurats.size();
    }


    @Override
    public Filter getFilter() {
        return filter;
    }
    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ModelSurat> results =new ArrayList<>();
            List<ModelSearch> modelSearches;
           int number=0;

            if (constraint ==null || constraint.length()==0){

                results.clear();

            }else{
                String c=constraint.toString().toLowerCase().trim();
                for(ModelSurat model:copyModels){
                    modelSearches=new ArrayList<>();
                  //  String c=constraint.toString().toLowerCase().trim();
                    for(ModelSearch m:model.getModelSearchList()){
                        if (m.getAyat().toLowerCase().contains(c)){
                            number++;
                          /*  SpannableString spannableString=new SpannableString(m.getAyat());
                            ForegroundColorSpan colorSpan=new ForegroundColorSpan(Color.RED);
                           int start= m.getAyat().indexOf(c,1);
                           int end=start+c.length()-1;
                           spannableString.setSpan(colorSpan,start,end,SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);*/
                            modelSearches.add(m);
                        }

                    }
                    model.setSurahName(model.getSurahName()+"("+number+")"+"times");
                    number=0;
                    model.setModelSearchList(modelSearches);
                    if (model.getModelSearchList().size()==0){
                        results.remove(model);
                    }else {
                        results.add(model);
                    }


                }
            }

            FilterResults result=new FilterResults();
            result.values=results;
            return result;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            modelSurats.clear();
            modelSurats.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        RecyclerView expandable;
        LinearLayout linearLayout;
        ImageView downUp;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        expandable=itemView.findViewById(R.id.expanded);
        title=itemView.findViewById(R.id.title);
         linearLayout=itemView.findViewById(R.id.iam);
         downUp=itemView.findViewById(R.id.downup);


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new expand().start();
                ModelSurat modelSurat=modelSurats.get(getAdapterPosition());
                if (modelSurat.isExpanded()){
                    downUp.setImageResource(R.drawable.ic_up);
                }else {
                    downUp.setImageResource(R.drawable.ic_down);
                }
            }
        });
    }
    class expand extends Thread{
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ModelSurat modelSurat=modelSurats.get(getAdapterPosition());
                    modelSurat.setExpanded(!modelSurat.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });

        }
    }
}
}
