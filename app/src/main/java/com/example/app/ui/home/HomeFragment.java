package com.example.app.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.example.app.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.databinding.FragmentHomeBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private PieChart pieChart;

    private BarChart barChart;

    private void setIconToCalendar(com.applandeo.materialcalendarview.CalendarView calendarView,
                                   List<EventDay> events, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        try {
            calendarView.setDate(calendar);
        } catch (OutOfDateRangeException | NullPointerException e) {
            Log.e("FitnessApp", "Error setting calendar date: " + e.getMessage());
        }
        events.add(new EventDay(calendar, R.drawable.dumbbell_solid));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        com.applandeo.materialcalendarview.CalendarView calendarView = (com.applandeo.materialcalendarview.CalendarView) root.findViewById(R.id.calendarView);

        Calendar minDate = Calendar.getInstance();
        minDate.set(2024, Calendar.JANUARY, 1);
        calendarView.setMinimumDate(minDate);

        List<EventDay> events = new ArrayList<>();
        setIconToCalendar(calendarView, events, 2024, 4, 2);
        setIconToCalendar(calendarView, events, 2024, 4, 5);
        calendarView.setEvents(events);

        pieChart = (PieChart) root.findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(65f, "Выполнено"));
        entries.add(new PieEntry(35f, "Пропущено"));
        PieDataSet dataSet = new PieDataSet(entries, "");

        int pink = Color.parseColor("#FC8086");
        dataSet.setColors(pink, Color.LTGRAY);
        dataSet.setValueTextSize(25f);
        dataSet.setValueTextColors(Arrays.asList(Color.GRAY, pink));
        pieChart.setHoleRadius(75);
        pieChart.setHoleColor(Color.TRANSPARENT);


        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh chart
        pieChart.setDrawSliceText(false);
        pieChart.setDrawMarkers(false);
        data.setDrawValues(false);
        pieChart.getDescription().setEnabled(false);
        Legend l = pieChart.getLegend(); // get legend of pie
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER); // set vertical alignment for legend
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // set horizontal alignment for legend
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setTextColor(Color.WHITE);
        l.setFormSize(20f);
        l.setTextSize(20f);
        l.setDrawInside(false);

        barChart = (BarChart) root.findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);

        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getAxisLeft().setDrawLabels(false);

        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisRight().setDrawLabels(false);

        ArrayList<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(1, 20));
        entries2.add(new BarEntry(2, 25));
        entries2.add(new BarEntry(3, 30));
        entries2.add(new BarEntry(4, 5));
        entries2.add(new BarEntry(5, 50));
        entries2.add(new BarEntry(6, 10));
        BarDataSet dataSet2 = new BarDataSet(entries2, "");
        barChart.getLegend().setEnabled(false);
        dataSet2.setColor(pink);
        dataSet2.setDrawValues(false);
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