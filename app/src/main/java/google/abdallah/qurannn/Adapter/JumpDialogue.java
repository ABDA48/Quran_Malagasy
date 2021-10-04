package google.abdallah.qurannn.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatEditText;

import google.abdallah.qurannn.R;

public class JumpDialogue extends AppCompatDialogFragment {
    Spinner  spinner;
   AppCompatEditText jumpEdit;
    JumpListner jumpListner;
    int jump;
    String []surat_fr;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.jumpone,null);
        spinner=view.findViewById(R.id.spinerjump);
        jumpEdit=view.findViewById(R.id.editJump);
     surat_fr=getResources().getStringArray(R.array.verses_number);
        jump=spinner.getSelectedItemPosition();
        jumpEdit.setHint("1-"+surat_fr[jump]);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jump=spinner.getSelectedItemPosition();
                jumpEdit.setHint("1-"+surat_fr[jump]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String j=jumpEdit.getText().toString();

                       if (j.isEmpty()){
                           Toast.makeText(getActivity(), "Fill the blank", Toast.LENGTH_SHORT).show();
                       }else{

                           int num=Integer.parseInt(j);
                           jumpListner.jumpMethode(jump,num-1);
                       }


                    }
                });
        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            jumpListner=(JumpListner)context;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public interface JumpListner {
        void jumpMethode(int i,int j);
    }
}
