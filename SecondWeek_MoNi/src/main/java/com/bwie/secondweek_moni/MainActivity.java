package com.bwie.secondweek_moni;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.bwie.secondweek_moni.fragment.Login_Fragment;
import com.bwie.secondweek_moni.fragment.Reg_Fragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.button_person)
    RadioButton buttonPerson;
    @Bind(R.id.button_list)
    RadioButton buttonList;
    @Bind(R.id.groups)
    RadioGroup groups;
    @Bind(R.id.main_viewPager)
    ViewPager mainViewPager;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //定义集合添加数据
        list = new ArrayList<>();
        list.add(new Login_Fragment());
        list.add(new Reg_Fragment());

        //加入此行代码可使当页数据一直有效显示，不会随着页面滑动而消失
        mainViewPager.setOffscreenPageLimit(list.size());

        //设置适配器
        mainViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        //设置viewpager的滑动监听事件
        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                groups.check(groups.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //设置button按钮的监听事件
        groups.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.button_person:
                        mainViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.button_list:
                        mainViewPager.setCurrentItem(1, false);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
