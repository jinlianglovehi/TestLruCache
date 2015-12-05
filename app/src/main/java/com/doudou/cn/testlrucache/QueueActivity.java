package com.doudou.cn.testlrucache;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by jinliang on 15/12/5.
 */
public class QueueActivity extends Activity{
    private static  final  String TAG =QueueActivity.class.getSimpleName();
    private ExecutorService executorService ;
    private final static int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private final static int POOL_SIZE= CPU_COUNT+1;
    private final static int MAX_POOL_SIZE= CPU_COUNT*2+1;
    private final static int KEEP_ALIVE_TIME=4;
    private  Executor mExecutor;
    private Button addThread,startQueue;
    private  volatile int  sum = 0;
    private LinkedBlockingQueue<Runnable>  queue =new LinkedBlockingQueue<Runnable>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_queue);
        addThread = (Button) findViewById(R.id.addThread);
        addThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick ");
                Runnable runnable1 = new Runnable() {
                    @Override
                    public void run() {

                        Log.i("QueueActivity", "run ：" + 1);
                    }
                };
                Runnable runnable2 = new Runnable() {
                    @Override
                    public void run() {

                        Log.i("QueueActivity", "run ：" + 2);
                    }
                };
                Runnable runnable3 = new Runnable() {
                    @Override
                    public void run() {

                        Log.i("QueueActivity", "run ：" + 3);
                    }
                };
                Runnable runnable4 = new Runnable() {
                    @Override
                    public void run() {

                        Log.i("QueueActivity", "run ：" + 4);
                    }
                };

                Runnable runnable5 = new Runnable() {
                    @Override
                    public void run() {

                        Log.i("QueueActivity", "run ：" + 5);
                    }
                };

                Runnable runnable6 = new Runnable() {
                    @Override
                    public void run() {

                        Log.i("QueueActivity", "run ：" + 6);
                    }
                };
                queue.add(runnable1);
                queue.add(runnable2);
                queue.add(runnable3);
                queue.add(runnable4);
                queue.add(runnable5);
                queue.add(runnable6);
            }
        });
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
        executorService = new ThreadPoolExecutor(1, 10,
            0L, TimeUnit.MILLISECONDS,queue
            );

        startQueue = (Button) findViewById(R.id.startQueue);
        startQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {

                        while (true){
                            Runnable  run =queue.peek();
                            if(run!=null){
                                executorService.execute(run);
                            }

                        }
                    }
                }.start();
            }
        });
    }
}
