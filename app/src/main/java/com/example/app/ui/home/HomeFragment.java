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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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
        entries.add(new PieEntry(25f, "Выполненные тренировки"));
        entries.add(new PieEntry(35f, "Пропущенные тренировки"));
        PieDataSet dataSet = new PieDataSet(entries, "Pie Chart");

        int purple500 = 0xFF6200EE;
        dataSet.setColors(purple500, Color.GRAY);
        dataSet.setValueTextSize(25f);
        dataSet.setValueTextColor(Color.BLACK);
        pieChart.setHoleColor(Color.TRANSPARENT);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh chart

        BarChart barChart = (BarChart) root.findViewById(R.id.barChart);

        ArrayList<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(1, 20));
        entries2.add(new BarEntry(2, 25));
        entries2.add(new BarEntry(3, 30));
        entries2.add(new BarEntry(4, 5));
        entries2.add(new BarEntry(5, 50));
        entries2.add(new BarEntry(6, 10));
        BarDataSet dataSet2 = new BarDataSet(entries2, "Label");

        int teal = 0xFF03DAC5;
        dataSet2.setLabel("Количество тренировок в месяц");
        dataSet2.setColor(teal);
        dataSet2.setValueTextColor(Color.BLACK);
        dataSet2.setValueTextSize(15f);
        BarData data2 = new BarData(dataSet2);
        barChart.setData(data2);
        barChart.invalidate();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}