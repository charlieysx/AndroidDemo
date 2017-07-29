package com.codebear.demo.demo.main.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.codebear.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description:
 * <p>
 * Created by CodeBear on 2017/7/29.
 */

public class MainButtonViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_button)
    public TextView tvButton;

    public MainButtonViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
