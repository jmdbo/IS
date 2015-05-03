package org.restlet.example.firstapplication;

import org.restlet.data.MediaType;
import org.restlet.engine.Engine;
import org.restlet.example.firstapplication.shared.Contact;
import org.restlet.example.firstapplication.shared.ContactResource;
import org.restlet.ext.jackson.JacksonConverter;
import org.restlet.resource.ClientResource;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	/** The resource proxy. */
	ContactResource resource;
	/** The current contact. */
	Contact contact = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		Engine.getInstance().getRegisteredConverters()
				.add(new JacksonConverter());
		// Initialize the resource proxy.
		ClientResource cr = new ClientResource(
				"http://restlet-firstapplication.appspot.com/contacts/123");
		// Workaround for GAE servers to prevent chunk encoding
		cr.setRequestEntityBuffering(true);
		cr.accept(MediaType.APPLICATION_JSON);

		resource = cr.wrap(ContactResource.class);
	}

	/**
	 * 
	 * @param view
	 */
	public void retrieveContact(View view) {
		// Get the remote contact
		contact = resource.retrieve();
		if (contact != null) {
			TextView textView = (TextView) findViewById(R.id.editFirstName);
			textView.setText(contact.getFirstName());

			textView = (TextView) findViewById(R.id.editLastName);
			textView.setText(contact.getLastName());

			textView = (TextView) findViewById(R.id.editAge);
			textView.setText(Integer.toString(contact.getAge()));
		}
	}

	/** Called when the user touches the button */
	public void updateContact(View view) {
		if (contact == null) {
			contact = new Contact();
		}
		TextView textView = (TextView) findViewById(R.id.editFirstName);
		contact.setFirstName(textView.getText().toString());

		textView = (TextView) findViewById(R.id.editLastName);
		contact.setLastName(textView.getText().toString());

		textView = (TextView) findViewById(R.id.editAge);
		contact.setAge(Integer.parseInt(textView.getText().toString()));

		resource.store(contact);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
