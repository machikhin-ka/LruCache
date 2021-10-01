import org.junit.jupiter.api.Test;

import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.*;

class ThreadSafeLruCacheTest {
	@Test
	public void testThreadSafe() {
		int size = 5000;
		Cache<Integer, String> cache = new ThreadSafeLruCache<>(size);
		range(0, size).parallel().forEach(key -> cache.put(key, "value" + key));
		assertEquals(cache.size(), size);
		range(0, size).forEach(i -> assertEquals("value" + i, cache.get(i)));
	}

	@Test
	public void testNotThreadSafe() {
		int size = 5000;
		Cache<Integer, String> cache = new LruCache<>(size);
		range(0, size).parallel().forEach(key -> cache.put(key, "value" + key));
		assertNotEquals(cache.size(), size);
	}

}