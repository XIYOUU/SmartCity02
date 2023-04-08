package com.example.smartcity02.dataAna;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.smartcity02.R;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.commonBean.NewsBean;
import com.example.smartcity02.dataAnaBean.DataAnaBean;
import com.example.smartcity02.utils.ConnUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*数据分析模块*/
public class AnaMainActivity extends AppCompatActivity {
//    private HorizontalBarChart bar_chart;
    private BarChart bar_chart;

    //    private BarChart bar_chart;
    List<BarEntry> list = new ArrayList<>();//实例化一个List用来存储数据
//    List<BarEntry> list2 = new ArrayList<>();//实例化一个List用来存储数据
//    List<BarEntry> list;
    //    private NewsBean newsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_main);

//        bar_chart = (HorizontalBarChart) findViewById(R.id.bar_chart);
//        initDataAna();

        bar_chart = (BarChart) findViewById(R.id.bar_chart);


        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        initBar();
//        initBar();
        initDataAna();
    }
//BarChart
    public void initDataAna() {
        ApiServe api = ConnUtils.getApi();
        Call<DataAnaBean> pressList = api.getPressList(5, 1);
        pressList.enqueue(new Callback<DataAnaBean>() {


            @Override
            public void onResponse(Call<DataAnaBean> call, final Response<DataAnaBean> response) {


                for (int i = 0; i < response.body().getRows().size(); i++) {
                    list.add(new BarEntry(i, response.body().getRows().get(i).getLikeNum()));
                }

//                Log.e("i:", String.valueOf(list.get(0)));
                BarDataSet barDataSet = new BarDataSet(list, "新闻点赞数");
                BarData barData = new BarData(barDataSet);
//                Log.e("i:", String.valueOf(barData));
                bar_chart.setData(barData);
                bar_chart.getDescription().setEnabled(false);//隐藏右下角英文
                bar_chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的位置 默认为上面
                bar_chart.getAxisRight().setEnabled(false);//隐藏右侧Y轴   默认是左右两侧都有Y轴

                XAxis xAxis = bar_chart.getXAxis();
                xAxis.setLabelCount(response.body().getRows().size());
                xAxis.setDrawGridLines(false);  //是否绘制X轴上的网格线（背景里面的竖线）
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);        //X轴所在位置   默认为上面
                xAxis.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        Log.e("value", String.valueOf(value));
                        for (int i = 0; i < response.body().getRows().size(); i++) {
                            if ((int) value == i) {
                                return response.body().getRows().get(i).getTitle().substring(0, 6);
                            }
                        }
                        return "";
                    }
                });


                bar_chart.invalidate();


            }

            @Override
            public void onFailure(Call<DataAnaBean> call, Throwable throwable) {

            }
        });
    }

//BarChart
//    public void initDataAna() {
//        ApiServe api = ConnUtils.getApi();
//        Call<DataAnaBean> pressList = api.getPressList(5, 1);
//        pressList.enqueue(new Callback<DataAnaBean>() {
//
//
//            @Override
//            public void onResponse(Call<DataAnaBean> call, final Response<DataAnaBean> response) {
//
//
//                for (int i = 0; i < response.body().getRows().size(); i++) {
//                    list.add(new BarEntry(i, response.body().getRows().get(i).getLikeNum()));
//                }
//
////                Log.e("i:", String.valueOf(list.get(0)));
//                BarDataSet barDataSet = new BarDataSet(list, "新闻点赞数");
//                BarData barData = new BarData(barDataSet);
////                Log.e("i:", String.valueOf(barData));
//                bar_chart.setData(barData);
//                bar_chart.getDescription().setEnabled(false);//隐藏右下角英文
//                bar_chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的位置 默认为上面
//                bar_chart.getAxisRight().setEnabled(false);//隐藏右侧Y轴   默认是左右两侧都有Y轴
//
//                XAxis xAxis = bar_chart.getXAxis();
//                xAxis.setLabelCount(response.body().getRows().size());
//                xAxis.setDrawGridLines(false);  //是否绘制X轴上的网格线（背景里面的竖线）
//                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);        //X轴所在位置   默认为上面
//                xAxis.setValueFormatter(new ValueFormatter() {
//                    @Override
//                    public String getFormattedValue(float value) {
//                        Log.e("value", String.valueOf(value));
//                        for (int i = 0; i < response.body().getRows().size(); i++) {
//                            if ((int) value == i) {
//                                return response.body().getRows().get(i).getTitle().substring(0, 6);
//                            }
//                        }
//                        return "";
//                    }
//                });
//
//
//                bar_chart.invalidate();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<DataAnaBean> call, Throwable throwable) {
//
//            }
//        });
//    }

