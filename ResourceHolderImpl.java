public class ResourceHolderImpl implements IResourceHolder {
	
	public ResourceHolderImpl() {
	}

	@Override
	public boolean isActive() throws Exception {
		return IResourceHolder.super.isActive();
	}
	
}
