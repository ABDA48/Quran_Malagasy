package google.abdallah.qurannn.Activity;
import google.abdallah.qurannn.Adapter.FragAdapter;
import google.abdallah.qurannn.Fragayat.Juzfrag;
import google.abdallah.qurannn.Fragayat.SuratFrag;
import google.abdallah.qurannn.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class JuzActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juz);
        toolbar=findViewById(R.id.toolbar);
        viewPager2=findViewById(R.id.viewpager);
        tabLayout=findViewById(R.id.toolbarLayout);
        setSupportActionBar(toolbar);
        int pos=getIntent().getIntExtra("pos",0);
        FragAdapter fragAdapter= new FragAdapter(this);
        for (int i = 0; i <30 ; i++) {
            fragAdapter.addFragMent(Juzfrag.newInstance(i));
        }
        viewPager2.setAdapter(fragAdapter);
        tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        ViewCompat.setLayoutDirection(viewPager2, ViewCompat.LAYOUT_DIRECTION_RTL);
        viewPager2.setCurrentItem(pos,true);
        // tabLayout.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("Juz"+(position+1));
            }
        });
        tabLayoutMediator.attach();
    }


}
