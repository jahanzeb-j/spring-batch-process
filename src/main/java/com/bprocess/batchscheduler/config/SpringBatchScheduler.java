package com.bprocess.batchscheduler.config;

import com.bprocess.batchscheduler.batch.listener.BatchJobListener;
import com.bprocess.batchscheduler.batch.listener.BatchStepListener;
import com.bprocess.batchscheduler.batch.processor.BatchItemProcessor;
import com.bprocess.batchscheduler.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.ScheduledMethodRunnable;

import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class SpringBatchScheduler {

    private final Logger logger = LoggerFactory.getLogger(SpringBatchScheduler.class);

    private AtomicBoolean enabled = new AtomicBoolean(true);

    private AtomicInteger batchRunCounter = new AtomicInteger(0);

    private final Map<Object, ScheduledFuture<?>> scheduledTasks = new IdentityHashMap<>();

    @Value("${file.path.readSingle}")
    private String filePath;

    @Value("${file.path.read}")
    private Resource[] fileResources;

    @Value("${file.path.success}")
    private String folderSuccessPath;

    @Value("${file.path.error}")
    private String folderErrorPath;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobLauncher jobLauncher;

    @Scheduled(fixedRate = 15000)
    public void launchJob() throws Exception {
        Date date = new Date();
        logger.debug("scheduler starts at " + date);
            if (enabled.get()) {
                JobExecution jobExecution = jobLauncher.run(job(), new JobParametersBuilder().addDate("launchDate", date)
                        .toJobParameters());
                batchRunCounter.incrementAndGet();
                logger.debug("Batch job ends with status as " + jobExecution.getStatus());
            }
            logger.debug("scheduler ends ");
    }

    public void stop() {
        enabled.set(false);
    }

    public void start() {
        enabled.set(true);
    }

    @Bean
    public TaskScheduler poolScheduler() {
        return new CustomTaskScheduler();
    }

    private class CustomTaskScheduler extends ThreadPoolTaskScheduler {

        private static final long serialVersionUID = -7142624085505040603L;

        @Override
        public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) {
            ScheduledFuture<?> future = super.scheduleAtFixedRate(task, period);

            ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) task;
            scheduledTasks.put(runnable.getTarget(), future);

            return future;
        }

    }

    public void cancelFutureSchedulerTasks() {
        scheduledTasks.forEach((k, v) -> {
            if (k instanceof SpringBatchScheduler) {
                v.cancel(false);
            }
        });
    }

    @Bean
    public Job job() {
        return jobBuilderFactory
                .get("schedulerJob")
                .listener(new BatchJobListener(fileResources,filePath,folderSuccessPath,folderErrorPath))
                .start(subStep())
                .build();
    }

//    @Bean
//    protected Step mainStep() {
//        return stepBuilderFactory.get("readFileMain")
//                .partitioner("subStep", new Partitioner() {
//                    @Override
//                    public Map<String, ExecutionContext> partition(int i) {
//                        HashMap<String, ExecutionContext> partitionHashMap = new HashMap<>();
//                        ExecutionContext executionContext = new ExecutionContext();
//
//                        executionContext.putString("threadName", "Thread"+i);
//
//                        return (Map<String, ExecutionContext>) partitionHashMap.put("hi",executionContext);
//                    }
//                })
//                .step(subStep())
//                .gridSize(1)
//                .taskExecutor(new SimpleAsyncTaskExecutor())
//                .build();
//    }

    @Bean
    protected Step subStep() {
        return stepBuilderFactory.get("readFileSub")
                .<Book, Book> chunk(2)
                .reader(multiResourceItemReader())
                .processor(new BatchItemProcessor())
                .writer(writer())
                .listener(new BatchStepListener())
//                .taskExecutor(new SimpleAsyncTaskExecutor())
//                .throttleLimit(1)
                .build();
    }

    @Bean
    public MultiResourceItemReader<Book> multiResourceItemReader() {
        MultiResourceItemReader<Book> resourceItemReader = new MultiResourceItemReader<Book>();
        ClassLoader cl = this.getClass().getClassLoader();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
        resourceItemReader.setResources(fileResources);
        resourceItemReader.setDelegate(reader());
        return resourceItemReader;
    }

    @Bean
    public FlatFileItemReader<Book> reader() {
        FlatFileItemReader<Book> reader = new FlatFileItemReader<Book>();
        reader.setLineMapper(new DefaultLineMapper() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"Id", "Name"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Book>() {{
                setTargetType(Book.class);
            }});
        }});
        reader.setLinesToSkip(1);
        return reader;
    }
//  Single file read
//    @Bean
//    public FlatFileItemReader<Book> reader() {
//        return new FlatFileItemReaderBuilder<Book>().name("ItemReader")
//                .resource(new FileSystemResource(filePath))
//                .delimited()
//                .names(new String[] { "id", "name" })
//                .linesToSkip(1)
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<Book>() {
//                    {
//                        setTargetType(Book.class);
//                    }
//                })
//                .build();
//    }

    @Bean
    public ItemProcessor processor() {
        return new BatchItemProcessor();
    }
    
//    @Bean
//    public StepExecutionListener listener(){
//        return new BatchStepListener();
//    }


    @Bean
    public ItemWriter<Book> writer() {
        return new ItemWriter<Book>() {

            @Override
            public void write(List<? extends Book> items) throws Exception {
                logger.info("write items..." + items.size());
               // logger.info("***NNN");
                for (Book item : items) {
//                    int s = 1/0;
                    logger.info(item.toString());
                }

            }
        };
    }

    public AtomicInteger getBatchRunCounter() {
        return batchRunCounter;
    }

}
