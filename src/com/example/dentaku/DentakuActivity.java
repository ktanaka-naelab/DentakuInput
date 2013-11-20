package com.example.dentaku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DentakuActivity extends Activity {

    /*
     * ログのタグ
     * 設定しておくと、ログから該当箇所を探しやすくなります。
     */
    private static final String TAG = "DentakuActivity";

    /**
     * Buttonの配列
     */
    Button mButton[];

    /**
     * Idの配列
     */
    int mId[] = { R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8, R.id.button9, R.id.buttonPlus, R.id.buttonMinus, R.id.buttonEqual,
            R.id.buttonTen, R.id.buttonClear };

    /**
     * キー
     */
    private final int KEY_0 = 0;
    private final int KEY_1 = 1;
    private final int KEY_2 = 2;
    private final int KEY_3 = 3;
    private final int KEY_4 = 4;
    private final int KEY_5 = 5;
    private final int KEY_6 = 6;
    private final int KEY_7 = 7;
    private final int KEY_8 = 8;
    private final int KEY_9 = 9;
    private final int KEY_PLUS = 10;
    private final int KEY_MINUS = 11;
    private final int KEY_EQUAL = 12;
    private final int KEY_TEN = 13;
    private final int KEY_CLEAR = 14;

    /**
     * TextView
     */
    TextView mTextView;

    /**
     * 前の処理
     */
    int beforeStatus = 0;

    /**
     * 合計
     */
    int total = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // 表示用TextView
        mTextView = (TextView) findViewById(R.id.display);

        // Button
        mButton = new Button[mId.length];

        // Buttonの取り込みとイベントのはりつけ
        for (int i = 0; i < mId.length; i++) {
            // buttonを取り込む
            mButton[i] = (Button) findViewById(mId[i]);
            // buttonのイベント処理
            mButton[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 押されたボタンがどのボタンかを判定
                    for (int i = 0; i < mId.length; i++) {
                        if (view.equals(mButton[i])) {
                            Log.d(TAG, "Click:" + i);
                            Toast.makeText(DentakuActivity.this, "Click " + i, Toast.LENGTH_SHORT).show();
                            // CLEAR
                            if (i == KEY_CLEAR) {
                                mTextView.setText("");
                                total = 0;
                                beforeStatus = KEY_CLEAR;
                            }
                            // イコール
                            else if (i == KEY_EQUAL) {
                                String text = mTextView.getText().toString();
                                if (!"".equals(text)) {
                                    share(mTextView.getText().toString());
                                }
                            }
                            // 数字
                            else if (i < 10) {
                                String nowValue = mTextView.getText().toString();
                                nowValue = nowValue + i;
                                mTextView.setText(nowValue);
                                beforeStatus = i;
                            }
                            break;
                        }
                    }

                }
            });
        }
    }

    private void share(String text) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);
    }

}