package jp.ac.titech.itpro.sdl.accountbook;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {
  private List<Item> list;

  public ItemViewAdapter(List<Item> list) {
    this.list = list;
  }

  @NonNull
  @Override
  public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
    View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
    ItemViewHolder holder = new ItemViewHolder(inflate);
    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
    itemViewHolder.nameView.setText(list.get(i).getName());
    itemViewHolder.priceView.setText(String.valueOf(list.get(i).getPrice()));
  }

  @Override
  public int getItemCount() {
    return list.size();
  }
}
