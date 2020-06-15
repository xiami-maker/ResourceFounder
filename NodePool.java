public class NodePool {
	// TODO 最好给一个可以配置的扫描时间，方便用户进行调优。
	private static final long DEFAULT_DELAY_TIME = 3000;
	
	private static final Map<Integer, INetNode> nodePool
			= new ConcurrentHashMap<Integer, INetNode>();
	private static ScanTimer scanTimer = new ScanTimer(DEFAULT_DELAY_TIME);
	
	public NodePool() {
	}
	
	public static void startScanNode() {
		scanTimer.start();
	}
	
	public static void stopScanNode() {
		scanTimer.stop();
	}
	
	public static void addNode(INetNode node) {
		int key = node.hashCode();
		if (nodePool.containsKey(key)) {
			return;
		}
		
		nodePool.put(key, new ResourceHolderNode(node));
	}
	
	public static void removeNode(INetNode node) {
		int key = node.hashCode();
		if (nodePool.containsKey(key)) {
			nodePool.remove(key);
		}
	}
	
	static class ScanTimer extends DidaDida {
		public ScanTimer() {
			super();
		}
		
		public ScanTimer(long delay) {
			super(delay);
		}
		
		@Override
		public void doing() {
			// 定时扫描资源持有者是否Active
			if (nodePool.isEmpty()) {
				return;
			}
			Iterator<INetNode> nodeList = nodePool.values().iterator();
			while (nodeList.hasNext()) {
				INetNode node = nodeList.next();
				try {
					((ResourceHolderNode) node).isActive();
				} catch (Throwable th) {
					// 发现了非活跃资源持有者，应该删除它！
					ResourceNodePool.logout(node);
				}
			}
		}
		
	}
	
}
