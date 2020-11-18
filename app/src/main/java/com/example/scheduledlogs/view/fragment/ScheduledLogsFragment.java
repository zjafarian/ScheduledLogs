package com.example.scheduledlogs.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scheduledlogs.R;
import com.example.scheduledlogs.databinding.FragmentScheduledLogsBinding;
import com.example.scheduledlogs.viewmodel.ScheduledLogsViewModel;


public class ScheduledLogsFragment extends Fragment {
    private FragmentScheduledLogsBinding mBinding;
    private ScheduledLogsViewModel mViewModel;



    public ScheduledLogsFragment() {
        // Required empty public constructor
    }


    public static ScheduledLogsFragment newInstance() {
        ScheduledLogsFragment fragment = new ScheduledLogsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ScheduledLogsViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_scheduled_logs,
                container,
                false);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }
}