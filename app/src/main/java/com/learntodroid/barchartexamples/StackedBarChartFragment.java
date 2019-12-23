package com.learntodroid.barchartexamples;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StackedBarChartFragment extends Fragment {
    private static final int MAX_X_VALUE = 30;
    private static final int MAX_Y_VALUE = 50;
    private static final int MIN_Y_VALUE = 5;
    private static final String STACK_1_LABEL = "Stack 1";
    private static final String STACK_2_LABEL = "Stack 2";
    private static final String STACK_3_LABEL = "Stack 3";
    private static final String SET_LABEL = "Things Set";
    BarChart chart;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stackedbarchart, container, false);

        chart = view.findViewById(R.id.fragment_stackedbarchart_chart);

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < MAX_X_VALUE; i++) {
            float value1 = Math.max(MIN_Y_VALUE, (float) Math.random() * (MAX_Y_VALUE + 1));
            float value2 = Math.max(MIN_Y_VALUE, (float) Math.random() * (MAX_Y_VALUE + 1));
            float value3 = Math.max(MIN_Y_VALUE, (float) Math.random() * (MAX_Y_VALUE + 1));
            values.add(new BarEntry(i, new float[]{value1, value2, value3}));
        }

        BarDataSet set1 = new BarDataSet(values, SET_LABEL);

        set1.setColors(ColorTemplate.MATERIAL_COLORS);
        set1.setStackLabels(new String[] {STACK_1_LABEL, STACK_2_LABEL, STACK_3_LABEL});

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);

        chart.setData(data);
        chart.invalidate();

        return view;
    }
}
