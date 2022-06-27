package com.ozge.medicinereminder.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import com.ozge.medicinereminder.OlcumActivity;
import com.ozge.medicinereminder.R;
import com.ozge.medicinereminder.doktorekle.DoctorListActivity;
import com.ozge.medicinereminder.notekle.NoteListActivity;
import com.ozge.medicinereminder.randevuekle.MeetListActivity;


public class DashboardFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //bilgi panelindeki ilk 4 buton burada aktif edildi.

        View myview = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ImageButton doktorekle = (ImageButton) myview.findViewById(R.id.doktorEkle);
        ImageButton randevuekle = (ImageButton) myview.findViewById(R.id.randevuEkle);
        ImageButton notekle = (ImageButton) myview.findViewById(R.id.notEkle);
        ImageButton olcumekle = (ImageButton) myview.findViewById(R.id.olcumEkle);




        doktorekle.setOnClickListener(view -> {
            Intent my1Intent = new Intent(DashboardFragment.this.getActivity(), DoctorListActivity.class);
            startActivity(my1Intent);

        });
        randevuekle.setOnClickListener(v -> {
            Intent my2Intent = new Intent(DashboardFragment.this.getActivity(), MeetListActivity.class);
            startActivity(my2Intent);
        });
        notekle.setOnClickListener(v -> {
            Intent my3Intent = new Intent(DashboardFragment.this.getActivity(), NoteListActivity.class);
            startActivity(my3Intent);
        });
        olcumekle.setOnClickListener(view -> {
            Intent my4Intent = new Intent(DashboardFragment.this.getActivity(), OlcumActivity.class);
            startActivity(my4Intent);
        });
        return myview;
    }

}