package com.example.homework1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.view.View;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar sb;
    TextView price,status,tip;
    EditText budget;
    int val;
    RadioGroup memory,storage;
    CheckBox mouse,flashDrive,coolingPad,carryingCase;
    Switch delivery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.comp1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        budget =(EditText) findViewById(R.id.editText);
        price =(TextView)findViewById(R.id.textView8);
        status = (TextView)findViewById(R.id.textView10);
        tip= (TextView) findViewById(R.id.textView1);
        sb = (SeekBar) findViewById(R.id.seekBar);
        memory = (RadioGroup)findViewById(R.id.radioGroup);
        storage = (RadioGroup)findViewById(R.id.radioGroup2);
        mouse = (CheckBox)findViewById(R.id.checkBox);
        flashDrive = (CheckBox)findViewById(R.id.checkBox2);
        coolingPad = (CheckBox)findViewById(R.id.checkBox3);
        carryingCase = (CheckBox)findViewById(R.id.checkBox4);
        delivery = (Switch)findViewById(R.id.switch1);
        val = sb.getProgress();
        tip.setText(val * 5 +"%");



        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                tip.setText(sb.getProgress() * 5 +"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }




    public int findMemory()       //Method to find selected memory size
    {
      if(memory.getCheckedRadioButtonId() == R.id.radioButton1)
      {
          return(2);
      }
       else if(memory.getCheckedRadioButtonId() == R.id.radioButton2)
      {
          return(4);
      }
        else if(memory.getCheckedRadioButtonId() == R.id.radioButton3)
      {
          return(8);
      }
        else
      {
          return(16);
      }
    }


    public int findStorage()    //Method to find selected storage option
    {
        if(storage.getCheckedRadioButtonId() == R.id.radioButton5)
        {
            return(250);
        }
        else if(storage.getCheckedRadioButtonId() == R.id.radioButton6)
        {
            return(500);
        }
        else if(storage.getCheckedRadioButtonId() == R.id.radioButton7)
        {
            return(750);
        }
        else
        {
            return(1000);
        }

    }

    public int countAccessories()    //Method to count accessories selected
    {
        int count =0;
        if(mouse.isChecked())
        {
            count++;
        }
        if(flashDrive.isChecked())
        {
            count++;
        }
        if(coolingPad.isChecked())
        {
            count++;
        }
        if(carryingCase.isChecked())
        {
            count++;
        }
        return count;
    }

    public int findDelivery()     //Method to check delivery option
    {
        if(delivery.isChecked())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public void setStatus(float amnt, float cost)    //Method to display status
    {
        if(amnt > cost)
        {
            status.setBackgroundColor(Color.GREEN);
            status.setText("Within budget");
        }
        else
        {
            status.setBackgroundColor(Color.RED);
            status.setText("Over budget");
        }
    }

    public void calculate(View v)
    {
        int memoryRadio,storageRadio,accessories,del;
        float amnt,p1,p2,p3,cost;
      if(budget.getText().toString().length() >0)
      {
          amnt = Float.parseFloat(budget.getText().toString().trim());      //Entered budget amount
          memoryRadio = findMemory();
          storageRadio = findStorage();
          accessories = countAccessories();
          val = sb.getProgress() * 5;
          del = findDelivery();
          Log.d("demo", "val = " + val);
          p1 = (float) ((10.0 * (float) memoryRadio) + (0.75 * (float) storageRadio) + (20 * (float) accessories));
          p2 = (float) ((float) val / 100) + 1;
          p3 = (float) (5.95 * del);
          cost = (p1 * p2) + p3;
          setStatus(amnt, cost);
          price.setText("$" + cost);
      }
        else
      {
          Toast.makeText(this,"Please enter the budget amount", Toast.LENGTH_SHORT).show();
      }

    }

    public void reset(View v)       //Method to clear the contents
    {
        budget.setText("");
        price.setText("$0.00");
        status.setText("");
        memory.check(R.id.radioButton1);
        storage.check(R.id.radioButton5);
        delivery.setChecked(true);
        mouse.setChecked(false);
        flashDrive.setChecked(false);
        coolingPad.setChecked(false);
        carryingCase.setChecked(false);
        sb.setProgress(3);
        status.setBackgroundColor(Color.TRANSPARENT);

    }

}
