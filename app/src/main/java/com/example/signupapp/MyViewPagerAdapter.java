package com.example.signupapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.signupapp.Fragments.CameraFragment;
import com.example.signupapp.Fragments.GalleryFragment;
import com.example.signupapp.Fragments.ProfileFragment;

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    public MyViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if (position==0)
            return new GalleryFragment();
        else if (position==1)
            return new CameraFragment();
        else
            return new ProfileFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0){
            return "Gallery";
        } else if (position==1){
            return "Camera";
        }else {
            return "Profile";
        }
    }
}
