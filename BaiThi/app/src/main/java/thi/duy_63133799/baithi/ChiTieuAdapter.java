package thi.duy_63133799.baithi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChiTieuAdapter extends RecyclerView.Adapter<ChiTieuAdapter.ChiTieuViewHolder> {

    private List<String> chiTieuList = new ArrayList<>();

    @NonNull
    @Override
    public ChiTieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chi_tieu, parent, false);
        return new ChiTieuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTieuViewHolder holder, int position) {
        String chiTieu = chiTieuList.get(position);
        holder.textViewChiTieu.setText(chiTieu);
    }

    @Override
    public int getItemCount() {
        return chiTieuList.size();
    }

    public void setChiTieuList(List<String> chiTieuList) {
        this.chiTieuList = chiTieuList;
        notifyDataSetChanged();
    }

    class ChiTieuViewHolder extends RecyclerView.ViewHolder {
        TextView textViewChiTieu;

        public ChiTieuViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewChiTieu = itemView.findViewById(R.id.textViewChiTieu);
        }
    }
}
