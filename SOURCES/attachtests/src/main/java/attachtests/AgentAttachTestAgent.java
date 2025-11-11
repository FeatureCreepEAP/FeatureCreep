package attachtests;

import java.lang.instrument.Instrumentation;

public class AgentAttachTestAgent {
    // The agentmain entry point
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("Agent is attached!");
        System.out.println("Agent arguments: " + agentArgs);
        
        // You can add your agent logic here
        // For example, you can use the Instrumentation instance to redefine classes, etc.
    }
}
