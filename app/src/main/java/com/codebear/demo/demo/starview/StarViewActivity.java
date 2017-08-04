package com.codebear.demo.demo.starview;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.codebear.demo.R;
import com.codebear.demo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:
 * <p>
 * Created by CodeBear on 2017/8/3.
 */

public class StarViewActivity extends BaseActivity {

    @BindView(R.id.sv_star)
    StarView svStar;
    @BindView(R.id.sb_count)
    SeekBar sbCount;
    @BindView(R.id.sb_progress)
    SeekBar sbProgress;
    @BindView(R.id.sb_size)
    SeekBar sbSize;
    @BindView(R.id.sb_space)
    SeekBar sbSpace;
    @BindView(R.id.tv_show_stroke)
    TextView tvShowStroke;

    private boolean showStroke = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start_view;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        sbCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0) {
                    progress = 1;
                }
                svStar.setStarCount(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                svStar.setStarProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0) {
                    progress = 1;
                }
                svStar.setStarSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbSpace.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                svStar.setStarSpace(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbCount.setProgress(5);
        sbProgress.setProgress(30);
        sbSize.setProgress(50);
        sbSpace.setProgress(10);
        tvShowStroke.setText("不显示边框");
    }

    @OnClick(R.id.tv_show_stroke)
    public void onShowStrokeClick() {
        showStroke = !showStroke;
        svStar.setShowStarStroke(showStroke);
        if(showStroke) {
            tvShowStroke.setText("不显示边框");
        } else {
            tvShowStroke.setText("显示边框");
        }
    }
}
