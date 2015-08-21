package dnldd.backpack.contract;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface InflateDialogInterface {
    View inflateDialog(LayoutInflater inflater, ViewGroup container);
    void bind(View view);
}
