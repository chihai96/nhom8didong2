package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomButton extends LinearLayout {

    /*2 thuộc tính tương ứng với 2 view
     * trong tập tin custom_button.xml
     */
    ImageView thumb = null;
    TextView label = null;

    public CustomButton(Context context, AttributeSet attrs) {
        super(context,attrs);
        //chuẩn bị đọc tập tin custom_button.xml
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(inflater != null){
            inflater.inflate(R.layout.custom_button, this);

            //đọc tập tin custom_button.xml lấy về 2 View tương ứng
            this.thumb = (ImageView) findViewById(R.id.CustomButtonImage);
            this.label = (TextView) findViewById(R.id.CustomButtonText);

            //lấy thuộc tính text từ thẻ com.danweb.vietnamtourism.CustomButton
            CharSequence labelText = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "text");

            //thiết lập cho TextView trên CustomButton hiện tại
            this.label.setText(labelText);

            //lấy id của drawable từ thẻ com.danweb.vietnamtourism.CustomButton
            int resId = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "src", 0);

            //thiết lập cho ImageView trên CustomButton hiện tại
            this.thumb.setImageResource(resId);
        }
    }
}
