package thi.duy_63133799.baithi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ChiTieuAdapter extends RecyclerView.Adapter<ChiTieuAdapter.ViewHolder> {

    private List<String> chiTieuList = new ArrayList<>();

    public void setChiTieuList(List<String> chiTieuList) {
        this.chiTieuList = chiTieuList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chi_tieu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String chiTieu = chiTieuList.get(position);
        holder.bind(chiTieu);
    }

    @Override
    public int getItemCount() {
        return chiTieuList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewChiTieu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewChiTieu = itemView.findViewById(R.id.textViewChiTieu);
        }

        public void bind(String chiTieu) {
            textViewChiTieu.setText(chiTieu);
        }
    }
}
