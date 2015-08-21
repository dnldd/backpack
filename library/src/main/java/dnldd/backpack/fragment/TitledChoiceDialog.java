package dnldd.backpack.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dnldd.backpack.R;
import dnldd.backpack.view.Button;
import dnldd.backpack.view.TextView;

public class TitledChoiceDialog extends BaseDialog {
    private String prompt;
    private String heading;

    public TitledChoiceDialog setMessage(String message){
        prompt = message;
        return this;
    }

    public TitledChoiceDialog setTitle(String title){
        heading = title;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        this.layoutResID = R.layout.bp_titled_choice_dialog_layout;
        return super.onCreateView(inflater, container, bundle);
    }

    @Override
    public void bind(View view) {
        TextView message = (TextView) view.findViewById(R.id.message);
        TextView title = (TextView) view.findViewById(R.id.title);
        Button cancel = (Button) view.findViewById(R.id.cancel);
        Button ok = (Button) view.findViewById(R.id.ok);

        final TitledChoiceDialog fragment = this;

        message.setText(prompt);
        title.setText(heading);
        fragment.getDialog().setCanceledOnTouchOutside(false);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.dismiss();
            }
        });

        /* override, call super and add the action for ok */
    }
}
