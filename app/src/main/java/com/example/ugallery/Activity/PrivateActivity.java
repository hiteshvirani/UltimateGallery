package com.example.ugallery.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.ugallery.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PrivateActivity extends AppCompatActivity {

    FloatingActionButton add_btn,video_btn,image_btn,all_btn;
    Boolean closed ;
    Animation from_bottom_anim,to_bottom_anim,rotate_open_anim,rotate_close_anim;
    public static final int abc = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private);
        Toolbar toolbar  = findViewById(R.id.private_toolbar);
        setSupportActionBar(toolbar);



        closed = false;

        add_btn = findViewById(R.id.add_btn);
        video_btn = findViewById(R.id.video_btn);
        image_btn = findViewById(R.id.image_btn);
        all_btn = findViewById(R.id.all_btn);

        from_bottom_anim = AnimationUtils.loadAnimation
                (this,R.anim.from_bottom_anim);
        to_bottom_anim = AnimationUtils.loadAnimation
                (this,R.anim.to_bottom_anim);
        rotate_open_anim = AnimationUtils.loadAnimation
                (this,R.anim.rotate_open_anim);
        rotate_close_anim = AnimationUtils.loadAnimation
                (this,R.anim.rotate_close_anim);

        add_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        OnAddButtonClick();
                    }
                });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_menu,menu);

        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.filter:
                Toast.makeText(this, "filter", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu:
                Toast.makeText(this, "menu", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void OnAddButtonClick() {
        setVisibility(closed);
        setAnimation(closed);

        closed = !closed;
    }
    private void setAnimation(Boolean closed) {
        if(!closed){

            video_btn.startAnimation(from_bottom_anim);
            image_btn.startAnimation(from_bottom_anim);
            all_btn.startAnimation(from_bottom_anim);
            video_btn.startAnimation(rotate_open_anim);
            image_btn.startAnimation(rotate_open_anim);
            all_btn.startAnimation(rotate_open_anim);



        }else{

            video_btn.startAnimation(to_bottom_anim);
            image_btn.startAnimation(to_bottom_anim);
            all_btn.startAnimation(to_bottom_anim);
            video_btn.startAnimation(rotate_close_anim);
            image_btn.startAnimation(rotate_close_anim);
            all_btn.startAnimation(rotate_close_anim);


        }
    }

    private void setVisibility(Boolean closed) {
        if(!closed)
        {
            video_btn.show();
            image_btn.show();
            all_btn.show();

            video_btn.setVisibility(View.VISIBLE);
            image_btn.setVisibility(View.VISIBLE);
            all_btn.setVisibility(View.VISIBLE);

        }else{

            video_btn.hide();
            image_btn.hide();
            all_btn.hide();

            video_btn.setVisibility(View.GONE);
            image_btn.setVisibility(View.GONE);
            all_btn.setVisibility(View.GONE);

        }
    }
}