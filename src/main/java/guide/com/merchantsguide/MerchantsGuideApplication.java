package guide.com.merchantsguide;

import java.util.ArrayList;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import guide.com.merchantsguide.service.Conversion;
import guide.com.merchantsguide.service.DisplayOutput;

@SpringBootApplication
public class MerchantsGuideApplication {

	@Autowired
	private Conversion conversion;
	
	public static void main(String[] args) {
		SpringApplication.run(MerchantsGuideApplication.class, args);
	}
	
	@PostConstruct
	public void conversion() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Merchants Guide to The Galaxy");
		System.out.println("Enter the input. For ex, glob is I and glob glob Silver is 34 Credits. Enter empty line to end.");
        ArrayList<String> list = new ArrayList<>();
        String line = null;
        while(!(line = scanner.nextLine()).isEmpty()) {
            list.add(line);
        }
		try {
			conversion.processInput(list);
			DisplayOutput.processOutput();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
