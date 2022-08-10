package com.bprocess.batchscheduler.batch.processor;

import com.bprocess.batchscheduler.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class BatchItemProcessor implements ItemProcessor<Book,Book> {

    @Override
    public Book process(Book book) throws Exception {
        log.info("Process item...");
        return book;
    }
}