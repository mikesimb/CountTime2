package cllc.me.counttime;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private EditText setTime_edt;
    private Button  setTime_btn,start_btn,stop_btn;
    private TextView tv;

    int Count;

    Timer timer;
    TimerTask timeTask;


    private View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.settime_btn:
                    tv.setText(setTime_edt.getText().toString());
                    Count = Integer.parseInt(tv.getText().toString());
                    break;
                case R.id.startBtn:
                    StartTime();
                    break;
                case R.id.stopBtn:
                    stopTime();
                    break;
            }

        }
    };
    private Handler mHandle =new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            tv.setText(msg.arg1+" ");
            StartTime();
        }
    };

    private void StartTime()
    {
        timer = new Timer();
        timeTask =new TimerTask() {
            @Override
            public void run() {
                Count--;
                if(Count <0) return;
                Message msg = mHandle.obtainMessage();
                msg.arg1 = Count;
                mHandle.sendMessage(msg);
            }
        };
        timer.schedule(timeTask,1000);


    }

    private void stopTime()
    {
        timer.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();
    }



    private void InitView()
    {
        setTime_edt = (EditText)findViewById(R.id.settime_edt);
        setTime_btn = (Button)findViewById(R.id.settime_btn);
        setTime_btn.setOnClickListener(clickListener);

        start_btn =(Button)findViewById(R.id.startBtn);
        start_btn.setOnClickListener(clickListener);
        stop_btn =(Button)findViewById(R.id.stopBtn);
        stop_btn.setOnClickListener(clickListener);
        tv = (TextView)findViewById(R.id.tv);
    }
}
