package org.example.assignment.sink;

import org.example.common.SubscriberImpl;
import org.example.common.Util;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReaderServiceImpl implements FileReaderService{
    @Override
    public Flux<String> read(Path path) {
        return Flux.create(sink -> {
            sink.onRequest(request -> {
                if (!sink.isCancelled()){
                    try {
                        Files.readString(path, StandardCharsets.UTF_8)
                                .lines().map(line -> List.of(line.split(" "))).flatMap(List::stream).limit(request).forEach(sink::next);
                    } catch (IOException e) {
                        sink.error(new RuntimeException(e));
                    }
                }
            });

        });
    }

    public static void main(String[] args) {
        var subscriber = new SubscriberImpl();
        FileReaderService readerService = new FileReaderServiceImpl();
        readerService.read(Path.of("src/main/resources/sec04", "input.txt"))
                .subscribe(subscriber);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);

    }
}
