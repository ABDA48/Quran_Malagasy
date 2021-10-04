package google.abdallah.qurannn.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import google.abdallah.qurannn.Adapter.JumpDialogue;
import google.abdallah.qurannn.Adapter.PagerAdapter;
import google.abdallah.qurannn.R;
import google.abdallah.qurannn.fragment.Bookmark;
import google.abdallah.qurannn.fragment.Juz;
import google.abdallah.qurannn.fragment.surah;


public class MainActivity extends AppCompatActivity  implements JumpDialogue.JumpListner {
Toolbar toolbar;
ViewPager viewPager;
    PagerAdapter pagerAdapter;
    TabLayout tabLayout;
    JumpDialogue jumpDialogue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
toolbar=findViewById(R.id.toolbar);
viewPager=findViewById(R.id.viewpager);
tabLayout=findViewById(R.id.toolbarLayout);
setSupportActionBar(toolbar);
pagerAdapter=new PagerAdapter(getSupportFragmentManager());
pagerAdapter.addFrag(new surah(),"Surah");
pagerAdapter.addFrag(new Juz(),"JUZ");
pagerAdapter.addFrag(new Bookmark(),"Bookmark");
viewPager.setAdapter(pagerAdapter);
tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_sitting,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.setting:
                Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
             break;
            case R.id.ratting:
                Intent intent=new Intent(MainActivity.this,ResearchActivity.class);
                startActivity(intent);
                break;
            case R.id.about:
                Toast.makeText(MainActivity.this, "about", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search:
                jumpDialogue=new JumpDialogue();
                jumpDialogue.show(this.getSupportFragmentManager(),"open");
                break;

        }
        return true;
    }

    @Override
    public void jumpMethode(int i, int j) {
        Toast.makeText(this, "sourat "+i+" "+"ayat "+j, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        intent.putExtra("int",i);
        intent.putExtra("name","jump");
        intent.putExtra("position",j);
        startActivity(intent);
    }
}