//LineChart
//    public void initDataAna() {
//        ApiServe api = ConnUtils.getApi();
//        Call<DataAnaBean> pressList = api.getPressList(5, 1);
//        pressList.enqueue(new Callback<DataAnaBean>() {
//
//
//            @Override
//            public void onResponse(Call<DataAnaBean> call, final Response<DataAnaBean> response) {
//
//
//                for (int i = 0; i < response.body().getRows().size(); i++) {
//                    list.add(new Entry(i, response.body().getRows().get(i).getLikeNum()));
//                }
//
////                Log.e("i:", String.valueOf(list.get(0)));
//                LineDataSet barDataSet = new LineDataSet(list, "新闻点赞数");
//                LineData barData = new LineData(barDataSet);
////                Log.e("i:", String.valueOf(barData));
//                bar_chart.setData(barData);
//                bar_chart.getDescription().setEnabled(false);//隐藏右下角英文
//                bar_chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的位置 默认为上面
//                bar_chart.getAxisRight().setEnabled(false);//隐藏右侧Y轴   默认是左右两侧都有Y轴
//
//                XAxis xAxis = bar_chart.getXAxis();
//                xAxis.setLabelCount(response.body().getRows().size());
//                xAxis.setDrawGridLines(false);  //是否绘制X轴上的网格线（背景里面的竖线）
//                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);        //X轴所在位置   默认为上面
//                xAxis.setValueFormatter(new ValueFormatter() {
//                    @Override
//                    public String getFormattedValue(float value) {
//                        Log.e("value", String.valueOf(value));
//                        for (int i = 0; i < response.body().getRows().size(); i++) {
//                            if ((int) value == i) {
//                                return response.body().getRows().get(i).getTitle().substring(0, 6);
//                            }
//                        }
//                        return "";
//                    }
//                });
//
//
//                bar_chart.invalidate();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<DataAnaBean> call, Throwable throwable) {
//
//            }
//        });
//    }

//PieChart
//    public void initDataAna() {
//        ApiServe api = ConnUtils.getApi();
//        Call<DataAnaBean> pressList = api.getPressList(5, 1);
//        pressList.enqueue(new Callback<DataAnaBean>() {
//
//
//            @Override
//            public void onResponse(Call<DataAnaBean> call, final Response<DataAnaBean> response) {
//
//
//                for (int i = 0; i < response.body().getRows().size(); i++) {
////                    list.add(new PieEntry(i, response.body().getRows().get(i).getLikeNum()));
//                    list.add(new PieEntry(response.body().getRows().get(i).getLikeNum(), response.body().getRows().get(i).getTitle().substring(0, 6)));
//                }
//
////                Log.e("i:", String.valueOf(list.get(0)));
//                PieDataSet barDataSet = new PieDataSet(list, "新闻点赞数");
//                PieData barData = new PieData(barDataSet);
//
//                List<Integer> colors = new ArrayList<>();
//                colors.add(Color.parseColor("#FF0000"));
//                colors.add(Color.parseColor("#00FF00"));
//                colors.add(Color.parseColor("#0000FF"));
//                colors.add(Color.parseColor("#ee6e66"));
//                colors.add(Color.parseColor("#ee6e55"));
//
////                barDataSet.setColors();
//                barDataSet.setColors(colors);
//                bar_chart.setData(barData);
//                bar_chart.getDescription().setEnabled(false);//隐藏右下角英文
//                bar_chart.invalidate();
////                bar_chart.getDescription().setEnabled(false);
//
//            }
//
//            @Override
//            public void onFailure(Call<DataAnaBean> call, Throwable throwable) {
//
//            }
//        });
//    }


