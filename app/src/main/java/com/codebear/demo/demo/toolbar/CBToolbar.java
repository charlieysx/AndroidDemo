package com.codebear.demo.demo.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codebear.demo.R;
import com.codebear.demo.utils.ScreenUtil;

import static com.codebear.demo.R.id.tv_title;

/**
 * description:
 * <p>
 * Created by CodeBear on 2017/8/2.
 */

public class CBToolbar extends FrameLayout {

    public interface OnLeftIconClickListener {
        void onIconClick(View v);
    }

    public interface OnRightIconClickListener {
        void onIconClick(View v);
    }

    private Context mContext;

    private View rootView;
    private FrameLayout viewToolbarBg;
    private ImageView iconLeft;
    private ImageView iconRight;
    private TextView tvTitle;
    private View viewLine;
    private View viewShadow;

    private int toolbarColor;
    private float toolbarHeight;

    private boolean showShadow;
    private int shadowTopColor;
    private int shadowBottomColor;
    private float shadowHeight;

    private boolean showLine;
    private int lineColor;
    private float lineHeight;

    private String titleText;
    private int titleColor;
    private float titleSize;

    private Bitmap leftIcon;
    private Bitmap rightIcon;

    private OnLeftIconClickListener onLeftIconClickListener;
    private OnRightIconClickListener onRightIconClickListener;

    public CBToolbar(Context context) {
        this(context, null);
    }

