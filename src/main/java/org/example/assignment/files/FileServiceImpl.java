package org.example.assignment.files;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileServiceImpl implements FileService{

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public Mono<String> read(String fileName) {
        return Mono.fromCallable(() -> readFileAsString(fileName));
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(() -> writeStringInFile(fileName,content));
    }

    @Override
    public Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(() -> deleteFile(fileName));
    }

    private static String readFileAsString(String fileName) throws IOException {
        log.info("reading from {}", fileName);
        Path path = Path.of("src/main/resources/sec02");
        return String.join("", Files.readAllLines(path.resolve(fileName), StandardCharsets.UTF_8));
    }

    private static void writeStringInFile(String fileName, String content) {
        log.info("writing to {}", fileName);
        try {
            Path path = Path.of("src/main/resources/sec02");
            Files.writeString(path.resolve(fileName), content, StandardOpenOption.APPEND);
        }catch (IOException e){
            log.error(e.toString());
        }
    }

    private static void deleteFile(String fileName){
        log.info("deleting {}", fileName);
        try {
            Path path = Path.of("src/main/resources/sec02");
            Files.delete(path.resolve(fileName));
        }catch (IOException e){
            log.error(e.toString());
        }
    }

    public static void main(String[] args) {
        FileService service = new FileServiceImpl();
        String fileName = "greetings.txt";
        service.read(fileName)
                .subscribe(Util.subscriber());
        service.write(fileName,"added value")
                .subscribe(Util.subscriber());
        service.delete(fileName)
                .subscribe(Util.subscriber());

    }
}
