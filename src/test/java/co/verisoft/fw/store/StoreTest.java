package co.verisoft.fw.store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoreTest {

    @Test
    public void shouldSaveAndRetrieveValueFromGlobalStore() {
        String name = "Nir";
        StoreManager.getStore(StoreType.GLOBAL).putValueInStore("NIR", name);
        String receivedName = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore("NIR");
        Assertions.assertEquals(name, receivedName, "Should have retrieved from store");
    }

    @Test
    public void shouldSaveAndRetrieveValueFromLocalStore() {
        String name = "Irit";
        StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("IRIT", name);
        String receivedName = StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("IRIT");
        Assertions.assertEquals(name, receivedName, "Should have retrieved from store");
    }

    @Test
    public void valueFromLocalNotFoundInGlobal() {
        String name = "Amit";
        StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("AMIT", name);
        String receivedName = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore("AMIT");
        Assertions.assertNull(receivedName, "Should not contain any value");
    }

    @Test
    public void valueFromGlobalNotFoundInLocal() {
        String name = "Lior";
        StoreManager.getStore(StoreType.GLOBAL).putValueInStore("LIOR", name);
        String receivedName = StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("LIOR");
        Assertions.assertNull(receivedName, "Should not contain any value");
    }

    @Test
    public void valueDoesNotAppearAfterRemove() {
        String name = "Ofir";

        // Step 1 - create a value and put in store. Make sure it is there.
        StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("OFIR", name);
        String receivedName = StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("OFIR");
        Assertions.assertEquals(name, receivedName, "Should not contain any value");

        // Step 2 - remove from store and check again. Value should be gone
        StoreManager.getStore(StoreType.LOCAL_THREAD).removeValueFromStore("OFIR");
        receivedName = StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("OFIR");
        Assertions.assertNull(receivedName, "Should not contain any value");
    }

    @Test
    public void shouldUseOtherValueThanString() {
        String name = "Nir";
        StoreManager.getStore(StoreType.GLOBAL).putValueInStore(12, name);
        String receivedName = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore(12);
        Assertions.assertEquals(name, receivedName, "Should have retrieved from store");
    }

    @Test
    public void shouldUseOBject() {
        Object key = new Object();
        String name = "Nir";
        StoreManager.getStore(StoreType.GLOBAL).putValueInStore(key, name);
        String receivedName = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore(key);
        Assertions.assertEquals(name, receivedName, "Should have retrieved from store");
    }

    @Test
    public void shouldNotUse2OBject() {
        Object key1 = new Object();
        Object key2 = new Object();
        String name = "Nir";
        StoreManager.getStore(StoreType.GLOBAL).putValueInStore(key1, name);
        String receivedName = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore(key2);
        Assertions.assertNull(receivedName, "Should have retrieved from store");
    }
}
