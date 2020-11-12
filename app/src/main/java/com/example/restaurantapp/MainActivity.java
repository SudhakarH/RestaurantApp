package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.restaurantapp.R;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void increments(View view) {
        if(quantity == 100) {
            Toast.makeText(this, "You Cant go more that 100 Cups at once.", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1;
        display(quantity);
    }
    public void decrements(View view) {
        if(quantity == 1) {
            Toast.makeText(this, "You Cant go less that 1 Cup.", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity-1;
        display(quantity);
    }
    public void submitOrder(View view) {
        EditText text = (EditText) findViewById(R.id.name_field);
        String value = text.getText().toString();

        CheckBox DosaCheckBox = (CheckBox) findViewById(R.id.dosa_checkbox);
        boolean hasDosa = DosaCheckBox.isChecked();

        CheckBox IdliCheckBox = (CheckBox) findViewById(R.id.idli_checkbox);
        boolean hasIdli = IdliCheckBox.isChecked();

        int price = calculatePrice(hasDosa,hasIdli);
        String orderSumary = createOrderSummary(price,value,hasDosa,hasIdli);

        Intent intent = new Intent(MainActivity.this,Summary.class);

        intent.putExtra("summary",orderSumary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
    private String createOrderSummary(int price,String name, boolean addDosa, boolean addIdli) {
        String priceMessage = "Name: " + name;
        if(addDosa) {
            priceMessage += "\nMasala Dosa";
        }
        if(addIdli) {
            priceMessage += "\nIdli Voda";
        }

        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: Rs. " + price +"\n Your Order will be delivered at 12:00 AM"+ "\nThank You!";
        return priceMessage;

    }
    private int calculatePrice(boolean addDosa, boolean addIdli) {
        int basePrice = 0;
        if(addDosa) {
            basePrice += 40;
        }
        if(addIdli) {
            basePrice += 20;
        }
        return (quantity * basePrice);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}
