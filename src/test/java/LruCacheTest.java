import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LruCacheTest {
	@Test
	public void test() {
		LruCache<String, String> lruCache = new LruCache<>(3);
		lruCache.put("1", "test1");
		lruCache.put("2", "test2");
		lruCache.put("3", "test3");
		lruCache.get("1");
		lruCache.put("4", "test4");
		assertAll(
				() -> assertEquals(3, lruCache.size()),
				() -> assertNotNull(lruCache.get("1")),
				() -> assertNull(lruCache.get("2"))
		);
	}
}