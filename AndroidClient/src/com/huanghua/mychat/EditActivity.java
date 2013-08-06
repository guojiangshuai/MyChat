
package com.huanghua.mychat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends Activity implements OnClickListener {

    private Button mBack;
    private Button mOk;
    private EditText mEdit;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_view);
        init();
    }

    private void init() {
        mBack = (Button) findViewById(R.id.back);
        mOk = (Button) findViewById(R.id.ok);
        mBack.setOnClickListener(this);
        mOk.setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.title);
        mEdit = (EditText) findViewById(R.id.edit_context);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        mTitle.setText(intent.getStringExtra("title"));
        mBack.setText(intent.getStringExtra("back"));
        mEdit.setText(intent.getStringExtra("old_context"));
    }

    @Override
    public void onClick(View v) {
        if (v == mBack) {
            finish();
        } else if (v == mOk) {
            
        }
    }

}
