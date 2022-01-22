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


import co.verisoft.fw.utils.ExtendedLog;
import lombok.ToString;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;


/**
 * Generic store implementation
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @see Store
 * @see StoreManager
 * @see StoreType
 * @since 0.0.2 (Jan 2022)
 */
@ToString
public class StoreImp implements Store {
    private static final Logger logger = new ExtendedLog(StoreImp.class);
    private final Map<Object, Object> store;

    public StoreImp() {
        store = new HashMap<>();
    }


    @Override
    public <T> T getValueFromStore(Object key) {
        //noinspection unchecked
        return (T) store.get(key);
    }


    @Override
    public void putValueInStore(Object key, Object value) {
        if (store.get(key) != null)
            store.remove(key);
        store.put(key, value);
    }


    @Override
    public void removeValueFromStore(Object key) {
        store.remove(key);
    }
}
