package com.example.ugallery.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.example.ugallery.Activity.ImageDisplay;
import com.example.ugallery.MainActivity;
import com.example.ugallery.R;
import com.example.ugallery.utils.MarginDecoration;
import com.example.ugallery.utils.PicHolder;
import com.example.ugallery.utils.itemClickListener;
import com.example.ugallery.utils.pictureFacer;
import com.example.ugallery.utils.pictureFolderAdapter;

import java.util.ArrayList;


public class AlbumFragment extends Fragment implements itemClickListener {
    GridView gridView;
    RecyclerView folderRecycler;
    private itemClickListener listenToClick;
    private ArrayList<String> images;
    private pictureFolderAdapter folderAdapter;

    public AlbumFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_album, container, false);
        folderRecycler = (RecyclerView) v.findViewById(R.id.folderRecycler);
        folderRecycler.addItemDecoration(new MarginDecoration(getActivity()));
        folderRecycler.hasFixedSize();
        ArrayList<com.example.ugallery.utils.imageFolder> folds = getPicturePaths();
        if (folds.isEmpty()) {
            folderAdapter = new pictureFolderAdapter(folds, getActivity(), this);
            folderRecycler.setAdapter(folderAdapter);
        } else {
            folderAdapter = new pictureFolderAdapter(folds, getActivity(), this);
           folderRecycler.setAdapter(folderAdapter);
        }

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1,
//                                    int position, long arg3) {
//                if (null != images && !images.isEmpty()) {
//
//                }
//            }
//        });
        return v;
    }

    private ArrayList<com.example.ugallery.utils.imageFolder> getPicturePaths() {
        ArrayList<com.example.ugallery.utils.imageFolder> picFolders = new ArrayList<>();
        ArrayList<String> picPaths = new ArrayList<>();
        Uri allImagesuri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID};
        Cursor cursor = getActivity().getContentResolver().query(allImagesuri, projection, null, null, null);
        try {
            if (cursor != null) {
                cursor.moveToFirst();
            }
            do {
                com.example.ugallery.utils.imageFolder folds = new com.example.ugallery.utils.imageFolder();
                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                String folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                String datapath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

                //String folderpaths =  datapath.replace(name,"");
                String folderpaths = datapath.substring(0, datapath.lastIndexOf(folder + "/"));
                folderpaths = folderpaths + folder + "/";
                if (!picPaths.contains(folderpaths)) {
                    picPaths.add(folderpaths);

                    folds.setPath(folderpaths);
                    folds.setFolderName(folder);
                    folds.setFirstPic(datapath);//if the folder has only one picture this line helps to set it as first so as to avoid blank image in itemview
                    folds.addpics();
                    picFolders.add(folds);
                } else {
                    for (int i = 0; i < picFolders.size(); i++) {
                        if (picFolders.get(i).getPath().equals(folderpaths)) {
                            picFolders.get(i).setFirstPic(datapath);
                            picFolders.get(i).addpics();
                        }
                    }
                }
            } while (cursor.moveToNext());
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < picFolders.size(); i++) {
            Log.d("picture folders", picFolders.get(i).getFolderName() + " and path = " + picFolders.get(i).getPath() + " " + picFolders.get(i).getNumberOfPics());
        }

        //reverse order ArrayList
       /* ArrayList<imageFolder> reverseFolders = new ArrayList<>();

        for(int i = picFolders.size()-1;i > reverseFolders.size()-1;i--){
            reverseFolders.add(picFolders.get(i));
        }*/

        return picFolders;
    }

    public void onPicClicked(com.example.ugallery.utils.PicHolder holder, int position, ArrayList<com.example.ugallery.utils.pictureFacer> pics) {

    }

    public void onPicClicked(String pictureFolderPath, String folderName) {
        Intent move = new Intent(getActivity(), ImageDisplay.class);
        move.putExtra("folderPath", pictureFolderPath);
        move.putExtra("folderName", folderName);
        //move.putExtra("recyclerItemSize",getCardsOptimalWidth(4));
        startActivity(move);
    }
}