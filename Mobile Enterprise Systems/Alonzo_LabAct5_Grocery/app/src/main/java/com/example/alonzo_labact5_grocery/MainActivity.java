package com.example.alonzo_labact5_grocery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText itemName, itemPrice, itemQuantity;
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
        itemName = (EditText) findViewById(R.id.editItemName);
        itemPrice = (EditText) findViewById(R.id.editItemPrice);
        itemQuantity = (EditText) findViewById(R.id.editQuantity);

        itemName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validateInputs();
            }
        });

        itemPrice.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validateInputs();
            }
        });

        itemQuantity.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validateInputs();
            }
        });
    }

    private boolean validateInputs() {
        boolean valid = true;
        if (itemName.getText().toString().isEmpty()) {
            itemName.setError("Item name cannot be empty");
            valid = false;
        }

        String priceText = itemPrice.getText().toString();
        if (priceText.isEmpty()) {
            itemPrice.setError("Price cannot be empty");
            valid = false;
        } else {
            double price = Double.parseDouble(priceText);
            if (price <= 0) {
                itemPrice.setError("Price must be greater than zero");
                valid = false;
            }
        }

        String quantityText = itemQuantity.getText().toString();
        if (quantityText.isEmpty()) {
            itemQuantity.setError("Quantity cannot be empty");
            valid = false;
        } else {
            int quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                itemQuantity.setError("Quantity must be greater than zero");
                valid = false;
            }
        }

        return valid;
    }

    public void clickAdd(View view) {
        if (!validateInputs()) { return; }

        String upperCaseItemName = itemName.getText().toString().toUpperCase();
        double price = Double.parseDouble(itemPrice.getText().toString());
        int quantity = Integer.parseInt(itemQuantity.getText().toString());
        Computations computations = Computations.getInstance();

        if(computations.isAlreadyAdded(upperCaseItemName)){
            computations.addItem(upperCaseItemName, price, quantity);
            Toast.makeText(getApplicationContext(), String.format("Item: %s has been updated!", upperCaseItemName), Toast.LENGTH_LONG).show();
        }else{
            computations.addItem(upperCaseItemName, price, quantity);
            Toast.makeText(getApplicationContext(), "Adding Successful!", Toast.LENGTH_LONG).show();
        }

        itemName.setText("");
        itemPrice.setText("");
        itemQuantity.setText("");
    }

    public void clickGetReceipt(View view) {
        Computations computations = Computations.getInstance();
        if(computations.isItemListEmpty()){
            Toast.makeText(getApplicationContext(), "Please add items first!", Toast.LENGTH_SHORT).show();
            return;
        }

        computations.createReceipt();
        Intent receiptIntent = new Intent(MainActivity.this, ReceiptsActivity.class);
        startActivity(receiptIntent);
        finish();
    }

    public void clickClear(View view) {
        Computations computations = Computations.getInstance();
        if(computations.isItemListEmpty()){
            Toast.makeText(getApplicationContext(), "Item list is already empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        computations.clearItems();
        Toast.makeText(getApplicationContext(), "Items Cleared", Toast.LENGTH_SHORT).show();
    }


}

