package com.fml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class FmlApplication {

	public static final String UserProfile = "FmlUserProfiles";
	public static final String Lease = "LeaseInfo";
	public static final String Wishlist = "Wishlist";
	public static final String UserIdCookie = "fmlUname";
	
	public static void main(String[] args) throws IOException {
		ClassLoader cl = FmlApplication.class.getClassLoader();
		File file = new File(Objects.requireNonNull(cl.getResource("fsfbkey.json")).getFile());
		FileInputStream serviceAccount =
				  new FileInputStream(file.getAbsolutePath());
		FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .build();
		
		if(FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
		
		SpringApplication.run(FmlApplication.class, args);
	}

}
