package google.abdallah.qurannn.Fragayat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import google.abdallah.qurannn.Adapter.RecyclerAyahAdapter;
import google.abdallah.qurannn.Constant;
import google.abdallah.qurannn.Database.DatabaseFetchAr;
import google.abdallah.qurannn.Database.DatabaseFetchMg;
import google.abdallah.qurannn.Database.DatabaseFetchfr;
import google.abdallah.qurannn.Database.DefaultFetch;
import google.abdallah.qurannn.Model.JuzModel;
import google.abdallah.qurannn.Model.ModelSurat;
import google.abdallah.qurannn.R;
public class Juzfrag extends Fragment {
    RecyclerView recyclerView;
    Handler fhandler=new Handler();
    ProgressDialog progressDialog;

    RecyclerAyahAdapter recyclerAyahAdapter;
    SQLiteOpenHelper openHelperMg;
    SQLiteOpenHelper openHelperAr;
    SQLiteOpenHelper openHelperFr;
    private SQLiteDatabase databaseAr;
    private SQLiteDatabase databaseFR;
    private ModelSurat modelSurat;
    SQLiteDatabase databasedef;
    SQLiteOpenHelper openHelper ;
    Context mcontext;
    private SQLiteDatabase databasemg;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mcontext=context;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
    }

    private static final String key="key";
    public static Juzfrag newInstance(int str){
        Juzfrag suratFrag =new Juzfrag();
        Bundle bundle=new Bundle();
        bundle.putInt(key,str);
        suratFrag.setArguments(bundle);
        return suratFrag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.juz1,container,false);
       recyclerView=view.findViewById(R.id.juz1);

        int i=0;
        initDB();
        if (getArguments()!=null){
            i=getArguments().getInt(key);
        }
        progressDialog=new ProgressDialog(mcontext);
        progressDialog.setTitle("Charging");
        progressDialog.show();
        new threadHandler2(i).start();

       return view;
    }
    private void initDB() {
        openHelperAr=new DatabaseFetchAr(mcontext);
        databaseAr=openHelperAr.getWritableDatabase();
        openHelperFr=new DatabaseFetchfr(mcontext);
        databaseFR=openHelperFr.getWritableDatabase();
        openHelper=new DefaultFetch(mcontext);
        databasedef=openHelper.getWritableDatabase();
        openHelperMg=new DatabaseFetchMg(mcontext);
        databasemg=openHelperMg.getWritableDatabase();
    }
    class  threadHandler2 extends Thread{
        int number;
        List<ModelSurat> modelSurats;
        public  threadHandler2(int i){
            this.number=i;
        }

        @Override
        public void run() {
            modelSurats= takeJuz(number);
            fhandler.post(new Runnable() {
                @Override
                public void run() {
                    LinearLayoutManager layoutManager=new LinearLayoutManager(mcontext,LinearLayoutManager.VERTICAL,false);
                    recyclerAyahAdapter=new RecyclerAyahAdapter(layoutManager,modelSurats, mcontext,number,getFragmentManager());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(recyclerAyahAdapter);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.ItemDecoration decoration=new DividerItemDecoration(mcontext,layoutManager.getOrientation());
                    recyclerView.addItemDecoration(decoration);
                    progressDialog.hide();
                }
            });
        }
    }
    public List<ModelSurat> takeJuz(int i){
        List<ModelSurat> AllGlobal=new ArrayList<>();

        Cursor cursorAr =databaseAr.rawQuery("select sura,ayah,text,juz FROM arabic_text WHERE juz="+(i+1),new String[]{});
        Cursor cursorFr =databasemg.rawQuery("select surah,ayah,text,juz FROM KoranText WHERE juz=\'juz "+(i+1)+"\'" ,new String[]{});

        String a="﴾";
        String aa="﴿";
        while (cursorAr.moveToNext() && cursorFr.moveToNext()) {
            modelSurat = new ModelSurat();
            modelSurat.setNumberSurah(cursorAr.getString(1));
            modelSurat.setSurahNameAr(cursorAr.getString(2)+"   "+aa+countArabic(Integer.parseInt(cursorAr.getString(1)))+a);
            modelSurat.setSurahName(cursorFr.getString(2));
            AllGlobal.add(modelSurat);
        }

        databaseAr.close();
        databaseFR.close();
        return AllGlobal;
    }
    public String countArabic(int j){
        int r;
        String []arabic=mcontext.getResources().getStringArray(R.array.ayats);
        String s="";
        while (j>0){
            r =j%10;
            s=arabic[r]+s;
            j=j/10;
        }
        return s;
    }
}
