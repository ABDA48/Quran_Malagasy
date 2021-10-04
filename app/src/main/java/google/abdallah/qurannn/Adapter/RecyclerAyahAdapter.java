package google.abdallah.qurannn.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.thin.downloadmanager.DefaultRetryPolicy;
import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.DownloadStatusListener;
import com.thin.downloadmanager.ThinDownloadManager;

import java.util.ArrayList;
import java.util.List;

import google.abdallah.qurannn.Activity.SurahActivity;
import google.abdallah.qurannn.Database.DatabaseTrack;
import google.abdallah.qurannn.Database.DefaultFetch;
import google.abdallah.qurannn.Model.BookmarkModel;
import google.abdallah.qurannn.Model.TrackModel;
import google.abdallah.qurannn.Model.loveModel;
import google.abdallah.qurannn.R;
import google.abdallah.qurannn.Model.ModelSurat;


public class RecyclerAyahAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ModelSurat> modelSurats;
    List<View> item=new ArrayList<>();
    Context context;
    private ModelSurat modelSurat;
    SQLiteDatabase databasedef;
    SQLiteOpenHelper openHelper ;
     Snackbar snackbar;
    List<VHolder> viewHolders=new ArrayList<>();
    List<VHolderbismi> vHolderbismis=new ArrayList<>();
    MediaPlayer player;
      int bismifatiha;
      String bismi;
      String suratName,versenumber;
    FragmentManager fragmentManager;
    Snackbar snackbar2;

    SQLiteDatabase databaseTrack;
    List<TrackModel> trackModels;
    SQLiteOpenHelper openHelpTrack;
    LinearLayoutManager manager;
    boolean o=true;
    int j=0;
    ThinDownloadManager thinDownloadManager;

    ProgressDialog downloadprogress;

    public RecyclerAyahAdapter(LinearLayoutManager manager,List<ModelSurat> modelSurats, Context context, int bismifatiha, FragmentManager fragmentManager) {
        this.modelSurats = modelSurats;
        this.context = context;
        bismi=context.getResources().getString(R.string.bismi);
        this.manager=manager;
        this.fragmentManager=fragmentManager;
        openHelpTrack=new DatabaseTrack(context);
        databaseTrack=openHelpTrack.getWritableDatabase();
        this.bismifatiha = bismifatiha;
        thinDownloadManager=new ThinDownloadManager();
        downloadprogress=new ProgressDialog(context);
        downloadprogress.setMax(100);
        downloadprogress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }



    @Override
    public int getItemViewType(int position) {

        if (modelSurats.get(position).getNumberSurah().equals("1") && !modelSurats.get(position).getSurahNameAr()
        .contains(context.getResources().getString(R.string.bismi))) {
            return 0;
        }else if (modelSurats.get(position).getNumberSurah().equals("1")||!modelSurats.get(position).getNumberSurah().equals("1")) {
            return 1;
        }
        else {
            return  1;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==0){
            View view= LayoutInflater.from(context).inflate(R.layout.layoutbismi,parent,false);
            return new VHolderbismi(view);
        }else{
            final View view= LayoutInflater.from(context).inflate(R.layout.layoutayats,parent,false);
            item.add(view);
            return new VHolder(view);

        }

    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (modelSurats.get(position).getNumberSurah().equals("1") && !modelSurats.get(position).getSurahNameAr()
                .contains(context.getResources().getString(R.string.bismi))) {
            VHolderbismi holderbismi= (VHolderbismi) holder;
            o=false;
        }else {
            final VHolder holder1 = (VHolder) holder;
if (o) {
    holder1.number.setText(modelSurats.get(position ).getNumberSurah());
    holder1.textAr.setText(modelSurats.get(position ).getSurahNameAr());
    holder1.textFr.setText(modelSurats.get(position ).getSurahName());
}else {
    holder1.number.setText(modelSurats.get(position -1 ).getNumberSurah());
    holder1.textAr.setText(modelSurats.get(position -1).getSurahNameAr());
    holder1.textFr.setText(modelSurats.get(position -1).getSurahName());
}
            holder1.setIsRecyclable(false);
            viewHolders.add(holder1);

            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar = Snackbar.make(holder1.itemView, "", Snackbar.LENGTH_INDEFINITE);
                    View view = LayoutInflater.from(context).inflate(R.layout.snacklayout, null);
                    LinearLayout play = view.findViewById(R.id.play);
                    LinearLayout share = view.findViewById(R.id.share);
                    LinearLayout copy = view.findViewById(R.id.copy);
                    LinearLayout bookmark = view.findViewById(R.id.bookmark);
                    LinearLayout last = view.findViewById(R.id.lastread);

                    bookmark.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                            String[] strfr = context.getResources().getStringArray(R.array.surat_fr);
                            suratName = strfr[bismifatiha];
                            versenumber = String.valueOf(position + 1);
                            BokkmakDialogue bokkmakDialogue = new BokkmakDialogue(new BookmarkModel("", suratName, versenumber));
                            bokkmakDialogue.show(fragmentManager, "open");
                            Toast.makeText(context, modelSurats.get(position).getSurahNameAr(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    play.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar2 = Snackbar.make(holder1.itemView, "", Snackbar.LENGTH_INDEFINITE);
                            final View view1 = LayoutInflater.from(context).inflate(R.layout.snack, null);
                            ImageView stop = view1.findViewById(R.id.imageView5);
                            final ImageView playpause = view1.findViewById(R.id.imageView3);
                            ImageView next = view1.findViewById(R.id.imageView4);
                            ImageView prev = view1.findViewById(R.id.imageView2);
                            stop.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    player.stop();
                                    snackbar2.dismiss();
                                    player.release();
                                }
                            });
                            playpause.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (player.isPlaying()){
                                        player.pause();
                                        playpause.setImageResource(R.drawable.ic_pause_black_24dp);
                                    }else {
                                        player.start();
                                        playpause.setImageResource(R.drawable.ic_play);
                                    }
                                }
                            });
                            next.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    j++;
                                    if (j<viewHolders.size()) {
                                        player.stop();
                                        PlayList(j);
                                    }
                                }
                            });
                            prev.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    j--;
                                    if (j>0){
                                        player.stop();
                                        PlayList(j);
                                    }else {
                                        j=0;
                                        player.stop();
                                        PlayList(0);
                                    }
                                }
                            });
                            Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar2.getView();
                            snackbarLayout.setPadding(0, 0, 0, 0);
                            snackbarLayout.addView(view1);
                            snackbar2.show();
                            PlayList(position);

                        }
                    });

                    Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                    snackbarLayout.setPadding(0, 0, 0, 0);
                    snackbarLayout.addView(view);
                    snackbar.show();
                }
            });

        }
        if (position%2!=0){
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.cardColor));
        }else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorwhite));
        }

    }
    public boolean Take(int i){
        trackModels=new ArrayList<>();
        Cursor c=databaseTrack.rawQuery("select ayah,qirah,uri,juz from QuranAudio where juz="+i,new String[]{});
        TrackModel trackModel;
        while (c.moveToNext()){
            trackModel=new TrackModel();
            trackModel.setAyah(c.getString(0));
            trackModel.setQirah(c.getString(1));
            trackModel.setUri(c.getString(2));
            trackModel.setJuz(c.getString(3));
            trackModels.add(trackModel);
        }
        if (trackModels.size()==0){
            final AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("Download");
            builder.setMessage("You have to Download the Audio");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DownloadPreparation();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            return false;
        }else {
            int l=0;
            Toast.makeText(context, "trackModels finished "+trackModels.size(), Toast.LENGTH_SHORT).show();
            for (TrackModel v:trackModels){
                modelSurats.get(l).setUri(Uri.parse(v.getUri()));
                Log.d("TAG:H", "Take: "+trackModels.size()+"--> "+modelSurats.get(l).getUri());
                l++;
                if (l>modelSurats.size()){
                    break;
                }
            }
            return  true;
        }
    }
    private void PlayList(final int position) {

            if (modelSurats.get(position).getUri() != null) {
                for (ModelSurat m : modelSurats) {
                    Log.d("Tag:-->R<--", "PlayList: " + m.getUri());

                }
                player = MediaPlayer.create(context, modelSurats.get(position).getUri());
                for (VHolder Holder : viewHolders) {
                    Holder.itemView.setBackgroundColor(Color.WHITE);
                }
                viewHolders.get(position).itemView.setBackgroundColor(Color.GREEN);
                manager.scrollToPositionWithOffset(position, 0);
                player.start();
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(context, "Play " + player, Toast.LENGTH_SHORT).show();
                        int i = position;
                        i++;
                        if (i < viewHolders.size()) {
                            PlayList(i);
                            j = i;
                        } else {
                            viewHolders.get(position).itemView.setBackgroundColor(Color.WHITE);
                            snackbar2.dismiss();

                        }

                    }

                });

            } else {
               if (Take(bismifatiha+1)) {
                   PlayList(position);
               }
            }

    }
    public String Fetchayat(int i){
        String s;
        if (i<10){
            s="00"+i;
        }else if(i<100){
            s="0"+i;
        }else {
            s=""+i;
        }
        return s;
    }
    public int setP(int i){
        return (i*100)/modelSurats.size();
    }
    public int postition(int i){
        String []number=context.getResources().getStringArray(R.array.verses_number);
        return Integer.parseInt(number[i]);
    }
    public void DownloadPreparation() {
        TrackModel trackModel;
       String qirah="AbdulSamad_64kbps_QuranExplorer.Com";
        String source="https://everyayah.com/data/";
        int n=bismifatiha+1;
        int pos=postition(bismifatiha);
        for (int i = 1; i < pos+1; i++) {
            trackModel=new TrackModel();
            Uri uri = Uri.parse(source + qirah + "/" + Fetchayat(n) + Fetchayat(i) + ".mp3");
            String dest=context.getExternalCacheDir().toString() +"/"+qirah+"/"+Fetchayat(n) + Fetchayat(i)+".mp3";
            final Uri destination = Uri.parse(dest);
            Log.d("Tag:emp",""+uri);
            Log.d("Tag:w",""+destination);
            trackModel.setQirah(qirah);trackModel.setUri(String.valueOf(uri));
            trackModel.setAyah(String.valueOf(i));trackModel.setJuz(modelSurats.get(i-1).getJuz());
            Audio( trackModel.getSurah(),trackModel.getAyah(),trackModel.getJuz(),trackModel.getQirah(),trackModel.getUri());
            DownloadProcess(destination,trackModel);

        }
    }

    private void loveAdapter(int position) {
        String[] strfr = context.getResources().getStringArray(R.array.surat_fr);
        suratName = strfr[bismifatiha];
        versenumber = String.valueOf(position + 1);
        System.out.println("I ame" + suratName);
        BokkmakDialogue bokkmakDialogue = new BokkmakDialogue(new BookmarkModel("", suratName, versenumber));
     //   bokkmakDialogue.show(fragmentManager, "open");
    }


