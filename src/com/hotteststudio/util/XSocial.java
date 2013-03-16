package com.hotteststudio.util;

import android.content.Intent;

public class XSocial {
	public void sendMail() {
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        String[] recipients = new String[]{""};
        //String[] ccList = { "truongkimminh@gmail.com"};

        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
        //emailIntent.putExtra(android.content.Intent.EXTRA_CC, ccList);

        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hey ! Join this app with me!");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hey, get the app for free and explore the funny of face troll comic.");

        emailIntent.setType("text/plain");

        //startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        //finish();
	}
}
