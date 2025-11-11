package attachtests;

import java.io.File;
import java.net.URISyntaxException;
import java.lang.instrument.Instrumentation;

import featurecreep.attach.Attach;

public class AgentAttachTest {

    public static void main(String[] args) {
        try {
            String jarLocation = new File(AgentAttachTest.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            Attach.attach(jarLocation, null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    // The agentmain entry point
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("Agent is attached!");
        System.out.println("Agent arguments: " + agentArgs);
        
        // You can add your agent logic here
        // For example, you can use the Instrumentation instance to redefine classes, etc.
    }
}
