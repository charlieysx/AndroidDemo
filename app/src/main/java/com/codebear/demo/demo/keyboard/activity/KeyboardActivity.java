package com.codebear.demo.demo.keyboard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codebear.demo.R;
import com.codebear.demo.base.BaseActivity;
import com.codebear.demo.demo.keyboard.NormalFragment;
import com.codebear.demo.demo.main.adapter.MainButtonAdapter;
import com.codebear.demo.demo.main.bean.MainButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * description:
 * <p>
 * Created by CodeBear on 2017/8/1.
 */

public class KeyboardActivity extends BaseActivity {

    @BindView(R.id.rcv_button)
    RecyclerView rcvButton;

    private MainButtonAdapter mainButtonAdapter;
    private List<MainButton> buttonList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        init();
        initData();
        showData();
    }

    private void init() {
        rcvButton.setHasFixedSize(true);
        rcvButton.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mainButtonAdapter = new MainButtonAdapter(this);
        rcvButton.setAdapter(mainButtonAdapter);

        mainButtonAdapter.setOnItemClickListener(new MainButtonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openDemo(position);
            }
        });
    }

    private void initData() {
        buttonList.add(new MainButton(1, "Normal Activity"));
        buttonList.add(new MainButton(2, "Normal Fragment"));
        buttonList.add(new MainButton(3, "Full Activity"));
    }

    private void showData() {
        mainButtonAdapter.addAll(buttonList);
    }

    private void openDemo(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, NormalActivity.class));
                break;
            case 1:
                openFragment = true;
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(android.R.id.content, new NormalFragment()).addToBackStack("NormalFragment");
                ft.commit();
                break;
            case 2:
                startActivity(new Intent(this, FullActivity.class));
                break;
            default:
                break;
        }
    }

    private boolean openFragment = false;

    @Override
    public void onBackPressed() {
        if(openFragment) {
            openFragment = false;
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
