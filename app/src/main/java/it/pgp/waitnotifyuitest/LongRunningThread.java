package it.pgp.waitnotifyuitest;

import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

class LongRunningThread extends Thread {

    final AtomicInteger choice = new AtomicInteger(-1);
    private final MainActivity mainActivity;

    LongRunningThread(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void run() {
        try {sleep(1000);} catch (InterruptedException ignored) {}
        mainActivity.runOnUiThread(()-> new DecisionDialog(mainActivity,LongRunningThread.this).show());
        try {
            synchronized (choice) {
                choice.wait();
            }
        }
        catch (InterruptedException e) {
            Log.e(this.getClass().getName(),"Interrupted");
            return;
        }
        Log.e(this.getClass().getName(),"Choice: "+choice.get());
    }
}
