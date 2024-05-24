package thi.duy_63133799.baithi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ThemFragment extends Fragment {

    private TextView textViewTime;
    private EditText editTextLiDoChiTieu;
    private EditText editTextSoTienChiTieu;
    private Button buttonThemChiTieu;

    public ThemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_them, container, false);

        textViewTime = view.findViewById(R.id.textViewTime);
        editTextLiDoChiTieu = view.findViewById(R.id.editTextLiDoChiTieu);
        editTextSoTienChiTieu = view.findViewById(R.id.editTextSoTienChiTieu);
        buttonThemChiTieu = view.findViewById(R.id.buttonThemChiTieu);

        // Lấy thời gian hiện tại và định dạng nó
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String currentTime = dateFormat.format(calendar.getTime());

        // Đặt giá trị của TextView là thời gian đã được định dạng
        textViewTime.setText(currentTime);

        buttonThemChiTieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nhấn vào nút
                addExpense();
            }
        });

        return view;
    }

    private void addExpense() {
        String liDoChiTieu = editTextLiDoChiTieu.getText().toString();
        String soTienChiTieuStr = editTextSoTienChiTieu.getText().toString();

        if (liDoChiTieu.isEmpty() || soTienChiTieuStr.isEmpty()) {
            // Nếu không có thông tin nhập vào, thông báo lỗi hoặc yêu cầu nhập đầy đủ thông tin
            return;
        }

        // Chuyển đổi số tiền chi tiêu từ String sang double
        double soTienChiTieu = Double.parseDouble(soTienChiTieuStr);

        // Lấy thời gian hiện tại
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String ngayGio = dateFormat.format(calendar.getTime());
        editTextLiDoChiTieu.setText("");
        editTextSoTienChiTieu.setText("");

        Toast.makeText(getActivity(), "Thêm khoản chi tiêu thành công", Toast.LENGTH_SHORT).show();
    }
}
