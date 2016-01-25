package dnldd.backpack.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dnldd.backpack.R;
import dnldd.backpack.view.Button;
import dnldd.backpack.view.TextView;

public class ChoiceDialog extends BaseDialog {
    private String prompt;

    public ChoiceDialog setMessage(String message){
        prompt = message;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        this.layoutResID = R.layout.bp_choice_dialog_layout;
        return super.onCreateView(inflater, container, bundle);
    }

    @Override
    public void bind(View view) {
        TextView message = (TextView) view.findViewById(R.id.message);
        Button cancel = (Button) view.findViewById(R.id.cancel);
        //Button ok = (Button) view.findViewById(R.id.ok);

        final ChoiceDialog fragment = this;

        message.setText(prompt);
        fragment.getDialog().setCanceledOnTouchOutside(false);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.dismiss();
            }
        });

        /* override, call super and add the action for  the ok button */
    }
}
