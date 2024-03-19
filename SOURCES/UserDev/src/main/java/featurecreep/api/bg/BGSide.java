package featurecreep.api.bg;

import featurecreep.loader.ExecutionSide;

public class BGSide {

	public static ExecutionSide CLIENT_ONLY = new ExecutionSide(); // For clients connecting to non-FC Servers
	public static ExecutionSide SERVER_ONLY = new ExecutionSide(); // For Servers with non FC clients

	public static ExecutionSide getExecutionSide() {
		// We will soon get a config for Plugin (Server side specific) and client only
		// stuff (for connecting to a nonFC server)
		return BGSuperLoader.getExecutionSide();
	}

	// Currently only works for client/client_only(to be implemented) and not custom
	// ones
	public static boolean isClient() {
		if (getExecutionSide().equals(CLIENT_ONLY) || getExecutionSide().equals(ExecutionSide.CLIENT)) {
			return true;
		}
		return false;
	}

	// Currently only works for client/client_only(to be implemented) and not custom
	// ones
	public static boolean isServer() {
		if (getExecutionSide().equals(SERVER_ONLY) || getExecutionSide().equals(ExecutionSide.SERVER)) {
			return true;
		}
		return false;
	}

}
