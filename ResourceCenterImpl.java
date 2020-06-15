public class ResourceCenterImpl implements IResourceCenter {
	
	public ResourceCenterImpl() {
	}
	
	@Override
	public boolean registry(ResourceInfo res, DefaultNetNode addr) {
		ResourceNodePool.registry(res, addr);
		
		return true;
	}

	@Override
	public boolean logout(ResourceInfo res, DefaultNetNode addr) {
		ResourceNodePool.logout(res, addr);
		
		return true;
	}

	@Override
	public List<DefaultNetNode> getAddressList(ResourceInfo res) {
		List<DefaultNetNode> result = new ArrayList<DefaultNetNode>();
		
		List<INetNode> nodeList = ResourceNodePool.getAddressList(res);
		if (nodeList == null || nodeList.isEmpty()) {
			return result;
		}
		
		for (INetNode node : nodeList) {
			result.add((DefaultNetNode) node);
		}
		
		return result;
	}

}
