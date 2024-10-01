package com.example.alonzo_ordering_app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText[] orderQuantities;
    TextView totalPrice, orderReport, quantityTotal, orderPrice;
    final int[] prices = {179, 99, 72, 44, 156, 55, 78};
    String[] menuItems = {"Big Mac x ","McNuggets x ","Chicken Fillet x ", "McMuffin x ",
            "Chicken with Spaghetti x ","Hot Fudge Sundae x ", "Large Fries x "};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        quantityTotal = (TextView) findViewById(R.id.txtQuantityTotal);
        totalPrice = (TextView) findViewById(R.id.txtTotal);
        orderReport = (TextView) findViewById(R.id.txtReport);
        orderPrice = (TextView) findViewById(R.id.txtOrderPrice);
        orderQuantities = new EditText[]{
                findViewById(R.id.txtBigMac),
                findViewById(R.id.txtMcNuggets),
                findViewById(R.id.txtChickenFillet),
                findViewById(R.id.txtMcMuffin),
                findViewById(R.id.txtChickenSpag),
                findViewById(R.id.txtHotFudge),
                findViewById(R.id.txtLargeFries)};
    }

    public void btnOrder(View view) {
        totalStatement();
    }

     void totalStatement(){
         StringBuilder orders = new StringBuilder();
         StringBuilder qtyPrice = new StringBuilder();
         int totalSum = 0, totalQuantity = 0;
         boolean hasItems = false;

         for(int i = 0; i < orderQuantities.length; i++){
             String quantityText = orderQuantities[i].getText().toString().trim();
             int quantity = quantityText.isEmpty() ? 0 : Integer.parseInt(quantityText);

             if(quantity > 0){
                 orders.append(menuItems[i]).append(quantity).append("\n");
                 qtyPrice.append(quantity * prices[i]).append("\n");
                 totalSum += quantity * prices[i];
                 totalQuantity += quantity;
                 hasItems = true;
             }
         }

         if(hasItems){
             quantityTotal.setText(String.format(Locale.US,"Total Quantity: %d", totalQuantity));
             orderReport.setText(orders);
             orderPrice.setText(qtyPrice);
             totalPrice.setText(String.format("Price Total: PHP " + totalSum));
         }
         else{
             orderReport.setText("");
             orderPrice.setText("");
             quantityTotal.setText(String.format("%s","Total Quantity: --"));
             totalPrice.setText(String.format("%s", "Price Total: --"));
         }
    }

    public void plusClick(View view) {
        int index = Integer.parseInt(view.getTag().toString());
        EditText currentQuantityEditText = orderQuantities[index];
        String currentText = currentQuantityEditText.getText().toString().trim();

        int currentQuantity = currentText.isEmpty() ? 0 : Integer.parseInt(currentText);
        currentQuantity++;
        currentQuantityEditText.setText(String.valueOf(currentQuantity));
    }


    public void minusClick(View view) {
        int index = Integer.parseInt(view.getTag().toString());
        EditText currentQuantityEditText = orderQuantities[index];
        String currentText = currentQuantityEditText.getText().toString().trim();

        int currentQuantity = currentText.isEmpty() ? 0 : Integer.parseInt(currentText);
        if (currentQuantity > 0) {
            currentQuantity--;
        }
        currentQuantityEditText.setText(String.valueOf(currentQuantity));
    }
}