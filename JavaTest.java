
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.BasicConfigurator;

public class JavaTest {

	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        try {
        	InteropTest i = new InteropTest();
        	InteropTest.setup();
        	i.clientGetContextGroupInfo();
                			
			in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
