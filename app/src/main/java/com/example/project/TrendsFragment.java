package com.example.project;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrendsFragment extends Fragment {

    private LineChart daychart;
    private LineChart daychart1;
    private LineChart daychart2;

    public TrendsFragment() {
        // Required empty public constructor
    }


    public static TrendsFragment newInstance() {
        TrendsFragment fragment = new TrendsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trends, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        daychart = (LineChart) view.findViewById(R.id.linechart);
        daychart1 = (LineChart) view.findViewById(R.id.linechart2);
        daychart2 = (LineChart) view.findViewById(R.id.linechart3);
        //daychart.setOnChartGestureListener(trends.this);
        //daychart.setOnChartValueSelectedListener(trends.this);

        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.montserrat);


        daychart.setDragEnabled(true);
        daychart.setClickable(false);
        daychart.setTouchEnabled(false);
        daychart.setScaleEnabled(false);
        daychart1.setDragEnabled(true);
        daychart1.setScaleEnabled(false);
        daychart2.setDragEnabled(true);
        daychart2.setScaleEnabled(false);


        ArrayList<Entry> yValue = new ArrayList<>();

        yValue.add(new Entry(7,60));

        /*
        yValue.add(new Entry(0, 60f ));
        yValue.add(new Entry(1, 50f ));
        yValue.add(new Entry(2, 70f ));
        yValue.add(new Entry(3, 30f ));
        yValue.add(new Entry(4, 50f ));
        yValue.add(new Entry(5, 60f ));
        yValue.add(new Entry(6, 65f ));*/
        LineDataSet set1 = new LineDataSet(yValue, "data set 1");

        daychart.getDescription().setEnabled(false);
        daychart.getLegend().setEnabled(false);
        daychart.getAxisRight().setEnabled(false);
        daychart.getAxisLeft().setDrawGridLines(false);
        daychart.getAxisRight().setDrawGridLines(false);
        daychart.getXAxis().setGridColor(getResources().getColor(R.color.main_color));
        //daychart.getXAxis().setGridLineWidth(0.3);
        daychart.getXAxis().setTypeface(typeface);
        daychart.getAxisLeft().setTypeface(typeface);

        daychart.getAxisLeft().setLabelCount(5,true);
        daychart.getAxisLeft().setAxisMinimum(0);
        daychart.getAxisLeft().setAxisMaximum(100);
        daychart.getAxisLeft().setTextColor(getResources().getColor(R.color.main_color));


        daychart.getXAxis().setGranularity(1);
        daychart.getXAxis().setLabelCount(9);
        daychart.getXAxis().setAxisMinimum(1);
        daychart.getXAxis().setAxisMaximum(10);
        daychart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        daychart.getXAxis().setTextColor(getResources().getColor(R.color.main_color));



        /*IndexAxisValueFormatter valueFormatter = new IndexAxisValueFormatter(){
            @Override
            public
        };*/


        set1.setHighlightLineWidth(3);
        set1.setHighLightColor(getResources().getColor(R.color.main_color));
        set1.setDrawHorizontalHighlightIndicator(false);
        set1.setDrawValues(false);
        set1.setValueTypeface(typeface);
        set1.setCircleRadius(7);
        set1.setCircleHoleColor(getResources().getColor(R.color.main_color));
        set1.setCircleColor(getResources().getColor(R.color.main_color));
        //set1.setFillAlpha(110);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        daychart.setData(data);
        daychart.highlightValue(5,0);
        daychart1.setData(data);
        daychart2.setData(data);
    }
}