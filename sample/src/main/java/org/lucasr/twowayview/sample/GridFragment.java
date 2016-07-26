/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lucasr.twowayview.sample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.ItemClickSupport.OnItemClickListener;
import org.lucasr.twowayview.ItemClickSupport.OnItemLongClickListener;
import org.lucasr.twowayview.widget.DividerItemDecoration;
import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

public class GridFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_spannable_grid, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TwoWayView mRecyclerView = (TwoWayView) view.findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);
        final ItemClickSupport itemClick = ItemClickSupport.addTo(mRecyclerView);
        itemClick.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View child, int position, long id) {
            }
        });
        itemClick.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(RecyclerView parent, View child, int position, long id) {
                return true;
            }
        });
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            }
        });
        final Drawable divider = getResources().getDrawable(R.drawable.divider);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(divider));
        mRecyclerView.setAdapter(new GridAdapter(getActivity(), mRecyclerView, getItems()));
    }

    private List<GridItem> getItems() {
        List<GridItem> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add(new GridItem(i));
        }
        return items;
    }
}
