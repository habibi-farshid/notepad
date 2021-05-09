package com.example.farshid.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class myDialog extends DialogFragment {

    View view;
    EditText title,des;
    Button add;
    OnAddNoteClick onAddNoteClick;

    public void setOnAddNoteClick(OnAddNoteClick onAddNoteClick) {
        this.onAddNoteClick = onAddNoteClick;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder alertDialog =new AlertDialog.Builder(getContext());
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog,null);
        alertDialog.setView(view);

        setUpView();

        return alertDialog.create();
    }

    private void setUpView() {
        title =view.findViewById(R.id.edt_title);
        des =view.findViewById(R.id.edt_description);
        add =view.findViewById(R.id.btn_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onAddNoteClick.onAddNote(title.getText().toString(),des.getText().toString());
                dismiss();

            }
        });


    }

    public interface OnAddNoteClick{
        void onAddNote(String title,String des);
    }
}
