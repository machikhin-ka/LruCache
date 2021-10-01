import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeLruCache<K, V> implements Cache<K, V> {
	private final Map<K, V> cache;
	private final ReentrantLock lock = new ReentrantLock();

	public ThreadSafeLruCache(int capacity) {
		this.cache = new LinkedHashMap<>(capacity, 1, true) {
			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
				return size() > capacity;
			}
		};
	}

	@Override
	public V put(K key, V value) {
		lock.lock();
		try {
			return cache.put(key, value);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public V get(K key) {
		lock.lock();
		try {
			return cache.get(key);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public int size() {
		return cache.size();
	}
}
