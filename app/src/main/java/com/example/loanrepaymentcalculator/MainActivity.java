package com.example.loanrepaymentcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Declaring UI elements
    private EditText amount,term,rate;
    private TextView monthlyPayment,totalPayment,totalInterest,monthlyInterest;
    private Button calculateBtn,clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //wiring
        amount=(EditText) findViewById(R.id.amountId);
        term=(EditText) findViewById(R.id.termId);
        rate=(EditText) findViewById(R.id.rateId);
        monthlyPayment=(TextView) findViewById(R.id.monthlyPaymentId);
        totalPayment=(TextView) findViewById(R.id.totalPaymentId);
        totalInterest=(TextView) findViewById(R.id.totalInterestId);
        calculateBtn=(Button) findViewById(R.id.btnId);
        monthlyInterest=(TextView) findViewById(R.id.monthlyInterestId);
        clearBtn=(Button) findViewById(R.id.clearbtn);

        //calculate button
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double principal=Double.parseDouble(amount.getText().toString());
                double termInYears=Double.parseDouble(term.getText().toString());
                double annualRate=Double.parseDouble(rate.getText().toString());

                double monthlyRate=annualRate/1200;
                double n=termInYears*12;
                double monthlyPay=principal*monthlyRate/(1-(Math.pow(1+monthlyRate,-n)));
                double totalPay=monthlyPay*n;
                double totalIntrt=totalPay-principal;
                double monthlyIntrt=totalIntrt/n;

                monthlyPayment.setText("Rs. " + String.format("%.2f", monthlyPay));
                totalPayment.setText("Rs. " + String.format("%.2f", totalPay));
                totalInterest.setText("Rs. " + String.format("%.2f", totalIntrt));
                monthlyInterest.setText("Rs. " + String.format("%.2f", monthlyIntrt));

            }
        });

        //clear button
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                term.setText("");
                rate.setText("");
                monthlyPayment.setText("");
                totalPayment.setText("");
                totalInterest.setText("");
                monthlyInterest.setText("");
            }
        });

    }
}