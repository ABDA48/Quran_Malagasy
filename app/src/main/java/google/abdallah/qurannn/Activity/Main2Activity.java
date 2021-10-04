package google.abdallah.qurannn.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;




import java.util.ArrayList;
import java.util.List;

import google.abdallah.qurannn.Adapter.BokkmakDialogue;
import google.abdallah.qurannn.Adapter.RecyclerAyahAdapter;
import google.abdallah.qurannn.Constant;
import google.abdallah.qurannn.Database.DatabaseFetchAr;
import google.abdallah.qurannn.Database.DatabaseFetchfr;
import google.abdallah.qurannn.Database.DefaultFetch;
import google.abdallah.qurannn.Model.BookmarkModel;
import google.abdallah.qurannn.Model.JuzModel;
import google.abdallah.qurannn.Model.ModelSurat;
import google.abdallah.qurannn.Model.loveModel;
import google.abdallah.qurannn.R;
import google.abdallah.qurannn.fragment.Bookmark;

public class Main2Activity extends AppCompatActivity {  //implements BokkmakDialogue.BookmarkListner {
RecyclerView recyclerView;
RecyclerAyahAdapter recyclerAyahAdapter;
List<ModelSurat> modelSurats;
      SQLiteOpenHelper openHelperAr;
      SQLiteOpenHelper openHelperFr;
    private SQLiteDatabase databaseAr;
    private SQLiteDatabase databaseFR;
    private ModelSurat modelSurat;
    SQLiteDatabase databasedef;
    SQLiteOpenHelper openHelper ;
    Toolbar toolbar;
    Handler handler=new Handler();
    Handler handler2=new Handler();
    Handler fhandler=new Handler();
    ProgressDialog progressDialog;
    int k=0;
    LinearLayoutManager linearLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
     /*   toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDB();
        recyclerView=findViewById(R.id.recyclerayat);
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Charging");
        progressDialog.show();
        String toolbarName=getIntent().getStringExtra("suratName");
        toolbar.setTitle(toolbarName);

        String name=getIntent().getStringExtra("name");
        if(name.equals("Surah")){
            int i=getIntent().getIntExtra("int",1);
             new threadHandler(i,0).start();
        }else if(name.equals("Juz")){
            int i=getIntent().getIntExtra("int",1);
            toolbar.setTitle("Juz"+(i+1));
          new threadHandler2(i).start();
        }else if (name.equals("jump")){
            int i=getIntent().getIntExtra("int",1);
            int p=getIntent().getIntExtra("position",1);
            new threadHandler(i,p).start();

        }

    }
/*


    private void initDB() {
        openHelperAr=new DatabaseFetchAr(this);
        databaseAr=openHelperAr.getWritableDatabase();
        openHelperFr=new DatabaseFetchfr(this);
        databaseFR=openHelperFr.getWritableDatabase();
        openHelper=new DefaultFetch(this);
        databasedef=openHelper.getWritableDatabase();
    }

    @Override
    public void love(BookmarkModel bookmarkModel) {
      Toast.makeText(this, bookmarkModel.getTitle()+" "+
                bookmarkModel.getNameofsurat(), Toast.LENGTH_SHORT).show();
        ContentValues contentValues=new ContentValues();
        contentValues.put("sourah",bookmarkModel.getNameofsurat());
        contentValues.put("ayat",bookmarkModel.getVerseNumber());
        contentValues.put("title",bookmarkModel.getTitle());
        System.out.println(" i am title :"+bookmarkModel.getTitle());
        databasedef.insert("Book_Last",null,contentValues);*/
    }
