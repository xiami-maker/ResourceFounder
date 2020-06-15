public interface IResourceSpeaker {
	
	default void addListener(IResourceListener listener) {
		List<IResourceListener> listenerList = getListenerList();
		if (listenerList == null) {
			synchronized (IResourceSpeaker.class) {
				listenerList = getListenerList();
				if (listenerList == null) {
					listenerList = new ArrayList<>();
					setListenerList(listenerList);
				}
			}
		}
		if (listenerList.contains(listener)) {
			return;
		}
		listenerList.add(listener);
	}
	
	default void removeListener(IResourceListener listener) {
		List<IResourceListener> listenerList = getListenerList();
		if (listenerList == null || !listenerList.contains(listener)) {
			return;
		}
		listenerList.remove(listener);
	}
	
	default void speakOut(String message) {
		List<IResourceListener> listenerList = getListenerList();
		if (listenerList == null || listenerList.isEmpty()) {
			return;
		}
		for (IResourceListener listener : listenerList) {
			listener.dealMessage(message);
		}
	}
	
	List<IResourceListener> getListenerList();
	void setListenerList(List<IResourceListener> listenerList);
}
