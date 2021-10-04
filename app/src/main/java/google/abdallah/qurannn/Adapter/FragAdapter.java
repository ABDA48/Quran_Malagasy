package google.abdallah.qurannn.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragAdapter extends FragmentStateAdapter {
List<Fragment> fragmentList=new ArrayList<>();
    public FragAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }
    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    public void addFragMent(Fragment frag){
        this.fragmentList.add(frag);
    }

}
