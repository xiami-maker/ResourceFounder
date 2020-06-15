public class ResourceHolder extends Resourcer {
	private RMIServer rmiServer;
	private INetNode address;
	
	public static void scanRMIMapping(String mappingFile) {
		RMIFactory.scanRMIMapping(mappingFile);
	}
	
	public ResourceHolder(String ip, int port) {
		super();
		this.address = new DefaultNetNode(ip, port);
		rmiServer = new RMIServer();
		rmiServer.setPort(port);
		rmiServer.startup();
	}
	
	public void registry(ResourceInfo res) {
		irc.registry(res, (DefaultNetNode) address);
	}
	
	public void logout(ResourceInfo res) {
		irc.logout(res, (DefaultNetNode) address);
	}
	
	public void shutdown() {
		rmiServer.shutdown();
	}
	
}
