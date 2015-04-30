package org.restlet.example.firstapplication.client;

import org.restlet.data.MediaType;
import org.restlet.example.firstapplication.shared.Contact;
import org.restlet.example.firstapplication.shared.ContactResource;
import org.restlet.resource.ClientResource;

public class TestClient {

	public static void main(String[] args) {
		// Initialize the resource proxy.
		ClientResource cr = new ClientResource(
				"http://restlet-firstapplication.appspot.com/contacts/123");
		// Workaround for GAE servers to prevent chunk encoding
		cr.setRequestEntityBuffering(true);
		cr.accept(MediaType.APPLICATION_JSON);

		ContactResource resource = cr.wrap(ContactResource.class);

		// Get the remote contact
		Contact contact = resource.retrieve();
		if (contact != null) {
			System.out.println("firstname: " + contact.getFirstName());
			System.out.println(" lastname: " + contact.getLastName());
			System.out.println("      age: " + contact.getAge());
		}

		// Update the contact
		contact.setFirstName("Roy");
		resource.store(contact);
	}

}
