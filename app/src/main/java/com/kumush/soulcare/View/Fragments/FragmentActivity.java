package com.kumush.soulcare.View.Fragments;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.kumush.soulcare.R;
import com.kumush.soulcare.databinding.ActivityFragmentBinding;

public class FragmentActivity extends AppCompatActivity {

    ActivityFragmentBinding binding;

    private static final int MENU_HOME = R.id.home;
    private static final int MENU_CHAT_BOT = R.id.chat_bot;
    private static final int MENU_MY_DIARY = R.id.my_diary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        binding = ActivityFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setItemIconTintList(null);
        HomeFragment homeFragment = new HomeFragment();
        ChatBotFragment chatBotFragment = new ChatBotFragment();
        MyDiaryFragment myDiaryFragment = new MyDiaryFragment();
        ReplacementFragment(homeFragment);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == MENU_HOME) {
                ReplacementFragment(homeFragment);
            } else if (itemId == MENU_CHAT_BOT) {
                ReplacementFragment(chatBotFragment);
            } else if (itemId == MENU_MY_DIARY) {
                ReplacementFragment(myDiaryFragment);
            }
            return true;
        });


    }

    private void ReplacementFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}