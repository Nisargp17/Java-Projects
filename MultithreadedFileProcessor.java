package java.Projects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class FileReaderThread implements Runnable {
    private String filePath;
    private BlockingQueue<String> queue;

    public FileReaderThread(String filePath, BlockingQueue<String> queue) {
        this.filePath = filePath;
        this.queue = queue;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                queue.put(line);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                queue.put("EOF");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class WorkerThread implements Runnable {
    private BlockingQueue<String> queue;
    private AtomicInteger totalLines;
    private AtomicInteger errorLines;

    public WorkerThread(BlockingQueue<String> queue, AtomicInteger totalLines, AtomicInteger errorLines) {
        this.queue = queue;
        this.totalLines = totalLines;
        this.errorLines = errorLines;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String line = queue.take();
                if ("EOF".equals(line)) {
                    queue.put("EOF");
                    break;
                }

                totalLines.incrementAndGet();
                if (line.contains("ERROR")) {
                    errorLines.incrementAndGet();
                }

                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class MultithreadedFileProcessor {

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.out.println("Usage: java MultithreadedFileProcessor <file_path>");
            return;
        }

        String filePath = args[0];
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(1000);
        AtomicInteger totalLines = new AtomicInteger(0);
        AtomicInteger errorLines = new AtomicInteger(0);

        Thread reader = new Thread(new FileReaderThread(filePath, queue));
        int numWorkers = 4;
        ExecutorService workers = Executors.newFixedThreadPool(numWorkers);

        reader.start();

        for (int i = 0; i < numWorkers; i++) {
            workers.submit(new WorkerThread(queue, totalLines, errorLines));
        }

        reader.join();

        workers.shutdown();
        workers.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Total lines processed: " + totalLines.get());
        System.out.println("Total ERROR lines: " + errorLines.get());
    }
}
