package thi.duy_63133799.baithi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ShareViewModel extends ViewModel {
    private final MutableLiveData<String> luong = new MutableLiveData<>();
    private final MutableLiveData<List<String>> chiTieuListLiveData = new MutableLiveData<>();

    public void setLuong(String luong) {
        this.luong.setValue(luong);
    }

    public LiveData<String> getLuong() {
        return luong;
    }

    public void setChiTieuList(List<String> chiTieuList) {
        chiTieuListLiveData.setValue(chiTieuList);
    }

    public LiveData<List<String>> getChiTieuList() {
        return chiTieuListLiveData;
    }

    public void addChiTieu(String chiTieu) {
        List<String> chiTieuList = chiTieuListLiveData.getValue();
        if (chiTieuList == null) {
            chiTieuList = new ArrayList<>();
        }
        chiTieuList.add(chiTieu);
        chiTieuListLiveData.setValue(chiTieuList);
    }

    public void clearData() {
        luong.setValue("");
        chiTieuListLiveData.setValue(new ArrayList<>());
    }

    public void filterChiTieuListByDate(int dayOfMonth, int month, int year) {
        List<String> chiTieuList = chiTieuListLiveData.getValue();
        if (chiTieuList != null) {
            List<String> filteredList = new ArrayList<>();
            for (String chiTieu : chiTieuList) {
                // Phân tích chi tiêu để lấy ngày tháng năm
                String[] parts = chiTieu.split(" ");
                String[] ngayThangNam = parts[0].split("-");
                int chiTieuDay = Integer.parseInt(ngayThangNam[0]);
                int chiTieuMonth = Integer.parseInt(ngayThangNam[1]);
                int chiTieuYear = Integer.parseInt(ngayThangNam[2]);
                // Kiểm tra nếu ngày của chi tiêu trùng với ngày được chọn thì thêm vào danh sách lọc
                if (dayOfMonth == chiTieuDay && month == chiTieuMonth && year == chiTieuYear) {
                    filteredList.add(chiTieu);
                }
            }
            chiTieuListLiveData.setValue(filteredList);
        }
    }
}
