package com.learntodroid.barchartexamples;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GroupedBarChartFragment extends Fragment {
    private static final int MAX_X_VALUE = 7;
    private static final int MAX_Y_VALUE = 50;
    private static final int MIN_Y_VALUE = 5;
    private static final int GROUPS = 3;
    private static final String GROUP_1_LABEL = "Group 1";
    private static final String GROUP_2_LABEL = "Group 2";
    private static final String GROUP_3_LABEL = "Group 3";
    BarChart chart;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groupedbarchart, container, false);

        chart = view.findViewById(R.id.fragment_groupedbarchart_chart);

        ArrayList<BarEntry> values1 = new ArrayList<>();
        ArrayList<BarEntry> values2 = new ArrayList<>();
        ArrayList<BarEntry> values3 = new ArrayList<>();

        for (int i = 0; i < MAX_X_VALUE; i++) {
            values1.add(new BarEntry(i, Math.max(MIN_Y_VALUE, (float) Math.random() * (MAX_Y_VALUE + 1))));
            values2.add(new BarEntry(i, Math.max(MIN_Y_VALUE, (float) Math.random() * (MAX_Y_VALUE + 1))));
            values3.add(new BarEntry(i, Math.max(MIN_Y_VALUE, (float) Math.random() * (MAX_Y_VALUE + 1))));
        }

        BarDataSet set1 = new BarDataSet(values1, GROUP_1_LABEL);
        BarDataSet set2 = new BarDataSet(values2, GROUP_2_LABEL);
        BarDataSet set3 = new BarDataSet(values3, GROUP_3_LABEL);

        set1.setColor(ColorTemplate.MATERIAL_COLORS[0]);
        set2.setColor(ColorTemplate.MATERIAL_COLORS[1]);
        set3.setColor(ColorTemplate.MATERIAL_COLORS[2]);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);

        BarData data = new BarData(dataSets);

        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        Description d = new Description();
        d.setText("Grouped Bar Chart");
        chart.setDescription(d);

        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);

        chart.getAxisRight().setEnabled(false);

        float barSpace = 0.05f;
        float barWidth = 0.2f;
        float groupSpace = 1f - ((barSpace + barWidth) * GROUPS);

        chart.setData(data);
        chart.getBarData().setBarWidth(barWidth);

        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(MAX_X_VALUE);

        chart.groupBars(0, groupSpace, barSpace);
        chart.invalidate();

        return view;
    }
}
