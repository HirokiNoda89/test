package to.msn.wings.timer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import static to.msn.wings.timer.R.id.timer;

public class MainActivity extends AppCompatActivity {
    private TextView timerText;
    MediaPlayer player1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerText = (TextView) findViewById(timer);
        timerText.setText("0:00.000");
        player1 = MediaPlayer.create(this, R.raw.sample);
    }

    CountDown countdown1 = new CountDown(180000, 100);
    CountDown countdown2 = new CountDown(240000, 100);
    CountDown countdown3 = new CountDown(300000, 100);
    //コメント
    /*ABC
    */


    public void onClick1(View v) {
        countdown1.start();
    }

    public void onClick2(View v) {
        countdown2.start();
    }

    public void onClick3(View v) {
        countdown3.start();
    }

    public void onClick4(View v){
        countdown1.cancel();
        countdown2.cancel();
        countdown3.cancel();
        timerText.setText("0:00.000");
    }
    public void onClick5(View v){
        player1.stop();
        Toast.makeText(this, "再生STOP", Toast.LENGTH_SHORT).show();
        try {
            player1.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class CountDown extends CountDownTimer {

        public CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }


        public void onFinish() {
            // 完了
            player1.start();
            player1.setLooping(true);
            timerText.setText("0:00.000");
            Toast.makeText(MainActivity.this, "タイマー終了", Toast.LENGTH_SHORT).show();
        }

        public void onTick(long millisUntilFinished) {
            // 残り時間を分、秒、ミリ秒に分割
            long mm = millisUntilFinished / 1000 / 60;
            long ss = millisUntilFinished / 1000 % 60;
            long ms = millisUntilFinished - ss * 1000 - mm * 1000 * 60;

            timerText.setText(String.format("%1$02d:%2$02d.%3$03d", mm, ss, ms));
        }
    }

}