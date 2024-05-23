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

public class HomeFragment extends Fragment {

    private EditText editTextLuong;
    private TextView textViewTietKiem, textViewDailyChiTieu, textViewDanhGia;
    private Button buttonTinhToan, buttonDanhGia;
    private double dailySpending;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        editTextLuong = view.findViewById(R.id.editTextLuong);
        textViewTietKiem = view.findViewById(R.id.textViewTietKiem);
        textViewDailyChiTieu = view.findViewById(R.id.textViewChiTieu);
        textViewDanhGia = view.findViewById(R.id.textViewDanhGia);
        buttonTinhToan = view.findViewById(R.id.buttonTinhToan);
        buttonDanhGia = view.findViewById(R.id.buttonDanhGia);

        buttonTinhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        buttonDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluate();
            }
        });

        return view;
    }

    private void calculate() {
        String salaryStr = editTextLuong.getText().toString();

        if (salaryStr.isEmpty()) {
            Toast.makeText(getActivity(), "Hãy nhập mức lương", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double salary = Double.parseDouble(salaryStr);
            double savings = salary * 0.3;
            dailySpending = (salary * 0.7) / 30; // Giả định 30 ngày trong một tháng

            textViewTietKiem.setText(String.format("Tiền tiết kiệm: %.2f", savings));
            textViewDailyChiTieu.setText(String.format("Số tiền chi mỗi ngày: %.2f", dailySpending));
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "Mức lương không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }

    private void evaluate() {
        if (dailySpending == 0) {
            Toast.makeText(getActivity(), "Hãy tính toán trước khi nhận đánh giá", Toast.LENGTH_SHORT).show();
            return;
        }

        String evaluation;
        if (dailySpending < 100000) {
            evaluation = "Mức lương của bạn thuộc mức lương part time, bạn nên chi tiêu thật kĩ lưỡng.";
        } else if (dailySpending >= 100000 && dailySpending < 200000) {
            evaluation = "Mức lương của bạn ổn định, có thể sống tạm ổn với mức lương này.";
        } else {
            evaluation = "Bạn đang có một công việc tốt, cuộc sống dư giả. Chúc bạn phát triển bản thân với công việc này!";
        }

        textViewDanhGia.setText(evaluation);
    }
}
