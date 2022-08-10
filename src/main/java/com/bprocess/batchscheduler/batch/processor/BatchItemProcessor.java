package com.bprocess.batchscheduler.batch.processor;

import com.bprocess.batchscheduler.model.Book;
import org.springframework.batch.item.ItemProcessor;

public class BatchItemProcessor implements ItemProcessor<Book,Book> {

    @Override
    public Book process(Book book) throws Exception {
        return book;
    }
}