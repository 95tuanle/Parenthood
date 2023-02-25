package com.humber.parenthood;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.humber.parenthood.Fragments.ChatFragment;
import com.humber.parenthood.Fragments.EatInOutFragment;
import com.humber.parenthood.Fragments.GroceryListFragment;
import com.humber.parenthood.databinding.ActivityMainBinding;
import com.humber.parenthood.eat_in_layout.IngredientPicker;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FragmentManager fragmentManager;
    Fragment chatFragment;
    Fragment groceryFragment;
    Fragment settingsFragment;
    Fragment active;
    Fragment eatInOutFragment;
    Fragment ingredientPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatFragment = new ChatFragment();
        eatInOutFragment = new EatInOutFragment();
        settingsFragment = new SettingsFragment();
        groceryFragment = new GroceryListFragment();
        ingredientPicker = new IngredientPicker();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(binding.container.getId(), settingsFragment).hide(settingsFragment);
        fragmentTransaction.add(binding.container.getId(), groceryFragment).hide(groceryFragment);
        fragmentTransaction.add(binding.container.getId(), eatInOutFragment).hide(eatInOutFragment);
        fragmentTransaction.add(binding.container.getId(), ingredientPicker).hide(ingredientPicker);
        fragmentTransaction.add(binding.container.getId(), chatFragment);
        fragmentTransaction.commitNow();
        active = chatFragment;
        getSupportActionBar().setTitle("Chat");

        binding.btm.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.chat) {
                changeFragment(chatFragment);
                getSupportActionBar().setTitle("Chat");
            } else if (itemId == R.id.groceries) {
                changeFragment(groceryFragment);
                getSupportActionBar().setTitle("Groceries List");
            } else if (itemId == R.id.settings) {
                changeFragment(settingsFragment);
                getSupportActionBar().setTitle("Settings");
            } else if (itemId == R.id.eat) {
                changeFragment(eatInOutFragment);
                getSupportActionBar().setTitle("Eat In/Out");
            } else if (itemId == R.id.eat_options) {
                changeFragment(ingredientPicker);
                getSupportActionBar().setTitle("Eat In/Out");
            }
            return true;
        });
    }

    private void changeFragment(Fragment fragment){
        fragmentManager.beginTransaction().hide(active).show(fragment).commit();
        active = fragment;
    }
}