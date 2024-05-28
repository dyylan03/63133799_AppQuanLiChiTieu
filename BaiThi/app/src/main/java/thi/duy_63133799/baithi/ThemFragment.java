package thi.duy_63133799.baithi;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;

public class ThemFragment extends Fragment {

    private EditText editTextLiDoChiTieu, editTextSoTienChiTieu;
    private Button buttonThemChiTieu, buttonChonNgay; // Thêm buttonChonNgay vào đây
    private ShareViewModel shareViewModel;
    private String liDoChiTieu, soTienChiTieu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them, container, false);

        editTextLiDoChiTieu = view.findViewById(R.id.editTextLiDoChiTieu);
        editTextSoTienChiTieu = view.findViewById(R.id.editTextSoTienChiTieu);
        buttonThemChiTieu = view.findViewById(R.id.buttonThemChiTieu);
        buttonChonNgay = view.findViewById(R.id.buttonChonNgay); // Ánh xạ buttonChonNgay

        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        // Sự kiện click cho buttonChonNgay
        buttonChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lưu trữ dữ liệu của lý do chi tiêu và giá tiền
                liDoChiTieu = editTextLiDoChiTieu.getText().toString().trim();
                soTienChiTieu = editTextSoTienChiTieu.getText().toString().trim();

                // Gọi phương thức showDatePickerDialog()
                showDatePickerDialog();
            }
        });

        buttonThemChiTieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nếu muốn thêm ngày vào khi thêm khoản chi tiêu, bạn có thể gọi showDatePickerDialog() ở đây
                // liDoChiTieu = editTextLiDoChiTieu.getText().toString().trim();
                // soTienChiTieu = editTextSoTienChiTieu.getText().toString().trim();

                // showDatePickerDialog();
            }
        });

        return view;
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int hour = calendar.get(Calendar.HOUR_OF_DAY);
                        int minute = calendar.get(Calendar.MINUTE);

                        // Tạo đối tượng Calendar với ngày và thời gian đã chọn
                        calendar.set(year, month, dayOfMonth, hour, minute);

                        // Format thời gian
                        String thoiGian = DateFormat.format("dd-MM-yyyy HH:mm:ss", calendar.getTime()).toString();

                        // Tạo chuỗi thông tin chi tiêu sử dụng dữ liệu đã lưu trữ
                        String chiTieu = thoiGian + " - " + liDoChiTieu + ": " + soTienChiTieu;
                        shareViewModel.addChiTieu(chiTieu);

                        Toast.makeText(requireContext(), "Thêm khoản chi tiêu thành công", Toast.LENGTH_SHORT).show();
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}
