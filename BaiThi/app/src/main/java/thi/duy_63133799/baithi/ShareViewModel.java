package thi.duy_63133799.baithi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShareViewModel extends ViewModel {
    private final MutableLiveData<List<String>> chiTieuListLiveData = new MutableLiveData<>();

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
            chiTieuListLiveData.setValue(chiTieuList); // Đảm bảo rằng MutableLiveData đã được khởi tạo
        }
        chiTieuList.add(chiTieu);
        chiTieuListLiveData.setValue(chiTieuList);
    }



    public void clearData() {
        chiTieuListLiveData.setValue(new ArrayList<>());
    }

}
