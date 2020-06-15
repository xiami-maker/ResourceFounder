public interface IResourceCenter {
	boolean registry(ResourceInfo res, DefaultNetNode addr);
	boolean logout(ResourceInfo res, DefaultNetNode addr);
	List<DefaultNetNode> getAddressList(ResourceInfo res);
}
