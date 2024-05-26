package thi.duy_63133799.baithi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class DanhSachFragment extends Fragment {

    private LinearLayout linearLayoutContent;
    private TextView textViewLuong;
    private ShareViewModel shareViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danh_sach, container, false);

        linearLayoutContent = view.findViewById(R.id.linearLayoutContent);
        textViewLuong = view.findViewById(R.id.textViewLuong);

        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        shareViewModel.getChiTieuList().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> chiTieuList) {
                updateChiTieuList(chiTieuList);
            }
        });

        shareViewModel.getLuong().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String luong) {
                textViewLuong.setText("Phần lương: " + luong);
            }
        });

        return view;
    }

    private void updateChiTieuList(List<String> chiTieuList) {
        linearLayoutContent.removeAllViews();
        for (String chiTieu : chiTieuList) {
            String[] parts = chiTieu.split(" - ");
            String thoiGian = parts[0];
            String[] chiTieuInfo = parts[1].split(": ");
            String liDoChiTieu = chiTieuInfo[0];
            String giaTien = chiTieuInfo[1];

            TextView textViewChiTieu = new TextView(requireContext());
            textViewChiTieu.setText("Thời gian: " + thoiGian + "\n"
                    + "Lí do: " + liDoChiTieu + "\n"
                    + "Giá tiền: " + giaTien);
            textViewChiTieu.setPadding(0, 0, 0, 16); // Khoảng cách giữa các mục
            linearLayoutContent.addView(textViewChiTieu);
        }
    }
}
