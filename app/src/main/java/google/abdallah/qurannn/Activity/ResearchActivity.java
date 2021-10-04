package google.abdallah.qurannn.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import google.abdallah.qurannn.Adapter.ResearchAdapter;
import google.abdallah.qurannn.Adapter.SearchAdapter;
import google.abdallah.qurannn.Database.DatabaseFetchfr;
import google.abdallah.qurannn.Model.ModelAyat;
import google.abdallah.qurannn.Model.ModelSearch;
import google.abdallah.qurannn.Model.ModelSurat;
import google.abdallah.qurannn.R;

public class ResearchActivity extends AppCompatActivity {
RecyclerView recyclerView;
    List<ModelSurat> researchList;
    private SQLiteOpenHelper openHelperFr;
    private  SQLiteDatabase databaseFR;
    ModelSurat modelSurat;
    ModelSearch modelSearch;
    Toolbar toolbar;
    Handler handler=new Handler();
    ProgressDialog progressDialog;

    ResearchAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);
   toolbar=findViewById(R.id.toolbar);
   setSupportActionBar(toolbar);
        openHelperFr=new DatabaseFetchfr(this);
        databaseFR = openHelperFr.getWritableDatabase();
          progressDialog=new ProgressDialog(ResearchActivity.this);
          progressDialog.setTitle("Charging");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menuresearch,menu);
        MenuItem item=menu.findItem(R.id.research);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerView=findViewById(R.id.recyclersearch);
                progressDialog.show();
                new DbThread(query).start();


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return true;
    }
/*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.research){
            Toast.makeText(ResearchActivity.this, "Ok", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }*/

     class  DbThread extends Thread{
         String query;
         DbThread(String quewry)   {
             this.query=quewry;
         }
         @Override
         public void run() {
            initData();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    adapter=new ResearchAdapter(researchList,ResearchActivity.this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ResearchActivity.this));
                    adapter.getFilter().filter(query);
                    progressDialog.hide();
                }
            });
         }
     }
    private void initData() {
     researchList=new ArrayList<>();

        List<ModelSearch> model;


   String []surat_fr=getResources().getStringArray(R.array.surat_fr);
        for (int i = 0; i < surat_fr.length; i++) {
             model=new ArrayList<>();
             modelSurat=new ModelSurat();
           //  System.out.println("SELECT c1ayah,c2text FROM verses_content WHERE c0sura="+(i+1));
            Cursor cursorFr =databaseFR.rawQuery("SELECT c1ayah,c2text FROM verses_content WHERE c0sura="+(i+1),new String[]{});
            int j=0;
            while (cursorFr.moveToNext()){
                modelSearch=new ModelSearch();
                j++;
                modelSearch.setAyat(cursorFr.getString(1));
                modelSearch.setNumber(String.valueOf(j));

                model.add(modelSearch);


            }

            modelSurat.setSurahName(surat_fr[i]);
            modelSurat.setModelSearchList(model);
            researchList.add(modelSurat);
        }



    }
}
