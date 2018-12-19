package it.pgp.waitnotifyuitest;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

class DecisionDialog extends Dialog {
    DecisionDialog(Context context, final LongRunningThread refThread) {
        super(context);
        setContentView(R.layout.decision_dialog);
        final RadioGroup rg = findViewById(R.id.radioGroup);
        ((RadioButton)rg.getChildAt(2)).setChecked(true);
        final Button b = findViewById(R.id.okButton);
        b.setOnClickListener(view -> {
            synchronized (refThread.choice) {
                refThread.choice.set(
                        rg.indexOfChild(rg.findViewById(rg.getCheckedRadioButtonId())));
                refThread.choice.notify();
            }
            dismiss();
        });
        setOnCancelListener(dialogInterface -> {
            synchronized (refThread.choice) {
                refThread.choice.notify();
            }
        });
    }
}
