package bibi.teste;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView txtx;
    private TextView txty;
    private TextView txtz;
    private ImageView bola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                txtx = findViewById(R.id.txtx);
                txtx.setText("valor de x: "+x);
                txty = findViewById(R.id.txty);
                txty.setText("valor de y: "+y);
                txtz = findViewById(R.id.txtz);
                txtz.setText("valor de z: "+z);

                bola = findViewById(R.id.bola);
                if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){

                    x += (int)event.values[0]*100;
                    y -= (int)event.values[1]*20;

                    bola.setTranslationX(x);
                    bola.setTranslationY(y);
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }

        }, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }
}