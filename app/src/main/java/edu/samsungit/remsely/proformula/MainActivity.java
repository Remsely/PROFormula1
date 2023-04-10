package edu.samsungit.remsely.proformula;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import edu.samsungit.remsely.proformula.databinding.ActivityMainBinding;
import edu.samsungit.remsely.proformula.databinding.FragmentHomeBinding;
import edu.samsungit.remsely.proformula.databinding.FragmentNotificationsSettingsBinding;
import edu.samsungit.remsely.proformula.ui.home.HomeFragment;
import edu.samsungit.remsely.proformula.ui.notifications_settings_dialog.NotificationsSettingsFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public NavController navController;

    HomeFragment homeFragment;
    NotificationsSettingsFragment notificationsSettingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
        homeFragment = new HomeFragment();
        notificationsSettingsFragment = new NotificationsSettingsFragment();
    }
}