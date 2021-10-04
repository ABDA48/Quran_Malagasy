package google.abdallah.qurannn.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
    List<Fragment>fragments=new ArrayList<>();
    List<String> strings=new ArrayList<>();

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }

    @Override
    public int getCount() {
        return strings.size();
    }
    public void addFrag(Fragment fragment,String title){
        fragments.add(fragment);
        strings.add(title);
    }
}
