/*
 * Copyright (c)  2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.siddhi.core.stream.output.sink;

import io.siddhi.annotation.Example;
import io.siddhi.annotation.Extension;
import io.siddhi.core.config.SiddhiAppContext;
import io.siddhi.core.event.Event;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.transport.OptionHolder;
import io.siddhi.core.util.transport.TemplateBuilder;
import io.siddhi.query.api.definition.StreamDefinition;

import java.util.Map;

/**
 * Implementation of {@link SinkMapper} representing pass-through scenario where no mapping is done and
 * {@link Event}s are send directly to transports.
 */
@Extension(
        name = "passThrough",
        namespace = "sinkMapper",
        description = "Pass-through mapper passed events (Event[]) through without any mapping or modifications.",
        examples = @Example(
                syntax = "@sink(type='inMemory', @map(type='passThrough'))\n" +
                        "define stream BarStream (symbol string, price float, volume long);",
                description = "In the following example BarStream uses passThrough outputmapper which emit " +
                        "Siddhi event directly without any transformation into sink."
        )
)
public class PassThroughSinkMapper extends SinkMapper {

    @Override
    public String[] getSupportedDynamicOptions() {
        return new String[0];
    }

    @Override
    public void init(StreamDefinition streamDefinition, OptionHolder optionHolder, Map<String, TemplateBuilder>
            payloadTemplateBuilderMap, ConfigReader mapperConfigReader, SiddhiAppContext siddhiAppContext) {
        // do nothing
    }

    @Override
    public Class[] getOutputEventClasses() {
        return new Class[]{Event[].class, Event.class};
    }

    @Override
    public void mapAndSend(Event[] events, OptionHolder optionHolder,
                           Map<String, TemplateBuilder> payloadTemplateBuilderMap, SinkListener sinkListener) {
        sinkListener.publish(events);
    }

    @Override
    public void mapAndSend(Event event, OptionHolder optionHolder,
                           Map<String, TemplateBuilder> payloadTemplateBuilderMap, SinkListener sinkListener) {
        sinkListener.publish(event);
    }
}
