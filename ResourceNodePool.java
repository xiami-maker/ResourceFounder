public class ResourceNodePool {
	private static final Map<Integer, List<INetNode>> rnPool
			= new ConcurrentHashMap<Integer, List<INetNode>>();
	
	public ResourceNodePool() {
	}

	public static void registry(ResourceInfo res, INetNode addr) {
		int key = res.hashCode();
		List<INetNode> addrList = null;
		synchronized (rnPool) {
			addrList = rnPool.get(key);
			if (addrList == null) {
				addrList = new CopyOnWriteArrayList<INetNode>();
				rnPool.put(key, addrList);
			}
		}
		addrList.add(addr);
		NodePool.addNode(addr);
	}
	
	public static void logout(ResourceInfo res, INetNode addr) {
		int key = res.hashCode();
		List<INetNode> addrList = null;
		synchronized (rnPool) {
			addrList = rnPool.get(key);
			if (addrList == null) {
				// TODO 日志：资源不存在异常！
				return;
			}
			addrList.remove(addr);
			if (addrList.isEmpty()) {
				rnPool.remove(key);
			}
		}
	}
	
	public synchronized static void logout(INetNode addr) {
		Iterator<List<INetNode>> nodeListList = rnPool.values().iterator();
		while (nodeListList.hasNext()) {
			List<INetNode> nodeList = nodeListList.next();
			nodeList.remove(addr);
		}
		NodePool.removeNode(addr);
	}
	
	public static List<INetNode> getAddressList(ResourceInfo res) {
		int key = res.hashCode();
		List<INetNode> nodeList = null;
		synchronized (rnPool) {
			nodeList = rnPool.get(key);
		}
		
		return nodeList;
	}
	
}
