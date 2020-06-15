public class Resourcer {
	protected RMIClient rmiClient;
	protected IResourceCenter irc;

	protected Resourcer() {
		this.rmiClient = new RMIClient();
		this.irc = this.rmiClient.getProxy(IResourceCenter.class);
	}
	
	public void setRmiServerIp(String rmiServerIp) {
		this.rmiClient.setRmiServerIp(rmiServerIp);
	}

	public void setRmiServerPort(int rmiServerPort) {
		this.rmiClient.setRmiServerPort(rmiServerPort);
	}
	
}
