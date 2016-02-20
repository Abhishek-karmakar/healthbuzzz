package com.healthbuzz.foodapp.ui.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import heathbuzz.app.healthapp.R;

/**
 * Created by vassharma on 2/20/16.
 */
public class MyOrdersFragment extends Fragment {

    private final static String TAG = MyOrdersFragment.class.getSimpleName();

    private Views mViews;

    public MyOrdersFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_orders, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViews = new Views(view);

    }

    @Override
    public void onDestroyView() {
        mViews = null;
        super.onDestroyView();
    }

    static class Views {
        final TextView textContactUs;

        public Views(View view) {
            textContactUs = (TextView) view.findViewById(R.id.text_contact_us);
        }
    }
}
