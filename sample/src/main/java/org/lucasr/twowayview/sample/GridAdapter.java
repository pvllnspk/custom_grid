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

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lucasr.twowayview.TwoWayLayoutManager;
import org.lucasr.twowayview.widget.TwoWayView;
import org.lucasr.twowayview.widget.SpannableGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.SimpleViewHolder> {
	private static final int COUNT = 100;

	private final Context mContext;
	private final TwoWayView mRecyclerView;
	private final List<Integer> mItems;
	private int mCurrentItemId = 0;

	public static class SimpleViewHolder extends RecyclerView.ViewHolder {
		public final TextView title;

		public SimpleViewHolder(View view) {
			super(view);
			title = (TextView) view.findViewById(R.id.title);
		}
	}

	public GridAdapter(Context context, TwoWayView recyclerView) {
		mContext = context;
		mItems = new ArrayList<Integer>(COUNT);
		for (int i = 0; i < COUNT; i++) {
			addItem(i);
		}

		mRecyclerView = recyclerView;
	}

	public void addItem(int position) {
		final int id = mCurrentItemId++;
		mItems.add(position, id);
		notifyItemInserted(position);
	}

	public void removeItem(int position) {
		mItems.remove(position);
		notifyItemRemoved(position);
	}

	@Override
	public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
		return new SimpleViewHolder(view);
	}

	@Override
	public void onBindViewHolder(SimpleViewHolder holder, int position) {
		holder.title.setText(mItems.get(position).toString());

		boolean isVertical = (mRecyclerView.getOrientation() == TwoWayLayoutManager.Orientation.VERTICAL);
		final View itemView = holder.itemView;

		final int itemId = mItems.get(position);

		final SpannableGridLayoutManager.LayoutParams lp =
				(SpannableGridLayoutManager.LayoutParams) itemView.getLayoutParams();

		final int span1 = (itemId == 0 || itemId == 3 ? 2 : 1);
		final int span2 = (itemId == 0 ? 2 : (itemId == 3 ? 3 : 1));

		final int colSpan = (isVertical ? span2 : span1);
		final int rowSpan = (isVertical ? span1 : span2);

		if (lp.rowSpan != rowSpan || lp.colSpan != colSpan) {
			lp.rowSpan = rowSpan;
			lp.colSpan = colSpan;
			itemView.setLayoutParams(lp);
		}
	}

	@Override
	public int getItemCount() {
		return mItems.size();
	}
}
