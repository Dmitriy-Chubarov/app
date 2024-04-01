package com.example.app.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.app.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.databinding.FragmentHomeBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private PieChart pieChart;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        pieChart = (PieChart) root.findViewById(R.id.pieChart);
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(25f, "Red"));
        entries.add(new PieEntry(35f, "Blue"));
        entries.add(new PieEntry(40f, "Green"));

        PieDataSet dataSet = new PieDataSet(entries, "Pie Chart");
        dataSet.setColors(Color.RED, Color.BLUE, Color.GREEN);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh chart
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}