class  thrLast extends Thread{
        int pos;
        thrLast(int pos){
            this.pos=pos;
        }

    @Override
    public void run() {
        super.run();
        lastRead(pos);
    }
}
    private void lastRead( int position) {
        openHelper=new DefaultFetch(context);
        databasedef=openHelper.getWritableDatabase();
        String []strfr=context.getResources().getStringArray(R.array.surat_fr);
        suratName=strfr[bismifatiha];
        versenumber= String.valueOf(position+1);

        ContentValues contentValues=new ContentValues();
        contentValues.put("sourah",suratName);
        contentValues.put("ayat",versenumber);
        contentValues.put("title","last");
        databasedef.insert("Book_Last",null,contentValues);
    }
    private void DownloadProcess(final Uri destination, final TrackModel trackModel) {
        final  int i=Integer.parseInt(trackModel.getAyah());
        DownloadRequest downloadRequest=new DownloadRequest(Uri.parse(trackModel.getUri()))
                .setRetryPolicy(new DefaultRetryPolicy())
                .setDestinationURI(destination)
                .setPriority(DownloadRequest.Priority.HIGH)
                .setDownloadListener(new DownloadStatusListener() {
                    @Override
                    public void onDownloadComplete(int id) {
                        downloadprogress.setProgress(setP(i));
                        downloadprogress.setTitle("Downloading...");
                        downloadprogress.show();
                        if (i==postition(bismifatiha)){
                            downloadprogress.hide();
                            Take(bismifatiha+1);
                        }
                    }
                    @Override
                    public void onDownloadFailed(int id, int errorCode, String errorMessage) {
                        Toast.makeText(context, "I am  falled "+errorMessage, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(int id, long totalBytes, long downloadedBytes, int progress) {

                    }
                });

        thinDownloadManager.add(downloadRequest);

    }
    public void Audio(String surah,String ayah,String juz,String qirah,String uri){
        ContentValues contentValues=new ContentValues();
        contentValues.put("surah",surah);
        contentValues.put("ayah",ayah);
        contentValues.put("juz",juz);
        contentValues.put("qirah",qirah);
        contentValues.put("uri",uri);
        long t= databaseTrack.insert("QuranAudio",null,contentValues);
        if (t<0){
            Toast.makeText(context, "Error Occurd ", Toast.LENGTH_SHORT).show();
        }else {
            Log.d("Audio:TAG", "Audio:done");
        }
    }
    @Override
    public int getItemCount() {
        return modelSurats.size();
    }


    public class  VHolder  extends RecyclerView.ViewHolder{
    TextView number;
    TextView textAr;
    TextView textFr;
    ImageView love,edit,imageView2,play;
        public VHolder(@NonNull View itemView) {
            super(itemView);
            textAr=itemView.findViewById(R.id.textAr);
            number=itemView.findViewById(R.id.number);
            textFr=itemView.findViewById(R.id.textFr);
           /* love=itemView.findViewById(R.id.love);
            edit=itemView.findViewById(R.id.edit);
            imageView2=itemView.findViewById(R.id.imageView2);
            play=itemView.findViewById(R.id.play);*/
        }
    }
    public class VHolderbismi extends RecyclerView.ViewHolder{
        public VHolderbismi(@NonNull View itemView) {
            super(itemView);



        }
    }


}
