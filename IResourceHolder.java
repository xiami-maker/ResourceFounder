public interface IResourceHolder {
	default boolean isActive() throws Exception {
		return true;
	}
}