//    public void initDataAna() {
//        ApiServe api = ConnUtils.getApi();
//        Call<DataAnaBean> pressList = api.getPressList(5, 1);
//        pressList.enqueue(new Callback<DataAnaBean>() {
//
//
//            @Override
//            public void onResponse(Call<DataAnaBean> call, final Response<DataAnaBean> response) {
//
//
//                for (int i = 0; i < response.body().getRows().size(); i++) {
//                    list.add(new BarEntry(i, response.body().getRows().get(i).getLikeNum()));
//                    list2.add(new BarEntry(i, response.body().getRows().get(i).getLikeNum()+100));
//                }
//
////                Log.e("i:", String.valueOf(list.get(0)));
//                BarDataSet barDataSet = new BarDataSet(list, "新闻点赞数");
//                BarData barData = new BarData(barDataSet);
//                BarDataSet barDataSet2 = new BarDataSet(list2, "新闻点赞数");
////                BarData barData2 = new BarData(barDataSet);
//                barData.addDataSet(barDataSet2);
//
//                barData.setBarWidth(0.2f);//柱子的宽度
//                //重点！   三个参数   分别代表   X轴起点     组与组之间的间隔      组内柱子的间隔
//                barData.groupBars(1f,0.6f,0);
////                Log.e("i:", String.valueOf(barData));
//                bar_chart.setData(barData);
////                bar_chart.setData(barData2);
//                bar_chart.getDescription().setEnabled(false);//隐藏右下角英文
//                bar_chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的位置 默认为上面
//                bar_chart.getAxisRight().setEnabled(false);//隐藏右侧Y轴   默认是左右两侧都有Y轴
//                bar_chart.getXAxis().setCenterAxisLabels(true);
//                XAxis xAxis = bar_chart.getXAxis();
//                xAxis.setLabelCount(response.body().getRows().size());
//                xAxis.setDrawGridLines(false);  //是否绘制X轴上的网格线（背景里面的竖线）
//                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);        //X轴所在位置   默认为上面
//                xAxis.setValueFormatter(new ValueFormatter() {
//                    @Override
//                    public String getFormattedValue(float value) {
//                        Log.e("value", String.valueOf(value));
//                        for (int i = 0; i < response.body().getRows().size(); i++) {
//                            if ((int) value == i) {
//                                return response.body().getRows().get(i).getTitle().substring(0, 6);
//                            }
//                        }
//                        return "";
//                    }
//                });
//
//
//                bar_chart.invalidate();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<DataAnaBean> call, Throwable throwable) {
//
//            }
//        });
//    }

//双折线
//    public void initDataAna() {
//        ApiServe api = ConnUtils.getApi();
//        Call<DataAnaBean> pressList = api.getPressList(5, 1);
//        pressList.enqueue(new Callback<DataAnaBean>() {
//
//
//            @Override
//            public void onResponse(Call<DataAnaBean> call, final Response<DataAnaBean> response) {
//
//
//                for (int i = 0; i < response.body().getRows().size(); i++) {
//                    list.add(new Entry(i, response.body().getRows().get(i).getLikeNum()));
//                    list2.add(new Entry(i, response.body().getRows().get(i).getLikeNum()+100));
//                }
//
////                Log.e("i:", String.valueOf(list.get(0)));
//                LineDataSet barDataSet = new LineDataSet(list, "新闻点赞数");
//                LineData barData = new LineData(barDataSet);
//                LineDataSet barDataSet2 = new LineDataSet(list2, "新闻点赞数");
////                BarData barData2 = new BarData(barDataSet);
//                barData.addDataSet(barDataSet2);
//
//                barData.setBarWidth(0.2f);//柱子的宽度
////                //重点！   三个参数   分别代表   X轴起点     组与组之间的间隔      组内柱子的间隔
//                barData.groupBars(1f,0.6f,0);
////                Log.e("i:", String.valueOf(barData));
//                bar_chart.setData(barData);
////                bar_chart.setData(barData2);
//                bar_chart.getDescription().setEnabled(false);//隐藏右下角英文
//                bar_chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的位置 默认为上面
//                bar_chart.getAxisRight().setEnabled(false);//隐藏右侧Y轴   默认是左右两侧都有Y轴
//                bar_chart.getXAxis().setCenterAxisLabels(true);
//                XAxis xAxis = bar_chart.getXAxis();
//                xAxis.setLabelCount(response.body().getRows().size());
//                xAxis.setDrawGridLines(false);  //是否绘制X轴上的网格线（背景里面的竖线）
//                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);        //X轴所在位置   默认为上面
//                xAxis.setValueFormatter(new ValueFormatter() {
//                    @Override
//                    public String getFormattedValue(float value) {
//                        Log.e("value", String.valueOf(value));
//                        for (int i = 0; i < response.body().getRows().size(); i++) {
//                            if ((int) value == i) {
//                                return response.body().getRows().get(i).getTitle().substring(0, 6);
//                            }
//                        }
//                        return "";
//                    }
//                });
//
//
//                bar_chart.invalidate();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<DataAnaBean> call, Throwable throwable) {
//
//            }
//        });
//    }

