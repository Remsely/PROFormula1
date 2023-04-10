package edu.samsungit.remsely.proformula.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import edu.samsungit.remsely.proformula.databinding.FragmentHomeBinding;
import edu.samsungit.remsely.proformula.ui.notifications_settings_dialog.NotificationsSettingsFragment;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    private LinearLayout mOpenNotificationDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mOpenNotificationDialog = binding.notificationsButton;
        mOpenNotificationDialog.setOnClickListener(v -> {
            NotificationsSettingsFragment dialog = new NotificationsSettingsFragment();
            assert getFragmentManager() != null;
            dialog.show(getFragmentManager(), "NotificationDialog");
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}