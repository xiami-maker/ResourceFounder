public class ResourceRegistryCenter implements IResourceSpeaker {
	private static final int DEFAULT_PORT = 54200;
	
	static {
		RMIFactory.scanRMIMapping("/RMIMapping.xml");
	}
	
	private RMIServer rmiServer;
	private volatile boolean startup;

	private List<IResourceListener> listenerList;

	public ResourceRegistryCenter() {
		this(DEFAULT_PORT);
	}
	
	public ResourceRegistryCenter(int rmiServerPort) {
		this.rmiServer = new RMIServer();
		this.rmiServer.setPort(rmiServerPort);
	}

	public void setRmiServerPort(int rmiServerPort) {
		this.rmiServer.setPort(rmiServerPort);
	}

	public void startup() {
		if (this.startup == true) {
			speakOut("RMI服务器已启动！");
			return;
		}
		this.startup = true;
		this.rmiServer.startup();
		NodePool.startScanNode();
		speakOut("RMI服务器启动成功！");
	}
	
	public void shutdown() {
		if (this.startup == false) {
			speakOut("RMI服务器未启动！");
			return;
		}
		this.startup = false;
		this.rmiServer.shutdown();
		NodePool.stopScanNode();
		speakOut("RMI服务器宕机！");
	}
	
	@Override
	public List<IResourceListener> getListenerList() {
		return listenerList;
	}

	@Override
	public void setListenerList(List<IResourceListener> listenerList) {
		this.listenerList = listenerList;
	}
	
}
