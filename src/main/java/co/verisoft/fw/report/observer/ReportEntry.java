package co.verisoft.fw.report.observer;

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

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

/**
 * Data class for the report information. Each report entry, which is passed to the report publisher
 * should carry this information
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.5 (Jan 2023)
 */
@Data
@Builder
public class ReportEntry {
    ReportLevel reportLevel;
    ReportSource reportSource;
    @Nullable
    Object additionalObject;
    String msg;
}
