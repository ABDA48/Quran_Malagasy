package google.abdallah.qurannn.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import google.abdallah.qurannn.Adapter.RecyclerSuratAdapter;
import google.abdallah.qurannn.Model.ModelSurat;
import google.abdallah.qurannn.R;


public class Juz extends Fragment {
    RecyclerView recyclerView;
    List<ModelSurat> modelSurats;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_juz,container,false);
        recyclerView=view.findViewById(R.id.recyclerjuz);
        initModel();
        RecyclerSuratAdapter recyclerSuratAdapter=new RecyclerSuratAdapter(modelSurats,getContext());
        recyclerView.setAdapter(recyclerSuratAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
          return view;
    }
    public void initModel(){
        int []juz={1,142,253,92,24,148,83,111,88,41,94,6,53,2,1,75,1,1,21,60,45,31,22,32,47,1,31,1,1,1};
        String[] str={"Al-Fatiha","Baquara","Baquara","Al-Imran","An-Nissa","An-Nissa","Al-Maaida","Al-Anaam","Al ARaaf","Al-Anfal","At-Tawba","Hud",
        "Yusuf","Hijr","Al-isra","Al-KAhf","Al-Anbyaa","Al-MuMiNoon","Al-Furquan","An-Naml","Al-AnKaBoot","Al-Ahzab","Yassen","Az-ZuMar",
        "Fussilat","Al-Ahkaf","Adh-Dhaariya","Al-Mujadala","Al-Mulk","An-Naba"};
        modelSurats=new ArrayList<>();
        ModelSurat model;

        int j=0;
        for (int i = 0; i < 30; i++) {

        j++;
        model=new ModelSurat();
        model.setNumberSurah(String.valueOf(j));
        model.setSurahName("JUZ"+j);
        model.setNumberVerse("Surat"+str[i].toUpperCase()+" "+juz[i]);
            modelSurats.add(model);
        }
    }
}