package oclass.student.com.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import oclass.student.com.newproject.activity.SecondActivity;
import oclass.student.com.newproject.activity.ThreeActivity;
import oclass.student.com.newproject.adapter.HomeAdapter;
import oclass.student.com.newproject.bean.BaseWeather;
import oclass.student.com.newproject.bean.City;
import oclass.student.com.newproject.bean.Weather;
import oclass.student.com.newproject.retrofit.RetrofitFactory;
import oclass.student.com.newproject.util.LogUtils;
import oclass.student.com.newproject.util.ToastUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.radio)
    RadioButton radio;
    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio2)
    RadioButton radio2;
    @BindView(R.id.radio3)
    RadioButton radio3;
    @BindView(R.id.datepicker)
    DatePicker datepicker;
    @BindView(R.id.ratbar)
    RatingBar ratbar;
    //    @BindView(R.id.tool)
//    Toolbar toolbar;
    @BindView(R.id.main_layout)
    FrameLayout mainLayout;
    //    @BindView(R.id.drawer_navigation)
//    NavigationView navigation;
//    @BindView(R.id.drawer_layout)
//    DrawerLayout drawerLayout;
    //    @BindView(R.id.seekbar)
//    SeekBar seekbar;
    private ActionBarDrawerToggle drawerToggle;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigation;
    private RecyclerView recyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mmain);
        ButterKnife.bind(this);
        initData();
        initView();
//        init();
//        ratingbar();
//        chron();
//        analog();
//        dialog();
//
        toolbar.setTitle("首页");
//        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(MainActivity.this, "打开", Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(View.GONE);
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(MainActivity.this, "关闭", Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(View.VISIBLE);
                super.onDrawerClosed(drawerView);
            }
        };
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                return false;
            }
        });
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    private void initView() {
        toolbar = findViewById(R.id.tool);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigation = findViewById(R.id.drawer_navigation);
        recyclerView = findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(mAdapter = new HomeAdapter(MainActivity.this, mDatas));
        recyclerView.setAdapter(mAdapter=new HomeAdapter(MainActivity.this,mDatas));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this, "onClick事件您点击了第：" + position + "个Item", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {
                Intent intent = new Intent(MainActivity.this, ThreeActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "onLongClick事件您点击了第：" + position + "个Item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void dialog() {

    }


    private void analog() {
        AnalogClock analogClock = findViewById(R.id.analog);
    }

    private void chron() {
        Chronometer chronometer = findViewById(R.id.chronometer);
        long base = chronometer.getBase();
        Log.d("aaa", "base" + base);
        chronometer.setBase(0);
        chronometer.start();
    }

    private void ratingbar() {
        RatingBar ratingBar = findViewById(R.id.ratbar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

            }
        });
    }

    private void init() {
        SeekBar seekBar = findViewById(R.id.seekbar);
//        seekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) MainActivity.this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBar.setSecondaryProgress((i + seekBar.getMax()) / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void requestInboxData(final String city) {
        final City city1 = new City();
        city1.setCity(city);
        Call<Weather> call = RetrofitFactory.getGsonRetrofitService(RetrofitFactory.BASE_URL).getWeather(RetrofitFactory.getParams(city1));
        call.enqueue(new Callback<Weather>() {
            public void onResponse(Call<Weather> call, final Response<Weather> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                LogUtils.e(TAG, t.getMessage(), t);
                ToastUtils.toastShort(getApplicationContext(), getResources().getString(R.string.app_name));
            }
        });


    }

    private static final String TAG = "MailListActivity";


//    String jsonData = "https://www.sojson.com/open/api/weather/json.shtml?city=郑州";
//    List<BaseWeather> students = GsonUtil.parseJsonArrayWithGson(jsonData, BaseWeather.class);
//
//    /*
//    22  * 封装的GSON解析工具类，提供泛型参数
//    23  */
//    static class GsonUtil {
//        // 将Json数据解析成相应的映射对象
//        public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
//            Gson gson = new Gson();
//            T result = gson.fromJson(jsonData, type);
//            return result;
//        }
//
//        // 将Json数组解析成相应的映射对象列表
//        public static <T> List<T> parseJsonArrayWithGson(String jsonData,
//                                                         Class<T> type) {
//            Gson gson = new Gson();
//            List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {
//            }.getType());
//            return result;
//        }
//    }
}
