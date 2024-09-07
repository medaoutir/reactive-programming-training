package org.example.assignment.handle;

import org.example.common.Util;
import reactor.core.publisher.Flux;

public class HandleOperator {

    public static void main(String[] args) {
        handleDemo();
    }


    private static void handleDemo(){
        Flux.<String>generate(sink -> sink.next(Util.faker().country().name()))
                .handle((item, sink) -> {
                    sink.next(item);
                    if ("canada".equalsIgnoreCase(item)){
                        sink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }




}
