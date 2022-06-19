package com.example.ugallery.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ugallery.Activity.FullScreenActivity;
import com.example.ugallery.Activity.ImageDisplay;
import com.example.ugallery.Adapter.ImageAdapter;
import com.example.ugallery.R;
import com.example.ugallery.fragments.pictureBrowserFragment;

import java.util.ArrayList;

public class PictureFragment extends Fragment {
    GridView gridView;
    private ArrayList<String> images;
    private com.example.ugallery.Adapter.ImageAdapter ImageAdapter;
    public PictureFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_picture, container, false);
        gridView = (GridView)v.findViewById(R.id.gridview);
        ImageAdapter = new ImageAdapter(getActivity());
        gridView.setAdapter(ImageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
//                if (null != images && !images.isEmpty()){
                    Toast.makeText(getContext(), "Please long press the key", Toast.LENGTH_LONG );
                    Intent intent = new Intent(getActivity(), FullScreenActivity.class);
                    intent.putExtra("id",position);
                    startActivity(intent);
//                }
            }
        });
        return v;
    }
}