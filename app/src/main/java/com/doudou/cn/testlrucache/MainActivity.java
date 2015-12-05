package com.doudou.cn.testlrucache;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * 测试的lru 算法
 */
public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button addDataBtn,removeData;
    //lru 缓存的数据
    private LruCache<String, String> mMemoryCache;
    private int sum=0;
    int current =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addDataBtn = (Button) findViewById(R.id.addData);
        removeData = (Button) findViewById(R.id.removeData);
        if (mMemoryCache == null)
            mMemoryCache = new LruCache<String, String>(
                    5) {
                @Override
                protected int sizeOf(String key, String value) {
//                    Log.i(TAG, "sizeOf key:" + key + "--value:" + value);
                    return super.sizeOf(key, value);
                }

                @Override
                protected void entryRemoved(boolean evicted, String key, String oldValue, String newValue) {
                    super.entryRemoved(evicted, key, oldValue, newValue);
                    Log.i(TAG, "entryRemoved ：evicted:" + evicted + "--key:" + key + "--oldValue:" + oldValue
                                    + "---newValue:" + newValue
                    );
                }
            };

        addDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = sum++;
                Log.i(TAG, "onClick 添加：" + current);
                mMemoryCache.put(current + "", current + "");

                Log.i(TAG, "onClick :" + mMemoryCache.toString());
            }
        });
        removeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mMemoryCache.remove();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
