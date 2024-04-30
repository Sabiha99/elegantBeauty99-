package com.companyname.elegantbeauty;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

public class LoginAdapter extends FragmentStateAdapter{
   // private Context context;
   // private int totPage;

    public LoginAdapter(@NonNull @NotNull FragmentActivity fragmentActivity/*Context context,int totPage*/)
    {

        super(fragmentActivity);
       // this.context = context;
        //this.totPage = totPage;
    }


    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LoginClass();

            case 1:
                return new SignupClass();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}