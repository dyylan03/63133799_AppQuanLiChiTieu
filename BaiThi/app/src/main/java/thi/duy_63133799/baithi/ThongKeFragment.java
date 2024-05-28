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
                // Kiểm tra nếu selectedDate không null thì chỉ hiển thị danh sách chi tiêu của ngày đó
                if (selectedDate != null) {
                    filterChiTieuListByDate(selectedDate, chiTieuList);
                } else {
                    // Nếu selectedDate là null, hiển thị toàn bộ danh sách chi tiêu
                    updateChiTieuList(chiTieuList);
                }
            }
        });

        return view;
    }

    // Trong ThongKeFragment
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar newSelectedDate = Calendar.getInstance();
                        newSelectedDate.set(year, month, dayOfMonth);
                        // Kiểm tra xem ngày mới có khác với ngày được chọn trước đó không
                        if (selectedDate == null || !selectedDate.equals(newSelectedDate)) {
                            selectedDate = newSelectedDate;
                            recyclerView.setVisibility(View.VISIBLE);
                            // Cập nhật lại danh sách chi tiêu sau khi chọn ngày mới
                            shareViewModel.filterChiTieuListByDate(dayOfMonth, month + 1, year);
                        }
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }



    private void filterChiTieuListByDate(Calendar date, List<String> chiTieuList) {
        List<String> filteredList = new ArrayList<>();
        for (String chiTieu : chiTieuList) {
            // Phân tích chi tiêu để lấy ngày tháng năm
            String[] parts = chiTieu.split(" ");
            String[] ngayThangNam = parts[0].split("-");
            int year = Integer.parseInt(ngayThangNam[2]);
            int month = Integer.parseInt(ngayThangNam[1]) - 1; // Calendar.MONTH là zero-based
            int day = Integer.parseInt(ngayThangNam[0]);
            // Kiểm tra nếu ngày của chi tiêu trùng với ngày được chọn thì thêm vào danh sách lọc
            if (date.get(Calendar.YEAR) == year && date.get(Calendar.MONTH) == month && date.get(Calendar.DAY_OF_MONTH) == day) {
                filteredList.add(chiTieu);
            }
        }
        updateChiTieuList(filteredList);
    }

    private void updateChiTieuList(List<String> chiTieuList) {
        adapter.setChiTieuList(chiTieuList);
    }
}