    public CBToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CBToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(attrs);
        initView();
        setupToolbar();
        setupTitle();
        setupLine();
        setupShadow();
        setupIcon();
    }

    private void initAttrs(AttributeSet attrs) {
        if (null == attrs) {
            return;
        }
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.CBToolbar);

        toolbarColor = a.getColor(R.styleable.CBToolbar_toolbarColor,
                ContextCompat.getColor(mContext, R.color.toolbarColorDefault));
        toolbarHeight = a.getDimension(R.styleable.CBToolbar_toolbarHeight, -1);

        showShadow = a.getBoolean(R.styleable.CBToolbar_showShadow, true);
        shadowTopColor = a.getColor(R.styleable.CBToolbar_shadowTopColor,
                ContextCompat.getColor(mContext, R.color.shadowTopDefault));
        shadowBottomColor = a.getColor(R.styleable.CBToolbar_shadowTopColor,
                ContextCompat.getColor(mContext, R.color.shadowBottomDefault));
        shadowHeight = a.getDimension(R.styleable.CBToolbar_shadowHeight,
                getResources().getDimension(R.dimen.shadowHeightDefault));

        showLine = a.getBoolean(R.styleable.CBToolbar_showLine, false);
        lineColor = a.getColor(R.styleable.CBToolbar_lineColor,
                ContextCompat.getColor(mContext, R.color.lineColorDefault));
        lineHeight = a.getDimension(R.styleable.CBToolbar_lineHeight,
                getResources().getDimension(R.dimen.lineHeightDefault));

        titleText = a.getString(R.styleable.CBToolbar_titleText);
        if (titleText == null) {
            titleText = "";
        }
        titleColor = a.getColor(R.styleable.CBToolbar_titleColor,
                ContextCompat.getColor(mContext, R.color.titleColorDefault));
        titleSize = a.getDimension(R.styleable.CBToolbar_titleSize,
                getResources().getDimension(R.dimen.titleTextSizeDefault));
        titleSize = ScreenUtil.px2dip(mContext, titleSize);

        leftIcon = BitmapFactory.decodeResource(getResources(), a.getResourceId(R.styleable.CBToolbar_leftIcon, 0));
        rightIcon = BitmapFactory.decodeResource(getResources(), a.getResourceId(R.styleable.CBToolbar_rightIcon, 0));

        a.recycle();
    }

    private void initView() {
        rootView = LayoutInflater.from(mContext).inflate(R.layout.layout_toolbar, this, false);
        addView(rootView);

        viewToolbarBg = (FrameLayout) rootView.findViewById(R.id.toolbar);
        iconLeft = (ImageView) rootView.findViewById(R.id.icon_left);
        iconRight = (ImageView) rootView.findViewById(R.id.icon_right);
        tvTitle = (TextView) rootView.findViewById(tv_title);
        viewLine = rootView.findViewById(R.id.view_line);
        viewShadow = rootView.findViewById(R.id.view_shadow);
    }

    private void setupToolbar() {
        viewToolbarBg.setBackgroundColor(toolbarColor);
        if(toolbarHeight != -1) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewToolbarBg.getLayoutParams();
            params.height = (int) toolbarHeight;
            viewToolbarBg.setLayoutParams(params);
        }
    }

    private void setupTitle() {
        tvTitle.setText(titleText);
        tvTitle.setTextColor(titleColor);
        tvTitle.setTextSize(titleSize);
    }

    private void setupLine() {
        if (!showLine) {
            viewLine.setVisibility(GONE);
            return;
        }
        viewLine.setVisibility(VISIBLE);
        viewLine.setBackgroundColor(lineColor);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewLine.getLayoutParams();
        params.height = (int) lineHeight;
        viewLine.setLayoutParams(params);
    }

    private void setupShadow() {
        if (!showShadow) {
            viewShadow.setVisibility(GONE);
            return;
        }
        viewShadow.setVisibility(VISIBLE);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewShadow.getLayoutParams();
        params.height = (int) shadowHeight;
        viewShadow.setLayoutParams(params);

        GradientDrawable grad = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{shadowTopColor, shadowBottomColor}
        );
        viewShadow.setBackground(grad);
    }

    private void setupIcon() {
        setupLeftIcon();
        setupRightIcon();
    }

    private void setupLeftIcon() {
        if(leftIcon == null) {
            iconLeft.setVisibility(GONE);
            return;
        }
        iconLeft.setVisibility(VISIBLE);
        iconLeft.setImageBitmap(leftIcon);
        iconLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onLeftIconClickListener != null) {
                    onLeftIconClickListener.onIconClick(v);
                }
            }
        });
    }

    private void setupRightIcon() {
        if(rightIcon == null) {
            iconRight.setVisibility(GONE);
            return;
        }
        iconRight.setVisibility(VISIBLE);
        iconRight.setImageBitmap(rightIcon);
        iconRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRightIconClickListener != null) {
                    onRightIconClickListener.onIconClick(v);
                }
            }
        });
    }

    public int getToolbarColor() {
        return toolbarColor;
    }

    public CBToolbar setToolbarColor(int toolbarColor) {
        this.toolbarColor = toolbarColor;
        setupToolbar();
        return this;
    }

    public float getToolbarHeight() {
        return toolbarHeight;
    }

    public CBToolbar setToolbarHeight(float toolbarHeight) {
        this.toolbarHeight = toolbarHeight;
        setupToolbar();
        return this;
    }

    public boolean isShowShadow() {
        return showShadow;
    }

    public CBToolbar setShowShadow(boolean showShadow) {
        this.showShadow = showShadow;
        setupShadow();
        return this;
    }

    public int getShadowTopColor() {
        return shadowTopColor;
    }

    public CBToolbar setShadowTopColor(int shadowTopColor) {
        this.shadowTopColor = shadowTopColor;
        setupShadow();
        return this;
    }

    public int getShadowBottomColor() {
        return shadowBottomColor;
    }

    public CBToolbar setShadowBottomColor(int shadowBottomColor) {
        this.shadowBottomColor = shadowBottomColor;
        setupShadow();
        return this;
    }

    public CBToolbar setShadowColor(int shadowTopColor, int shadowBottomColor) {
        this.shadowTopColor = shadowTopColor;
        this.shadowBottomColor = shadowBottomColor;
        setupShadow();
        return this;
    }

    public float getShadowHeight() {
        return shadowHeight;
    }

    public CBToolbar setShadowHeight(float shadowHeight) {
        this.shadowHeight = shadowHeight;
        setupShadow();
        return this;
    }

    public boolean isShowLine() {
        return showLine;
    }

    public CBToolbar setShowLine(boolean showLine) {
        this.showLine = showLine;
        setupLine();
        return this;
    }

    public int getLineColor() {
        return lineColor;
    }

    public CBToolbar setLineColor(int lineColor) {
        this.lineColor = lineColor;
        setupLine();
        return this;
    }

    public float getLineHeight() {
        return lineHeight;
    }

    public CBToolbar setLineHeight(float lineHeight) {
        this.lineHeight = lineHeight;
        setupLine();
        return this;
    }

    public String getTitleText() {
        return titleText;
    }

    public CBToolbar setTitleText(String titleText) {
        this.titleText = titleText;
        setupTitle();
        return this;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public CBToolbar setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        setupTitle();
        return this;
    }

    public float getTitleSize() {
        return titleSize;
    }

    public CBToolbar setTitleSize(float titleSize) {
        this.titleSize = titleSize;
        setupTitle();
        return this;
    }

    public Bitmap getLeftIcon() {
        return leftIcon;
    }

    public CBToolbar setLeftIcon(Bitmap leftIcon) {
        this.leftIcon = leftIcon;
        setupLeftIcon();
        return this;
    }

    public Bitmap getRightIcon() {
        return rightIcon;
    }

    public CBToolbar setRightIcon(Bitmap rightIcon) {
        this.rightIcon = rightIcon;
        setupRightIcon();
        return this;
    }

    public OnLeftIconClickListener getOnLeftIconClickListener() {
        return onLeftIconClickListener;
    }

    public CBToolbar setOnLeftIconClickListener(OnLeftIconClickListener onLeftIconClickListener) {
        this.onLeftIconClickListener = onLeftIconClickListener;
        return this;
    }

    public OnRightIconClickListener getOnRightIconClickListener() {
        return onRightIconClickListener;
    }

    public CBToolbar setOnRightIconClickListener(OnRightIconClickListener onRightIconClickListener) {
        this.onRightIconClickListener = onRightIconClickListener;
        return this;
    }
}
