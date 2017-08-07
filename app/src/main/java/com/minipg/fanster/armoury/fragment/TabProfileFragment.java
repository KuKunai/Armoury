package com.minipg.fanster.armoury.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.adapter.XpListAdapter;

import java.util.ArrayList;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class TabProfileFragment extends Fragment {

    private double[] values;
    private String[] codename;
    private String[] colors;
    private View mView;
    private PieChart chart;
    private ListView listView;
    private XpListAdapter xpListAdapter;
    private ListView listView2;

    public TabProfileFragment() {
        super();
    }

    public static TabProfileFragment newInstance() {
        TabProfileFragment fragment = new TabProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_profile, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        mView = rootView;
        chart = (PieChart) rootView.findViewById(R.id.pieChart);
        initChart();
        listView = (ListView) rootView.findViewById(R.id.listXp);
        listView2 = (ListView) rootView.findViewById(R.id.listXp);
        xpListAdapter = new XpListAdapter();
        listView.setAdapter(xpListAdapter);
        listView2.setAdapter(xpListAdapter);
    }

    private void initChart() {
//        final ArrayList<Student> listStudent = Student.getSampleStudentData(5);
//        ArrayList<PieEntry> entries = new ArrayList<>();
//        for (Student student : listStudent) {
//            entries.add(new PieEntry(student.getScore(), student.getName()));
//        }
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(20,"IOS"));
        entries.add(new PieEntry(15,"Android"));
        entries.add(new PieEntry(18,"Web"));
        entries.add(new PieEntry(30,"Service"));

        PieDataSet dataset = new PieDataSet(entries, "Topic");
        dataset.setSelectionShift(10);
        dataset.setValueTextSize(14);
        dataset.setColors(ColorTemplate.MATERIAL_COLORS);
        //dataset.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataset.setValueLinePart1Length(0.5f);
        dataset.setValueLinePart2Length(0.5f);
        dataset.setValueFormatter(new PercentFormatter());

        PieData data = new PieData(dataset);

        chart.setData(data);
        chart.setDescription("%Topic that you shared");
        chart.setHoleRadius(20);
        chart.setTransparentCircleRadius(0);
        chart.animateY(500);
        chart.setUsePercentValues(true);
    }


    public static class Student {
        float score;
        String name;

        public Student(String name,float score) {
            this.score = score;
            this.name = name;
        }

        public float getScore() {
            return score;
        }

        public void setScore(float score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static ArrayList<Student> getSampleStudentData(int size) {
            ArrayList<Student> student = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                student.add(new Student("Android v"+i, (float) Math.random() * 100));
            }
            return student;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
}
