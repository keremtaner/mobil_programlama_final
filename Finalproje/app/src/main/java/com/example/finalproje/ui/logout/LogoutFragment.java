package com.example.finalproje.ui.logout;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.finalproje.OpenView;
import com.example.finalproje.R;
import com.example.finalproje.databinding.FragmentAboutBinding;
import com.example.finalproje.databinding.FragmentLogoutBinding;
import com.google.firebase.auth.FirebaseAuth;

import org.checkerframework.checker.nullness.qual.NonNull;


public class LogoutFragment extends Fragment {

    private FragmentLogoutBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLogoutBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        Button btnlogout = binding.logout;
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                startActivity(new Intent(getActivity(),OpenView.class));
                getActivity().finish();
            }
        });


        return root;
    }

    @Override
    public  void onDestroyView(){
        super.onDestroyView();
        binding=null;
    }
}