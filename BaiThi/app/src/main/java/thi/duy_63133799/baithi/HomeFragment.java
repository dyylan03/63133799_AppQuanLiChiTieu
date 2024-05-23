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

    private EditText editTextSalary;
    private TextView textViewSavings, textViewDailySpending;
    private Button buttonCalculate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        editTextSalary = view.findViewById(R.id.editTextSalary);
        textViewSavings = view.findViewById(R.id.textViewSavings);
        textViewDailySpending = view.findViewById(R.id.textViewDailySpending);
        buttonCalculate = view.findViewById(R.id.buttonCalculate);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        return view;
    }

    private void calculate() {
        String salaryStr = editTextSalary.getText().toString();

        if (salaryStr.isEmpty()) {
            Toast.makeText(getActivity(), "Hãy nhập mức lương", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double salary = Double.parseDouble(salaryStr);
            double savings = salary * 0.3;
            double dailySpending = (salary * 0.7) / 30; // Giả định 30 ngày trong một tháng

            textViewSavings.setText(String.format("Tiền tiết kiệm: %.2f", savings));
            textViewDailySpending.setText(String.format("Số tiền chi mỗi ngày: %.2f", dailySpending));
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "Mức lương không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
}
