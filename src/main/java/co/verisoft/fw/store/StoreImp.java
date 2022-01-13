package co.verisoft.fw.store;

/*
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


import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import co.verisoft.selenium.framework.inf.ExtendedLog;

/**
 * Generic store implementation
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since Jan 2022
 */
public class StoreImp implements Store {
    private static final Logger logger = new ExtendedLog(StoreImp.class);
    private Map<Object, Object> store;

    public StoreImp() {
        store = new HashMap<>();
    }

    public <T> T getValueFromStore(Object key) {
        return (T) store.get(key);
    }

    /**
     * Puts a value in the store. If the key was already there, it will replace the old value with the new one
     *
     * @param key   key of the objce
     * @param value object to be stored
     */
    public void putValueInStore(Object key, Object value) {
        if (store.get(key) != null)
            store.remove(key);
        store.put(key, value);
    }

    public void removeValueFromStore(Object key) {
        store.remove(key);
    }
}
