package com.example.finalproje.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.finalproje.R;
import com.example.finalproje.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment {

    private FragmentAboutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAboutBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        Button btnmail = binding.mail;
        btnmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent il = new Intent(Intent.ACTION_SEND);
                il.setType("text/plain");
                il.putExtra(Intent.EXTRA_EMAIL, new String[]{"Kerem.tnr41@hotmail.com"});
                if(il.resolveActivity(getActivity().getPackageManager())!= null){
                    startActivity(il);
                }
            }
        });
        Button btngithub = binding.github;
        btngithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url2 = "https://github.com/keremtaner";
                Intent i3 = new Intent(Intent.ACTION_VIEW);
                i3.setData(Uri.parse(url2));
                startActivity(i3);
            }
        });
        return  root;
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding=null;
    }
}