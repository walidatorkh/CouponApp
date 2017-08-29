package com.course.couponapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.course.couponapp.R;

import java.util.ArrayList;

public class CouponsAdapter extends ArrayAdapter<Coupon> {

    private LayoutInflater layoutInflater;

    public CouponsAdapter(Context context, ArrayList<Coupon> coupons) {
        super(context, 0, coupons);
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        RelativeLayout relativeLayout = (RelativeLayout)layoutInflater.inflate(R.layout.item_coupon, null);

        final ImageView imageViewCoupon = (ImageView)relativeLayout.findViewById(R.id.imageViewCoupon);
        TextView textViewTitle = (TextView)relativeLayout.findViewById(R.id.textViewTitle);
        TextView textViewStartDate = (TextView)relativeLayout.findViewById(R.id.textViewStartDate);
        TextView textViewEndDate = (TextView)relativeLayout.findViewById(R.id.textViewEndDate);
        TextView textViewPrice = (TextView)relativeLayout.findViewById(R.id.textViewPrice);



        Coupon coupon = getItem(position);
        //imageViewCoupon.setImageResource(R.mipmap.ic_launcher);
        textViewTitle.setText(coupon.getTitle());
        textViewStartDate.setText(coupon.getStartDate());
        textViewEndDate.setText(coupon.getEndDate());
        textViewPrice.setText(coupon.getPrice()+ " USD");

        ImageDownloader imageDownloader = new ImageDownloader(new ImageDownloader.Callbacks() {
            @Override
            public void onAboutToBegin() {

            }

            @Override
            public void onSuccess(Bitmap downloadedBitmap) {
                imageViewCoupon.setImageBitmap(downloadedBitmap);

            }

            @Override
            public void onError(int httpStatusCode, String errorMessage) {
                imageViewCoupon.setImageResource(R.mipmap.ic_launcher);
            }
        });
        imageDownloader.execute(coupon.getImage());

        return relativeLayout;
    }
}
