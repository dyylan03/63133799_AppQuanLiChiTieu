package thi.duy_63133799.baithi;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;
import java.util.Date;

public class ThemFragment extends Fragment {

    private EditText editTextLiDoChiTieu, editTextSoTienChiTieu;
    private Button buttonThemChiTieu;
    private ShareViewModel shareViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them, container, false);

        editTextLiDoChiTieu = view.findViewById(R.id.editTextLiDoChiTieu);
        editTextSoTienChiTieu = view.findViewById(R.id.editTextSoTienChiTieu);
        buttonThemChiTieu = view.findViewById(R.id.buttonThemChiTieu);

        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        buttonThemChiTieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liDoChiTieu = editTextLiDoChiTieu.getText().toString().trim();
                String soTienChiTieu = editTextSoTienChiTieu.getText().toString().trim();
                if (!liDoChiTieu.isEmpty() && !soTienChiTieu.isEmpty()) {
                    // Lấy thời gian hiện tại
                    String thoiGian = DateFormat.format("dd-MM-yyyy HH:mm:ss", new Date()).toString();
                    String chiTieu = thoiGian + " - " + liDoChiTieu + ": " + soTienChiTieu;
                    shareViewModel.addChiTieu(chiTieu);
                }
            }
        });

        return view;
    }
}