/*

    class threadHandler extends Thread{
        int number;
        int jump;
        public threadHandler(int i,int jump)
        {
            this.jump=jump;
            this.number=i;
        }

        @Override
        public void run() {
          FetchData(number);
          handler.post(new Runnable() {
              @Override
              public void run() {
                  recyclerAyahAdapter=new RecyclerAyahAdapter(modelSurats,Main2Activity.this,number);
                  linearLayoutManager=new LinearLayoutManager(Main2Activity.this);
                  recyclerView.setLayoutManager(linearLayoutManager);
                  recyclerView.setAdapter(recyclerAyahAdapter);
                  linearLayoutManager.scrollToPositionWithOffset(jump,0);
                  RecyclerView.ItemDecoration decoration=new DividerItemDecoration(Main2Activity.this,linearLayoutManager.getOrientation());
                  recyclerView.addItemDecoration(decoration);
                  progressDialog.hide();


              }
          });
        }
    }

    class  threadHandler2 extends Thread{
        int number;
        List<ModelSurat> modelSurats;
        public  threadHandler2(int i){
            this.number=i;
        }

        @Override
        public void run() {
            List<JuzModel>limit=new Constant().juzList();
            int start =limit.get(number).getStart();
            int end =limit.get(number).getEnd();
            modelSurats= takeJuz(start,end);
            handler2.post(new Runnable() {
                @Override
                public void run() {
                    recyclerAyahAdapter=new RecyclerAyahAdapter(modelSurats,Main2Activity.this,number);
                   LinearLayoutManager layoutManager=new LinearLayoutManager(Main2Activity.this,LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(recyclerAyahAdapter);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.ItemDecoration decoration=new DividerItemDecoration(Main2Activity.this,layoutManager.getOrientation());
                    recyclerView.addItemDecoration(decoration);
                    progressDialog.hide();


                }
            });
        }
    }

    private void FetchData(int i) {
        modelSurats=new ArrayList<>();

        Cursor cursorAr =databaseAr.rawQuery("SELECT ayah,text from arabic_text WHERE sura="+(i+1),new String[]{});
        Cursor cursorFr =databaseFR.rawQuery("SELECT c1ayah,c2text FROM verses_content WHERE c0sura="+(i+1),new String[]{});
int j=0;
String a="﴾";
String aa="﴿";
        while (cursorAr.moveToNext() && cursorFr.moveToNext()) {
            modelSurat = new ModelSurat();
            j++;
            modelSurat.setNumberSurah(String.valueOf(j));
            modelSurat.setSurahNameAr(cursorAr.getString(1)+" "+aa+countArabic(j)+a);
            modelSurat.setSurahName(cursorFr.getString(1));
            modelSurats.add(modelSurat);

        }
databaseAr.close();
        databaseFR.close();

    }
   public List<ModelSurat> takeJuz(int start,int end){
        List<ModelSurat> AllGlobal=new ArrayList<>();

        Cursor cursorAr =databaseAr.rawQuery("SELECT ayah,text from arabic_text",new String[]{});
        Cursor cursorFr =databaseFR.rawQuery("SELECT c1ayah,c2text FROM verses_content",new String[]{});

        int j=0;

        String a="﴾";
        String aa="﴿";
        while (cursorAr.moveToNext() && cursorFr.moveToNext()) {
            modelSurat = new ModelSurat();
            j++;
            modelSurat.setNumberSurah(String.valueOf(j));
            if (cursorAr.getString(1).contains(getResources().getString(R.string.bismi))){
                j=1;
            }
            modelSurat.setSurahNameAr(cursorAr.getString(1)+" "+aa+countArabic(j)+a);
            modelSurat.setSurahName(cursorFr.getString(1));
            AllGlobal.add(modelSurat);
        }

         List<ModelSurat> Juz=AllGlobal.subList(start,end);
       databaseAr.close();
       databaseFR.close();
      return Juz;
    }
    public String countArabic(int j){
        int r;
        String []arabic=getResources().getStringArray(R.array.ayats);
        String s="";
        while (j>0){
           r =j%10;
           s=arabic[r]+s;
           j=j/10;
        }
        return s;
    }
scrollThread scrollThread=new scrollThread();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_ayat, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        switch (id){
            case R.id.setting:
                Toast.makeText(Main2Activity.this, "setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ratting:
                Toast.makeText(Main2Activity.this, "rating", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(Main2Activity.this, "about", Toast.LENGTH_SHORT).show();
                break;

            case R.id.download:
                Toast.makeText(Main2Activity.this, "download", Toast.LENGTH_SHORT).show();
                break;
            case R.id.jump:

                try {
                    scrollThread.start();
                } catch (java.lang.IllegalThreadStateException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.go:
                Toast.makeText(Main2Activity.this, "go", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }



    class scrollThread extends Thread{
        @Override
        public void run() {
            for (int i = 0; i <modelSurats.size(); i++) {
                try {
                    Thread.sleep(5000);

                    fhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollRecycler( k);
                        }
                    });
                    k++;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void scrollRecycler(int k)  {
        try{
            linearLayoutManager.scrollToPositionWithOffset(k,0);
            recyclerView.findViewHolderForAdapterPosition(k).itemView.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        }catch(NullPointerException  e){
            e.printStackTrace();

        }
    }
*/
}
