package com.plubits.pokerspeedrange.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.plubits.pokerspeedrange.R;
import com.plubits.pokerspeedrange.models.TableValue;
import com.plubits.pokerspeedrange.utils.Constants;
import com.plubits.pokerspeedrange.utils.Constants.VALUES_STATES;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Marc on 8/2/2016.
 */
public class TableAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<TableValue> table;

    public TableAdapter(Context context, ArrayList<TableValue> table){
        mContext = context;
        this.table = table;
    }

    @Override
    public int getCount() {
        return this.table.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            tv = new TextView(mContext);

            tv.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            tv.setTextSize(10);
            int margin1 = mContext.getResources().getDimensionPixelSize(R.dimen.table_cell_rightLeft_padding);
            int margin2 = mContext.getResources().getDimensionPixelSize(R.dimen.table_cell_topBottom_padding);
            tv.setPadding(margin1, margin2, margin1, margin2);
        } else {
            tv = (TextView) convertView;
        }

        tv.setText(table.get(position).valueID);

        int drawableID;
        int textColorID;

        switch (table.get(position).state){
            case UNSELECTED:
                drawableID = R.drawable.grid_cell_unselected_background;
                textColorID = R.color.table_text_color_unselected;
                break;
            case ANTES:
                drawableID = R.drawable.grid_cell_antes_background;
                textColorID = R.color.table_text_color;
                break;
            case NO_ANTES:
                drawableID = R.drawable.grid_cell_no_antes_background;
                textColorID = R.color.table_text_color;
                break;
            default:
                drawableID = R.drawable.grid_cell_unselected_background;
                textColorID = R.color.table_text_color_unselected;
                break;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tv.setBackground(mContext.getDrawable(drawableID));
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tv.setBackground(ContextCompat.getDrawable(mContext, drawableID));
        }
        tv.setTextColor(mContext.getResources().getColor(textColorID));

        return tv;
    }
}
