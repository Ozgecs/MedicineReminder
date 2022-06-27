package com.ozge.medicinereminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ozge.medicinereminder.db.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import rm.com.clocks.ClockImageView;

public class AlarmActivity extends AppCompatActivity {

    TextView textViewMedicineName, textViewTime;
    Vibrator mVibrator;
    Button buttonDismiss, buttonSnooze, buttonTake;

    /*Alarm çaldığı zaman karşımıza çıkan sayfadaki butonların işlevlerinin kodlanması
    ve tıklanan butonun istenen activiye yönlendirmesi.*/

    //take butonu tarafımdan sonradan eklenmiştir.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Intent intent = getIntent();

        ClockImageView clockImageView = findViewById(R.id.clock_alarm); //Saat ikonunun oluşturulması
        Calendar mCurrentTime = Calendar.getInstance(); //Şimdiki sistem saatine ayarlanması
        clockImageView.animateToTime(mCurrentTime.get(Calendar.HOUR_OF_DAY), mCurrentTime.get(Calendar.MINUTE)); //saatin gösterilmesi

        String medicineName = intent.getStringExtra("medicineName"); //İlaç adı burada gösteriliyor
        textViewMedicineName = findViewById(R.id.text_view_medicine_name_alarm);
        textViewMedicineName.setText(medicineName);

        textViewTime = findViewById(R.id.text_view_time_alarm);
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        textViewTime.setText(format.format(mCurrentTime.getTime()));


        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE); //titreşim efektinin verilmesi
        long[] pattern = {0, 1000, 1000};
        mVibrator.vibrate(pattern, 0);


        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), alarmUri);
        ringtone.play();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<String> timingList = databaseHelper.getTimings(medicineName);
        for (int i = 0; i < timingList.size(); i++) {
            Log.i(AlarmActivity.class.getName(), timingList.get(i));
        }

        Calendar nextAlarmTime = Calendar.getInstance();
        nextAlarmTime.set(Calendar.SECOND, 0);
        nextAlarmTime.set(Calendar.MILLISECOND, 0);


        buttonDismiss = findViewById(R.id.button_dismiss); //Alarmı reddeden buton
        //Alarmı tamamen kapatmaktadır ertesi gün aynı saatte tekrar çalmıyor.
        buttonDismiss.setOnClickListener(v -> {
            for (int i = 0; i < timingList.size(); i++) {
                String [] time = timingList.get(i).split(":");
                nextAlarmTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
                nextAlarmTime.set(Calendar.MINUTE, Integer.parseInt(time[1]));
                if (mCurrentTime.before(nextAlarmTime)) {
                    break;
                } else if (mCurrentTime.after(nextAlarmTime) && i == (timingList.size() - 1) ) {
                    nextAlarmTime.set(Calendar.DAY_OF_MONTH, nextAlarmTime.get(Calendar.DAY_OF_MONTH) + 1);
                    time = timingList.get(0).split(":");
                    nextAlarmTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
                    nextAlarmTime.set(Calendar.MINUTE, Integer.parseInt(time[1]));
                }
            }
            mVibrator.cancel();
            ringtone.stop();
            Log.i("daysLeft", String.valueOf(databaseHelper.noOfDaysLeft(medicineName, nextAlarmTime)));
            if (databaseHelper.noOfDaysLeft(medicineName, nextAlarmTime) > 0)
                setAlarm(nextAlarmTime, medicineName);
            finish();
        });

        buttonSnooze = findViewById(R.id.button_snooze); //Alarmı ertele
        buttonSnooze.setOnClickListener(v -> {
            nextAlarmTime.set(Calendar.MINUTE, nextAlarmTime.get(Calendar.MINUTE) + 5); //5 dakika ertele
            mVibrator.cancel(); //titreşimi kapat
            ringtone.stop(); //alarmı durdur
            setAlarm(nextAlarmTime, medicineName); //yeni alarm süresini ve adı tekrar alarm kurucuya set et.
            finish();
        });
        //Alındı butonu bu iki kısım arasına yazılmıştır.
        buttonTake = findViewById(R.id.button_take); //alarmı bir gün sonraya ertele
        buttonTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextAlarmTime.set(Calendar.MINUTE, nextAlarmTime.get(Calendar.MINUTE)+1440); //alarmı bir sonraki güne atar
                mVibrator.cancel();
                ringtone.stop();
                setAlarm(nextAlarmTime,medicineName);
                finish();
            }
        });
        //İlacın alındığını uygulamaya bildiren ve alarmı bir sonraki güne atar ve aynı saatte tekrar
        //hatırlatır.

    }



    public void setAlarm(Calendar mAlarmTime, String medicineName) {
        Intent intent = new Intent(this, AlarmActivity.class);
        intent.putExtra("medicineName", medicineName);

        PendingIntent operation = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        /** Getting a reference to the System Service ALARM_SERVICE */
        AlarmManager alarmManagerNew = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManagerNew.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, mAlarmTime.getTimeInMillis(), operation);
        } else
            alarmManagerNew.setExact(AlarmManager.RTC_WAKEUP, mAlarmTime.getTimeInMillis(), operation);

    }
}
