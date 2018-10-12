package com.akki.urlwebview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mUrlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUrlText = (EditText) findViewById(R.id.ed_url);

        mUrlText.setText("https://www.");
        Selection.setSelection(mUrlText.getText(), mUrlText.getText().length());


        mUrlText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().startsWith("https://www.")){
                    mUrlText.setText("https://www.");
                    Selection.setSelection(mUrlText.getText(), mUrlText.getText().length());

                }

            }
        });



        Button openUrlBtn = (Button) findViewById(R.id.btn_open_url);
        openUrlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboardIfOpen();
                if(!mUrlText.getText().toString().isEmpty()) {

                    if(isValidUrl(mUrlText.getText().toString())) {
                        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                        intent.putExtra("webUrl", mUrlText.getText().toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Url is not valid!",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter the Url to open!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /*private String getFormatedUrl(String inputUrl) {
        if(inputUrl.startsWith("http://") || inputUrl.startsWith("https://")) {
            if(inputUrl.startsWith("http://")  || inputUrl.startsWith("https://"))
        }
    }*/

    private boolean isValidUrl(String inputUrl) {
        return  URLUtil.isValidUrl(inputUrl);
    }

    private void hideKeyboardIfOpen() {
        /*LinearLayout mainLayout = (LinearLayout)findViewById(R.id.main_layout);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);*/

        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
