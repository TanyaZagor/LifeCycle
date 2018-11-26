package ru.geekbrains.lifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LifeCycleActivity extends AppCompatActivity {

    private int counter;                    // Счетчик

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        String instanceState;
        if (savedInstanceState == null)
            instanceState = "Первый запуск!";
        else
            instanceState = "Повторный запуск!";

        infoMsg(instanceState + " - onCreate()");

        // Получить Presenter
        final LifeCyclePresenter presenter = LifeCyclePresenter.getInstance();

        final TextView textCounter = findViewById(R.id.textCounter);     // Текст
        // Выводим счетчик в поле
        counter = presenter.getCounter();
        textCounter.setText(String.valueOf(counter));

        Button button = findViewById(R.id.button);         // Кнопка
        button.setOnClickListener(new View.OnClickListener() {      // Обработка нажатий
            @Override
            public void onClick(View v) {
                presenter.incrementCounter();   // Увеличиваем счетчик на единицу
                // Выводим счетчик в поле
                counter = presenter.getCounter();
                textCounter.setText(String.valueOf(counter));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);

        saveInstanceState.putInt("Counter", counter);               // Сохраняем счетчик
        infoMsg("onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);

        counter = saveInstanceState.getInt("Counter");// Восстанавливаем счетчик
        infoMsg("Повторный запуск!! - onRestoreInstanceState()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        infoMsg("onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        infoMsg("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        infoMsg("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        infoMsg("onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        infoMsg("onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        infoMsg("onDestroy()");
    }

    private void infoMsg(String method) {
        Toast.makeText(getApplicationContext(), method, Toast.LENGTH_SHORT).show();
        Log.i("Logs", method);
    }
}
