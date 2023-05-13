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
package co.verisoft.fw.utils;

import org.apiguardian.api.API;
import org.json.simple.JSONObject;

/**
 * Representation of a Json object class. A json object class in considered to be a json object class if it is able
 * to produce a json object from the class data in 2 formats: json object (in this case it's google's JSONObject),
 * and in a json string format.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 */
@API(
        status = API.Status.EXPERIMENTAL,
        since = "0.0.2"
)
public interface JsonObject {

    /**
     * Retrieve class data in a json format representaion
     *
     * @return json representaion of the class data
     */
    JSONObject asJsonObject();


    /**
     * Retrieve class data in a json string format representaion
     *
     * @return json string representation of the class data
     */
    String asString();
}
