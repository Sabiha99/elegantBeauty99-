package com.companyname.elegantbeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import org.jetbrains.annotations.NotNull;

import static java.sql.DriverManager.println;

public class Introduction extends AppCompatActivity {

    ImageView image1,image2,name,logo,inverselogo;
    LottieAnimationView lottieSplash;

     private static final int page2=1;
    private ViewPager viewpager;
    private page2Adapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        logo=findViewById(R.id.logoId);
        inverselogo=findViewById(R.id.inverseLogoId);
        image1=findViewById(R.id.background_image1);
        image2=findViewById(R.id.background_image2);
        name=findViewById(R.id.nameId);
        lottieSplash=findViewById(R.id.splashId);
        viewpager=findViewById(R.id.pager);



        image1.animate().translationY(-1500).setDuration(1000).setStartDelay(4000);
        image2.animate().translationY(-1500).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(1500).setDuration(1000).setStartDelay(4000);
        inverselogo.animate().translationY(-1500).setDuration(1000).setStartDelay(4000);
        name.animate().translationY(-1500).setDuration(1000).setStartDelay(4000);
        lottieSplash.animate().translationY(1500).setDuration(1000).setStartDelay(4000);
       // println("1");

       // viewpager=findViewById(R.id.pager);
        pageAdapter= new page2Adapter(getSupportFragmentManager());
        viewpager.setAdapter(pageAdapter);

    }

   private static class page2Adapter extends FragmentStatePagerAdapter {
        public page2Adapter(@NonNull @NotNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            //switch(position)
           // {
                //case 1:

                Page2Activity pg2 = new Page2Activity();
                return pg2;


               // default:
                    //return null;
           // }
        }

        @Override
        public int getCount() {
            return page2;
        }
    }
}