//    public void initDataAna() {
//        ApiServe api = ConnUtils.getApi();
//        Call<DataAnaBean> pressList = api.getPressList(5, 1);
//        pressList.enqueue(new Callback<DataAnaBean>() {
//
//
//            @Override
//            public void onResponse(Call<DataAnaBean> call, final Response<DataAnaBean> response) {
//
//
//                for (int i = 0; i < response.body().getRows().size(); i++) {
//                    list.add(new BarEntry(i, response.body().getRows().get(i).getLikeNum()));
//                }
//
////                Log.e("i:", String.valueOf(list.get(0)));
//                BarDataSet barDataSet = new BarDataSet(list, "新闻点赞数");
//                BarData barData = new BarData(barDataSet);
////                Log.e("i:", String.valueOf(barData));
//                bar_chart.setData(barData);
//                bar_chart.getDescription().setEnabled(false);//隐藏右下角英文
//                bar_chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的位置 默认为上面
//                bar_chart.getAxisRight().setEnabled(false);//隐藏右侧Y轴   默认是左右两侧都有Y轴
//
//                XAxis xAxis = bar_chart.getXAxis();
//                xAxis.setLabelCount(response.body().getRows().size());
//                xAxis.setDrawGridLines(false);  //是否绘制X轴上的网格线（背景里面的竖线）
//                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);        //X轴所在位置   默认为上面
//                xAxis.setValueFormatter(new ValueFormatter() {
//                    @Override
//                    public String getFormattedValue(float value) {
//                        Log.e("value", String.valueOf(value));
//                        for (int i = 0; i < response.body().getRows().size(); i++) {
//                            if ((int) value == i) {
//                                return response.body().getRows().get(i).getTitle().substring(0, 6);
//                            }
//                        }
//                        return "";
//                    }
//                });
//
//
//                bar_chart.invalidate();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<DataAnaBean> call, Throwable throwable) {
//
//            }
//        });
//    }
//    public void initBar(){
//        ApiServe api = ConnUtils.getApi();
//        Call<DataAnaBean> pressList = api.getPressList(5, 1);
//        pressList.enqueue(new Callback<DataAnaBean>() {
//            @Override
//            public void onResponse(Call<DataAnaBean> call, final Response<DataAnaBean> response) {
//                List<BarEntry> list=new ArrayList<>();
//
//                for (int i = 0; i < response.body().getRows().size(); i++) {
//                    list.add(new BarEntry(i,response.body().getRows().get(i).getLikeNum()));
//                }
//
//                BarDataSet barDataSet=new BarDataSet(list,"新闻");
//                BarData barData=new BarData(barDataSet);
//
//                bar_chart.setData(barData);
//                bar_chart.invalidate();
//
////                bar_chart.get
//                bar_chart.getAxisRight().setEnabled(false);
//                XAxis xAxis=bar_chart.getXAxis();
//                xAxis.setLabelCount(response.body().getRows().size());
//                xAxis.setDrawGridLines(false);
//                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//
//                xAxis.setValueFormatter(new ValueFormatter() {
//
//                    @Override
//                    public String getFormattedValue(float value) {
//                        for (int i = 0; i < response.body().getRows().size(); i++) {
//                            if((int) value==i){
//                                return response.body().getRows().get(i).getTitle().substring(0,6);
//                            }
//                        }
//                        return "";
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(Call<DataAnaBean> call, Throwable throwable) {
//
//            }
//        });
//    }


//    public void initBar() {
//        ApiServe api = ConnUtils.getApi();
//        Call<DataAnaBean> pressList = api.getPressList(5, 1);
//        pressList.enqueue(new Callback<DataAnaBean>() {
//            @Override
//            public void onResponse(Call<DataAnaBean> call, Response<DataAnaBean> response) {
//                List<BarEntry> list=new ArrayList<>();
//                for (int i = 0; i < response.body().getRows().size(); i++) {
//                    list.add(new BarEntry(i,response.body().getRows().get(i).getLikeNum()));
//                }
//                BarDataSet barDataSet=new BarDataSet(list,"新闻点赞数");
//                BarData barData=new BarData(barDataSet);
//
//                bar_chart.setData(barData);
//                bar_chart.invalidate();
//            }
//
//            @Override
//            public void onFailure(Call<DataAnaBean> call, Throwable throwable) {
//
//            }
//        });
//
//    }


}