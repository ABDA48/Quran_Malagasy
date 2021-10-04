package google.abdallah.qurannn.fragment;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import google.abdallah.qurannn.Model.ModelSurat;
import google.abdallah.qurannn.R;
import google.abdallah.qurannn.Adapter.RecyclerSuratAdapter;


public class surah extends Fragment {
    RecyclerView recyclerView;
    RecyclerSuratAdapter recyclerSuratAdapter;
    List<ModelSurat> modelSuratList=new ArrayList<>();
    Handler handler=new Handler();
    ProgressDialog p;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_surah,container,false);
        recyclerView=view.findViewById(R.id.recyclersurat);
        p=new ProgressDialog(getActivity());
        p.setMessage("Wait ...");
        p.show();
         new initThread().start();
          return view;
    }

    public  void initList(){
        modelSuratList=new ArrayList<>();
        ModelSurat modelSurat;
        String []surat_fr=getResources().getStringArray(R.array.surat_fr);
        String []surat_Ar=getResources().getStringArray(R.array.surat_names);
        String []number=getResources().getStringArray(R.array.verses_number);
        for (int i = 0; i < 114; i++) {
            modelSurat=new ModelSurat();
            modelSurat.setSurahName(surat_fr[i]);
            modelSurat.setSurahNameAr(surat_Ar[i]);
            modelSurat.setDownorup(R.drawable.ic_volume_up_black_24dp);
            modelSurat.setSurahCity("Madina | ");
            modelSurat.setNumberSurah(String.valueOf(i+1));
            modelSurat.setNumberVerse(number[i]);
            modelSuratList.add(modelSurat);
        }
    }
    class initThread extends Thread{
        @Override
        public void run() {
            super.run();

            initList();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    recyclerSuratAdapter=new RecyclerSuratAdapter(modelSuratList,getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(recyclerSuratAdapter);
                    p.hide();
                }
            });
        }
    }
}
