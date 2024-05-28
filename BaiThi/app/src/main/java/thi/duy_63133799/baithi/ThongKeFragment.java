package thi.duy_63133799.baithi;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThongKeFragment extends Fragment {

    private ShareViewModel shareViewModel;
    private RecyclerView recyclerView;
    private ChiTieuAdapter adapter;
    private Calendar selectedDate; // Biến để lưu trữ ngày được chọn

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);

        Button buttonChonNgay = view.findViewById(R.id.buttonChonNgay);
        recyclerView = view.findViewById(R.id.recyclerViewThongKe);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new ChiTieuAdapter();
        recyclerView.setAdapter(adapter);

        // Ẩn RecyclerView khi fragment được tạo lần đầu tiên
        recyclerView.setVisibility(View.GONE);

        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        buttonChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        shareViewModel.getChiTieuList().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> chiTieuList) {
                // Chỉ cập nhật danh sách khi có ngày được chọn
                if (selectedDate != null) {
                    List<String> filteredList = filterChiTieuListByDate(selectedDate, chiTieuList);
                    updateChiTieuList(filteredList);
                }
            }
        });

        return view;
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);

                        // Cập nhật danh sách chi tiêu dựa trên ngày được chọn
                        List<String> chiTieuList = shareViewModel.getChiTieuList().getValue();
                        if (chiTieuList != null) {
                            List<String> filteredList = filterChiTieuListByDate(selectedDate, chiTieuList);
                            updateChiTieuList(filteredList);
                            // Hiển thị RecyclerView sau khi người dùng chọn ngày
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private List<String> filterChiTieuListByDate(Calendar date, List<String> chiTieuList) {
        List<String> filteredList = new ArrayList<>();
        for (String chiTieu : chiTieuList) {
            // Phân tích chi tiêu để lấy ngày tháng năm
            String[] parts = chiTieu.split(" - ");
            String[] ngayThangNam = parts[0].split("-");
            int year = Integer.parseInt(ngayThangNam[2]);
            int month = Integer.parseInt(ngayThangNam[1]) - 1; // Calendar.MONTH là zero-based
            int day = Integer.parseInt(ngayThangNam[0]);
            // Kiểm tra nếu ngày của chi tiêu trùng với ngày được chọn thì thêm vào danh sách lọc
            if (date.get(Calendar.YEAR) == year && date.get(Calendar.MONTH) == month && date.get(Calendar.DAY_OF_MONTH) == day) {
                filteredList.add(chiTieu);
            }
        }
        return filteredList;
    }

    private void updateChiTieuList(List<String> chiTieuList) {
        adapter.setChiTieuList(chiTieuList);
        recyclerView.setVisibility(chiTieuList.isEmpty() ? View.GONE : View.VISIBLE);
    }
}
