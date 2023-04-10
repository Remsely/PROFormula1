package edu.samsungit.remsely.proformula.ui.notifications_settings_dialog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import edu.samsungit.remsely.proformula.MainActivity;
import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.FragmentNotificationsSettingsBinding;

public class NotificationsSettingsFragment extends Fragment {
    private FragmentNotificationsSettingsBinding binding;

    private NotificationsSettingsViewModel mViewModel;
    private LinearLayout mOpenHomeFragment;
    public static NotificationsSettingsFragment newInstance() {
        return new NotificationsSettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNotificationsSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mOpenHomeFragment = binding.notificationsBackButton;
        navigateToHome();
    }

    public void navigateToHome(){
        mOpenHomeFragment.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_navigation_notifications_settings_to_navigation_home2);
        });
    }
}