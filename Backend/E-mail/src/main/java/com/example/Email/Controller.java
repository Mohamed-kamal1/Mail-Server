package com.example.Email;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/back")
public class Controller {
	UserManager userManager;

	User user;
	public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

	Controller()
	{
		userManager = new UserManager();
	}
    @GetMapping("/signUp")
    public String signUp(@RequestParam String name,@RequestParam String email,@RequestParam String password)
	{
		ArrayList<Mail> mail = new ArrayList<Mail>(10);
		 user = new User(name,email,password,mail );
		return Boolean.toString(userManager.signUp(user));
	}

	@GetMapping("/login")
    public String login(@RequestParam String email,@RequestParam String password)
	{
		return Boolean.toString(userManager.login(email, password));
	}

	@GetMapping("/email")
	public String sendEmail(@RequestParam String Subject,@RequestParam String[] Recipient,@RequestParam String[] attachments,@RequestParam String date,@RequestParam String Content ,@RequestParam int degreeOfImportance){
		Mail mail= new Mail(Subject,Recipient,date,Content, attachments);
		mail.setSender(userManager.currentUser.getEmail());
		    boolean flage= userManager.processMail(mail);
		    mail.setSent(true);
			mail.setImportance(degreeOfImportance);
			System.out.println(mail.isSent());
		return Boolean.toString(flage);
	}
	@GetMapping("/openfolder")
	public ArrayList<Mail> openFolder(@RequestParam String folder, @RequestParam String sortby){
		return userManager.currentUser.getFolder(folder,sortby); //{email,recipient, subject ,content, starred}

	}
	@GetMapping("/addRemove")
	public String add_remove(@RequestParam String folder, @RequestParam String sID,@RequestParam String sIndex,@RequestParam String rID,@RequestParam String rIndex){
		EmailID ids = new EmailID(sID,sIndex,rID,rIndex) ;
		if(folder.equals("trash"))
		userManager.trashEmail(ids);
		else userManager.starEmail(ids);
		return "done";
	}
	@GetMapping("/deleteforever")
	public String deleteForever(@RequestParam String sID,@RequestParam String sIndex,@RequestParam String rID,@RequestParam String rIndex){
		EmailID ids = new EmailID(sID,sIndex,rID,rIndex) ;
		userManager.deleteEmail(ids);
		return "done";
	}
	@GetMapping("/todraft")
	public String addToDraft(@RequestParam String Subject,@RequestParam String[] Recipient, @RequestParam String date,@RequestParam String Content ){
		String [] s={};
		Mail mail= new Mail(Subject,Recipient,date,Content, s);
		mail.setSender(userManager.currentUser.getEmail());
		 Boolean.toString(userManager.processMail(mail));
		 mail.toDraft();
		 return "done";
	}

	@GetMapping("/addcontact")
	public String addContact(@RequestParam String name,@RequestParam String[] emails)
	{
		System.out.println(emails[0]);
		userManager.currentUser.addContact(emails, name);
		userManager.userToFile(userManager.currentUser, userManager.currentUserFile);
		return "done";
	}

	@GetMapping("/deletecontact")
	public String deleteContact(@RequestParam String[] emails)
	{
		userManager.currentUser.removeContact(emails);
		userManager.userToFile(userManager.currentUser, userManager.currentUserFile);
		return "done";
	}

	@GetMapping("/contacts")
	public HashMap<String, String[]> getContacts()
	{
		Gson gson = new Gson();
		ArrayList<String[]> emails = userManager.currentUser.getContactEmails();
		ArrayList<String> names = userManager.currentUser.getContactNames();
		HashMap<String,String[]> contacts=new HashMap<>();
		for(int i=0;i<names.size();i++){
			contacts.put(names.get(i),emails.get(i));
		}
		//System.out.println(names.get(0)+"123");
		//return gson.toJson(emails) + gson.toJson(names);
		return contacts;
	}

	@GetMapping("/addlable")
	public String addNewLable()
	{
		userManager.currentUser.addCustomFolder();
		userManager.userToFile(userManager.currentUser, userManager.currentUserFile);
		return "done";
	}

	@GetMapping("/removelable")
	public String removeLable(@RequestParam int i)
	{
		userManager.currentUser.deleteCustomFolder(i);
		userManager.userToFile(userManager.currentUser, userManager.currentUserFile);
		return "done";
	}

	@GetMapping("/addtolable")
	public String addToLable(@RequestParam int i, @RequestParam String sID,@RequestParam String sIndex,@RequestParam String rID,@RequestParam String rIndex){
		EmailID ids = new EmailID(sID,sIndex,rID,rIndex);
		userManager.currentUser.toCustomFolder(ids,i);
		userManager.userToFile(userManager.currentUser, userManager.currentUserFile);
		return "done";
	}

	@GetMapping("/removefromlable")
	public String removeFromLable(@RequestParam int i, @RequestParam String sID,@RequestParam String sIndex,@RequestParam String rID,@RequestParam String rIndex){
		EmailID ids = new EmailID(sID,sIndex,rID,rIndex);
		userManager.currentUser.removeFromCustomFolder(ids,i);
		userManager.userToFile(userManager.currentUser, userManager.currentUserFile);
		return "done";
	}



	@PostMapping("/upload")
	public ResponseEntity<List<String>> uploadFiles(@RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
		List<String> filenames = new ArrayList<>();
		for(MultipartFile file : multipartFiles) {
			String filename = StringUtils.cleanPath(file.getOriginalFilename());
			Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
			copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
			filenames.add(filename);
		}
		return ResponseEntity.ok().body(filenames);
	}

	@GetMapping("download/{filename}")
	public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
		Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
		if(!Files.exists(filePath)) {
			throw new FileNotFoundException(filename + " was not found on the server");
		}
		Resource resource = new UrlResource(filePath.toUri());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("File-Name", filename);
		httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
				.headers(httpHeaders).body(resource);
	}

}

