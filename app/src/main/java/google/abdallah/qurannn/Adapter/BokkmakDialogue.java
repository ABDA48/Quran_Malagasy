package google.abdallah.qurannn.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import java.util.ArrayList;
import java.util.List;

import google.abdallah.qurannn.Model.BookmarkModel;
import google.abdallah.qurannn.Model.loveModel;
import google.abdallah.qurannn.R;
import google.abdallah.qurannn.fragment.Bookmark;

public class BokkmakDialogue extends AppCompatDialogFragment {
    BookmarkModel loveModel;
    BookmarkListner bookmarkListner;

    public BokkmakDialogue(BookmarkModel loveModel) {
        this.loveModel = loveModel;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
      View view=inflater.inflate(R.layout.dialoguefolderlove,null);
        final EditText editText=view.findViewById(R.id.takefoldername);

        editText.setHint("title of bookMark");
      builder.setView(view)
              .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {

                   String folder= editText.getText().toString();
                         if (!folder.isEmpty()) {
                             BookmarkModel bookmarkModel = new BookmarkModel(folder, loveModel.getNameofsurat(), loveModel.getVerseNumber());
                             bookmarkListner.love(bookmarkModel);
                         }
                         else {
                             Toast.makeText(getContext(), "please give title", Toast.LENGTH_SHORT).show();
                         }
                  }
              })
              .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {

                  }
              })
              .setTitle("Dialogue");
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            bookmarkListner= (BookmarkListner) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface BookmarkListner {
        void love(BookmarkModel bookmarkModel);

    }
}
