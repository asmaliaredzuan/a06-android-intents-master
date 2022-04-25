package edu.ualr.intentsassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import edu.ualr.intentsassignment.model.Contact;

public class ContactInfoActivity extends AppCompatActivity {
    // TODO 03. Create a new layout file to define the GUI elements of the ContactInfoActivity.
    // TODO 04. Define the basic skeleton of the ContactInfoActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    // TODO 07. Create a new method that reads the contact info coming from ContactFormActivity and use it to populate the several TextView elements in the layout.
    // TODO 08. Create a new method that invokes a Phone Dialer app, using as parameter the phone number included in the contact info received from ContactFormActivity in the previous step
    // TODO 09. Create a new method that invokes a Messages app, using as parameter the phone number included in the contact info received from ContactFormActivity in the 7th step
    // TODO 10. Create a new method that invokes a Maps app, using as parameter the address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 11. Create a new method that invokes an Email app, using as parameter the email address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 12. Create a new method that invokes an Web Browser app, using as parameter the web url included in the contact info received from ContactFormActivity in the 7th step

    private TextView contactNameTV;
    private TextView contactPhoneTV;
    private TextView contactEmailTV;
    private TextView contactAddressTV;
    private TextView contactWebsiteTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info);

        contactNameTV = findViewById(R.id.contact_name);
        contactPhoneTV = findViewById(R.id.phone_display);
        contactEmailTV = findViewById(R.id.email_display);
        contactAddressTV = findViewById(R.id.address_display);
        contactWebsiteTV = findViewById(R.id.website_display);

        Chip callChip = findViewById(R.id.phone_chip);
        Chip textChip = findViewById(R.id.text_chip);
        Chip emailChip = findViewById(R.id.email_chip);
        Chip mapChip = findViewById(R.id.map_chip);
        Chip webChip = findViewById(R.id.web_chip);

        callChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialer(contactPhoneTV.getText().toString());
            }
        });

        textChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMessenger(contactPhoneTV.getText().toString());
            }
        });

        emailChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmail(contactEmailTV.getText().toString());
            }
        });

        mapChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMaps(contactAddressTV.getText().toString());
            }
        });

        webChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWeb(contactWebsiteTV.getText().toString());
            }
        });

        Contact c = getIntent().getParcelableExtra(ContactFormActivity.CONTACT);
        populateContactData(c);
    }

    public void openDialer(String phoneNum) {
        String phoneNumUri = String.format("tel:%s", phoneNum);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumUri));
        startActivity(intent);
    }

    public void openMessenger(String phoneNum) {
        String smsUri = String.format("smsto:%s", phoneNum);
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(smsUri));
        startActivity(intent);
    }

    public void openEmail(String email) {
        String emailUri = String.format("mailto:%s", email);
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(emailUri));
        startActivity(intent);
    }

    public void openMaps(String address) {
        String placeUri = String.format("geo:0,0?q=(%s)",address);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(placeUri));
        startActivity(intent);
    }

    public void openWeb(String web) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(web));
        startActivity(intent);
    }

    public void populateContactData(Contact c) {
        contactNameTV.setText(c.getFullName());
        contactPhoneTV.setText(c.getPhoneNumber());
        contactEmailTV.setText(c.getEmailAddress());
        contactAddressTV.setText(c.getAddress());
        contactWebsiteTV.setText(c.getWebsite());
    }

}


