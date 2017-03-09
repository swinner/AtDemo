package com.example.atdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    private EditText etInput;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInput= (EditText)findViewById(R.id.et_input);
        btnSubmit= (Button)findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
        etInput.setOnKeyListener(this);
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String msgContent = charSequence.toString();
                if (!TextUtils.isEmpty(msgContent)) {
                    if(msgContent.endsWith("@")){
                        Intent contactIntent = new Intent(MainActivity.this,ContactActivity.class);
                        startActivityForResult(contactIntent,99);
                    }
                    checkAtValid(msgContent);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }
    private HashMap<String,String> atMap = new HashMap<>();
    private void checkAtValid(String msgContent) {
        Set mapset = atMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = mapset.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> entry= iterator.next();
            String key= entry.getKey();
            if(!msgContent.contains(key)){
                iterator.remove();
            }
        }
    }

    @Override
    public void onClick(View view) {
        String textInputTemp = etInput.getText().toString();
        if(TextUtils.isEmpty(textInputTemp) || atMap.size() == 0){
            Toast.makeText(this,"没有",Toast.LENGTH_LONG).show();
        }else{
            List<String> mList = new ArrayList<>(atMap.values());
            Toast.makeText(this,"@的人有==》"+ mList.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 99 && resultCode == RESULT_OK){
            String id = data.getStringExtra("id");
            String name = data.getStringExtra("name");
            String textInputTemp = etInput.getText().toString();
            etInput.setText(textInputTemp+name+"\t");
            etInput.setSelection(etInput.getText().length());

            atMap.put("@"+name,id);
        }
    }
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            String str = etInput.getText().toString();
            Editable edit = etInput.getEditableText();

            int selectPos = etInput.getSelectionStart();
            selectPos = selectPos == 0? -1 : selectPos;
            int lastAtpos = str.lastIndexOf("@");
            lastAtpos = lastAtpos == -1 ? 0 : lastAtpos;
            int lastTabpos = str.lastIndexOf("\t");
            boolean isDeleteAt =(lastTabpos+1) == selectPos;
            if(isDeleteAt){
                edit.delete(lastAtpos,lastTabpos+1);
                return true;
            }
        }
        return false;
    }
}
