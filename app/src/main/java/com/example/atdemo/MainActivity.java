package com.example.atdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
    private String  placeHolder = "\u001F";//空格占位符
    private String  atHolder = "@";//@字符占位符

//    private String testStr = "中"+placeHolder+"国共"+placeHolder+"产党万"+placeHolder+
//            "岁中国共"+placeHolder+"产党万岁a"+placeHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInput= (EditText)findViewById(R.id.et_input);
        btnSubmit= (Button)findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
        etInput.setOnKeyListener(this);
//        etInput.setText(testStr);
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String msgContent = charSequence.toString();
                if (!TextUtils.isEmpty(msgContent)) {

                    int selectPos = etInput.getSelectionStart();
                    if(selectPos > 0){
                        String selectionStr =  String.valueOf(msgContent.charAt(selectPos-1));
                        if(atHolder.equals(selectionStr) && !isNotShowAt){
                            if(selectPos == 1){
                                showAt();
                            }else{
                                String befourSelectionStr =  String.valueOf(msgContent.charAt(selectPos-2));
                                boolean isChinese = CharUtil.isChinese(befourSelectionStr);
                                if(atHolder.equals(befourSelectionStr) || placeHolder.equals(befourSelectionStr) || isChinese){
                                    showAt();
                                }
                            }
                        }
                    }
                    checkAtValid(msgContent);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }
    private void showAt(){
        Intent contactIntent = new Intent(MainActivity.this,ContactActivity.class);
        startActivityForResult(contactIntent,99);
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

            int index = etInput.getSelectionStart();//获取光标所在位置
            Editable edit = etInput.getEditableText();
            if (index < 0 || index >= edit.length() ){
                edit.append(name+placeHolder);
            }else{
                edit.insert(index,name+placeHolder);//光标所在位置插入文字
            }
            atMap.put(atHolder+name,id);
        }
    }
    private boolean isNotShowAt = false;
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            String str = etInput.getText().toString();
            Editable edit = etInput.getEditableText();
            int selectPos = etInput.getSelectionStart();
            if(selectPos < 2){
                isNotShowAt = false;
            }else{
                String deleteSelectionStr =  String.valueOf(str.charAt(selectPos-2));
                isNotShowAt = atHolder.equals(deleteSelectionStr);
            }
            selectPos = selectPos == 0? -1 : selectPos;
            int lastAtpos = str.lastIndexOf(atHolder);
            lastAtpos = lastAtpos == -1 ? 0 : lastAtpos;
            int lastTabpos = str.lastIndexOf(placeHolder);
            boolean isDeleteAt =(lastTabpos+1) == selectPos;
            if(isDeleteAt){
                edit.delete(lastAtpos,lastTabpos+1);
                return true;
            }
        }
        return false;
    }
}
