package edu.samsungit.remsely.proformula.ui.notifications_settings_dialog;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.FragmentNotificationsSettingsBinding;
import edu.samsungit.remsely.proformula.databinding.FragmentSeasonsBinding;

public class NotificationsSettingsFragment extends DialogFragment {
    private FragmentNotificationsSettingsBinding binding;

    private  static final String TAG = "NotificationDialog";
    private LinearLayout mCloseNotificationsDialog;

    private NotificationsSettingsViewModel mViewModel;

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
        mCloseNotificationsDialog = binding.notificationsBackButton;
        mCloseNotificationsDialog.setOnClickListener(v -> {
            getDialog().dismiss();
        });


    }
}