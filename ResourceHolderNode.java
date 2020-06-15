public class ResourceHolderNode extends DefaultNetNode {
	private IResourceHolder resourceHolder;
	
	public ResourceHolderNode(INetNode node) {
		super(node.getIp(), node.getPort());
		RMIClient rmiClient = new RMIClient();
		rmiClient.setRmiServerIp(getIp());
		rmiClient.setRmiServerPort(getPort());
		rmiClient.setConnectDelay(10);
		this.resourceHolder = rmiClient.getProxy(IResourceHolder.class);
	}
	
	public boolean isActive() throws Exception {
		return resourceHolder.isActive();
	}
	
}
