package com.geektime.kafka.springbootkafka.processor;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TestOutPutProcessor {
    String TEST_OUTPUT ="testOutput";
    @Output(TEST_OUTPUT)
    MessageChannel testOutput();
}
