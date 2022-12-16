package com.geektime.kafka.springbootakafka.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TestInPutProcessor {
    String TEST_INPUT ="testInput";

    @Input(TEST_INPUT)
    SubscribableChannel testInput();
}
