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

import lombok.ToString;

/**
 * Attribute for store type
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @see Store
 * @see StoreImp
 * @see StoreManager
 * @since 0.0.2 (Jan 2022)
 */
@ToString
public enum StoreType {
    LOCAL_THREAD,
    GLOBAL
}
