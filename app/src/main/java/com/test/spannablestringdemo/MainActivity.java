package com.test.spannablestringdemo;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        flags 常用的几种属性：
//
//        Spanned.SPAN_INCLUSIVE_EXCLUSIVE 从起始下标到结束下标，包括起始下标不包含结束坐标
//        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE 从起始下标到结束下标，但都不包括起始下标和结束下标
//        Spanned.SPAN_INCLUSIVE_INCLUSIVE 从起始下标到终了下标，同时包括起始下标和结束下标
//        Spanned.SPAN_EXCLUSIVE_INCLUSIVE 从起始下标到终了下标，包括结束下标不包含起始坐标

        TextView tv1 = findViewById(R.id.tv1);
        tv1.setText("点击,后面的,文字", TextView.BufferType.SPANNABLE);
        getEachParagraph(tv1);
        tv1.setMovementMethod(LinkMovementMethod.getInstance());

        // 前景色
        TextView tv2 = findViewById(R.id.tv2);
        SpannableString spannableString = new SpannableString("设置文字的前景色");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#56DEA9"));
        spannableString.setSpan(foregroundColorSpan, spannableString.length() - 3, spannableString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv2.setText(spannableString);

        // 背景色
        TextView tv3 = findViewById(R.id.tv3);
        spannableString = new SpannableString("设置文字的背景色");
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.parseColor("#56DEA9"));
        spannableString.setSpan(backgroundColorSpan, spannableString.length() - 3, spannableString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv3.setText(spannableString);

        // 相对大小
        TextView tv4 = findViewById(R.id.tv4);
        spannableString = new SpannableString("9月22日");
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(2.0f);
        spannableString.setSpan(relativeSizeSpan, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv4.setText(spannableString);

        // 删除线
        TextView tv5 = findViewById(R.id.tv5);
        spannableString = new SpannableString("删除这段文字");
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv5.setText(spannableString);

        // 下划线
        TextView tv6 = findViewById(R.id.tv6);
        spannableString = new SpannableString("给文字添加下划线");
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableString.setSpan(underlineSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv6.setText(spannableString);

        // 上角标
        TextView tv7 = findViewById(R.id.tv7);
        spannableString = new SpannableString("右上角有一个角标1");
        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
        spannableString.setSpan(superscriptSpan, spannableString.length() - 1, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv7.setText(spannableString);

        // 下角标
        TextView tv8 = findViewById(R.id.tv8);
        spannableString = new SpannableString("右下角有一个角标1");
        SubscriptSpan subscriptSpan = new SubscriptSpan();
        spannableString.setSpan(subscriptSpan, spannableString.length() - 1, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv8.setText(spannableString);

        // 设置风格（粗体、斜体）
        TextView tv9 = findViewById(R.id.tv9);
        spannableString = new SpannableString("为文字设置粗体,斜体风格");
        StyleSpan styleSpan1 = new StyleSpan(Typeface.BOLD);
        StyleSpan styleSpan2 = new StyleSpan(Typeface.ITALIC);
        spannableString.setSpan(styleSpan1, 5, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(styleSpan2, 8, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv9.setText(spannableString);

        // 图文混排
        TextView tv10 = findViewById(R.id.tv10);
        spannableString = new SpannableString("在文本中添加XX");
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_launcher);
        // 设置边框
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        ImageSpan imageSpan = new ImageSpan(drawable);
        spannableString.setSpan(imageSpan, 6, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(backgroundColorSpan, 0, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv10.setText(spannableString);

        // 超链接
        TextView tv11 = findViewById(R.id.tv11);
        spannableString = new SpannableString("为文字设置超链接");
        URLSpan urlSpan = new URLSpan("http://www.baidu.com/");
        spannableString.setSpan(urlSpan, spannableString.length() - 3, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv11.setText(spannableString);
        tv11.setHighlightColor(Color.RED);
        tv11.setMovementMethod(LinkMovementMethod.getInstance());

        // 为文字设置模糊
        TextView tv12 = findViewById(R.id.tv12);
        spannableString = new SpannableString("为文字设置模糊");
//        BlurMaskFilter.Blur.NORMAL 默认类型，模糊内外边界
//        BlurMaskFilter.Blur.INNER 内部模糊
//        BlurMaskFilter.Blur.OUTER 外部模糊
//        BlurMaskFilter.Blur.SOLID 在边界内绘制固体，模糊
        MaskFilterSpan maskFilterSpan = new MaskFilterSpan(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
        spannableString.setSpan(maskFilterSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv12.setText(spannableString);

        // 为文字设置浮雕
        TextView tv13 = findViewById(R.id.tv13);
        spannableString = new SpannableString("为文字设置浮雕");
        maskFilterSpan = new MaskFilterSpan(new EmbossMaskFilter(new float[]{0, 1, 1}, 0.2f, 8, 10));
        spannableString.setSpan(maskFilterSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv13.setText(spannableString);

        //
        TextView tv14 = findViewById(R.id.tv14);
        spannableString = new SpannableString("为文字设置光栅");
        BulletSpan bulletSpan = new BulletSpan();
        spannableString.setSpan(bulletSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv14.setText(spannableString);

        TextView tv15 = findViewById(R.id.tv15);
        spannableString = new SpannableString("为文字设置光栅");
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(100);
        spannableString.setSpan(absoluteSizeSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv15.setText(spannableString);

        TextView tv17 = findViewById(R.id.tv17);
//        spannableString = new SpannableString("TabStopSpan");
//        TabStopSpan tabStopSpan = new TabStopSpan.Standard(29);
//        spannableString.setSpan(tabStopSpan, 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//        spannableString = new SpannableString("ScaleXSpan");
//        ScaleXSpan span = new ScaleXSpan(1.5f);
//        spannableString.setSpan(span, 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        spannableString = new SpannableString("ScaleXSpan");
        QuoteSpan span = new QuoteSpan();
        spannableString.setSpan(span, 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv17.setText(spannableString);

    }

    public void getEachParagraph(TextView textView) {
        Spannable spans = (Spannable) textView.getText();
        Integer[] indices = getIndices(
                textView.getText().toString().trim(), ',');
        int start = 0;
        int end = 0;
        // recycle
        for (int i = 0; i <= indices.length; i++) {
            ClickableSpan clickSpan = getClickableSpan();
            //setspan
            end = (i < indices.length ? indices[i] : spans.length());
            spans.setSpan(clickSpan, start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = end + 1;
        }
        // 改变选中文本的高亮颜色
        textView.setHighlightColor(Color.BLUE);
    }

    //click
    private ClickableSpan getClickableSpan() {
        return new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                TextView tv = (TextView) widget;
                String s = tv
                        .getText()
                        .subSequence(tv.getSelectionStart(),
                                tv.getSelectionEnd()).toString();
                Log.e("onclick--:", s);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.BLACK);
                ds.setUnderlineText(false);
            }
        };
    }

    //array
    public static Integer[] getIndices(String s, char c) {
        int pos = s.indexOf(c, 0);
        List<Integer> indices = new ArrayList<Integer>();
        while (pos != -1) {
            indices.add(pos);
            pos = s.indexOf(c, pos + 1);
        }
        return (Integer[]) indices.toArray(new Integer[0]);
    }
}