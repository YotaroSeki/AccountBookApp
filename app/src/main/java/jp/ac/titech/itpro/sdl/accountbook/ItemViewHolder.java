package jp.ac.titech.itpro.sdl.accountbook;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
  public TextView nameView;
  public TextView priceView;

  public ItemViewHolder(View view) {
    super(view);
    nameView = (TextView) view.findViewById(R.id.name);
    priceView = (TextView) view.findViewById(R.id.price);
  }
}
