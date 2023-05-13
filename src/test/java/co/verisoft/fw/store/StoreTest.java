/*
 * (C) Copyright 2023 VeriSoft (http://www.verisoft.co)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package co.verisoft.fw.store;

import co.verisoft.fw.CustomReportPortalExtension;
import co.verisoft.fw.report.observer.Report;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

/**
 * Unit tests for co.verisoft.fw.store
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 */
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({CustomReportPortalExtension.class})
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


    @Test
    public void shouldRemoveStore() {
        String key = "key";
        String val = "val";

        StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore(key, val);
        StoreManager.removeStore(StoreType.LOCAL_THREAD);

        // Value expected not to be present after removing the store.
        String receivedName = StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore(key);
        Assertions.assertNull(receivedName, "Store should have been empty");
    }


    @Test
    public void shouldReplaceValueInStore() {

        StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("key", "val1");
        StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("key", "val2");

        String receivedName = StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("key");
        Assertions.assertEquals(receivedName, "val2", "Store should replace the original value");
    }
}
