package google.abdallah.qurannn.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import google.abdallah.qurannn.Activity.Main2Activity;
import google.abdallah.qurannn.Adapter.BokkmakDialogue;
import google.abdallah.qurannn.Adapter.BookMarcAdapter;
import google.abdallah.qurannn.Adapter.RecyclerAyahAdapter;
import google.abdallah.qurannn.Database.DefaultFetch;
import google.abdallah.qurannn.Model.BookmarkModel;
import google.abdallah.qurannn.Model.loveModel;
import google.abdallah.qurannn.R;


public class Bookmark extends Fragment {
    RecyclerView recyclerView, recyclerLast;
    BookMarcAdapter adapter, adapter2;
    List<BookmarkModel> modelList, modelList2;

    SQLiteDatabase database;
    SQLiteOpenHelper openHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        recyclerView = view.findViewById(R.id.reclebook);
        recyclerLast = view.findViewById(R.id.reclerlast);
        openHelper = new DefaultFetch(getActivity());
        database = openHelper.getWritableDatabase();
        recycBookmark();
        recycleLast();

        return view;
    }

    private void recycleLast() {
        initDb2();
        adapter2 = new BookMarcAdapter(modelList2, getActivity());
        recyclerLast.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerLast.setAdapter(adapter2);
        ItemTouchHelper touchHelper=new ItemTouchHelper(simpleCallback2);
        touchHelper.attachToRecyclerView(recyclerLast);
    }

    private void recycBookmark() {
        initDb();
        adapter = new BookMarcAdapter(modelList, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        ItemTouchHelper touchHelper=new ItemTouchHelper(simpleCallback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    public void initDb() {
        modelList = new ArrayList<>();
        BookmarkModel loveModel;
        Cursor c = database.rawQuery("select title,sourah,ayat FROM Book_Last where title!='last'", new String[]{});
        while (c.moveToNext()) {
            loveModel = new BookmarkModel();
            loveModel.setTitle(c.getString(0));
            loveModel.setNameofsurat(c.getString(1));
            loveModel.setVerseNumber(c.getString(2));

            modelList.add(loveModel);
        }


    }

    public void initDb2() {
        modelList2 = new ArrayList<>();
        BookmarkModel loveModel;
        Cursor c = database.rawQuery("select title,sourah,ayat FROM Book_Last WHERE title='last'", new String[]{});
        while (c.moveToNext()) {
            loveModel = new BookmarkModel();
            loveModel.setTitle("");
            loveModel.setNameofsurat(c.getString(1));
            loveModel.setVerseNumber(c.getString(2));
            System.out.println(c.getString(1)+" "+c.getString(2));
            modelList2.add(loveModel);
        }
    }

    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int from=viewHolder.getAdapterPosition();
            int to=target.getAdapterPosition();
            Collections.swap(modelList,from,to);
            recyclerView.getAdapter().notifyItemMoved(from,to);
            return true;
        }
        BookmarkModel duno=null;

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position=viewHolder.getAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    duno = modelList.get(position);
                    modelList.remove(position);
                    delete(duno.getTitle(),duno.getNameofsurat(),duno.getVerseNumber());
                    adapter.notifyItemRemoved(position);
                    Snackbar.make(recyclerView, R.string.undo, Snackbar.LENGTH_LONG)
                            .setAction("undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    modelList.add(position, duno);
                                    add(duno.getTitle(),duno.getNameofsurat(),duno.getVerseNumber());
                                    adapter.notifyItemInserted(position);
                                }
                            })
                            .show();
                    break;
            }
        }
    };

    ItemTouchHelper.SimpleCallback simpleCallback2=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int from=viewHolder.getAdapterPosition();
            int to=target.getAdapterPosition();
            Collections.swap(modelList2,from,to);
            recyclerView.getAdapter().notifyItemMoved(from,to);
            return true;
        }
        BookmarkModel duno=null;

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position=viewHolder.getAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    duno = modelList2.get(position);
                    modelList2.remove(position);
                    delete("last",duno.getNameofsurat(),duno.getVerseNumber());
                    adapter2.notifyItemRemoved(position);
                    Snackbar.make(recyclerView, R.string.undo, Snackbar.LENGTH_LONG)
                            .setAction("undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    modelList2.add(position, duno);
                                    add("last",duno.getNameofsurat(),duno.getVerseNumber());
                                    adapter2.notifyItemInserted(position);
                                }
                            })
                            .show();
                    break;
            }
        }
    };
    public void delete(String t,String n,String p){
      long a = database.delete("Book_Last","title=? AND sourah=? AND ayat=? ",new String[]{t,n,p});
      if (a!=-1){
          Toast.makeText(getActivity(), "deleted into the db", Toast.LENGTH_SHORT).show();
      }
    }
public  void add(String t,String n,String p){
    ContentValues values=new ContentValues();
    values.put("title",t);
    values.put("sourah",n);
    values.put("ayat",p);
    database.insert("Book_Last",null,values);
}

}
