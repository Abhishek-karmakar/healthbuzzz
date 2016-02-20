package com.healthbuzz.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.healthbuzz.foodapp.ui.app.model.SideNavigationItem;

import java.util.List;

import heathbuzz.app.healthapp.R;

/**
 * Created by vassharma on 2/20/16.
 */
public class NavigationDrawerListAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 1;
    private static final int LAYOUT_TYPES = 2;

    private final Context context;
    private final List<SideNavigationItem> mSideNavigationItemList;

    public NavigationDrawerListAdapter(Context context, List<SideNavigationItem> sideNavigationItemList) {
        this.context = context;
        this.mSideNavigationItemList = sideNavigationItemList;
    }

    private boolean isHeader(int position) {
        return mSideNavigationItemList.get(position).getIsItemIsHeader();
    }

    @Override
    public boolean isEnabled(int position) {
        return !isHeader(position);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public int getCount() {
        return mSideNavigationItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSideNavigationItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView navigationDrawerItemText, navigationDrawerItemHeaderText;
        ImageView navigationDrawerItemIcon;

        public ViewHolder(View view, int type) {
            switch (type) {
                case TYPE_ITEM:
                    navigationDrawerItemText = (TextView) view.findViewById(R.id.side_navigation_list_item_title);
                    navigationDrawerItemIcon = (ImageView) view.findViewById(R.id.side_navigation_drawer_icon_image);
                    break;
            }
        }
    }
    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return LAYOUT_TYPES;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (type) {
                case TYPE_ITEM:
                    convertView = layoutInflater.inflate(R.layout.left_drawer_item, parent, false);
                    viewHolder = new ViewHolder(convertView, TYPE_ITEM);
                    break;
                default:
                    break;
            }
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        switch (type) {
            case TYPE_ITEM:
                viewHolder.navigationDrawerItemText.setText(mSideNavigationItemList.get(position).getItemTitle());
                viewHolder.navigationDrawerItemIcon.setImageResource(mSideNavigationItemList.get(position).getItemImage());
                break;
            default:
                break;
        }
        return convertView;
    }

}


