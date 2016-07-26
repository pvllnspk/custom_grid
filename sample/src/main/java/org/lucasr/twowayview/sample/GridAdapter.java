package org.lucasr.twowayview.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lucasr.twowayview.widget.TwoWayView;
import org.lucasr.twowayview.widget.SpannableGridLayoutManager;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.SimpleViewHolder> {
	private final Context mContext;
	private final List<GridItem> mItems;

	public static class SimpleViewHolder extends RecyclerView.ViewHolder {
		public final TextView title;
		public SimpleViewHolder(View view) {
			super(view);
			title = (TextView) view.findViewById(R.id.title);
		}
	}

	public GridAdapter(Context context, List<GridItem> items) {
		mContext = context;
		mItems = items;
		notifyDataSetChanged();
	}

	@Override
	public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
		return new SimpleViewHolder(view);
	}

	@Override
	public void onBindViewHolder(SimpleViewHolder holder, int position) {
		holder.title.setText(String.valueOf(mItems.get(position).getId()));
		final View itemView = holder.itemView;
		final SpannableGridLayoutManager.LayoutParams lp = (SpannableGridLayoutManager.LayoutParams) itemView.getLayoutParams();
		final int span1 = (position == 0 || position == 3 ? 2 : 1);
		final int span2 = (position == 0 ? 2 : (position == 3 ? 3 : 1));
		final int colSpan = span2;
		final int rowSpan = span1;
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
