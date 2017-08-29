package com.course.couponapp;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.course.couponapp.R;

import java.util.ArrayList;

public class BuyCouponActivity extends ListActivity implements DialogInterface.OnClickListener{

    private ArrayList<Coupon> coupons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_coupon);

        for (int i = 0;i<100;i++){
            Coupon c = new Coupon("Coupon.jpg", "Coupon Title", "6/8/2017", "9/9/2017", 10.5);
            coupons.add(c);
        }
        CouponsAdapter adapter = new CouponsAdapter(this, coupons);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("Agree coupon buy " + position +" ?");
        dialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", this);
        dialog.setButton(Dialog.BUTTON_POSITIVE, "Buy", this);
        dialog.setCancelable(false);
        dialog.show();


    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i==Dialog.BUTTON_POSITIVE){
            Toast.makeText(this, "buy " , Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "cancel ", Toast.LENGTH_LONG).show();
        }
    }
}
