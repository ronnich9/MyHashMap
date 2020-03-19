import com.example.ua.MyHashMap;
import org.junit.Assert;
import org.junit.Test;

public class TestMyHashMap {

    @Test
    public void testPut() {
        MyHashMap<Integer, Long> hashMap = new MyHashMap<>();

        Assert.assertTrue(hashMap.put(5, 6L));
        Assert.assertTrue(hashMap.put(12, 62312L));
        Assert.assertTrue(hashMap.put(5, 62L));
        Assert.assertTrue(hashMap.put(18293, 6L));
    }

    @Test
    public void testGet() {
        MyHashMap<Integer, Long> hashMap = new MyHashMap<>();

        hashMap.put(5, 6L);
        hashMap.put(55, 26L);
        hashMap.put(1232125, 63232L);
        hashMap.put(0, 6L);

        Assert.assertEquals(6, hashMap.get(5).longValue());
        Assert.assertEquals(26, hashMap.get(55).longValue());
        Assert.assertEquals(63232, hashMap.get(1232125).longValue());
        Assert.assertEquals(6, hashMap.get(0).longValue());


    }
}
