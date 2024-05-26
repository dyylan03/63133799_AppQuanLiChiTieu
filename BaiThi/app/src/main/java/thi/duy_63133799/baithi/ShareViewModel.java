package thi.duy_63133799.baithi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
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
}
