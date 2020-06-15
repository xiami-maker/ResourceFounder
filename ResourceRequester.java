public class ResourceRequester extends Resourcer {

	public ResourceRequester() {
		super();
	}
	
	public List<DefaultNetNode> getAddressList(ResourceInfo res) {
		return irc.getAddressList(res);
	}
	
}
