package com.doudou.cn.testlrucache;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.doudou.cn.testlrucache.testUpload.ComparableRunnable;
import com.doudou.cn.testlrucache.testUpload.FileQueue;

/**
 * Created by jinliang on 15/12/5.
 */
public class QueueActivity extends Activity {
    private static  final String TAG =QueueActivity.class.getSimpleName();
    private FileQueue fileQueue;
    private Button addThread;
    private EditText etPriority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
        fileQueue = new FileQueue();
        addThread = (Button) findViewById(R.id.addThread);
        etPriority = (EditText) findViewById(R.id.priority);
        addThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int priority = Integer.parseInt(etPriority.getText().toString());
                ComparableRunnable task = new ComparableRunnable(priority) {
                    @Override
                    public void run() {
                        try {
                            Log.d(TAG, this.getPriority() + "");
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                fileQueue.add(task);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileQueue.stop();
    }
}
